package com.cdzeroly.wvp.domain.bo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.zlm.dto.ZLMServerConfig;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.util.ObjectUtils;

/**
 * @author MGARY
 */
@Setter
@Getter
@Schema(description = "流媒体服务信息")
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MediaServer.class, reverseConvertGenerate = false)
public class MediaServerBo extends BaseEntity {

    @Schema(description = "ID")
    private String id;


    @Schema(description = "IP")
    private String ip;

    @Schema(description = "hook使用的IP（zlm访问WVP使用的IP）")
    private String hookIp = "127.0.0.1";

    @Schema(description = "SDP IP")
    private String sdpIp;

    @Schema(description = "流IP")
    private String streamIp;

    @Schema(description = "HTTP端口")
    private int httpPort;

    @Schema(description = "HTTPS端口")
    private int httpSslPort;

    @Schema(description = "RTMP端口")
    private int rtmpPort;

    @Schema(description = "flv端口")
    private int flvPort;

    @Schema(description = "https-flv_SSL端口")
    private int flvSslPort;

    @Schema(description = "ws-flv端口")
    private int wsFlvPort;

    @Schema(description = "wss-flv_SSL端口")
    private int wsFlvSslPort;

    @Schema(description = "RTMP_SSL端口")
    private int rtmpSslPort;

    @Schema(description = "RTP收流端口（单端口模式有用）")
    private int rtpProxyPort;

    @Schema(description = "RTSP端口")
    private int rtspPort;

    @Schema(description = "RTSP_SSL端口")
    private int rtspSslPort;

    @Schema(description = "是否开启自动配置ZLM")
    private boolean autoConfig;

    @Schema(description = "ZLM鉴权参数")
    private String secret;

    @Schema(description = "keepalive hook触发间隔,单位秒")
    private Float hookAliveInterval;

    @Schema(description = "是否使用多端口模式")
    private boolean rtpEnable;

    @Schema(description = "状态")
    @TableField(exist = false)
    private boolean status;

    @Schema(description = "多端口RTP收流端口范围")
    private String rtpPortRange;

    @Schema(description = "RTP发流端口范围")
    private String sendRtpPortRange;

    @Schema(description = "assist服务端口")
    private int recordAssistPort;


    @Schema(description = "上次心跳时间")
    @TableField(exist = false)
    private String lastKeepaliveTime;

    @Schema(description = "是否是默认ZLM")
    private boolean defaultServer;

    @Schema(description = "录像存储时长")
    private int recordDay;

    @Schema(description = "录像存储路径")
    private String recordPath;
    @Schema(description = "类型： zlm/abl")
    private String type;

    @Schema(description = "转码的前缀")
    private String transcodeSuffix;



    public MediaServerBo() {
    }

    public MediaServerBo(ZLMServerConfig zlmServerConfig, String sipIp) {
        id = zlmServerConfig.getGeneralMediaServerId();
        ip = zlmServerConfig.getIp();
        hookIp = ObjectUtils.isEmpty(zlmServerConfig.getHookIp())? sipIp: zlmServerConfig.getHookIp();
        sdpIp = ObjectUtils.isEmpty(zlmServerConfig.getSdpIp())? zlmServerConfig.getIp(): zlmServerConfig.getSdpIp();
        streamIp = ObjectUtils.isEmpty(zlmServerConfig.getStreamIp())? zlmServerConfig.getIp(): zlmServerConfig.getStreamIp();
        httpPort = zlmServerConfig.getHttpPort();
        flvPort = zlmServerConfig.getHttpPort();
        wsFlvPort = zlmServerConfig.getHttpPort();
        httpSslPort = zlmServerConfig.getHttpSSLport();
        flvSslPort = zlmServerConfig.getHttpSSLport();
        wsFlvSslPort = zlmServerConfig.getHttpSSLport();
        rtmpPort = zlmServerConfig.getRtmpPort();
        rtmpSslPort = zlmServerConfig.getRtmpSslPort();
        rtpProxyPort = zlmServerConfig.getRtpProxyPort();
        rtspPort = zlmServerConfig.getRtspPort();
        rtspSslPort = zlmServerConfig.getRtspSSlport();
        // 默认值true;
        autoConfig = true;
        secret = zlmServerConfig.getApiSecret();
        hookAliveInterval = zlmServerConfig.getHookAliveInterval();
        // 默认使用单端口;直到用户自己设置开启多端口
        rtpEnable = false;
        // 默认使用30000,30500作为级联时发送流的端口号
        rtpPortRange = zlmServerConfig.getPortRange().replace("_",",");
        // 默认关闭
        recordAssistPort = 0;
        transcodeSuffix = zlmServerConfig.getTranscodeSuffix();

    }

}
