package com.cdzeroly.wvp.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 精简的channel信息展示，主要是选择通道的时候展示列表使用
 * @author Administrator
 */
@Getter
@Setter
@Schema(description = "精简的channel信息展示")
public class ChannelReduce {

    /**
     * deviceChannel的数据库自增ID
     */

    @Setter
    @Schema(description = "deviceChannel的数据库自增ID")
    private Long id;

    /**
     * 通道id
     */
    @Schema(description = "通道国标编号")
    private String channelId;

    /**
     * 设备id
     */
    @Schema(description = "设备国标编号")
    private String deviceId;

    /**
     * 通道名
     */
    @Schema(description = "通道名")
    private String name;

    /**
     * 生产厂商
     */
    @Schema(description = "生产厂商")
    private String manufacturer;

    /**
     * wan地址
     */
    @Schema(description = "wan地址")
    private String  hostAddress;

    /**
     * 子节点数
     */
    @Schema(description = "子节点数")
    private Integer  subCount;

    /**
     * 平台Id
     */
    @Schema(description = "平台上级国标编号")
    private String  platformId;

    /**
     * 目录Id
     */
    @Schema(description = "目录国标编号")
    private String  catalogId;



}
