package com.cdzeroly.wvp.gb28181.domian.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 直播流关联国标上级平台
 * @author lin
 */
@Getter
@Setter
@Schema(description = "直播流关联国标上级平台")
public class GbStream extends PlatformGbStream{

    @Schema(description = "ID")
    private int gbStreamId;
    @Schema(description = "应用名")
    private String app;
    @Schema(description = "流ID")
    private String stream;
    @Schema(description = "国标ID")
    private String gbId;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "流媒体ID")
    private String mediaServerId;
    @Schema(description = "经度")
    private double longitude;
    @Schema(description = "纬度")
    private double latitude;
    @Schema(description = "流类型（拉流/推流）")
    private String streamType;
    @Schema(description = "状态")
    private boolean status;

    @Schema(description = "创建时间")
    public String createTime;

    @Override
    public Integer getGbStreamId() {
        return gbStreamId;
    }

    @Override
    public void setGbStreamId(Integer gbStreamId) {
        this.gbStreamId = gbStreamId;
    }




}
