package com.cdzeroly.wvp.media.zlm;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.cdzeroly.wvp.conf.task.DynamicTask;
import com.cdzeroly.wvp.gb28181.event.EventPublisher;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.event.mediaServer.MediaServerChangeEvent;
import com.cdzeroly.wvp.media.event.mediaServer.MediaServerDeleteEvent;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.media.zlm.dto.ZLMServerConfig;
import com.cdzeroly.wvp.media.zlm.event.HookZlmServerKeepaliveEvent;
import com.cdzeroly.wvp.media.zlm.event.HookZlmServerStartEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理zlm流媒体节点的状态
 *
 * @author MGARY
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ZLMMediaServerStatusManager {

    /**
     * 主要的媒体服务器
     */
    private final Map<Object, MediaServer> offlineZlmPrimaryMap = new ConcurrentHashMap<>();
    /**
     * 次要的媒体服务器
     */
    private final Map<Object, MediaServer> offlineZlmsecondaryMap = new ConcurrentHashMap<>();
    /**
     * 离线 ZLM 时间图
     */
    private final Map<Object, Long> offlineZlmTimeMap = new ConcurrentHashMap<>();

    private final ZLMRESTFullUtils zlmresTfulUtils;

    private final IMediaServerService mediaServerService;

    private final DynamicTask dynamicTask;

    @Value("${server.ssl.enabled:false}")
    private boolean sslEnabled;

    @Value("${server.port}")
    private Integer serverPort;

    @Value("${server.servlet.context-path:}")
    private String serverServletContextPath;

    private final EventPublisher eventPublisher;

    private static final String type = "zlm";

    @Async("taskExecutor")
    @EventListener
    public void onApplicationEvent(MediaServerChangeEvent event) {
        if (event.getMediaServerItemList() == null || event.getMediaServerItemList().isEmpty()) {
            return;
        }
        for (MediaServer mediaServerItem : event.getMediaServerItemList()) {
            if (!type.equals(mediaServerItem.getType())) {
                continue;
            }
            log.info("[ZLM-添加待上线节点] ID：" + mediaServerItem.getId());
            offlineZlmPrimaryMap.put(mediaServerItem.getId(), mediaServerItem);
            offlineZlmTimeMap.put(mediaServerItem.getId(), System.currentTimeMillis());
            execute();
        }
    }

    @Async("taskExecutor")
    @EventListener
    public void onApplicationEvent(HookZlmServerStartEvent event) {
        if (event.getMediaServerItem() == null || !type.equals(event.getMediaServerItem().getType()) || event.getMediaServerItem().isStatus()) {
            return;
        }
        MediaServer serverItem = mediaServerService.getOne(event.getMediaServerItem().getId());
        if (serverItem == null) {
            return;
        }
        log.info("[ZLM-HOOK事件-服务启动] ID：" + event.getMediaServerItem().getId());
        online(serverItem, null);
    }

    @Async("taskExecutor")
    @EventListener
    public void onApplicationEvent(HookZlmServerKeepaliveEvent event) {
        if (event.getMediaServer() == null) {
            return;
        }
        MediaServer serverItem = mediaServerService.getOne(event.getMediaServer().getId());
        if (serverItem == null) {
            return;
        }
        log.debug("[ZLM-HOOK事件-心跳] ID：{}", event.getMediaServer().getId());
        online(serverItem, null);
    }

    @Async("taskExecutor")
    @EventListener
    public void onApplicationEvent(MediaServerDeleteEvent event) {
        if (event.getMediaServer() == null) {
            return;
        }
        log.info("[ZLM-节点被移除] ID：" + event.getMediaServer().getId());
        offlineZlmPrimaryMap.remove(event.getMediaServer().getId());
        offlineZlmsecondaryMap.remove(event.getMediaServer().getId());
        offlineZlmTimeMap.remove(event.getMediaServer().getId());
    }

    @Scheduled(fixedDelay = 10 * 1000)   // 每隔10秒检查一次
    public void execute() {
        // 初次加入的离线节点会在30分钟内，每间隔十秒尝试一次，30分钟后如果仍然没有上线，则每隔30分钟尝试一次连接
        if (offlineZlmPrimaryMap.isEmpty() && offlineZlmsecondaryMap.isEmpty()) {
            return;
        }
        if (!offlineZlmPrimaryMap.isEmpty()) {
            for (MediaServer mediaServerItem : offlineZlmPrimaryMap.values()) {
                if (offlineZlmTimeMap.get(mediaServerItem.getId()) != null && offlineZlmTimeMap.get(mediaServerItem.getId()) < System.currentTimeMillis() - 30 * 60 * 1000) {
                    offlineZlmsecondaryMap.put(mediaServerItem.getId(), mediaServerItem);
                    offlineZlmPrimaryMap.remove(mediaServerItem.getId());
                    continue;
                }
                log.info("[ZLM-尝试连接] ID：{}, 地址： {}:{}", mediaServerItem.getId(), mediaServerItem.getIp(), mediaServerItem.getHttpPort());
                JSONObject responseJson = zlmresTfulUtils.getMediaServerConfig(mediaServerItem);
                ZLMServerConfig zlmServerConfig = null;
                if (responseJson == null) {
                    log.info("[ZLM-尝试连接]失败, ID：{}, 地址： {}:{}", mediaServerItem.getId(), mediaServerItem.getIp(), mediaServerItem.getHttpPort());
                    continue;
                }
                JSONArray data = responseJson.getJSONArray("data");
                if (data == null || data.isEmpty()) {
                    log.info("[ZLM-尝试连接]失败, ID：{}, 地址： {}:{}", mediaServerItem.getId(), mediaServerItem.getIp(), mediaServerItem.getHttpPort());
                } else {
                    zlmServerConfig = JSON.parseObject(JSON.toJSONString(data.get(0)), ZLMServerConfig.class);
                    initPort(mediaServerItem, zlmServerConfig);
                    online(mediaServerItem, zlmServerConfig);
                }
            }
        }
        if (!offlineZlmsecondaryMap.isEmpty()) {
            for (MediaServer mediaServerItem : offlineZlmsecondaryMap.values()) {
                if (offlineZlmTimeMap.get(mediaServerItem.getId()) < System.currentTimeMillis() - 30 * 60 * 1000) {
                    continue;
                }
                log.info("[ZLM-尝试连接] ID：{}, 地址： {}:{}", mediaServerItem.getId(), mediaServerItem.getIp(), mediaServerItem.getHttpPort());
                JSONObject responseJson = zlmresTfulUtils.getMediaServerConfig(mediaServerItem);
                ZLMServerConfig zlmServerConfig = null;
                if (responseJson == null) {
                    log.info("[ZLM-尝试连接]失败, ID：{}, 地址： {}:{}", mediaServerItem.getId(), mediaServerItem.getIp(), mediaServerItem.getHttpPort());
                    offlineZlmTimeMap.put(mediaServerItem.getId(), System.currentTimeMillis());
                    continue;
                }
                JSONArray data = responseJson.getJSONArray("data");
                if (data == null || data.isEmpty()) {
                    log.info("[ZLM-尝试连接]失败, ID：{}, 地址： {}:{}", mediaServerItem.getId(), mediaServerItem.getIp(), mediaServerItem.getHttpPort());
                    offlineZlmTimeMap.put(mediaServerItem.getId(), System.currentTimeMillis());
                } else {
                    zlmServerConfig = JSON.parseObject(JSON.toJSONString(data.get(0)), ZLMServerConfig.class);
                    initPort(mediaServerItem, zlmServerConfig);
                    online(mediaServerItem, zlmServerConfig);
                }
            }
        }
    }

    /**
     * 设备上线
     *
     * @param mediaServer 媒体服务器信息
     * @param config      设备配置
     */
    private void online(MediaServer mediaServer, ZLMServerConfig config) {
        offlineZlmPrimaryMap.remove(mediaServer.getId());
        offlineZlmsecondaryMap.remove(mediaServer.getId());
        offlineZlmTimeMap.remove(mediaServer.getId());
        if (!mediaServer.isStatus()) {
            log.info("[ZLM-连接成功] ID：{}, 地址： {}:{}", mediaServer.getId(), mediaServer.getIp(), mediaServer.getHttpPort());
            mediaServer.setStatus(true);
            mediaServer.setHookAliveInterval(10F);
            mediaServerService.update(mediaServer);
            // 发送上线通知
            eventPublisher.mediaServerOnlineEventPublish(mediaServer);
            if (mediaServer.isAutoConfig()) {
                if (config == null) {
                    JSONObject json = zlmresTfulUtils.getMediaServerConfig(mediaServer);
                    JSONArray data = json.getJSONArray("data");
                    if (data != null && !data.isEmpty()) {
                        config = JSON.parseObject(JSON.toJSONString(data.get(0)), ZLMServerConfig.class);
                    }
                }
                if (config != null) {
                    initPort(mediaServer, config);
                    setZLMConfig(mediaServer, "0".equals(config.getHookEnable()) || !Objects.equals(mediaServer.getHookAliveInterval(), config.getHookAliveInterval()));
                }
            }
            mediaServerService.update(mediaServer);
        }
        // 设置两次心跳未收到则认为zlm离线
        String key = "zlm-keepalive-" + mediaServer.getId();
        dynamicTask.startDelay(key, () -> {
            log.warn("[ZLM-心跳超时] ID：{}", mediaServer.getId());
            mediaServer.setStatus(false);
            offlineZlmPrimaryMap.put(mediaServer.getId(), mediaServer);
            offlineZlmTimeMap.put(mediaServer.getId(), System.currentTimeMillis());
            // 发送离线通知
            eventPublisher.mediaServerOfflineEventPublish(mediaServer);
            mediaServerService.update(mediaServer);
        }, (int) (mediaServer.getHookAliveInterval() * 2 * 1000));
    }

    private void initPort(MediaServer mediaServerItem, ZLMServerConfig zlmServerConfig) {
        // 端口只会从配置中读取一次，一旦自己配置或者读取过了将不在配置
        if (mediaServerItem.getHttpSslPort() == 0) {
            mediaServerItem.setHttpSslPort(zlmServerConfig.getHttpSSLport());
        }
        if (mediaServerItem.getRtmpPort() == 0) {
            mediaServerItem.setRtmpPort(zlmServerConfig.getRtmpPort());
        }
        if (mediaServerItem.getRtmpSslPort() == 0) {
            mediaServerItem.setRtmpSslPort(zlmServerConfig.getRtmpSslPort());
        }
        if (mediaServerItem.getRtspPort() == 0) {
            mediaServerItem.setRtspPort(zlmServerConfig.getRtspPort());
        }
        if (mediaServerItem.getRtspSslPort() == 0) {
            mediaServerItem.setRtspSslPort(zlmServerConfig.getRtspSSlport());
        }
        if (mediaServerItem.getRtpProxyPort() == 0) {
            mediaServerItem.setRtpProxyPort(zlmServerConfig.getRtpProxyPort());
        }
        if (mediaServerItem.getFlvSslPort() == 0) {
            mediaServerItem.setFlvSslPort(zlmServerConfig.getHttpSSLport());
        }
        if (mediaServerItem.getWsFlvSslPort() == 0) {
            mediaServerItem.setWsFlvSslPort(zlmServerConfig.getHttpSSLport());
        }
        mediaServerItem.setHookAliveInterval(10F);
    }

    /**
     * 流媒体参数配置
     *
     * @param mediaServer 流媒体服务
     * @param restart     重新启动
     */
    public void setZLMConfig(MediaServer mediaServer, boolean restart) {
        log.info("[媒体服务节点] 正在设置 ：{} -> {}:{}", mediaServer.getId(), mediaServer.getIp(), mediaServer.getHttpPort());
        String protocol = sslEnabled ? "https" : "http";
        String hookPrefix = String.format("%s://%s:%s%s/index/hook", protocol, mediaServer.getHookIp(), serverPort, (serverServletContextPath == null || "/".equals(serverServletContextPath)) ? "" : serverServletContextPath);

        Map<String, Object> param = new HashMap<>();
        // -profile:v Baseline
        param.put("api.secret", mediaServer.getSecret());
        if (mediaServer.getRtspPort() != 0) {
            param.put("ffmpeg.snap", "%s -rtsp_transport tcp -i %s -y -f mjpeg -frames:v 1 %s");
        }
        param.put("hook.enable", "1");
        param.put("hook.on_flow_report", "");
        param.put("hook.on_play", String.format("%s/on_play", hookPrefix));
        param.put("hook.on_http_access", "");
        param.put("hook.on_publish", String.format("%s/on_publish", hookPrefix));
        param.put("hook.on_record_ts", "");
        param.put("hook.on_rtsp_auth", "");
        param.put("hook.on_rtsp_realm", "");
        param.put("hook.on_server_started", String.format("%s/on_server_started", hookPrefix));
        param.put("hook.on_shell_login", "");
        param.put("hook.on_stream_changed", String.format("%s/on_stream_changed", hookPrefix));
        param.put("hook.on_stream_none_reader", String.format("%s/on_stream_none_reader", hookPrefix));
        param.put("hook.on_stream_not_found", String.format("%s/on_stream_not_found", hookPrefix));
        param.put("hook.on_server_keepalive", String.format("%s/on_server_keepalive", hookPrefix));
        param.put("hook.on_send_rtp_stopped", String.format("%s/on_send_rtp_stopped", hookPrefix));
        param.put("hook.on_rtp_server_timeout", String.format("%s/on_rtp_server_timeout", hookPrefix));
        param.put("hook.on_record_mp4", String.format("%s/on_record_mp4", hookPrefix));
        param.put("hook.timeoutSec", "30");
        param.put("hook.alive_interval", mediaServer.getHookAliveInterval());
        // 推流断开后可以在超时时间内重新连接上继续推流，这样播放器会接着播放。
        // 置0关闭此特性(推流断开会导致立即断开播放器)
        // 此参数不应大于播放器超时时间
        // 优化此消息以更快的收到流注销事件
        param.put("protocol.continue_push_ms", "3000");
        // 最多等待未初始化的Track时间，单位毫秒，超时之后会忽略未初始化的Track, 设置此选项优化那些音频错误的不规范流，
        // 等zlm支持给每个rtpServer设置关闭音频的时候可以不设置此选项
        if (mediaServer.isRtpEnable() && !ObjectUtils.isEmpty(mediaServer.getRtpPortRange())) {
            param.put("rtp_proxy.port_range", mediaServer.getRtpPortRange().replace(",", "-"));
        } else {
            param.put("rtp_proxy.port", mediaServer.getRtpProxyPort());
        }

        if (!ObjectUtils.isEmpty(mediaServer.getRecordPath())) {
            File recordPathFile = new File(mediaServer.getRecordPath());
            param.put("protocol.mp4_save_path", recordPathFile.getParentFile().getPath());
            param.put("protocol.downloadRoot", recordPathFile.getParentFile().getPath());
            param.put("record.appName", recordPathFile.getName());
        }

        JSONObject jsonObject = zlmresTfulUtils.setServerConfig(mediaServer, param);

        if (jsonObject != null && jsonObject.getInteger("code") == 0) {
            if (restart) {
                log.info("[媒体服务节点] 设置成功,开始重启以保证配置生效 {} -> {}:{}", mediaServer.getId(), mediaServer.getIp(), mediaServer.getHttpPort());
                zlmresTfulUtils.restartServer(mediaServer);
            } else {
                log.info("[媒体服务节点] 设置成功 {} -> {}:{}", mediaServer.getId(), mediaServer.getIp(), mediaServer.getHttpPort());
            }
        } else {
            log.info("[媒体服务节点] 设置媒体服务节点失败 {} -> {}:{}", mediaServer.getId(), mediaServer.getIp(), mediaServer.getHttpPort());
        }
    }

}
