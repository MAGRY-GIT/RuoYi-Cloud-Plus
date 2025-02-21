package com.cdzeroly.wvp.i1.temp.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.wvp.i1.temp.domain.VideoCaptureSettings;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 短视频采集参数设置报视图对象 video_capture_settings
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = VideoCaptureSettings.class)
public class VideoCaptureSettingsVo implements Serializable {

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
    private String channelNo;

    /**
     * 视频格式
     */
    @ExcelProperty(value = "视频格式")
    private String videoFormat;

    /**
     * 视频录制时间
     */
    @ExcelProperty(value = "视频录制时间")
    private String videoTime;




}
