package com.cdzeroly.wvp.service.domian.vo;

import com.cdzeroly.wvp.service.domian.bean.RecordPlan;
import com.cdzeroly.wvp.service.domian.bean.RecordPlanItem;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author MGARY
 */
@Getter
@Setter
@Data
@AutoMapper(target = RecordPlan.class)
public class RecordPlanVo implements Serializable {

    @Schema(description = "计划数据库ID")
    private int id;

    @Schema(description = "计划名称")
    private String name;

    @Schema(description = "计划关联通道数量")
    private int channelCount;

    @Schema(description = "是否开启定时截图")
    private Boolean snap;



    @Schema(description = "计划内容")
    private List<RecordPlanItem> planItemList;
}
