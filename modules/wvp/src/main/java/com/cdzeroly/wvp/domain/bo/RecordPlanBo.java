package com.cdzeroly.wvp.domain.bo;

import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.cdzeroly.wvp.domain.RecordPlan;
import com.cdzeroly.wvp.domain.RecordPlanItem;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * @author MGARY
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = RecordPlan.class, reverseConvertGenerate = false)
public class RecordPlanBo extends BaseEntity {

    @Schema(description = "计划数据库ID")
    private Long id;

    @Schema(description = "计划名称")
    private String name;

    @Schema(description = "计划关联通道数量")
    private int channelCount;

    @Schema(description = "是否开启定时截图")
    private Boolean snap;



    @Schema(description = "计划内容")
    private List<RecordPlanItem> planItemList;
}
