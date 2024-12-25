package com.cdzeroly.wvp.vmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.core.utils.AssertUtils;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.wvp.conf.SipConfig;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import com.cdzeroly.wvp.gb28181.service.IDeviceService;
import com.cdzeroly.wvp.media.domian.bean.MediaInfo;
import com.cdzeroly.wvp.media.domian.MediaServer;
import com.cdzeroly.wvp.media.domian.bo.MediaServerBo;
import com.cdzeroly.wvp.media.event.mediaServer.MediaServerChangeEvent;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.service.domian.vo.MediaServerLoadVo;
import com.cdzeroly.wvp.streamProxy.service.IStreamProxyService;
import com.cdzeroly.wvp.streamPush.service.IStreamPushService;
import com.cdzeroly.wvp.vmanager.bean.ResourceBaseInfo;
import com.cdzeroly.wvp.vmanager.bean.vo.ResourceInfoVo;
import com.cdzeroly.wvp.vmanager.bean.vo.SystemConfigInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MAGRY
 */
@Tag(name = "服务控制")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mediaServer")
public class MediaServerController {


    private final IMediaServerService mediaServerService;


    private final SipConfig sipConfig;

    private final UserSetting userSetting;

    private final IDeviceService deviceService;

    private final IDeviceChannelService channelService;

    private final IStreamPushService pushService;

    private final IStreamProxyService proxyService;

    @Value("${server.port}")
    private Integer serverPort;


    private final ApplicationEventPublisher applicationEventPublisher;


    @GetMapping(value = "/list")
    @Operation(summary = "流媒体服务列表")
    public R<List<MediaServer>> getMediaServerList() {
        return R.ok(mediaServerService.getAll());
    }

    @GetMapping(value = "/online/list")
    @Operation(summary = "在线流媒体服务列表")
    public R<List<MediaServer>> getOnlineMediaServerList() {
        return  R.ok(mediaServerService.getAllOnline());
    }

    @GetMapping(value = "/one/{id}")
    @Operation(summary = "获取单个流媒体")
    @Parameter(name = "id", description = "流媒体服务ID", required = true)
    public R<MediaServer> getMediaServer(@PathVariable String id) {
        return R.ok(mediaServerService.getOne(id));
    }

    @Operation(summary = "测试流媒体服务")
    @Parameter(name = "ip", description = "流媒体服务IP", required = true)
    @Parameter(name = "port", description = "流媒体服务HTT端口", required = true)
    @Parameter(name = "secret", description = "流媒体服务secret", required = true)
    @GetMapping(value = "/check")
    public R<MediaServer>  checkMediaServer(@RequestParam String ip, @RequestParam int port, @RequestParam String secret, @RequestParam String type) {
        return R.ok(mediaServerService.checkMediaServer(ip, port, secret, type));
    }

    @Operation(summary = "测试流媒体录像管理服务")
    @Parameter(name = "ip", description = "流媒体服务IP", required = true)
    @Parameter(name = "port", description = "流媒体服务HTT端口", required = true)
    @GetMapping(value = "/record/check")
    public R<Void> checkMediaRecordServer(@RequestParam String ip, @RequestParam int port) {
        boolean checkResult = mediaServerService.checkMediaRecordServer(ip, port);
        AssertUtils.isTrue(checkResult, "连接失败");
        return  R.ok();
    }

    @Operation(summary = "保存流媒体服务")
    @Parameter(name = "mediaServerItem", description = "流媒体信息", required = true)
    @PostMapping(value = "")
    public R<Void> saveMediaServer(@RequestBody MediaServerBo bo) {

        MediaServer database = mediaServerService.getMediaServer(bo.getId());
        MediaServer mediaServer = MapstructUtils.convert(bo, MediaServer.class);
        if (database != null) {
            mediaServerService.update(mediaServer);
        } else {
            mediaServerService.add(mediaServer);
            // 发送事件
            MediaServerChangeEvent event = new MediaServerChangeEvent(this);
            event.setMediaServerItemList(mediaServer);
            applicationEventPublisher.publishEvent(event);
        }
        return  R.ok();
    }

    @Operation(summary = "移除流媒体服务")
    @Parameter(name = "id", description = "流媒体ID", required = true)
    @DeleteMapping(value = "/delete")
    public  R<Void> deleteMediaServer(@RequestParam String id) {
        MediaServer mediaServer = mediaServerService.getOne(id);
        AssertUtils.isNotNull(mediaServer, "流媒体不存在");
        mediaServerService.delete(mediaServer);
        return  R.ok();
    }

    @Operation(summary = "获取流信息")
    @Parameter(name = "app", description = "应用名", required = true)
    @Parameter(name = "stream", description = "流ID", required = true)
    @Parameter(name = "mediaServerId", description = "流媒体ID", required = true)
    @GetMapping(value = "/media_info")
    public R<MediaInfo> getMediaInfo(String app, String stream, String mediaServerId) {
        MediaServer mediaServer = mediaServerService.getOne(mediaServerId);
        AssertUtils.isNotNull(mediaServer, "流媒体不存在");
        return R.ok(mediaServerService.getMediaInfo(mediaServer, app, stream));
    }


    @GetMapping(value = "/config")
    @Operation(summary = "获取配置信息")
    @Parameter(name = "type", description = "配置类型（sip, base）", required = true)
    public JSONObject getVersion(String type) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("server.port", serverPort);
        if (ObjectUtils.isEmpty(type)) {
            jsonObject.put("sip", JSON.toJSON(sipConfig));
            jsonObject.put("base", JSON.toJSON(userSetting));
        } else {
            switch (type) {
                case "sip":
                    jsonObject.put("sip", sipConfig);
                    break;
                case "base":
                    jsonObject.put("base", userSetting);
                    break;
                default:
                    break;
            }
        }
        return jsonObject;
    }


    @GetMapping(value = "/load")
    @Operation(summary = "获取负载信息")
    public R<List<MediaServerLoadVo>> getMediaLoad() {
        List<MediaServerLoadVo> result = new ArrayList<>();
        List<MediaServer> allOnline = mediaServerService.getAllOnline();
        if (allOnline.isEmpty()) {
            return R.ok(result);
        } else {
            for (MediaServer mediaServerItem : allOnline) {
                result.add(mediaServerService.getLoad(mediaServerItem));
            }
        }
        return R.ok(result);
    }

    @Operation(summary = "获取系统配置信息")
    @GetMapping(value = "/system/configInfo")
    public R<SystemConfigInfoVo> getConfigInfo() {
        SystemConfigInfoVo systemConfigInfoVo = new SystemConfigInfoVo();
        systemConfigInfoVo.setSip(sipConfig);
        systemConfigInfoVo.setAddOn(userSetting);
        systemConfigInfoVo.setServerPort(serverPort);
        return R.ok(systemConfigInfoVo);
    }


    @GetMapping(value = "/resource/info")
    @Operation(summary = "获取负载信息")
    public R<ResourceInfoVo> getResourceInfo() {
        ResourceInfoVo result = new ResourceInfoVo();
        ResourceBaseInfo deviceInfo = deviceService.getOverview();
        result.setDevice(deviceInfo);
        ResourceBaseInfo channelInfo = channelService.getOverview();
        result.setChannel(channelInfo);
        ResourceBaseInfo pushInfo = pushService.getOverview();
        result.setPush(pushInfo);
        ResourceBaseInfo proxyInfo = proxyService.getOverview();
        result.setProxy(proxyInfo);

        return R.ok(result);
    }

}
