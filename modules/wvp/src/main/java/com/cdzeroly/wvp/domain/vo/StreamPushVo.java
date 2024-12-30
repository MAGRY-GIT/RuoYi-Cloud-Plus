package com.cdzeroly.wvp.domain.vo;

import cn.hutool.core.date.DateUtil;
import com.cdzeroly.common.core.validate.EditGroup;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.common.enums.ChannelDataType;
import com.cdzeroly.wvp.gb28181.domian.CommonGbChannel;
import com.cdzeroly.wvp.media.event.media.MediaArrivalEvent;
import com.cdzeroly.wvp.domain.StreamPush;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.ObjectUtils;


/**
 * @author MGARY
 */
@Data
@Schema(description = "推流信息")
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = StreamPush.class)
public class StreamPushVo extends CommonGbChannel implements Comparable<StreamPushVo>{

    /**
     * id
     */
    @Schema(description = "id")
    @NotNull(message = "ID不可为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 应用名
     */
    @Schema(description = "应用名")
    private String app;

    /**
     * 流id
     */
    @Schema(description = "流id")
    private String stream;

    /**
     * 使用的流媒体ID
     */
    @Schema(description = "使用的流媒体ID")
    private String mediaServerId;

    /**
     * 使用的服务ID
     */
    @Schema(description = "使用的服务ID")
    private String serverId;

    /**
     * 推流时间
     */
    @Schema(description = "推流时间")
    private String pushTime;



    /**
     * 是否正在推流
     */
    @Schema(description = "是否正在推流")
    private boolean pushing;

    /**
     * 拉起离线推流
     */
    @Schema(description = "拉起离线推流")
    private boolean startOfflinePush;

    private String uniqueKey;

    @Override
    public int compareTo( StreamPushVo streamPushVoItem) {
        return Long.valueOf(DateUtil.betweenMs(super.getCreateTime(), streamPushVoItem.getCreateTime())).intValue();
    }

    public static StreamPushVo getInstance(StreamInfo streamInfo) {
        StreamPushVo streamPushVo = new StreamPushVo();
        streamPushVo.setApp(streamInfo.getApp());
        if (streamInfo.getMediaServer() != null) {
            streamPushVo.setMediaServerId(streamInfo.getMediaServer().getId());
        }

        streamPushVo.setStream(streamInfo.getStream());
        streamPushVo.setServerId(streamInfo.getServerId());
        return streamPushVo;

    }

    public static StreamPushVo getInstance(MediaArrivalEvent event, String serverId){
        StreamPushVo streamPushVoItem = new StreamPushVo();
        streamPushVoItem.setApp(event.getApp());
        streamPushVoItem.setMediaServerId(event.getMediaServer().getId());
        streamPushVoItem.setStream(event.getStream());
        streamPushVoItem.setServerId(serverId);
        return streamPushVoItem;
    }

    public CommonGbChannel buildCommonGBChannel() {
        if (ObjectUtils.isEmpty(this.getGbDeviceId())) {
            return null;
        }
        if (ObjectUtils.isEmpty(this.getGbName())) {
            this.setGbName( app+ "-" +stream);
        }
        this.setDataType(ChannelDataType.STREAM_PROXY.value);
        this.setDataDeviceId(this.getId());
        return this;
    }


}

