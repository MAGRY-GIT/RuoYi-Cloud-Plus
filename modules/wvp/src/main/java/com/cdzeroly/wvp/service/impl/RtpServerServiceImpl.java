package com.cdzeroly.wvp.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.cdzeroly.wvp.conf.task.DynamicTask;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.gb28181.bean.OpenRTPServerResult;
import com.cdzeroly.wvp.gb28181.session.SSRCFactory;
import com.cdzeroly.wvp.gb28181.session.SipInviteSessionManager;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.event.hook.Hook;
import com.cdzeroly.wvp.media.event.hook.HookSubscribe;
import com.cdzeroly.wvp.media.event.hook.HookType;
import com.cdzeroly.wvp.media.event.media.MediaArrivalEvent;
import com.cdzeroly.wvp.media.event.media.MediaDepartureEvent;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.service.IReceiveRtpServerService;
import com.cdzeroly.wvp.domain.bean.ErrorCallback;
import com.cdzeroly.wvp.domain.bean.InviteErrorCode;
import com.cdzeroly.wvp.domain.bean.RTPServerParam;
import com.cdzeroly.wvp.domain.bean.SSRCInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RtpServerServiceImpl implements IReceiveRtpServerService {

    private final IMediaServerService mediaServerService;

    private final DynamicTask dynamicTask;

    private final SSRCFactory ssrcFactory;

    private final UserSetting userSetting;

    private final HookSubscribe subscribe;

    private final SipInviteSessionManager sessionManager;

    /**
     * 流到来的处理
     */
    @Async("taskExecutor")
    @EventListener
    public void onApplicationEvent(MediaArrivalEvent event) {

    }

    /**
     * 流离开的处理
     */
    @Async("taskExecutor")
    @EventListener
    public void onApplicationEvent(MediaDepartureEvent event) {

    }

    @Override
    public SSRCInfo openRtpServer(RTPServerParam rtpServerParam, ErrorCallback<OpenRTPServerResult> callback) {
        if (callback == null) {
            log.warn("[开启RTP收流] 失败，回调为NULL");
            return null;
        }
        if (rtpServerParam.getMediaServer() == null) {
            log.warn("[开启RTP收流] 失败，媒体节点为NULL");
            return null;
        }

        // 获取mediaServer可用的ssrc
        final String ssrc;
        if (rtpServerParam.getPresetSsrc() != null) {
            ssrc = rtpServerParam.getPresetSsrc();
        }else {
            if (rtpServerParam.isPlayback()) {
                ssrc = ssrcFactory.getPlayBackSsrc(rtpServerParam.getMediaServer().getId());
            }else {
                ssrc = ssrcFactory.getPlaySsrc(rtpServerParam.getMediaServer().getId());
            }
        }
        final String streamId;
        if (rtpServerParam.getStreamId() == null) {
            streamId = String.format("%08x", Long.parseLong(ssrc)).toUpperCase();
        }else {
            streamId = rtpServerParam.getStreamId();
        }
        if (rtpServerParam.isSsrcCheck() && ObjUtil.isNotNull(rtpServerParam.getTcpMode())) {
            // 目前zlm不支持 tcp模式更新ssrc，暂时关闭ssrc校验
            log.warn("[openRTPServer] 平台对接时下级可能自定义ssrc，但是tcp模式zlm收流目前无法更新ssrc，可能收流超时，此时请使用udp收流或者关闭ssrc校验");
        }
        int rtpServerPort;
        if (rtpServerParam.getMediaServer().isRtpEnable()) {
            rtpServerPort = mediaServerService.createRTPServer(rtpServerParam.getMediaServer(), streamId,
                    rtpServerParam.isSsrcCheck() ? Long.parseLong(ssrc) : 0, rtpServerParam.getPort(), rtpServerParam.isOnlyAuto(),
                    rtpServerParam.isDisableAudio(), rtpServerParam.isReUsePort(), rtpServerParam.getTcpMode());
        } else {
            rtpServerPort = rtpServerParam.getMediaServer().getRtpProxyPort();
        }
        if (rtpServerPort == 0) {
            callback.run(InviteErrorCode.ERROR_FOR_RESOURCE_EXHAUSTION.getCode(), "开启RTPServer失败", null);
            // 释放ssrc
            if (rtpServerParam.getPresetSsrc() == null) {
                ssrcFactory.releaseSsrc(rtpServerParam.getMediaServer().getId(), ssrc);
            }
            return null;
        }

        // 设置流超时的定时任务
        String timeOutTaskKey = UUID.randomUUID().toString();

        SSRCInfo ssrcInfo = new SSRCInfo(rtpServerPort, ssrc, streamId, timeOutTaskKey);
        OpenRTPServerResult openRtpServerResult = new OpenRTPServerResult();
        openRtpServerResult.setSsrcInfo(ssrcInfo);

        Hook rtpHook = Hook.getInstance(HookType.ON_MEDIA_ARRIVAL, "rtp", streamId, rtpServerParam.getMediaServer().getId());
        dynamicTask.startDelay(timeOutTaskKey, () -> {
            // 收流超时
            // 释放ssrc
            if (rtpServerParam.getPresetSsrc() == null) {
                ssrcFactory.releaseSsrc(rtpServerParam.getMediaServer().getId(), ssrc);
            }
            // 关闭收流端口
            mediaServerService.closeRTPServer(rtpServerParam.getMediaServer(), streamId);
            subscribe.removeSubscribe(rtpHook);
            callback.run(InviteErrorCode.ERROR_FOR_STREAM_TIMEOUT.getCode(), InviteErrorCode.ERROR_FOR_STREAM_TIMEOUT.getMsg(), openRtpServerResult);
        }, userSetting.getPlayTimeout());
        // 开启流到来的监听
        subscribe.addSubscribe(rtpHook, (hookData) -> {
            dynamicTask.stop(timeOutTaskKey);
            // hook响应
            openRtpServerResult.setHookData(hookData);
            callback.run(InviteErrorCode.SUCCESS.getCode(), InviteErrorCode.SUCCESS.getMsg(), openRtpServerResult);
            subscribe.removeSubscribe(rtpHook);
        });

        return ssrcInfo;
    }

    @Override
    public void closeRtpServer(MediaServer mediaServer, SSRCInfo ssrcInfo) {
        if (mediaServer == null) {
            return;
        }
        if (ssrcInfo.getTimeOutTaskKey() != null) {
            dynamicTask.stop(ssrcInfo.getTimeOutTaskKey());
        }
        if (ssrcInfo.getSsrc() != null) {
            // 释放ssrc
            ssrcFactory.releaseSsrc(mediaServer.getId(), ssrcInfo.getSsrc());
        }
        mediaServerService.closeRTPServer(mediaServer, ssrcInfo.getString());
    }
}
