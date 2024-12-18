package com.cdzeroly.wvp.service.domian.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cdzeroly.common.tenant.core.TenantEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author MGARY
 */
@Setter
@Getter
@TableName("wvp_record_plan")
@Schema(description = "录制计划")
public class RecordPlan  extends TenantEntity {

    @Schema(description = "计划数据库ID")
    @TableId(value = "id")
    private int id;

    @Schema(description = "计划名称")
    private String name;

    @Schema(description = "计划关联通道数量")
    @TableField(exist = false)
    private int channelCount;

    @Schema(description = "是否开启定时截图")
    private Boolean snap;



}
