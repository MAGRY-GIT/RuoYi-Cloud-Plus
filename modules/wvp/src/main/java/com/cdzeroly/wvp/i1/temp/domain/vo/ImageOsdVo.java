package com.cdzeroly.wvp.i1.temp.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.wvp.i1.temp.domain.ImageOsd;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 图像OSD查询/设置报视图对象 image_osd
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImageOsd.class)
public class ImageOsdVo implements Serializable {

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
     * 是否显示时间
     */
    @ExcelProperty(value = "是否显示时间")
    private Long showTime;

    /**
     * 文本显示
     */
    @ExcelProperty(value = "文本显示")
    private Long showText;

    /**
     * 文本内容
     */
    @ExcelProperty(value = "文本内容")
    private String textContent;




}
