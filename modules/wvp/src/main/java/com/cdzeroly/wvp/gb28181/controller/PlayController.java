package com.cdzeroly.wvp.gb28181.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.web.core.BaseController;
import com.cdzeroly.wvp.common.InviteSessionType;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.conf.UserSetting;

import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.domian.DeviceChannel;
import com.cdzeroly.wvp.domain.bean.SsrcTransaction;
import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import com.cdzeroly.wvp.gb28181.service.IDeviceService;
import com.cdzeroly.wvp.gb28181.service.IInviteStreamService;
import com.cdzeroly.wvp.gb28181.service.IPlayService;
import com.cdzeroly.wvp.gb28181.session.SipInviteSessionManager;
import com.cdzeroly.wvp.gb28181.transmit.callback.DeferredResultHolder;
import com.cdzeroly.wvp.gb28181.transmit.callback.RequestMessage;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.domain.bean.InviteErrorCode;
import com.cdzeroly.wvp.utils.DateUtil;
import com.cdzeroly.wvp.domain.vo.AudioBroadcastVo;
import com.cdzeroly.wvp.domain.ErrorCode;
import com.cdzeroly.wvp.domain.vo.StreamContentVo;
import com.cdzeroly.wvp.domain.WVPResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;


/**
 * @author lin
 */
@Tag(name = "国标设备点播")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/play")
public class PlayController extends BaseController {

    private final SipInviteSessionManager sessionManager;

    private final IInviteStreamService inviteStreamService;

    private final DeferredResultHolder resultHolder;

    private final IPlayService playService;

    private final IMediaServerService mediaServerService;

    private final UserSetting userSetting;

    private final IDeviceService deviceService;

    private final IDeviceChannelService deviceChannelService;

    @Operation(summary = "开始点播")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @Parameter(name = "channelId", description = "通道国标编号", required = true)
    @GetMapping("/start/{deviceId}/{channelId}")
    public DeferredResult<WVPResult<StreamContentVo>> play(@PathVariable String deviceId, @PathVariable String channelId) {

        log.info("[开始点播] deviceId：{}, channelId：{}, ", deviceId, channelId);
        Assert.notNull(deviceId, "设备国标编号不可为NULL");
        Assert.notNull(channelId, "通道国标编号不可为NULL");
        // 获取可用的zlm
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        Assert.notNull(deviceId, "设备不存在");
        DeviceChannel channel = deviceChannelService.getOne(deviceId, channelId);
        Assert.notNull(channel, "通道不存在");
        MediaServer newMediaServerItem = playService.getNewMediaServerItem(device);

        RequestMessage requestMessage = new RequestMessage();
        String key = DeferredResultHolder.CALLBACK_CMD_PLAY + deviceId + channelId;
        requestMessage.setKey(key);
        String uuid = UUID.randomUUID().toString();
        requestMessage.setId(uuid);
        DeferredResult<WVPResult<StreamContentVo>> result = new DeferredResult<>(userSetting.getPlayTimeout().longValue());

        result.onTimeout(() -> {
            log.info("[点播等待超时] deviceId：{}, channelId：{}, ", deviceId, channelId);
            // 释放rtpserver
            WVPResult<StreamInfo> wvpResult = new WVPResult<>();
            wvpResult.setCode(ErrorCode.ERROR100.getCode());
            wvpResult.setMsg("点播超时");
            requestMessage.setData(wvpResult);
            resultHolder.invokeAllResult(requestMessage);
            inviteStreamService.removeInviteInfoByDeviceAndChannel(InviteSessionType.PLAY, channel.getId());
            deviceChannelService.stopPlay(channel.getId());
        });

        // 录像查询以channelId作为deviceId查询
        resultHolder.put(key, uuid, result);

        playService.play(newMediaServerItem, deviceId, channelId, null, (code, msg, streamInfo) -> {
            WVPResult<StreamContentVo> wvpResult = new WVPResult<>();
            if (code == InviteErrorCode.SUCCESS.getCode()) {
                wvpResult.setCode(ErrorCode.SUCCESS.getCode());
                wvpResult.setMsg(ErrorCode.SUCCESS.getMsg());

                if (streamInfo != null) {
                    if (userSetting.getUseSourceIpAsStreamIp()) {
                        streamInfo = streamInfo.clone();// 深拷贝
                        String host;
                        try {
                            URL url = new URL(request.getRequestURL().toString());
                            host = url.getHost();
                        } catch (MalformedURLException e) {
                            host = request.getLocalAddr();
                        }
                        streamInfo.channgeStreamIp(host);
                    }
                    if (!ObjectUtils.isEmpty(newMediaServerItem.getTranscodeSuffix()) && !"null".equalsIgnoreCase(newMediaServerItem.getTranscodeSuffix())) {
                        streamInfo.setStream(streamInfo.getStream() + "_" + newMediaServerItem.getTranscodeSuffix());
                    }
                    wvpResult.setData(new StreamContentVo(streamInfo));
                } else {
                    wvpResult.setCode(code);
                    wvpResult.setMsg(msg);
                }
            } else {
                wvpResult.setCode(code);
                wvpResult.setMsg(msg);
            }
            requestMessage.setData(wvpResult);
            // 此处必须释放所有请求
            resultHolder.invokeAllResult(requestMessage);
        });
        return result;
    }

