package com.cdzeroly.wvp.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cdzeroly.common.tenant.core.TenantEntity;
import com.cdzeroly.wvp.common.enums.ChannelDataType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author MGARY
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "推流信息")
@TableName("wvp_stream_push")
public class StreamPush extends TenantEntity {

    /**
     * id
     */
    @Schema(description = "id")
    @TableId
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

    @TableField(exist = false)
    private String uniqueKey;

    private Integer dataType = ChannelDataType.STREAM_PUSH.value;


}

