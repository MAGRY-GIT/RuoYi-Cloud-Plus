package com.cdzeroly.wvp.domain.vo;

import com.cdzeroly.wvp.domain.WvpScreenshotPlan;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 截图计划视图对象 wvp_screenshot_plan
 *
 * @author MGARY
 * @date 2025-02-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WvpScreenshotPlan.class)
public class WvpScreenshotPlanVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 表达式
     */
    @ExcelProperty(value = "表达式")
    private String cron;

    /**
     * 计划名称
     */
    @ExcelProperty(value = "计划名称")
    private String name;


}
