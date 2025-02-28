package com.cdzeroly.wvp.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.cdzeroly.common.tenant.core.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 截图计划对象 wvp_screenshot_plan
 *
 * @author MGARY
 * @date 2025-02-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wvp_screenshot_plan")
public class WvpScreenshotPlan extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 表达式
     */
    private String cron;

    /**
     * 计划名称
     */
    private String name;


}
