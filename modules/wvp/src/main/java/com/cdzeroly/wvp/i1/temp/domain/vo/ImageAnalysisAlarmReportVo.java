package com.cdzeroly.wvp.i1.temp.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisAlarmReport;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 图像分析告警上报报视图对象 image_analysis_alarm_report
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImageAnalysisAlarmReport.class)
public class ImageAnalysisAlarmReportVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 通道号
     */
    @ExcelProperty(value = "通道号")
    private Long channelNo;

    /**
     * 预置位
     */
    @ExcelProperty(value = "预置位")
    private Long presettingNo;

    /**
     * 告警时间;
     */
    @ExcelProperty(value = "告警时间;")
    private Long alarmTime;

    /**
     * 告警目标数量
     */
    @ExcelProperty(value = "告警目标数量")
    private Long alarmTargetCount;

    /**
     * 告警目标信息
     */
    @ExcelProperty(value = "告警目标信息")
    private String alarmTargetsInfo;




}
