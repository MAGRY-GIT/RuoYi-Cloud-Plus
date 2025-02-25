package com.cdzeroly.wvp.i1.temp.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.wvp.i1.temp.domain.PhotoTimeSettings;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 拍照时间设置报视图对象 photo_time_settings
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PhotoTimeSettings.class)
public class PhotoTimeSettingsVo implements Serializable {

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
     * 时1
     */
    @ExcelProperty(value = "时1")
    private Long hour1;

    /**
     * 分1
     */
    @ExcelProperty(value = "分1")
    private Long minute1;

    /**
     * 时2
     */
    @ExcelProperty(value = "时2")
    private Long hour2;

    /**
     * 分2
     */
    @ExcelProperty(value = "分2")
    private Long minute2;

    /**
     * 时3
     */
    @ExcelProperty(value = "时3")
    private Long hour3;

    /**
     * 分3
     */
    @ExcelProperty(value = "分3")
    private Long minute3;




}
