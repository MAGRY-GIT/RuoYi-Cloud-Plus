package com.cdzeroly.wvp.i1.temp.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.wvp.i1.temp.domain.ImageAcquisition;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 图像采集参数设置视图对象 image_acquisition
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImageAcquisition.class)
public class ImageAcquisitionVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 色彩选择
     */
    @ExcelProperty(value = "色彩选择")
    private String colorSelect;

    /**
     * 分辨率
     */
    @ExcelProperty(value = "分辨率")
    private String resolution;

    /**
     * 亮度
     */
    @ExcelProperty(value = "亮度")
    private Long luminance;

    /**
     * 对比度
     */
    @ExcelProperty(value = "对比度")
    private Long contrast;

    /**
     * 饱和度
     */
    @ExcelProperty(value = "饱和度")
    private Long saturation;

    /**
     * 创建人
     */
    @ExcelProperty(value = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 更新人
     */
    @ExcelProperty(value = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Long updatedTime;


}
