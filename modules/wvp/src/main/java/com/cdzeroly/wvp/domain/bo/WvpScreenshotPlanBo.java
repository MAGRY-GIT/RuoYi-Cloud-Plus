package com.cdzeroly.wvp.domain.bo;

import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
import com.cdzeroly.wvp.domain.WvpScreenshotPlan;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 截图计划业务对象 wvp_screenshot_plan
 *
 * @author MGARY
 * @date 2025-02-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WvpScreenshotPlan.class, reverseConvertGenerate = false)
public class WvpScreenshotPlanBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 表达式
     */
    @NotBlank(message = "表达式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String cron;

    /**
     * 计划名称
     */
    @NotBlank(message = "计划名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;


}
