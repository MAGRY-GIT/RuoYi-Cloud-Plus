package com.cdzeroly.wvp.vmanager.controller;

import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.redis.utils.RedisUtils;
import com.cdzeroly.wvp.common.NetProtocol;
import com.cdzeroly.wvp.common.VideoManagerConstants;
import com.cdzeroly.wvp.conf.task.DynamicTask;
import com.cdzeroly.wvp.conf.UserSetting;

import com.cdzeroly.wvp.gb28181.domian.bean.SendRtpInfo;
import com.cdzeroly.wvp.media.domian.MediaServer;
import com.cdzeroly.wvp.media.event.hook.Hook;
import com.cdzeroly.wvp.media.event.hook.HookSubscribe;
import com.cdzeroly.wvp.media.event.hook.HookType;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.service.ISendRtpServerService;
import com.cdzeroly.wvp.service.domian.bean.SSRCInfo;
import com.cdzeroly.wvp.vmanager.bean.OtherPsSendInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author MGARY
 */
@SuppressWarnings("rawtypes")
@Tag(name = "第三方PS服务对接")
@Slf4j
@RestController
@RequestMapping("/api/ps")
@RequiredArgsConstructor
public class PsController {

    private final HookSubscribe hookSubscribe;

    private final IMediaServerService mediaServerService;

    private final ISendRtpServerService sendRtpServerService;

    private final  UserSetting userSetting;

    private final DynamicTask dynamicTask;
    private final RedisTemplate<Object, Object> redisTemplate;


    @GetMapping(value = "/receive/open")
    @Operation(summary = "开启收流和获取发流信息")
    @Parameter(name = "isSend", description = "是否发送，false时只开启收流， true同时返回推流信息", required = true)
    @Parameter(name = "callId", description = "整个过程的唯一标识，为了与后续接口关联", required = true)
    @Parameter(name = "ssrc", description = "来源流的SSRC，不传则不校验来源ssrc", required = false)
    @Parameter(name = "stream", description = "形成的流的ID", required = true)
    @Parameter(name = "tcpMode", description = "收流模式， 0为UDP， 1为TCP被动", required = true)
    @Parameter(name = "callBack", description = "回调地址，如果收流超时会通道回调通知，回调为get请求，参数为callId", required = true)
    public OtherPsSendInfo openRtpServer(Boolean isSend, @RequestParam(required = false)String ssrc, String callId, String stream, Integer tcpMode, String callBack) {

        log.info("[第三方PS服务对接->开启收流和获取发流信息] isSend->{}, ssrc->{}, callId->{}, stream->{}, tcpMode->{}, callBack->{}",
                isSend, ssrc, callId, stream, tcpMode==0? NetProtocol.UDP.name():"TCP被动", callBack);

        MediaServer mediaServer = mediaServerService.getDefaultMediaServer();
        if (mediaServer == null) {
            throw new ServiceException("没有可用的MediaServer");
        }
        if (stream == null) {
            throw new ServiceException("stream参数不可为空");
        }
        if (isSend != null && isSend && callId == null) {
            throw new ServiceException("isSend为true时，CallID不能为空");
        }
        long ssrcInt = 0;
        if (ssrc != null) {
            try {
                ssrcInt = Long.parseLong(ssrc);
            }catch (NumberFormatException e) {
                throw new ServiceException("ssrc格式错误");
            }
        }
        String receiveKey = VideoManagerConstants.WVP_OTHER_RECEIVE_PS_INFO + userSetting.getServerId() + "_" + callId + "_"  + stream;
        SSRCInfo ssrcInfo = mediaServerService.openRTPServer(mediaServer, stream, ssrcInt + "", false, false, null, false, false, false, tcpMode);

        if (ssrcInfo.getPort() == 0) {
            throw new ServiceException( "获取端口失败");
        }
        // 注册回调如果rtp收流超时则通过回调发送通知
        if (callBack != null) {
            Hook hook = Hook.getInstance(HookType.on_rtp_server_timeout, "rtp", stream, mediaServer.getIp());
            // 订阅 zlm启动事件, 新的zlm也会从这里进入系统
            hookSubscribe.addSubscribe(hook,
                    (hookData)->{
                        if (stream.equals(hookData.getStream())) {
                            log.info("[第三方PS服务对接->开启收流和获取发流信息] 等待收流超时 callId->{}, 发送回调", callId);
                            // 将信息写入redis中，以备后用
                            redisTemplate.delete(receiveKey);
                            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
                            OkHttpClient client = httpClientBuilder.build();
                            String url = callBack + "?callId="  + callId;
                            Request request = new Request.Builder().get().url(url).build();
                            try {
                                client.newCall(request).execute();
                            } catch (IOException e) {
                                log.error("[第三方PS服务对接->开启收流和获取发流信息] 等待收流超时 callId->{}, 发送回调失败", callId, e);
                            }
                            hookSubscribe.removeSubscribe(hook);
                        }
                    });
        }
        OtherPsSendInfo otherPsSendInfo = new OtherPsSendInfo();
        otherPsSendInfo.setReceiveIp(mediaServer.getSdpIp());
        otherPsSendInfo.setReceivePort(ssrcInfo.getPort());
        otherPsSendInfo.setCallId(callId);
        otherPsSendInfo.setStream(stream);

        // 将信息写入redis中，以备后用
        redisTemplate.opsForValue().set(receiveKey, otherPsSendInfo);
        if (isSend != null && isSend) {
            String key = VideoManagerConstants.WVP_OTHER_SEND_PS_INFO + userSetting.getServerId() + "_"  + callId;
            // 预创建发流信息
            int port = sendRtpServerService.getNextPort(mediaServer);

            otherPsSendInfo.setSendLocalIp(mediaServer.getSdpIp());
            otherPsSendInfo.setSendLocalPort(port);
            // 将信息写入redis中，以备后用
            redisTemplate.opsForValue().set(key, otherPsSendInfo, 300, TimeUnit.SECONDS);
            log.info("[第三方PS服务对接->开启收流和获取发流信息] 结果，callId->{}， {}", callId, otherPsSendInfo);
        }
        return otherPsSendInfo;
    }

