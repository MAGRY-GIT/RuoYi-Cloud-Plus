package com.cdzeroly.weather.domain.vo;

import com.cdzeroly.weather.domain.GroundInfo;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 气象数据视图对象 w_ground_info
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GroundInfo.class)
public class GroundInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 乐观锁
     */
    @ExcelProperty(value = "乐观锁")
    private Long revision;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 天气情况
     */
    @ExcelProperty(value = "天气情况")
    private String wea;

    /**
     * 天气对应图标
     */
    @ExcelProperty(value = "天气对应图标")
    private String weaImg;

    /**
     * 实时温度
     */
    @ExcelProperty(value = "实时温度")
    private String tem;

    /**
     * 高温
     */
    @ExcelProperty(value = "高温")
    private String tem1;

    /**
     * 低温
     */
    @ExcelProperty(value = "低温")
    private String tem2;

    /**
     * 风向
     */
    @ExcelProperty(value = "风向")
    private String win;

    /**
     * 风力等级
     */
    @ExcelProperty(value = "风力等级")
    private String winSpeed;

    /**
     * 风速
     */
    @ExcelProperty(value = "风速")
    private String winMeter;

    /**
     * 湿度
     */
    @ExcelProperty(value = "湿度")
    private String humidity;

    /**
     * 能见度
     */
    @ExcelProperty(value = "能见度")
    private String visibility;

    /**
     * 气压hPa
     */
    @ExcelProperty(value = "气压hPa")
    private String pressure;

    /**
     * 今日降雨量数值
     */
    @ExcelProperty(value = "今日降雨量数值")
    private String rainPcpn;

    /**
     * 空气质量
     */
    @ExcelProperty(value = "空气质量")
    private String air;

    /**
     * 空气质量等级
     */
    @ExcelProperty(value = "空气质量等级")
    private String airLevel;

    /**
     * 空气质量描述
     */
    @ExcelProperty(value = "空气质量描述")
    private String airTips;


}
