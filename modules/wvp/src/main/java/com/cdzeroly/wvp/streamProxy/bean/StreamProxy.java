package com.cdzeroly.wvp.streamProxy.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.gb28181.domian.bean.CommonGBChannelBean;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.ObjectUtils;

/**
 * @author lin
 */
@Data
@Schema(description = "拉流代理的信息")
@EqualsAndHashCode(callSuper = true)
@TableName("wvp_stream_proxy")
public class StreamProxy extends CommonGBChannelBean {

    /**
     * 数据库自增ID
     */
    @Schema(description = "数据库自增ID")
    @TableId
    private int id;

    @Schema(description = "类型，取值，default： 流媒体直接拉流（默认），ffmpeg： ffmpeg实现拉流")
    private String type;

    @Schema(description = "应用名")
    private String app;

    @Schema(description = "流ID")
    private String stream;

    @Schema(description = "流媒体服务ID")
    private String mediaServerId;

    @Schema(description = "拉流地址")
    private String srcUrl;

    @Schema(description = "超时时间:秒")
    private int timeout;

    @Schema(description = "ffmpeg模板KEY")
    private String ffmpegCmdKey;

    @Schema(description = "rtsp拉流时，拉流方式，0：tcp，1：udp，2：组播")
    private String rtspType;

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

    @Schema(description = "拉流代理时zlm返回的key，用于停止拉流代理")
    private String streamKey;

    @Schema(description = "拉流状态")
    private Boolean pulling;

    /**
     * 转换传输对象
     * @return  CommonGBChannel
     */
    public CommonGBChannel buildCommonGBChannel() {


        if (ObjectUtils.isEmpty(this.getGbDeviceId())) {
            return null;
        }
        if (ObjectUtils.isEmpty(this.getGbName())) {
            this.setGbName( app+ "-" +stream);
        }
        this.setStreamProxyId(this.getId());

        return MapstructUtils.convert(this, CommonGBChannel.class);
    }
}
