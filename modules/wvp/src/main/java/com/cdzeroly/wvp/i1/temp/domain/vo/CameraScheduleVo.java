package com.cdzeroly.wvp.i1.temp.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.wvp.i1.temp.domain.CameraSchedule;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 摄像机定时工作时间设置视图对象 camera_schedule
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = CameraSchedule.class)
public class CameraScheduleVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 工作起始时间数组
     */
    @ExcelProperty(value = "工作起始时间数组")
    private String startTimes;

    /**
     * 工作结束时间数组
     */
    @ExcelProperty(value = "工作结束时间数组")
    private String endTimes;




}
