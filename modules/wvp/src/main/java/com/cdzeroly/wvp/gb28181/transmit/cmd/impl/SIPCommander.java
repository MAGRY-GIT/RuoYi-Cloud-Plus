package com.cdzeroly.wvp.gb28181.transmit.cmd.impl;

import cn.hutool.core.util.StrUtil;
import com.cdzeroly.wvp.common.BroadcastForPlatform;
import com.cdzeroly.wvp.common.InviteSessionType;
import com.cdzeroly.wvp.common.NetProtocol;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.conf.SipConfig;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.conf.exception.SsrcTransactionNotFoundException;
import com.cdzeroly.wvp.domain.bean.SendRtpInfo;
import com.cdzeroly.wvp.domain.bean.SipTransactionInfo;
import com.cdzeroly.wvp.domain.bean.SsrcTransaction;
import com.cdzeroly.wvp.gb28181.SipLayer;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.DeviceAlarm;
import com.cdzeroly.wvp.domain.DeviceChannel;
import com.cdzeroly.wvp.gb28181.event.SipSubscribe;
import com.cdzeroly.wvp.gb28181.session.SipInviteSessionManager;
import com.cdzeroly.wvp.gb28181.transmit.SIPSender;
import com.cdzeroly.wvp.gb28181.transmit.cmd.ISIPCommander;
import com.cdzeroly.wvp.gb28181.transmit.cmd.SIPRequestHeaderProvider;
import com.cdzeroly.wvp.gb28181.utils.NumericUtil;
import com.cdzeroly.wvp.gb28181.utils.SipUtils;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.event.hook.Hook;
import com.cdzeroly.wvp.media.event.hook.HookSubscribe;
import com.cdzeroly.wvp.media.event.hook.HookType;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.domain.bean.SSRCInfo;
import com.cdzeroly.wvp.utils.DateUtil;
import gov.nist.javax.sip.message.SIPRequest;
import gov.nist.javax.sip.message.SIPResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.sip.InvalidArgumentException;
import javax.sip.ResponseEvent;
import javax.sip.SipException;
import javax.sip.SipFactory;
import javax.sip.header.CallIdHeader;
import javax.sip.message.Request;
import java.text.ParseException;

/**
 * 设备能力接口，用于定义设备的控制、查询能力
 *
 * @author swwheihei
 */
@Component
@DependsOn("sipLayer")
@AllArgsConstructor
@Slf4j
public class SIPCommander implements ISIPCommander {

    private final SipConfig sipConfig;

    private final SipLayer sipLayer;

    private final SIPSender sipSender;

    private final SIPRequestHeaderProvider headerProvider;

    private final SipInviteSessionManager sessionManager;

    private final UserSetting userSetting;

    private final HookSubscribe subscribe;

    private final IMediaServerService mediaServerService;


    /**
     * 云台方向放控制，使用配置文件中的默认镜头移动速度
     *
     * @param device    控制设备
     * @param channelId 预览通道
     * @param leftRight 镜头左移右移 0:停止 1:左移 2:右移
     * @param upDown    镜头上移下移 0:停止 1:上移 2:下移
     */
    @Override
    public void ptzDirectCmd(Device device, String channelId, int leftRight, int upDown) throws InvalidArgumentException, ParseException, SipException {
        ptzCmd(device, channelId, leftRight, upDown, 0, sipConfig.getPtzSpeed(), 0);
    }

    /**
     * 云台方向放控制
     *
     * @param device    控制设备
     * @param channelId 预览通道
     * @param leftRight 镜头左移右移 0:停止 1:左移 2:右移
     * @param upDown    镜头上移下移 0:停止 1:上移 2:下移
     * @param moveSpeed 镜头移动速度
     */
    @Override
    public void ptzDirectCmd(Device device, String channelId, int leftRight, int upDown, int moveSpeed) throws InvalidArgumentException, ParseException, SipException {
        ptzCmd(device, channelId, leftRight, upDown, 0, moveSpeed, 0);
    }

    /**
     * 云台缩放控制，使用配置文件中的默认镜头缩放速度
     *
     * @param device    控制设备
     * @param channelId 预览通道
     * @param inOut     镜头放大缩小 0:停止 1:缩小 2:放大
     */
    @Override
    public void ptzZoomCmd(Device device, String channelId, int inOut) throws InvalidArgumentException, ParseException, SipException {
        ptzCmd(device, channelId, 0, 0, inOut, 0, sipConfig.getPtzSpeed());
    }

    /**
     * 云台缩放控制
     *
     * @param device    控制设备
     * @param channelId 预览通道
     * @param inOut     镜头放大缩小 0:停止 1:缩小 2:放大
     * @param zoomSpeed 镜头缩放速度
     */
    @Override
    public void ptzZoomCmd(Device device, String channelId, int inOut, int zoomSpeed) throws InvalidArgumentException, ParseException, SipException {
        ptzCmd(device, channelId, 0, 0, inOut, 0, zoomSpeed);
    }

    /**
     * 云台指令码计算
     *
     * @param cmdCode      指令码
     * @param parameter1   数据1
     * @param parameter2   数据2
     * @param combineCode2 组合码2
     */
    public static String frontEndCmdString(int cmdCode, int parameter1, int parameter2, int combineCode2) {
        StringBuilder builder = new StringBuilder("A50F01");
        String strTmp;
        strTmp = String.format("%02X", cmdCode);
        builder.append(strTmp, 0, 2);
        strTmp = String.format("%02X", parameter1);
        builder.append(strTmp, 0, 2);
        strTmp = String.format("%02X", parameter2);
        builder.append(strTmp, 0, 2);
        strTmp = String.format("%02X", combineCode2 << 4);
        builder.append(strTmp, 0, 2);
        // 计算校验码
        int checkCode = (0XA5 + 0X0F + 0X01 + cmdCode + parameter1 + parameter2 + (combineCode2 << 4)) % 0X100;
        strTmp = String.format("%02X", checkCode);
        builder.append(strTmp, 0, 2);
        return builder.toString();
    }

