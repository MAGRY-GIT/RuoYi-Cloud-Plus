package com.cdzeroly.wvp.i1.temp.domain.vo;

import java.util.Date;

import com.cdzeroly.common.json.utils.JsonUtils;
import com.cdzeroly.wvp.i1.bean.TimeTable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.wvp.i1.temp.domain.PhotoTimeTable;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


/**
 * 拍照时间设置视图对象 photo_time_table
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PhotoTimeTable.class)
public class PhotoTimeTableVo implements Serializable {

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
     * 时间表
     */
    @ExcelProperty(value = "时间表")
    private String timeTables;


    /**
     * 时间表
     */
    @NotBlank(message = "时间表不能为空")
    private List<TimeTable> timeTableList;

    public List<TimeTable> getTimeTableList() {
        return JsonUtils.parseObject(timeTables, new TypeReference<>() {
        });
    }

}
