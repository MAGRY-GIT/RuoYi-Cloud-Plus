package com.cdzeroly.wvp.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author MGARY
 */
@Data
@Schema(description = "录制计划-添加/编辑参数")
public class RecordPlanDto {

    @Schema(description = "关联的通道ID")
    private List<Long> channelIds;

    @Schema(description = "关联的设备ID，会为设备下的所有通道关联此录制计划，channelId存在是此项不生效，")
    private List<Integer> deviceDbIds;

    @Schema(description = "全部关联/全部取消关联")
    private Boolean allLink;

    @Schema(description = "录制计划ID, ID为空是删除关联的计划")
    private Long planId;
}