    /**
     * 云台控制，支持方向与缩放控制
     *
     * @param device    控制设备
     * @param channelId 预览通道
     * @param leftRight 镜头左移右移 0:停止 1:左移 2:右移
     * @param upDown    镜头上移下移 0:停止 1:上移 2:下移
     * @param inOut     镜头放大缩小 0:停止 1:缩小 2:放大
     * @param moveSpeed 镜头移动速度
     * @param zoomSpeed 镜头缩放速度
     */
    @Override
    public void ptzCmd(Device device, String channelId, int leftRight, int upDown, int inOut, int moveSpeed, int zoomSpeed) throws InvalidArgumentException, SipException, ParseException {
        String cmdStr = SipUtils.cmdString(leftRight, upDown, inOut, moveSpeed, zoomSpeed);


        StringBuilder ptzXml = new StringBuilder(200);
        String charset = device.getCharset();
        ptzXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        ptzXml.append("<Control>\r\n");
        ptzXml.append("<CmdType>DeviceControl</CmdType>\r\n");
        ptzXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        ptzXml.append("<DeviceID>").append(channelId).append("</DeviceID>\r\n");
        ptzXml.append("<PTZCmd>").append(cmdStr).append("</PTZCmd>\r\n");
        ptzXml.append("<Info>\r\n");
        ptzXml.append("<ControlPriority>5</ControlPriority>\r\n");
        ptzXml.append("</Info>\r\n");
        ptzXml.append("</Control>\r\n");

        Request request = headerProvider.createMessageRequest(device, ptzXml.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));

        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request);
    }

    /**
     * 前端控制，包括PTZ指令、FI指令、预置位指令、巡航指令、扫描指令和辅助开关指令
     *
     * @param device       控制设备
     * @param channelId    预览通道
     * @param cmdCode      指令码
     * @param parameter1   数据1
     * @param parameter2   数据2
     * @param combineCode2 组合码2
     */
    @Override
    public void frontEndCmd(Device device, String channelId, int cmdCode, int parameter1, int parameter2, int combineCode2) throws SipException, InvalidArgumentException, ParseException {

        String cmdStr = frontEndCmdString(cmdCode, parameter1, parameter2, combineCode2);
        StringBuilder ptzXml = new StringBuilder(200);
        String charset = device.getCharset();
        ptzXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        ptzXml.append("<Control>\r\n");
        ptzXml.append("<CmdType>DeviceControl</CmdType>\r\n");
        ptzXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        ptzXml.append("<DeviceID>").append(channelId).append("</DeviceID>\r\n");
        ptzXml.append("<PTZCmd>").append(cmdStr).append("</PTZCmd>\r\n");
        ptzXml.append("<Info>\r\n");
        ptzXml.append("<ControlPriority>5</ControlPriority>\r\n");
        ptzXml.append("</Info>\r\n");
        ptzXml.append("</Control>\r\n");


        SIPRequest request = (SIPRequest) headerProvider.createMessageRequest(device, ptzXml.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request);

    }

    /**
     * 前端控制指令（用于转发上级指令）
     *
     * @param device    控制设备
     * @param channelId 预览通道
     * @param cmdString 前端控制指令串
     */
    @Override
    public void fronEndCmd(Device device, String channelId, String cmdString, SipSubscribe.Event errorEvent, SipSubscribe.Event okEvent) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder ptzXml = new StringBuilder(200);
        String charset = device.getCharset();
        ptzXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        ptzXml.append("<Control>\r\n");
        ptzXml.append("<CmdType>DeviceControl</CmdType>\r\n");
        ptzXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        ptzXml.append("<DeviceID>").append(channelId).append("</DeviceID>\r\n");
        ptzXml.append("<PTZCmd>").append(cmdString).append("</PTZCmd>\r\n");
        ptzXml.append("<Info>\r\n");
        ptzXml.append("<ControlPriority>5</ControlPriority>\r\n");
        ptzXml.append("</Info>\r\n");
        ptzXml.append("</Control>\r\n");


        Request request = headerProvider.createMessageRequest(device, ptzXml.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, okEvent);

    }

    /**
     * 请求预览视频流
     *
     * @param device     视频设备
     * @param channel    预览通道
     * @param errorEvent sip错误订阅
     */
    @Override
    public void playStreamCmd(MediaServer mediaServerItem, SSRCInfo ssrcInfo, Device device, DeviceChannel channel, SipSubscribe.Event okEvent, SipSubscribe.Event errorEvent, Long timeout) throws InvalidArgumentException, SipException, ParseException {
        String stream = ssrcInfo.getString();

        if (device == null) {
            return;
        }
        String sdpIp;
        if (StrUtil.isNotBlank(device.getSdpIp())) {
            sdpIp = device.getSdpIp();
        } else {
            sdpIp = mediaServerItem.getSdpIp();
        }
        StringBuilder content = new StringBuilder(200);
        content.append("v=0\r\n");
        content.append("o=").append(device.getDeviceId()).append(" 0 0 IN IP4 ").append(sdpIp).append("\r\n");
        content.append("s=Play\r\n");
        content.append("c=IN IP4 ").append(sdpIp).append("\r\n");
        content.append("t=0 0\r\n");

        if (userSetting.getSeniorSdp()) {
            switch (device.getStreamMode()) {
                case UDP ->
                    content.append("m=video ").append(ssrcInfo.getPort()).append(" RTP/AVP 96 126 125 99 34 98 97\r\n");
                case TCP_PASSIVE, TCP_ACTIVE ->
                    content.append("m=video ").append(ssrcInfo.getPort()).append(" TCP/RTP/AVP 96 126 125 99 34 98 97\r\n");
            }
            content.append("a=recvonly\r\n");
            content.append("a=rtpmap:96 PS/90000\r\n");
            content.append("a=fmtp:126 profile-level-id=42e01e\r\n");
            content.append("a=rtpmap:126 H264/90000\r\n");
            content.append("a=rtpmap:125 H264S/90000\r\n");
            content.append("a=fmtp:125 profile-level-id=42e01e\r\n");
            content.append("a=rtpmap:99 H265/90000\r\n");
            content.append("a=rtpmap:98 H264/90000\r\n");
            content.append("a=rtpmap:97 MPEG4/90000\r\n");
            // tcp被动模式
            if (BroadcastForPlatform.TCP_PASSIVE.equals(device.getStreamMode())) {
                content.append("a=setup:passive\r\n");
                content.append("a=connection:new\r\n");
                // tcp主动模式
            } else if (BroadcastForPlatform.TCP_ACTIVE.equals(device.getStreamMode())) {
                content.append("a=setup:active\r\n");
                content.append("a=connection:new\r\n");
            }
        } else {
            switch (device.getStreamMode()) {
                case UDP -> content.append("m=video ").append(ssrcInfo.getPort()).append(" RTP/AVP 96 97 98 99\r\n");
                case TCP_PASSIVE, TCP_ACTIVE ->
                    content.append("m=video ").append(ssrcInfo.getPort()).append(" TCP/RTP/AVP 96 97 98 99\r\n");
            }

            content.append("a=recvonly\r\n");
            content.append("a=rtpmap:96 PS/90000\r\n");
            content.append("a=rtpmap:98 H264/90000\r\n");
            content.append("a=rtpmap:97 MPEG4/90000\r\n");
            content.append("a=rtpmap:99 H265/90000\r\n");
            // tcp被动模式
            if (BroadcastForPlatform.TCP_PASSIVE.equals(device.getStreamMode())) {
                content.append("a=setup:passive\r\n");
                content.append("a=connection:new\r\n");
                // tcp主动模式
            } else if (BroadcastForPlatform.TCP_ACTIVE.equals(device.getStreamMode())) {
                content.append("a=setup:active\r\n");
                content.append("a=connection:new\r\n");
            }
        }

        if (!ObjectUtils.isEmpty(channel.getStreamIdentification())) {
            content.append("a=").append(channel.getStreamIdentification()).append("\r\n");
        }
        // ssrc
        content.append("y=").append(ssrcInfo.getSsrc()).append("\r\n");
        // f字段:f= v/编码格式/分辨率/帧率/码率类型/码率大小a/编码格式/码率大小/采样率
        // content.append("f=v/2/5/25/1/4000a/1/8/1" + "\r\n"); // 未发现支持此特性的设备

        Request request = headerProvider.createInviteRequest(device, channel.getDeviceId(), content.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, ssrcInfo.getSsrc(), sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, (e -> {
            sessionManager.removeByStream(ssrcInfo.getString());
            mediaServerService.releaseSsrc(mediaServerItem.getId(), ssrcInfo.getSsrc());
            errorEvent.response(e);
        }), e -> {
            ResponseEvent responseEvent = (ResponseEvent) e.event;
            SIPResponse response = (SIPResponse) responseEvent.getResponse();
            String callId = response.getCallIdHeader().getCallId();
            SsrcTransaction ssrcTransaction = SsrcTransaction.buildForDevice(device.getDeviceId(), channel.getId(), callId, stream, ssrcInfo.getSsrc(), mediaServerItem.getId(), response, InviteSessionType.PLAY);
            sessionManager.put(ssrcTransaction);
            okEvent.response(e);
        }, timeout);
    }

    /**
     * 请求回放视频流
     *
     * @param device    视频设备
     * @param channel   预览通道
     * @param startTime 开始时间,格式要求：yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间,格式要求：yyyy-MM-dd HH:mm:ss
     */
    @Override
    public void playbackStreamCmd(MediaServer mediaServerItem, SSRCInfo ssrcInfo, Device device, DeviceChannel channel, String startTime, String endTime, SipSubscribe.Event okEvent, SipSubscribe.Event errorEvent, Long timeout) throws InvalidArgumentException, SipException, ParseException {


        log.info("{} 分配的ZLM为: {} [{}:{}]", ssrcInfo.getString(), mediaServerItem.getId(), mediaServerItem.getSdpIp(), ssrcInfo.getPort());
        String sdpIp;
        if (!ObjectUtils.isEmpty(device.getSdpIp())) {
            sdpIp = device.getSdpIp();
        } else {
            sdpIp = mediaServerItem.getSdpIp();
        }
        StringBuilder content = new StringBuilder(200);
        content.append("v=0\r\n");
        content.append("o=").append(device.getDeviceId()).append(" 0 0 IN IP4 ").append(sdpIp).append("\r\n");
        content.append("s=Playback\r\n");
        content.append("u=").append(channel.getDeviceId()).append(":0\r\n");
        content.append("c=IN IP4 ").append(sdpIp).append("\r\n");
        content.append("t=").append(DateUtil.yyyyMmDdHhMmSsToTimestamp(startTime)).append(" ").append(DateUtil.yyyyMmDdHhMmSsToTimestamp(endTime)).append("\r\n");

        BroadcastForPlatform streamMode = device.getStreamMode();

        if (userSetting.getSeniorSdp()) {
            switch (streamMode) {
                case UDP ->
                    content.append("m=video ").append(ssrcInfo.getPort()).append(" RTP/AVP 96 126 125 99 34 98 97\r\n");
                case TCP_PASSIVE, TCP_ACTIVE ->
                    content.append("m=video ").append(ssrcInfo.getPort()).append(" TCP/RTP/AVP 96 126 125 99 34 98 97\r\n");
            }

            content.append("a=recvonly\r\n");
            content.append("a=rtpmap:96 PS/90000\r\n");
            content.append("a=fmtp:126 profile-level-id=42e01e\r\n");
            content.append("a=rtpmap:126 H264/90000\r\n");
            content.append("a=rtpmap:125 H264S/90000\r\n");
            content.append("a=fmtp:125 profile-level-id=42e01e\r\n");
            content.append("a=rtpmap:99 H265/90000\r\n");
            content.append("a=rtpmap:98 H264/90000\r\n");
            content.append("a=rtpmap:97 MPEG4/90000\r\n");
            // tcp被动模式
            if (BroadcastForPlatform.TCP_PASSIVE.equals(streamMode)) {
                content.append("a=setup:passive\r\n");
                content.append("a=connection:new\r\n");
                // tcp主动模式
            } else if (BroadcastForPlatform.TCP_ACTIVE.equals(streamMode)) {
                content.append("a=setup:active\r\n");
                content.append("a=connection:new\r\n");
            }
        } else {
            switch (streamMode) {
                case UDP -> content.append("m=video ").append(ssrcInfo.getPort()).append(" RTP/AVP 96 97 98 99\r\n");
                case TCP_PASSIVE, TCP_ACTIVE ->
                    content.append("m=video ").append(ssrcInfo.getPort()).append(" TCP/RTP/AVP 96 97 98 99\r\n");
            }
            content.append("a=recvonly\r\n");
            content.append("a=rtpmap:96 PS/90000\r\n");
            content.append("a=rtpmap:97 MPEG4/90000\r\n");
            content.append("a=rtpmap:98 H264/90000\r\n");
            content.append("a=rtpmap:99 H265/90000\r\n");
            if (BroadcastForPlatform.TCP_PASSIVE.equals(streamMode)) {
                // tcp被动模式
                content.append("a=setup:passive\r\n");
                content.append("a=connection:new\r\n");
            } else if (BroadcastForPlatform.TCP_ACTIVE.equals(streamMode)) {
                // tcp主动模式
                content.append("a=setup:active\r\n");
                content.append("a=connection:new\r\n");
            }
        }

        // ssrc
        content.append("y=").append(ssrcInfo.getSsrc()).append("\r\n");

        Request request = headerProvider.createPlaybackInviteRequest(device, channel.getDeviceId(), content.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()), ssrcInfo.getSsrc());

        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, event -> {
            ResponseEvent responseEvent = (ResponseEvent) event.event;
            SIPResponse response = (SIPResponse) responseEvent.getResponse();
            SsrcTransaction ssrcTransaction = SsrcTransaction.buildForDevice(device.getDeviceId(), channel.getId(), sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()).getCallId(), ssrcInfo.getString(), ssrcInfo.getSsrc(), mediaServerItem.getId(), response, InviteSessionType.PLAYBACK);
            sessionManager.put(ssrcTransaction);
            okEvent.response(event);
        }, timeout);
    }

    /**
     * 请求历史媒体下载
     */
    @Override
    public void downloadStreamCmd(MediaServer mediaServerItem, SSRCInfo ssrcInfo, Device device, DeviceChannel channel, String startTime, String endTime, int downloadSpeed, SipSubscribe.Event errorEvent, SipSubscribe.Event okEvent, Long timeout) throws InvalidArgumentException, SipException, ParseException {

        log.info("[发送-请求历史媒体下载-命令] 流ID： {}，节点为: {} [{}:{}]", ssrcInfo.getString(), mediaServerItem.getId(), mediaServerItem.getSdpIp(), ssrcInfo.getPort());
        String sdpIp;
        if (!ObjectUtils.isEmpty(device.getSdpIp())) {
            sdpIp = device.getSdpIp();
        } else {
            sdpIp = mediaServerItem.getSdpIp();
        }
        StringBuilder content = new StringBuilder(200);
        content.append("v=0\r\n");
        content.append("o=").append(device.getDeviceId()).append(" 0 0 IN IP4 ").append(sdpIp).append("\r\n");
        content.append("s=Download\r\n");
        content.append("u=").append(channel.getDeviceId()).append(":0\r\n");
        content.append("c=IN IP4 ").append(sdpIp).append("\r\n");
        content.append("t=").append(DateUtil.yyyyMmDdHhMmSsToTimestamp(startTime)).append(" ").append(DateUtil.yyyyMmDdHhMmSsToTimestamp(endTime)).append("\r\n");

        BroadcastForPlatform streamMode = device.getStreamMode();

        if (userSetting.getSeniorSdp()) {
            switch (streamMode) {
                case UDP ->
                    content.append("m=video ").append(ssrcInfo.getPort()).append(" RTP/AVP 96 126 125 99 34 98 97\r\n");
                case TCP_PASSIVE, TCP_ACTIVE ->
                    content.append("m=video ").append(ssrcInfo.getPort()).append(" TCP/RTP/AVP 96 126 125 99 34 98 97\r\n");
            }

            content.append("a=recvonly\r\n");
            content.append("a=rtpmap:96 PS/90000\r\n");
            content.append("a=fmtp:126 profile-level-id=42e01e\r\n");
            content.append("a=rtpmap:126 H264/90000\r\n");
            content.append("a=rtpmap:125 H264S/90000\r\n");
            content.append("a=fmtp:125 profile-level-id=42e01e\r\n");
            content.append("a=rtpmap:99 MP4V-ES/90000\r\n");
            content.append("a=fmtp:99 profile-level-id=3\r\n");
            content.append("a=rtpmap:98 H264/90000\r\n");
            content.append("a=rtpmap:97 MPEG4/90000\r\n");
            // tcp被动模式
            if (BroadcastForPlatform.TCP_PASSIVE.equals(streamMode)) {
                content.append("a=setup:passive\r\n");
                content.append("a=connection:new\r\n");
                // tcp主动模式
            } else if (BroadcastForPlatform.TCP_ACTIVE.equals(streamMode)) {
                content.append("a=setup:active\r\n");
                content.append("a=connection:new\r\n");
            }
        } else {
            switch (streamMode) {
                case UDP -> {
                    content.append("m=video ").append(ssrcInfo.getPort()).append(" RTP/AVP 96 97 98 99\r\n");
                }
                case TCP_PASSIVE -> {
                    content.append("m=video ").append(ssrcInfo.getPort()).append(" TCP/RTP/AVP 96 97 98 99\r\n");
                }
                case TCP_ACTIVE -> {
                    content.append("m=video ").append(ssrcInfo.getPort()).append(" TCP/RTP/AVP 96 97 98 99\r\n");
                }
            }

            content.append("a=recvonly\r\n");
            content.append("a=rtpmap:96 PS/90000\r\n");
            content.append("a=rtpmap:97 MPEG4/90000\r\n");
            content.append("a=rtpmap:98 H264/90000\r\n");
            content.append("a=rtpmap:99 H265/90000\r\n");
            // tcp被动模式
            if (BroadcastForPlatform.TCP_PASSIVE.equals(streamMode)) {
                content.append("a=setup:passive\r\n");
                content.append("a=connection:new\r\n");
                // tcp主动模式
            } else if (BroadcastForPlatform.TCP_ACTIVE.equals(streamMode)) {
                content.append("a=setup:active\r\n");
                content.append("a=connection:new\r\n");
            }
        }
        content.append("a=downloadspeed:").append(downloadSpeed).append("\r\n");

        // ssrc
        content.append("y=").append(ssrcInfo.getSsrc()).append("\r\n");
        log.debug("此时请求下载信令的ssrc===>{}", ssrcInfo.getSsrc());
        // 添加订阅
        CallIdHeader newCallIdHeader = sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport());
        Request request = headerProvider.createPlaybackInviteRequest(device, channel.getDeviceId(), content.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, newCallIdHeader, ssrcInfo.getSsrc());

        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, event -> {
            ResponseEvent responseEvent = (ResponseEvent) event.event;
            SIPResponse response = (SIPResponse) responseEvent.getResponse();
            String contentString = new String(response.getRawContent());
            String ssrc = SipUtils.getSsrcFromSdp(contentString);
            SsrcTransaction ssrcTransaction = SsrcTransaction.buildForDevice(device.getDeviceId(), channel.getId(), response.getCallIdHeader().getCallId(), ssrcInfo.getString(), ssrc, mediaServerItem.getId(), response, InviteSessionType.DOWNLOAD);
            sessionManager.put(ssrcTransaction);
            okEvent.response(event);
        }, timeout);
    }

    @Override
    public void talkStreamCmd(MediaServer mediaServerItem, SendRtpInfo sendRtpItem, Device device, DeviceChannel channel, String callId, HookSubscribe.Event event, HookSubscribe.Event eventForPush, SipSubscribe.Event okEvent, SipSubscribe.Event errorEvent, Long timeout) throws InvalidArgumentException, SipException, ParseException {

        String stream = sendRtpItem.getStream();

        if (device == null) {
            return;
        }
        if (!mediaServerItem.isRtpEnable()) {
            // 单端口暂不支持语音喊话
            log.info("[语音喊话] 单端口暂不支持此操作");
            return;
        }

        log.info("[语音喊话] {} 分配的ZLM为: {} [{}:{}]", stream, mediaServerItem.getId(), mediaServerItem.getIp(), sendRtpItem.getPort());
        Hook hook = Hook.getInstance(HookType.ON_MEDIA_ARRIVAL, "rtp", stream, mediaServerItem.getId());
        subscribe.addSubscribe(hook, (hookData) -> {
            if (event != null) {
                event.response(hookData);
                subscribe.removeSubscribe(hook);
            }
        });

        CallIdHeader callIdHeader = sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport());
        callIdHeader.setCallId(callId);
        Hook publishHook = Hook.getInstance(HookType.ON_PUBLISH, "rtp", stream, mediaServerItem.getId());
        subscribe.addSubscribe(publishHook, (hookData) -> {
            if (eventForPush != null) {
                eventForPush.response(hookData);
            }
        });
        //
        StringBuilder content = new StringBuilder(200);
        content.append("v=0\r\n");
        content.append("o=").append(device.getDeviceId()).append(" 0 0 IN IP4 ").append(mediaServerItem.getSdpIp()).append("\r\n");
        content.append("s=Talk\r\n");
        content.append("c=IN IP4 ").append(mediaServerItem.getSdpIp()).append("\r\n");
        content.append("t=0 0\r\n");

        content.append("m=audio ").append(sendRtpItem.getPort()).append(" TCP/RTP/AVP 8\r\n");
        content.append("a=setup:passive\r\n");
        content.append("a=connection:new\r\n");
        content.append("a=sendrecv\r\n");
        content.append("a=rtpmap:8 PCMA/8000\r\n");
        // ssrc
        content.append("y=").append(sendRtpItem.getSsrc()).append("\r\n");
        // f字段:f= v/编码格式/分辨率/帧率/码率类型/码率大小a/编码格式/码率大小/采样率
        content.append("f=v/////a/1/8/1" + "\r\n");

        Request request = headerProvider.createInviteRequest(device, channel.getDeviceId(), content.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sendRtpItem.getSsrc(), callIdHeader);
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, (e -> {
            sessionManager.removeByStream(sendRtpItem.getStream());
            mediaServerService.releaseSsrc(mediaServerItem.getId(), sendRtpItem.getSsrc());
            errorEvent.response(e);
        }), e -> {
            // 这里为例避免一个通道的点播只有一个callID这个参数使用一个固定值
            ResponseEvent responseEvent = (ResponseEvent) e.event;
            SIPResponse response = (SIPResponse) responseEvent.getResponse();
            SsrcTransaction ssrcTransaction = SsrcTransaction.buildForDevice(device.getDeviceId(), channel.getId(), "talk", stream, sendRtpItem.getSsrc(), mediaServerItem.getId(), response, InviteSessionType.TALK);
            sessionManager.put(ssrcTransaction);
            okEvent.response(e);
        }, timeout);
    }

    /**
     * 视频流停止, 不使用回调
     */
    @Override
    public void streamByeCmd(Device device, String channelId, String stream, String callId) throws InvalidArgumentException, ParseException, SipException, SsrcTransactionNotFoundException {
        streamByeCmd(device, channelId, stream, callId, null);
    }

    /**
     * 视频流停止
     */
    @Override
    public void streamByeCmd(Device device, String channelId, String stream, String callId, SipSubscribe.Event okEvent) throws InvalidArgumentException, SipException, ParseException, SsrcTransactionNotFoundException {
        if (device == null) {
            log.warn("[发送BYE] device为null");
            return;
        }
        SsrcTransaction ssrcTransaction = null;
        if (callId != null) {
            ssrcTransaction = sessionManager.getSsrcTransactionByCallId(callId);
        } else if (stream != null) {
            ssrcTransaction = sessionManager.getSsrcTransactionByStream(stream);
        }

        if (ssrcTransaction == null) {
            log.info("[发送BYE] 未找到事务信息,设备： device: {}, channel: {}", device.getDeviceId(), channelId);
            throw new SsrcTransactionNotFoundException(device.getDeviceId(), channelId, callId, stream);
        }

        log.info("[发送BYE] 设备： device: {}, channel: {}, callId: {}", device.getDeviceId(), channelId, ssrcTransaction.getCallId());
        sessionManager.removeByCallId(ssrcTransaction.getCallId());
        Request byteRequest = headerProvider.createByteRequest(device, channelId, ssrcTransaction.getSipTransactionInfo());
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), byteRequest, null, okEvent);
    }

    @Override
    public void streamByeCmd(Device device, String channelId, SipTransactionInfo sipTransactionInfo, SipSubscribe.Event okEvent) throws InvalidArgumentException, SipException, ParseException, SsrcTransactionNotFoundException {
        Request byteRequest = headerProvider.createByteRequest(device, channelId, sipTransactionInfo);
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), byteRequest, null, okEvent);
    }

    @Override
    public void streamByeCmdForDeviceInvite(Device device, String channelId, SipTransactionInfo sipTransactionInfo, SipSubscribe.Event okEvent) throws InvalidArgumentException, SipException, ParseException {
        Request byteRequest = headerProvider.createByteRequestForDeviceInvite(device, channelId, sipTransactionInfo);
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), byteRequest, null, okEvent);
    }

    /**
     * 语音广播
     *
     * @param device 视频设备
     */
    @Override
    public void audioBroadcastCmd(Device device, String channelId, SipSubscribe.Event okEvent, SipSubscribe.Event errorEvent) throws InvalidArgumentException, SipException, ParseException {
        StringBuilder broadcastXml = new StringBuilder(200);
        String charset = device.getCharset();
        broadcastXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        broadcastXml.append("<Notify>\r\n");
        broadcastXml.append("<CmdType>Broadcast</CmdType>\r\n");
        broadcastXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        broadcastXml.append("<SourceID>").append(sipConfig.getId()).append("</SourceID>\r\n");
        broadcastXml.append("<TargetID>").append(channelId).append("</TargetID>\r\n");
        broadcastXml.append("</Notify>\r\n");

        Request request = headerProvider.createMessageRequest(device, broadcastXml.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, okEvent);

    }


    /**
     * 音视频录像控制
     *
     * @param device       视频设备
     * @param channelId    预览通道
     * @param recordCmdStr 录像命令：Record / StopRecord
     */
    @Override
    public void recordCmd(Device device, String channelId, String recordCmdStr, SipSubscribe.Event errorEvent, SipSubscribe.Event okEvent) throws InvalidArgumentException, SipException, ParseException {
        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Control>\r\n");
        cmdXml.append("<CmdType>DeviceControl</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        if (ObjectUtils.isEmpty(channelId)) {
            cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        } else {
            cmdXml.append("<DeviceID>").append(channelId).append("</DeviceID>\r\n");
        }
        cmdXml.append("<RecordCmd>").append(recordCmdStr).append("</RecordCmd>\r\n");
        cmdXml.append("</Control>\r\n");


        Request request = headerProvider.createMessageRequest(device, cmdXml.toString(), null, SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, okEvent);
    }

    /**
     * 远程启动控制命令
     *
     * @param device 视频设备
     */
    @Override
    public void teleBootCmd(Device device) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Control>\r\n");
        cmdXml.append("<CmdType>DeviceControl</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        cmdXml.append("<TeleBoot>Boot</TeleBoot>\r\n");
        cmdXml.append("</Control>\r\n");


        Request request = headerProvider.createMessageRequest(device, cmdXml.toString(), null, SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request);
    }

    /**
     * 报警布防/撤防命令
     *
     * @param device      视频设备
     * @param guardCmdStr "SetGuard"/"ResetGuard"
     */
    @Override
    public void guardCmd(Device device, String guardCmdStr, SipSubscribe.Event errorEvent, SipSubscribe.Event okEvent) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Control>\r\n");
        cmdXml.append("<CmdType>DeviceControl</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        cmdXml.append("<GuardCmd>").append(guardCmdStr).append("</GuardCmd>\r\n");
        cmdXml.append("</Control>\r\n");


        Request request = headerProvider.createMessageRequest(device, cmdXml.toString(), null, SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, okEvent);
    }

    /**
     * 报警复位命令
     *
     * @param device 视频设备
     */
    @Override
    public void alarmCmd(Device device, String alarmMethod, String alarmType, SipSubscribe.Event errorEvent, SipSubscribe.Event okEvent) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Control>\r\n");
        cmdXml.append("<CmdType>DeviceControl</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        cmdXml.append("<AlarmCmd>ResetAlarm</AlarmCmd>\r\n");
        if (!ObjectUtils.isEmpty(alarmMethod) || !ObjectUtils.isEmpty(alarmType)) {
            cmdXml.append("<Info>\r\n");
        }
        if (!ObjectUtils.isEmpty(alarmMethod)) {
            cmdXml.append("<AlarmMethod>").append(alarmMethod).append("</AlarmMethod>\r\n");
        }
        if (!ObjectUtils.isEmpty(alarmType)) {
            cmdXml.append("<AlarmType>").append(alarmType).append("</AlarmType>\r\n");
        }
        if (!ObjectUtils.isEmpty(alarmMethod) || !ObjectUtils.isEmpty(alarmType)) {
            cmdXml.append("</Info>\r\n");
        }
        cmdXml.append("</Control>\r\n");


        Request request = headerProvider.createMessageRequest(device, cmdXml.toString(), null, SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, okEvent);
    }

    /**
     * 强制关键帧命令,设备收到此命令应立刻发送一个IDR帧
     *
     * @param device    视频设备
     * @param channelId 预览通道
     */
    @Override
    public void iFrameCmd(Device device, String channelId) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Control>\r\n");
        cmdXml.append("<CmdType>DeviceControl</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        if (ObjectUtils.isEmpty(channelId)) {
            cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        } else {
            cmdXml.append("<DeviceID>").append(channelId).append("</DeviceID>\r\n");
        }
        cmdXml.append("<IFameCmd>Send</IFameCmd>\r\n");
        cmdXml.append("</Control>\r\n");


        Request request = headerProvider.createMessageRequest(device, cmdXml.toString(), null, SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request);
    }

    /**
     * 看守位控制命令
     *
     * @param device      视频设备
     * @param channelId   通道id，非通道则是设备本身
     * @param enabled     看守位使能：1 = 开启，0 = 关闭
     * @param resetTime   自动归位时间间隔，开启看守位时使用，单位:秒(s)
     * @param presetIndex 调用预置位编号，开启看守位时使用，取值范围0~255
     */
    @Override
    public void homePositionCmd(Device device, String channelId, Boolean enabled, Integer resetTime, Integer presetIndex, SipSubscribe.Event errorEvent, SipSubscribe.Event okEvent) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Control>\r\n");
        cmdXml.append("<CmdType>DeviceControl</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        if (ObjectUtils.isEmpty(channelId)) {
            cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        } else {
            cmdXml.append("<DeviceID>").append(channelId).append("</DeviceID>\r\n");
        }
        cmdXml.append("<HomePosition>\r\n");
        if (enabled) {
            cmdXml.append("<Enabled>1</Enabled>\r\n");
            cmdXml.append("<ResetTime>").append(resetTime).append("</ResetTime>\r\n");
            cmdXml.append("<PresetIndex>").append(presetIndex).append("</PresetIndex>\r\n");
        } else {
            cmdXml.append("<Enabled>0</Enabled>\r\n");
        }
        cmdXml.append("</HomePosition>\r\n");
        cmdXml.append("</Control>\r\n");


        Request request = headerProvider.createMessageRequest(device, cmdXml.toString(), null, SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, okEvent);
    }

    /**
     * 设备配置命令
     *
     * @param device 视频设备
     */
    @Override
    public void deviceConfigCmd(Device device) {
        // TODO Auto-generated method stub
    }

    /**
     * 设备配置命令：basicParam
     *
     * @param device            视频设备
     * @param channelId         通道编码（可选）
     * @param name              设备/通道名称（可选）
     * @param expiration        注册过期时间（可选）
     * @param heartBeatInterval 心跳间隔时间（可选）
     * @param heartBeatCount    心跳超时次数（可选）
     */
    @Override
    public void deviceBasicConfigCmd(Device device, String channelId, String name, String expiration, String heartBeatInterval, String heartBeatCount, SipSubscribe.Event errorEvent) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Control>\r\n");
        cmdXml.append("<CmdType>DeviceConfig</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        if (ObjectUtils.isEmpty(channelId)) {
            cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        } else {
            cmdXml.append("<DeviceID>").append(channelId).append("</DeviceID>\r\n");
        }
        cmdXml.append("<BasicParam>\r\n");
        if (!ObjectUtils.isEmpty(name)) {
            cmdXml.append("<Name>").append(name).append("</Name>\r\n");
        }
        if (NumericUtil.isInteger(expiration)) {
            if (Integer.parseInt(expiration) > 0) {
                cmdXml.append("<Expiration>").append(expiration).append("</Expiration>\r\n");
            }
        }
        if (NumericUtil.isInteger(heartBeatInterval)) {
            if (Integer.parseInt(heartBeatInterval) > 0) {
                cmdXml.append("<HeartBeatInterval>").append(heartBeatInterval).append("</HeartBeatInterval>\r\n");
            }
        }
        if (NumericUtil.isInteger(heartBeatCount)) {
            if (Integer.parseInt(heartBeatCount) > 0) {
                cmdXml.append("<HeartBeatCount>").append(heartBeatCount).append("</HeartBeatCount>\r\n");
            }
        }
        cmdXml.append("</BasicParam>\r\n");
        cmdXml.append("</Control>\r\n");


        Request request = headerProvider.createMessageRequest(device, cmdXml.toString(), null, SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent);
    }

    /**
     * 查询设备状态
     *
     * @param device 视频设备
     */
    @Override
    public void deviceStatusQuery(Device device, SipSubscribe.Event errorEvent) throws InvalidArgumentException, SipException, ParseException {

        String charset = device.getCharset();
        StringBuilder catalogXml = new StringBuilder(200);
        catalogXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        catalogXml.append("<Query>\r\n");
        catalogXml.append("<CmdType>DeviceStatus</CmdType>\r\n");
        catalogXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        catalogXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        catalogXml.append("</Query>\r\n");


        Request request = headerProvider.createMessageRequest(device, catalogXml.toString(), null, SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));

        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent);
    }

    /**
     * 查询设备信息
     *
     * @param device 视频设备
     */
    @Override
    public void deviceInfoQuery(Device device) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder catalogXml = new StringBuilder(200);
        String charset = device.getCharset();
        catalogXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        catalogXml.append("<Query>\r\n");
        catalogXml.append("<CmdType>DeviceInfo</CmdType>\r\n");
        catalogXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        catalogXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        catalogXml.append("</Query>\r\n");


        Request request = headerProvider.createMessageRequest(device, catalogXml.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));

        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request);

    }

    /**
     * 查询目录列表
     *
     * @param device 视频设备
     */
    @Override
    public void catalogQuery(Device device, int sn, SipSubscribe.Event errorEvent) throws SipException, InvalidArgumentException, ParseException {

        StringBuilder catalogXml = new StringBuilder(200);
        String charset = device.getCharset();
        catalogXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        catalogXml.append("<Query>\r\n");
        catalogXml.append("  <CmdType>Catalog</CmdType>\r\n");
        catalogXml.append("  <SN>").append(sn).append("</SN>\r\n");
        catalogXml.append("  <DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        catalogXml.append("</Query>\r\n");

        Request request = headerProvider.createMessageRequest(device, catalogXml.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));

        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent);
    }

    /**
     * 查询录像信息
     *
     * @param device    视频设备
     * @param startTime 开始时间,格式要求：yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间,格式要求：yyyy-MM-dd HH:mm:ss
     */
    @Override
    public void recordInfoQuery(Device device, String channelId, String startTime, String endTime, int sn, Integer secrecy, String type, SipSubscribe.Event okEvent, SipSubscribe.Event errorEvent) throws InvalidArgumentException, SipException, ParseException {
        if (secrecy == null) {
            secrecy = 0;
        }
        if (type == null) {
            type = "all";
        }

        StringBuilder recordInfoXml = new StringBuilder(200);
        String charset = device.getCharset();
        recordInfoXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        recordInfoXml.append("<Query>\r\n");
        recordInfoXml.append("<CmdType>RecordInfo</CmdType>\r\n");
        recordInfoXml.append("<SN>").append(sn).append("</SN>\r\n");
        recordInfoXml.append("<DeviceID>").append(channelId).append("</DeviceID>\r\n");
        if (startTime != null) {
            recordInfoXml.append("<StartTime>").append(DateUtil.yyyyMmDdHhMmSsToIso8601(startTime)).append("</StartTime>\r\n");
        }
        if (endTime != null) {
            recordInfoXml.append("<EndTime>").append(DateUtil.yyyyMmDdHhMmSsToIso8601(endTime)).append("</EndTime>\r\n");
        }
        recordInfoXml.append("<Secrecy> ").append(secrecy).append(" </Secrecy>\r\n");
        // 大华NVR要求必须增加一个值为all的文本元素节点Type
        recordInfoXml.append("<Type>").append(type).append("</Type>\r\n");
        recordInfoXml.append("</Query>\r\n");


        Request request = headerProvider.createMessageRequest(device, recordInfoXml.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));

        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, okEvent);
    }

    /**
     * 查询报警信息
     *
     * @param device        视频设备
     * @param startPriority 报警起始级别（可选）
     * @param endPriority   报警终止级别（可选）
     * @param alarmMethod   报警方式条件（可选）
     * @param alarmType     报警类型
     * @param startTime     报警发生起始时间（可选）
     * @param endTime       报警发生终止时间（可选）
     */
    @Override
    public void alarmInfoQuery(Device device, String startPriority, String endPriority, String alarmMethod, String alarmType, String startTime, String endTime, SipSubscribe.Event errorEvent) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Query>\r\n");
        cmdXml.append("<CmdType>Alarm</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        if (!ObjectUtils.isEmpty(startPriority)) {
            cmdXml.append("<StartAlarmPriority>").append(startPriority).append("</StartAlarmPriority>\r\n");
        }
        if (!ObjectUtils.isEmpty(endPriority)) {
            cmdXml.append("<EndAlarmPriority>").append(endPriority).append("</EndAlarmPriority>\r\n");
        }
        if (!ObjectUtils.isEmpty(alarmMethod)) {
            cmdXml.append("<AlarmMethod>").append(alarmMethod).append("</AlarmMethod>\r\n");
        }
        if (!ObjectUtils.isEmpty(alarmType)) {
            cmdXml.append("<AlarmType>").append(alarmType).append("</AlarmType>\r\n");
        }
        if (!ObjectUtils.isEmpty(startTime)) {
            cmdXml.append("<StartAlarmTime>").append(startTime).append("</StartAlarmTime>\r\n");
        }
        if (!ObjectUtils.isEmpty(endTime)) {
            cmdXml.append("<EndAlarmTime>").append(endTime).append("</EndAlarmTime>\r\n");
        }
        cmdXml.append("</Query>\r\n");


        Request request = headerProvider.createMessageRequest(device, cmdXml.toString(), null, SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent);
    }

    /**
     * 查询设备配置
     *
     * @param device     视频设备
     * @param channelId  通道编码（可选）
     * @param configType 配置类型：
     */
    @Override
    public void deviceConfigQuery(Device device, String channelId, String configType, SipSubscribe.Event errorEvent) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Query>\r\n");
        cmdXml.append("<CmdType>ConfigDownload</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        if (ObjectUtils.isEmpty(channelId)) {
            cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        } else {
            cmdXml.append("<DeviceID>").append(channelId).append("</DeviceID>\r\n");
        }
        cmdXml.append("<ConfigType>").append(configType).append("</ConfigType>\r\n");
        cmdXml.append("</Query>\r\n");


        Request request = headerProvider.createMessageRequest(device, cmdXml.toString(), null, SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent);
    }

    /**
     * 获取随机数
     *
     * @return 随机数
     */
    public int getRandom() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

    /**
     * 查询设备预置位置
     *
     * @param device 视频设备
     */
    @Override
    public void presetQuery(Device device, String channelId, SipSubscribe.Event errorEvent) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Query>\r\n");
        cmdXml.append("<CmdType>PresetQuery</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        if (ObjectUtils.isEmpty(channelId)) {
            cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        } else {
            cmdXml.append("<DeviceID>").append(channelId).append("</DeviceID>\r\n");
        }
        cmdXml.append("</Query>\r\n");


        Request request = headerProvider.createMessageRequest(device, cmdXml.toString(), null, SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent);
    }

    /**
     * 查询移动设备位置数据
     *
     * @param device 视频设备
     */
    @Override
    public void mobilePostitionQuery(Device device, SipSubscribe.Event errorEvent) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder mobilePostitionXml = new StringBuilder(200);
        String charset = device.getCharset();
        mobilePostitionXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        mobilePostitionXml.append("<Query>\r\n");
        mobilePostitionXml.append("<CmdType>MobilePosition</CmdType>\r\n");
        mobilePostitionXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        mobilePostitionXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        mobilePostitionXml.append("<Interval>60</Interval>\r\n");
        mobilePostitionXml.append("</Query>\r\n");


        Request request = headerProvider.createMessageRequest(device, mobilePostitionXml.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));

        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent);

    }

    /**
     * 订阅、取消订阅移动位置
     *
     * @param device 视频设备
     * @return true = 命令发送成功
     */
    @Override
    public SIPRequest mobilePositionSubscribe(Device device, SIPRequest requestOld, SipSubscribe.Event okEvent, SipSubscribe.Event errorEvent) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder subscribePostitionXml = new StringBuilder(200);
        String charset = device.getCharset();
        subscribePostitionXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        subscribePostitionXml.append("<Query>\r\n");
        subscribePostitionXml.append("<CmdType>MobilePosition</CmdType>\r\n");
        subscribePostitionXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        subscribePostitionXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        if (device.getSubscribeCycleForMobilePosition() > 0) {
            subscribePostitionXml.append("<Interval>").append(device.getMobilePositionSubmissionInterval()).append("</Interval>\r\n");
        } else {
            subscribePostitionXml.append("<Interval>5</Interval>\r\n");
        }
        subscribePostitionXml.append("</Query>\r\n");

        CallIdHeader callIdHeader;

        if (requestOld != null) {
            callIdHeader = SipFactory.getInstance().createHeaderFactory().createCallIdHeader(requestOld.getCallIdHeader().getCallId());
        } else {
            callIdHeader = sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport());
        }
        SIPRequest request = (SIPRequest) headerProvider.createSubscribeRequest(device, subscribePostitionXml.toString(), requestOld, device.getSubscribeCycleForMobilePosition(), "presence", callIdHeader);
        // Position;id=" + tm.substring(tm.length() - 4));

        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, okEvent);
        return request;
    }

    /**
     * 订阅、取消订阅报警信息
     *
     * @param device        视频设备
     * @param expires       订阅过期时间（0 = 取消订阅）
     * @param startPriority 报警起始级别（可选）
     * @param endPriority   报警终止级别（可选）
     * @param alarmMethod   报警方式条件（可选）
     * @param startTime     报警发生起始时间（可选）
     * @param endTime       报警发生终止时间（可选）
     * @return true = 命令发送成功
     */
    @Override
    public void alarmSubscribe(Device device, int expires, String startPriority, String endPriority, String alarmMethod, String startTime, String endTime) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Query>\r\n");
        cmdXml.append("<CmdType>Alarm</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        if (!ObjectUtils.isEmpty(startPriority)) {
            cmdXml.append("<StartAlarmPriority>").append(startPriority).append("</StartAlarmPriority>\r\n");
        }
        if (!ObjectUtils.isEmpty(endPriority)) {
            cmdXml.append("<EndAlarmPriority>").append(endPriority).append("</EndAlarmPriority>\r\n");
        }
        if (!ObjectUtils.isEmpty(alarmMethod)) {
            cmdXml.append("<AlarmMethod>").append(alarmMethod).append("</AlarmMethod>\r\n");
        }
        if (!ObjectUtils.isEmpty(startTime)) {
            cmdXml.append("<StartAlarmTime>").append(startTime).append("</StartAlarmTime>\r\n");
        }
        if (!ObjectUtils.isEmpty(endTime)) {
            cmdXml.append("<EndAlarmTime>").append(endTime).append("</EndAlarmTime>\r\n");
        }
        cmdXml.append("</Query>\r\n");


        Request request = headerProvider.createSubscribeRequest(device, cmdXml.toString(), null, expires, "presence", sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request);

    }

    @Override
    public SIPRequest catalogSubscribe(Device device, SIPRequest requestOld, SipSubscribe.Event okEvent, SipSubscribe.Event errorEvent) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder cmdXml = new StringBuilder(200);
        String charset = device.getCharset();
        cmdXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        cmdXml.append("<Query>\r\n");
        cmdXml.append("<CmdType>Catalog</CmdType>\r\n");
        cmdXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        cmdXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        cmdXml.append("</Query>\r\n");

        CallIdHeader callIdHeader;

        if (requestOld != null) {
            callIdHeader = SipFactory.getInstance().createHeaderFactory().createCallIdHeader(requestOld.getCallIdHeader().getCallId());
        } else {
            callIdHeader = sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport());
        }

        // 有效时间默认为60秒以上
        SIPRequest request = (SIPRequest) headerProvider.createSubscribeRequest(device, cmdXml.toString(), requestOld, device.getSubscribeCycleForCatalog(), "Catalog", callIdHeader);
        // 发送请求
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, okEvent);
        return request;
    }

    @Override
    public void dragZoomCmd(Device device, String channelId, String cmdString) throws InvalidArgumentException, SipException, ParseException {

        StringBuilder dragXml = new StringBuilder(200);
        String charset = device.getCharset();
        dragXml.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\"?>\r\n");
        dragXml.append("<Control>\r\n");
        dragXml.append("<CmdType>DeviceControl</CmdType>\r\n");
        dragXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        if (ObjectUtils.isEmpty(channelId)) {
            dragXml.append("<DeviceID>").append(device.getDeviceId()).append("</DeviceID>\r\n");
        } else {
            dragXml.append("<DeviceID>").append(channelId).append("</DeviceID>\r\n");
        }
        dragXml.append(cmdString);
        dragXml.append("</Control>\r\n");

        Request request = headerProvider.createMessageRequest(device, dragXml.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        log.debug("拉框信令： " + request.toString());
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request);
    }


    /**
     * 回放暂停
     */
    @Override
    public void playPauseCmd(Device device, DeviceChannel channel, StreamInfo streamInfo) throws InvalidArgumentException, ParseException, SipException {
        StringBuilder content = new StringBuilder(200);
        content.append("PAUSE RTSP/1.0\r\n");
        content.append("CSeq: ").append(getInfoCseq()).append("\r\n");
        content.append("PauseTime: now\r\n");

        playbackControlCmd(device, channel, streamInfo, content.toString(), null, null);
    }


    /**
     * 回放恢复
     */
    @Override
    public void playResumeCmd(Device device, DeviceChannel channel, StreamInfo streamInfo) throws InvalidArgumentException, ParseException, SipException {
        StringBuilder content = new StringBuilder(200);
        content.append("PLAY RTSP/1.0\r\n");
        content.append("CSeq: ").append(getInfoCseq()).append("\r\n");
        content.append("Range: npt=now-\r\n");

        playbackControlCmd(device, channel, streamInfo, content.toString(), null, null);
    }

    /**
     * 回放拖动播放
     */
    @Override
    public void playSeekCmd(Device device, DeviceChannel channel, StreamInfo streamInfo, long seekTime) throws InvalidArgumentException, ParseException, SipException {
        StringBuilder content = new StringBuilder(200);
        content.append("PLAY RTSP/1.0\r\n");
        content.append("CSeq: ").append(getInfoCseq()).append("\r\n");
        content.append("Range: npt=").append(Math.abs(seekTime)).append("-\r\n");

        playbackControlCmd(device, channel, streamInfo, content.toString(), null, null);
    }

    /**
     * 回放倍速播放
     */
    @Override
    public void playSpeedCmd(Device device, DeviceChannel channel, StreamInfo streamInfo, Double speed) throws InvalidArgumentException, ParseException, SipException {
        StringBuilder content = new StringBuilder(200);
        content.append("PLAY RTSP/1.0\r\n");
        content.append("CSeq: ").append(getInfoCseq()).append("\r\n");
        content.append("Scale: ").append(String.format("%.6f", speed)).append("\r\n");

        playbackControlCmd(device, channel, streamInfo, content.toString(), null, null);
    }

    private Integer getInfoCseq() {
        return (int) ((Math.random() * 9 + 1) * Math.pow(10, 8));
    }

    @Override
    public void playbackControlCmd(Device device, DeviceChannel channel, StreamInfo streamInfo, String content, SipSubscribe.Event errorEvent, SipSubscribe.Event okEvent) throws SipException, InvalidArgumentException, ParseException {

        playbackControlCmd(device, channel, streamInfo.getStream(), content, errorEvent, okEvent);
    }

    @Override
    public void playbackControlCmd(Device device, DeviceChannel channel, String stream, String content, SipSubscribe.Event errorEvent, SipSubscribe.Event okEvent) throws SipException, InvalidArgumentException, ParseException {

        SsrcTransaction ssrcTransaction = sessionManager.getSsrcTransactionByStream(stream);
        if (ssrcTransaction == null) {
            log.info("[回放控制]未找到视频流信息，设备：{}, 流ID: {}", device.getDeviceId(), stream);
            return;
        }

        SIPRequest request = headerProvider.createInfoRequest(device, channel.getDeviceId(), content, ssrcTransaction.getSipTransactionInfo());
        if (request == null) {
            log.info("[回放控制]构建Request信息失败，设备：{}, 流ID: {}", device.getDeviceId(), stream);
            return;
        }

        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request, errorEvent, okEvent);
    }

    @Override
    public void sendAlarmMessage(Device device, DeviceAlarm deviceAlarm) throws InvalidArgumentException, SipException, ParseException {
        if (device == null) {
            return;
        }
        log.info("[发送报警通知]设备： {}/{}->{},{}", device.getDeviceId(), deviceAlarm.getChannelId(), deviceAlarm.getLongitude(), deviceAlarm.getLatitude());

        String characterSet = device.getCharset();
        StringBuilder deviceStatusXml = new StringBuilder(600);
        deviceStatusXml.append("<?xml version=\"1.0\" encoding=\"").append(characterSet).append("\"?>\r\n");
        deviceStatusXml.append("<Notify>\r\n");
        deviceStatusXml.append("<CmdType>Alarm</CmdType>\r\n");
        deviceStatusXml.append("<SN>").append(getRandom()).append("</SN>\r\n");
        deviceStatusXml.append("<DeviceID>").append(deviceAlarm.getChannelId()).append("</DeviceID>\r\n");
        deviceStatusXml.append("<AlarmPriority>").append(deviceAlarm.getAlarmPriority()).append("</AlarmPriority>\r\n");
        deviceStatusXml.append("<AlarmMethod>").append(deviceAlarm.getAlarmMethod()).append("</AlarmMethod>\r\n");
        deviceStatusXml.append("<AlarmTime>").append(DateUtil.yyyyMmDdHhMmSsToIso8601(deviceAlarm.getAlarmTime())).append("</AlarmTime>\r\n");
        deviceStatusXml.append("<AlarmDescription>").append(deviceAlarm.getAlarmDescription()).append("</AlarmDescription>\r\n");
        deviceStatusXml.append("<Longitude>").append(deviceAlarm.getLongitude()).append("</Longitude>\r\n");
        deviceStatusXml.append("<Latitude>").append(deviceAlarm.getLatitude()).append("</Latitude>\r\n");
        deviceStatusXml.append("<info>\r\n");
        deviceStatusXml.append("<AlarmType>").append(deviceAlarm.getAlarmType()).append("</AlarmType>\r\n");
        deviceStatusXml.append("</info>\r\n");
        deviceStatusXml.append("</Notify>\r\n");


        Request request = headerProvider.createMessageRequest(device, deviceStatusXml.toString(), SipUtils.getNewViaTag(), SipUtils.getNewFromTag(), null, sipSender.getNewCallIdHeader(sipLayer.getLocalIp(device.getLocalIp()), device.getTransport()));
        sipSender.transmitRequest(sipLayer.getLocalIp(device.getLocalIp()), request);


    }
}
