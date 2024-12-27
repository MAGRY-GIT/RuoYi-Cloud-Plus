package com.cdzeroly.wvp.gb28181.transmit.event.request.impl;

import com.cdzeroly.wvp.common.InviteInfo;
import com.cdzeroly.wvp.common.InviteSessionType;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.conf.exception.SsrcTransactionNotFoundException;
import com.cdzeroly.wvp.domain.bean.AudioBroadcastCatch;
import com.cdzeroly.wvp.domain.bean.SendRtpInfo;
import com.cdzeroly.wvp.domain.bean.SsrcTransaction;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.domian.DeviceChannel;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.gb28181.enums.InviteStreamType;
import com.cdzeroly.wvp.gb28181.service.*;
import com.cdzeroly.wvp.gb28181.session.AudioBroadcastManager;
import com.cdzeroly.wvp.gb28181.session.SipInviteSessionManager;
import com.cdzeroly.wvp.gb28181.transmit.SIPProcessorObserver;
import com.cdzeroly.wvp.gb28181.transmit.cmd.ISIPCommander;
import com.cdzeroly.wvp.gb28181.transmit.event.request.ISIPRequestProcessor;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.cdzeroly.wvp.domain.bean.MediaInfo;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.service.ISendRtpServerService;
import com.cdzeroly.wvp.service.redisMsg.IRedisRpcService;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import gov.nist.javax.sip.message.SIPRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.header.CallIdHeader;
import javax.sip.message.Response;
import java.text.ParseException;

/**
 * SIP命令类型： BYE请求
 * @author MAGRY
 */
@Slf4j
@Component
@AllArgsConstructor
public class ByeRequestProcessor extends SIPRequestProcessorParent implements InitializingBean, ISIPRequestProcessor {

    private final ISIPCommander cmder;

	private final ISendRtpServerService sendRtpServerService;

	private final IRedisCatchStorage redisCatchStorage;

	private final IInviteStreamService inviteStreamService;

	private final IPlatformService platformService;

	private final IDeviceService deviceService;

	private final IDeviceChannelService deviceChannelService;

	private final AudioBroadcastManager audioBroadcastManager;

	private final IGbChannelService channelService;

	private final IMediaServerService mediaServerService;

	private final SIPProcessorObserver sipProcessorObserver;

	private final SipInviteSessionManager sessionManager;

	private final IPlayService playService;

	private final UserSetting userSetting;

	private final IRedisRpcService redisRpcService;


	@Override
	public void afterPropertiesSet() throws Exception {
		// 添加消息处理的订阅
        String method = "BYE";
        sipProcessorObserver.addRequestProcessor(method, this);
	}

