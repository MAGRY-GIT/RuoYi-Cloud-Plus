package com.cdzeroly.wvp.streamProxy.domain.bean;

import com.cdzeroly.wvp.streamProxy.domain.StreamProxy;
import com.cdzeroly.wvp.streamProxy.domain.bo.StreamProxyBo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lin
 */
@Data
@Schema(description = "拉流代理的信息")
public class StreamProxyParam {

    @Schema(description = "类型，取值，default： 流媒体直接拉流（默认），ffmpeg： ffmpeg实现拉流")
    private String type;

    @Schema(description = "应用名")
    private String app;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "流ID")
    private String stream;

    @Schema(description = "流媒体服务ID")
    private String mediaServerId;

    @Schema(description = "拉流地址")
    private String url;

    @Schema(description = "超时时间:秒")
    private int timeoutMs;

    @Schema(description = "ffmpeg模板KEY")
    private String ffmpegCmdKey;

    @Schema(description = "rtsp拉流时，拉流方式，0：tcp，1：udp，2：组播")
    private String rtpType;

    @Schema(description = "是否启用")
    private boolean enable;

    @Schema(description = "是否启用音频")
    private boolean enableAudio;

    @Schema(description = "是否启用MP4")
    private boolean enableMp4;

    @Schema(description = "是否 无人观看时删除")
    private boolean enableRemoveNoneReader;

    @Schema(description = "是否 无人观看时自动停用")
    private boolean enableDisableNoneReader;


    public StreamProxyBo buildStreamProxy() {
        StreamProxyBo streamProxyBo = new StreamProxyBo();
        streamProxyBo.setApp(app);
        streamProxyBo.setStream(stream);
        streamProxyBo.setMediaServerId(mediaServerId);
        streamProxyBo.setSrcUrl(url);
        streamProxyBo.setTimeout(timeoutMs/1000);
        streamProxyBo.setRtspType(rtpType);
        streamProxyBo.setEnable(enable);
        streamProxyBo.setEnableAudio(enableAudio);
        streamProxyBo.setEnableMp4(enableMp4);
        streamProxyBo.setEnableRemoveNoneReader(enableRemoveNoneReader);
        streamProxyBo.setEnableDisableNoneReader(enableDisableNoneReader);
        streamProxyBo.setFfmpegCmdKey(ffmpegCmdKey);
        streamProxyBo.setGbName(name);
        return streamProxyBo;

    }
}