    @GetMapping(value = "/receive/close")

    @Operation(summary = "关闭收流")
    @Parameter(name = "stream", description = "流的ID", required = true)
    public void closeRtpServer(String stream) {
        log.info("[第三方PS服务对接->关闭收流] stream->{}", stream);
        MediaServer mediaServerItem = mediaServerService.getDefaultMediaServer();
        mediaServerService.closeRTPServer(mediaServerItem, stream);
        String receiveKey = VideoManagerConstants.WVP_OTHER_RECEIVE_PS_INFO + userSetting.getServerId() + "_*_"  + stream;
        RedisUtils.deleteKeys(receiveKey);
    }

    @GetMapping(value = "/send/start")

    @Operation(summary = "发送流")
    @Parameter(name = "ssrc", description = "发送流的SSRC", required = true)
    @Parameter(name = "dstIp", description = "目标收流IP", required = true)
    @Parameter(name = "dstPort", description = "目标收流端口", required = true)
    @Parameter(name = "app", description = "待发送应用名", required = true)
    @Parameter(name = "stream", description = "待发送流Id", required = true)
    @Parameter(name = "callId", description = "整个过程的唯一标识，不传则使用随机端口发流", required = true)
    @Parameter(name = "isUdp", description = "是否为UDP", required = true)
    public void sendRTP(String ssrc,
                        String dstIp,
                        Integer dstPort,
                        String app,
                        String stream,
                        String callId,
                        Boolean isUdp
        ) {
        log.info("[第三方PS服务对接->发送流] " +
                        "ssrc->{}, \r\n" +
                        "dstIp->{}, \n" +
                        "dstPort->{},  \n" +
                        "app->{}, \n" +
                        "stream->{}, \n" +
                        "callId->{} \n",
                        ssrc,
                        dstIp,
                        dstPort,
                        app,
                        stream,
                        callId);
        MediaServer mediaServer = mediaServerService.getDefaultMediaServer();
        String key = VideoManagerConstants.WVP_OTHER_SEND_PS_INFO + userSetting.getServerId() + "_"  + callId;
        OtherPsSendInfo sendInfo = (OtherPsSendInfo)redisTemplate.opsForValue().get(key);
        if (sendInfo == null) {
            sendInfo = new OtherPsSendInfo();
        }
        sendInfo.setPushApp(app);
        sendInfo.setPushStream(stream);
        sendInfo.setPushSSRC(ssrc);
        SendRtpInfo sendRtpItem = SendRtpInfo.getInstance(app, stream, ssrc, dstIp, dstPort, !isUdp, sendInfo.getSendLocalPort(), null);
        Boolean streamReady = mediaServerService.isStreamReady(mediaServer, app, stream);
        if (streamReady) {
            mediaServerService.startSendRtp(mediaServer, sendRtpItem);
            log.info("[第三方PS服务对接->发送流] 视频流发流成功，callId->{}，param->{}", callId, sendRtpItem);
            redisTemplate.opsForValue().set(key, sendInfo);
        }else {
            log.info("[第三方PS服务对接->发送流] 流不存在，等待流上线，callId->{}", callId);
            String uuid = UUID.randomUUID().toString();
            Hook hook = Hook.getInstance(HookType.on_media_arrival, app, stream, mediaServer.getId());
            dynamicTask.startDelay(uuid, ()->{
                log.info("[第三方PS服务对接->发送流] 等待流上线超时 callId->{}", callId);
                redisTemplate.delete(key);
                hookSubscribe.removeSubscribe(hook);
            }, 10000);

            // 订阅 zlm启动事件, 新的zlm也会从这里进入系统
            OtherPsSendInfo finalSendInfo = sendInfo;
            hookSubscribe.removeSubscribe(hook);
            hookSubscribe.addSubscribe(hook,
                    (hookData)->{
                        dynamicTask.stop(uuid);
                        log.info("[第三方PS服务对接->发送流] 流上线，开始发流 callId->{}", callId);
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        mediaServerService.startSendRtp(mediaServer, sendRtpItem);
                        log.info("[第三方PS服务对接->发送流] 视频流发流成功，callId->{}，param->{}", callId, sendRtpItem);
                        redisTemplate.opsForValue().set(key, finalSendInfo);
                        hookSubscribe.removeSubscribe(hook);
                    });
        }
    }