	/**
	 * 处理BYE请求
	 */
	@Override
	public void process(RequestEvent evt) {
		SIPRequest request = (SIPRequest) evt.getRequest();
		try {
			responseAck(request, Response.OK);
		} catch (SipException | InvalidArgumentException | ParseException e) {
			log.error("[回复BYE信息失败]，{}", e.getMessage());
		}
		CallIdHeader callIdHeader = (CallIdHeader)evt.getRequest().getHeader(CallIdHeader.NAME);
		SendRtpInfo sendRtpItem =  sendRtpServerService.queryByCallId(callIdHeader.getCallId());

		// 收流端发送的停止
		if (sendRtpItem != null){
			CommonGBChannel channel = channelService.getOne(sendRtpItem.getChannelId());
			log.info("[收到bye] 来自{}，停止通道：{}, 类型： {}, callId: {}", sendRtpItem.getTargetId(), channel.getGbDeviceId(), sendRtpItem.getPlayType(), callIdHeader.getCallId());

			String streamId = sendRtpItem.getStream();
			log.info("[收到bye] 停止推流：{}, 媒体节点： {}", streamId, sendRtpItem.getMediaServerId());

			if (sendRtpItem.getPlayType().equals(InviteStreamType.PUSH)) {
				// 不是本平台的就发送redis消息让其他wvp停止发流
				Platform platform = platformService.queryPlatformByServerGBId(sendRtpItem.getTargetId());
				if (platform != null) {
					redisCatchStorage.sendPlatformStopPlayMsg(sendRtpItem, platform, channel);
					if (!userSetting.getServerId().equals(sendRtpItem.getServerId())) {
						redisRpcService.stopSendRtp(sendRtpItem.getCallId());
						sendRtpServerService.deleteByCallId(sendRtpItem.getCallId());
					}else {
						MediaServer mediaServer = mediaServerService.getOne(sendRtpItem.getMediaServerId());
						sendRtpServerService.deleteByCallId(callIdHeader.getCallId());
						if (mediaServer != null) {
							mediaServerService.stopSendRtp(mediaServer, sendRtpItem.getApp(), sendRtpItem.getStream(), sendRtpItem.getSsrc());
							if (userSetting.getUseCustomSsrcForParentInvite()) {
								mediaServerService.releaseSsrc(mediaServer.getId(), sendRtpItem.getSsrc());
							}
						}
					}
				}else {
					log.info("[上级平台停止观看] 未找到平台{}的信息，发送redis消息失败", sendRtpItem.getTargetId());
				}
			}else {
				MediaServer mediaInfo = mediaServerService.getOne(sendRtpItem.getMediaServerId());
				sendRtpServerService.delete(sendRtpItem);
				mediaServerService.stopSendRtp(mediaInfo, sendRtpItem.getApp(), sendRtpItem.getStream(), sendRtpItem.getSsrc());
				if (userSetting.getUseCustomSsrcForParentInvite()) {
					mediaServerService.releaseSsrc(mediaInfo.getId(), sendRtpItem.getSsrc());
				}
			}
			MediaServer mediaServer = mediaServerService.getOne(sendRtpItem.getMediaServerId());
			if (mediaServer != null) {
				AudioBroadcastCatch audioBroadcastCatch = audioBroadcastManager.get(sendRtpItem.getChannelId());
				if (audioBroadcastCatch != null && audioBroadcastCatch.getSipTransactionInfo().getCallId().equals(callIdHeader.getCallId())) {
					// 来自上级平台的停止对讲
					log.info("[停止对讲] 来自上级，平台：{}, 通道：{}", sendRtpItem.getTargetId(), sendRtpItem.getChannelId());
					audioBroadcastManager.del(sendRtpItem.getChannelId());
				}

				MediaInfo mediaInfo = mediaServerService.getMediaInfo(mediaServer, sendRtpItem.getApp(), streamId);

				if (mediaInfo.getReaderCount() <= 0) {
					log.info("[收到bye] {} 无其它观看者，通知设备停止推流", streamId);
					if (sendRtpItem.getPlayType().equals(InviteStreamType.PLAY)) {
						Device device = deviceService.getDeviceByDeviceId(sendRtpItem.getTargetId());
						if (device == null) {
							log.info("[收到bye] {} 通知设备停止推流时未找到设备信息", streamId);
							return;
						}
						DeviceChannel deviceChannel = deviceChannelService.getOneForSourceById(sendRtpItem.getChannelId());
						if (deviceChannel == null) {
							log.info("[收到bye] {} 通知设备停止推流时未找到通道信息", streamId);
							return;
						}
						try {
							log.info("[停止点播] {}/{}", sendRtpItem.getTargetId(), sendRtpItem.getChannelId());
							cmder.streamByeCmd(device, deviceChannel.getDeviceId(), streamId, null);
						} catch (InvalidArgumentException | ParseException | SipException |
								 SsrcTransactionNotFoundException e) {
							log.error("[收到bye] {} 无其它观看者，通知设备停止推流， 发送BYE失败 {}",streamId, e.getMessage());
						}
					}
				}
			}
		}
		// 可能是设备发送的停止
		SsrcTransaction ssrcTransaction = sessionManager.getSsrcTransactionByCallId(callIdHeader.getCallId());
		if (ssrcTransaction == null) {
			return;
		}
		log.info("[收到bye] 来自：{}, 通道: {}, 类型： {}", ssrcTransaction.getDeviceId(), ssrcTransaction.getChannelId(), ssrcTransaction.getType());
		// TODO 结束点播 避免等待

		if (ssrcTransaction.getPlatformId() != null ) {
			Platform platform = platformService.queryPlatformByServerGBId(ssrcTransaction.getPlatformId());
			if (ssrcTransaction.getType().equals(InviteSessionType.BROADCAST)) {
				log.info("[收到bye] 上级停止语音对讲，来自：{}, 通道已停止推流: {}", ssrcTransaction.getPlatformId(), ssrcTransaction.getChannelId());
				CommonGBChannel channel = channelService.getOne(ssrcTransaction.getChannelId());
				if (channel == null) {
					log.info("[收到bye] 未找到通道，上级：{}， 通道：{}", ssrcTransaction.getPlatformId(), ssrcTransaction.getChannelId());
					return;
				}
				String mediaServerId = ssrcTransaction.getMediaServerId();
				platformService.stopBroadcast(platform, channel, ssrcTransaction.getStream(), false,
						mediaServerService.getOne(mediaServerId));
				DeviceChannel deviceChannel = deviceChannelService.getOneForSourceById(channel.getGbId());
				Device device = deviceService.getDevice(channel.getDataDeviceId());
				playService.stopAudioBroadcast(device, deviceChannel);
			}

		}else {
			Device device = deviceService.getDeviceByDeviceId(ssrcTransaction.getDeviceId());
			if (device == null) {
				log.info("[收到bye] 未找到设备：{} ", ssrcTransaction.getDeviceId());
				return;
			}
			DeviceChannel channel = deviceChannelService.getOneForSourceById(ssrcTransaction.getChannelId());
			if (channel == null) {
				log.info("[收到bye] 未找到通道，设备：{}， 通道：{}", ssrcTransaction.getDeviceId(), ssrcTransaction.getChannelId());
				return;
			}
			switch (ssrcTransaction.getType()){
				case PLAY:
				case PLAYBACK:
				case DOWNLOAD:
					InviteInfo inviteInfo = inviteStreamService.getInviteInfoByDeviceAndChannel(InviteSessionType.PLAY, channel.getId());
					if (inviteInfo != null) {
						deviceChannelService.stopPlay(channel.getId());
						inviteStreamService.removeInviteInfo(inviteInfo);
						if (inviteInfo.getStreamInfo() != null) {
							mediaServerService.closeRTPServer(inviteInfo.getStreamInfo().getMediaServer(), inviteInfo.getStreamInfo().getStream());
						}
					}
					break;
				case BROADCAST:
				case TALK:
					// 查找来源的对讲设备，发送停止
					Device sourceDevice = deviceService.getDeviceByChannelId(ssrcTransaction.getChannelId());
					AudioBroadcastCatch audioBroadcastCatch = audioBroadcastManager.get(channel.getId());
					if (sourceDevice != null) {
						playService.stopAudioBroadcast(sourceDevice, channel);
					}
					if (audioBroadcastCatch != null) {
						// 来自上级平台的停止对讲
						log.info("[停止对讲] 来自上级，平台：{}, 通道：{}", ssrcTransaction.getDeviceId(), channel.getDeviceId());
						audioBroadcastManager.del(channel.getId());
					}
					break;
			}
			// 释放ssrc
			MediaServer mediaServerItem = mediaServerService.getOne(ssrcTransaction.getMediaServerId());
			if (mediaServerItem != null) {
				mediaServerService.releaseSsrc(mediaServerItem.getId(), ssrcTransaction.getSsrc());
			}
			sessionManager.removeByCallId(ssrcTransaction.getCallId());
		}
	}
}