    @Operation(summary = "停止点播")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @Parameter(name = "channelId", description = "通道国标编号", required = true)
    @GetMapping("/stop/{deviceId}/{channelId}")
    public JSONObject playStop(@PathVariable String deviceId, @PathVariable String channelId) {

        log.debug(String.format("设备预览/回放停止API调用，streamId：%s_%s", deviceId, channelId));

        if (deviceId == null || channelId == null) {
            throw new ServiceException("参数或方法错误");
        }

        Device device = deviceService.getDeviceByDeviceId(deviceId);
        DeviceChannel channel = deviceChannelService.getOneForSource(deviceId, channelId);
        Assert.notNull(device, "设备不存在");
        Assert.notNull(channel, "通道不存在");
        String streamId = String.format("%s_%s", device.getDeviceId(), channel.getDeviceId());
        playService.stop(InviteSessionType.PLAY, device, channel, streamId);
        JSONObject json = new JSONObject();
        json.put("deviceId", deviceId);
        json.put("channelId", channelId);
        return json;
    }

    /**
     * 结束转码
     */
    @Operation(summary = "结束转码")
    @Parameter(name = "key", description = "视频流key", required = true)
    @Parameter(name = "mediaServerId", description = "流媒体服务ID", required = true)
    @PostMapping("/convertStop/{key}")
    public void playConvertStop(@PathVariable String key, String mediaServerId) {
        if (mediaServerId == null) {
            throw new ServiceException( "流媒体：" + mediaServerId + "不存在");
        }
        MediaServer mediaInfo = mediaServerService.getOne(mediaServerId);
        if (mediaInfo == null) {
            throw new ServiceException("使用的流媒体已经停止运行");
        } else {
            Boolean deleted = mediaServerService.delFFmpegSource(mediaInfo, key);
            if (!deleted) {
                throw new ServiceException("失败");
            }
        }
    }

    @Operation(summary = "语音广播命令")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @Parameter(name = "deviceId", description = "通道国标编号", required = true)
    @Parameter(name = "timeout", description = "推流超时时间(秒)", required = true)
    @GetMapping("/broadcast/{deviceId}/{channelId}")
    @PostMapping("/broadcast/{deviceId}/{channelId}")
    public AudioBroadcastVo broadcastApi(@PathVariable String deviceId, @PathVariable String channelId, Integer timeout, Boolean broadcastMode) {
        if (log.isDebugEnabled()) {
            log.debug("语音广播API调用");
        }
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        if (device == null) {
            throw new ServiceException( "未找到设备： " + deviceId);
        }
        DeviceChannel channel = deviceChannelService.getOne(deviceId, channelId);
        if (channel == null) {
            throw new ServiceException( "未找到通道： " + channelId);
        }

        return playService.audioBroadcast(device, channel, broadcastMode);

    }

    @Operation(summary = "停止语音广播")
    @Parameter(name = "deviceId", description = "设备Id", required = true)
    @Parameter(name = "channelId", description = "通道Id", required = true)
    @GetMapping("/broadcast/stop/{deviceId}/{channelId}")
    @PostMapping("/broadcast/stop/{deviceId}/{channelId}")
    public void stopBroadcast(@PathVariable String deviceId, @PathVariable String channelId) {
        if (log.isDebugEnabled()) {
            log.debug("停止语音广播API调用");
        }
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        Assert.notNull(device, "设备不存在");
        DeviceChannel channel = deviceChannelService.getOne(deviceId, channelId);
        Assert.notNull(channel, "通道不存在");
        playService.stopAudioBroadcast(device, channel);
    }

    @Operation(summary = "获取所有的ssrc")
    @GetMapping("/ssrc")
    public JSONObject getSSRC() {
        if (log.isDebugEnabled()) {
            log.debug("获取所有的ssrc");
        }
        JSONArray objects = new JSONArray();
        List<SsrcTransaction> allSsrc = sessionManager.getAll();
        for (SsrcTransaction transaction : allSsrc) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("deviceId", transaction.getDeviceId());
            jsonObject.put("channelId", transaction.getChannelId());
            jsonObject.put("ssrc", transaction.getSsrc());
            jsonObject.put("streamId", transaction.getStream());
            objects.add(jsonObject);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", objects);
        jsonObject.put("count", objects.size());
        return jsonObject;
    }

    @Operation(summary = "获取截图")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @Parameter(name = "channelId", description = "通道国标编号", required = true)
    @GetMapping("/snap")
    public DeferredResult<String> getSnap(String deviceId, String channelId) {
        if (log.isDebugEnabled()) {
            log.debug("获取截图: {}/{}", deviceId, channelId);
        }

        DeferredResult<String> result = new DeferredResult<>(3 * 1000L);
        String key = DeferredResultHolder.CALLBACK_CMD_SNAP + deviceId;
        String uuid = UUID.randomUUID().toString();
        resultHolder.put(key, uuid, result);

        RequestMessage message = new RequestMessage();
        message.setKey(key);
        message.setId(uuid);

        String fileName = deviceId + "_" + channelId + "_" + DateUtil.getNowForUrl() + ".jpg";
        playService.getSnap(deviceId, channelId, fileName, (code, msg, data) -> {
            if (code == InviteErrorCode.SUCCESS.getCode()) {
                message.setData(data);
            } else {
                message.setData(WVPResult.fail(code, msg));
            }
            resultHolder.invokeResult(message);
        });
        return result;
    }

}

