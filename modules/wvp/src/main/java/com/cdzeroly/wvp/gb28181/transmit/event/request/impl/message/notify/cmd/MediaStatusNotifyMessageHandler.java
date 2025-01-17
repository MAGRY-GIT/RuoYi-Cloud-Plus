package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.notify.cmd;

import com.cdzeroly.wvp.common.InviteInfo;
import com.cdzeroly.wvp.common.InviteSessionType;
import com.cdzeroly.wvp.domain.CommonGbChannel;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.Platform;
import com.cdzeroly.wvp.domain.bean.SendRtpInfo;
import com.cdzeroly.wvp.domain.bean.SsrcTransaction;
import com.cdzeroly.wvp.gb28181.service.*;
import com.cdzeroly.wvp.gb28181.session.SipInviteSessionManager;
import com.cdzeroly.wvp.gb28181.transmit.cmd.impl.SIPCommanderForPlatform;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.notify.NotifyMessageHandler;
import com.cdzeroly.wvp.media.event.hook.Hook;
import com.cdzeroly.wvp.media.event.hook.HookSubscribe;
import com.cdzeroly.wvp.media.event.hook.HookType;
import com.cdzeroly.wvp.service.ISendRtpServerService;
import gov.nist.javax.sip.message.SIPRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.header.CallIdHeader;
import javax.sip.message.Response;
import java.text.ParseException;

import static com.cdzeroly.wvp.gb28181.utils.XmlUtil.getText;

/**
 * 媒体通知
 */
@Slf4j
@Component
@AllArgsConstructor
public class MediaStatusNotifyMessageHandler extends SIPRequestProcessorParent implements InitializingBean, IMessageHandler {

    private final String cmdType = "MediaStatus";

    private final NotifyMessageHandler notifyMessageHandler;


    private final SIPCommanderForPlatform sipCommanderFroPlatform;

    private final IPlatformChannelService platformChannelService;
    private final IPlatformService platformService;

    private final HookSubscribe subscribe;

    private final IInviteStreamService inviteStreamService;

    private final SipInviteSessionManager sessionManager;


    private final IPlayService playService;

    private final ISendRtpServerService sendRtpServerService;

    @Override
    public void afterPropertiesSet() throws Exception {
        notifyMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element rootElement) {
        // 回复200 OK
        try {
            responseAck((SIPRequest) evt.getRequest(), Response.OK);
        } catch (SipException | InvalidArgumentException | ParseException e) {
            log.error("[命令发送失败] 国标级联 录像流推送完毕，回复200OK: {}", e.getMessage());
        }
        CallIdHeader callIdHeader = (CallIdHeader)evt.getRequest().getHeader(CallIdHeader.NAME);
        String NotifyType =getText(rootElement, "NotifyType");
        if ("121".equals(NotifyType)){
            log.info("[录像流]推送完毕，收到关流通知");

            SsrcTransaction ssrcTransaction = sessionManager.getSsrcTransactionByCallId(callIdHeader.getCallId());
            if (ssrcTransaction != null) {
                log.info("[录像流]推送完毕，关流通知， device: {}, channelId: {}", ssrcTransaction.getDeviceId(), ssrcTransaction.getChannelId());
                InviteInfo inviteInfo = inviteStreamService.getInviteInfo(InviteSessionType.DOWNLOAD, ssrcTransaction.getChannelId(), ssrcTransaction.getStream());
                if (inviteInfo != null) {
                    playService.stop(inviteInfo);
                }
                // 去除监听流注销自动停止下载的监听
                Hook hook = Hook.getInstance(HookType.ON_MEDIA_ARRIVAL, "rtp", ssrcTransaction.getStream(), ssrcTransaction.getMediaServerId());
                subscribe.removeSubscribe(hook);
                // 如果级联播放，需要给上级发送此通知 TODO 多个上级同时观看一个下级 可能存在停错的问题，需要将点播CallId进行上下级绑定
                SendRtpInfo sendRtpInfo =  sendRtpServerService.queryByChannelId(ssrcTransaction.getChannelId(), ssrcTransaction.getPlatformId());
                if (sendRtpInfo != null) {
                    Platform parentPlatform = platformService.queryPlatformByServerGBId(sendRtpInfo.getTargetId());
                    if (parentPlatform == null) {
                        log.warn("[级联消息发送]：发送MediaStatus发现上级平台{}不存在", sendRtpInfo.getTargetId());
                        return;
                    }
                    CommonGbChannel channel = platformChannelService.queryChannelByPlatformIdAndChannelId(parentPlatform.getId(), sendRtpInfo.getChannelId());
                    if (channel == null) {
                        log.warn("[级联消息发送]：发送MediaStatus发现通道{}不存在", sendRtpInfo.getChannelId());
                        return;
                    }
                    try {
                        sipCommanderFroPlatform.sendMediaStatusNotify(parentPlatform, sendRtpInfo, channel);
                    } catch (SipException | InvalidArgumentException | ParseException e) {
                        log.error("[命令发送失败] 国标级联 录像播放完毕: {}", e.getMessage());
                    }
                }
            }else {
                log.info("[录像流]推送完毕，关流通知， 但是未找到对应的下载信息");
            }
        }
    }

    @Override
    public void handForPlatform(RequestEvent evt, Platform parentPlatform, Element element) {

    }
}
