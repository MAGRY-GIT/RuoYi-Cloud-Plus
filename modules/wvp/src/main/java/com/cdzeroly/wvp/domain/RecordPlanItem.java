package com.cdzeroly.wvp.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author MGARY
 */
@Data
@Schema(description = "录制计划项")
@TableName("wvp_record_plan_item")
public class RecordPlanItem {

    @Schema(description = "计划项数据库ID")
    @TableId(value = "id")
    private Long id;

    @Schema(description = "计划开始时间的序号， 从0点开始，每半个小时增加1")
    private Integer start;

    @Schema(description = "计划结束时间的序号， 从0点开始，每半个小时增加1")
    private Integer stop;

    @Schema(description = "计划周几执行")
    private Integer weekDay;

    @Schema(description = "所属计划ID")
    private Long planId;

}