    @GetMapping(value = "/send/stop")

    @Operation(summary = "关闭发送流")
    @Parameter(name = "callId", description = "整个过程的唯一标识，不传则使用随机端口发流", required = true)
    public void closeSendRTP(String callId) {
        log.info("[第三方PS服务对接->关闭发送流] callId->{}", callId);
        String key = VideoManagerConstants.WVP_OTHER_SEND_PS_INFO + userSetting.getServerId() + "_"  + callId;
        OtherPsSendInfo sendInfo = (OtherPsSendInfo)redisTemplate.opsForValue().get(key);
        if (sendInfo == null){
            throw new ServiceException("未开启发流");
        }
        MediaServer mediaServerItem = mediaServerService.getDefaultMediaServer();
        boolean result = mediaServerService.stopSendRtp(mediaServerItem, sendInfo.getPushApp(), sendInfo.getStream(), sendInfo.getPushSSRC());
        if (!result) {
            log.info("[第三方PS服务对接->关闭发送流] 失败 callId->{}", callId);
            throw new ServiceException( "停止发流失败");
        }else {
            log.info("[第三方PS服务对接->关闭发送流] 成功 callId->{}", callId);
        }
        redisTemplate.delete(key);
    }


    @GetMapping(value = "/getTestPort")

    public int getTestPort() {
        MediaServer defaultMediaServer = mediaServerService.getDefaultMediaServer();

//        for (int i = 0; i <300; i++) {
//            new Thread(() -> {
//                int nextPort = sendRtpPortManager.getNextPort(defaultMediaServer);
//                try {
//                    Thread.sleep((int)Math.random()*10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println(nextPort);
//            }).start();
//        }

        return sendRtpServerService.getNextPort(defaultMediaServer);
    }
}
