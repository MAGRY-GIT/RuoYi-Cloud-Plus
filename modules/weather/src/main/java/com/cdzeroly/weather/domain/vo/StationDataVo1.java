package com.cdzeroly.weather.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.weather.domain.StationData;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 地面站点数据视图对象 station_data
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = StationData.class)
public class StationDataVo1 implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 区站号
     */
    @ExcelProperty(value = "区站号")
    private Long stationId;


    /**
     * 区站名称
     */
    @ExcelProperty(value = "区站名称")
    private String stationName;
    /**
     * 气压
     */
    @ExcelProperty(value = "气压")
    private Long prs;

    /**
     * 海平面气压
     */
    @ExcelProperty(value = "海平面气压")
    private Long prsSea;

    /**
     * 最高气压
     */
    @ExcelProperty(value = "最高气压")
    private Long prsMax;

    /**
     * 最低气压
     */
    @ExcelProperty(value = "最低气压")
    private Long prsMin;

    /**
     * 最大风速
     */
    @ExcelProperty(value = "最大风速")
    private Long winSMax;

    /**
     * 极大风速
     */
    @ExcelProperty(value = "极大风速")
    private Long winSInstMax;

    /**
     * 极大风速的风向
     */
    @ExcelProperty(value = "极大风速的风向")
    private Long winDInstMax;

    /**
     * 2分钟平均风向
     */
    @ExcelProperty(value = "2分钟平均风向")
    private Long winDAvg2mi;

    /**
     * 2分钟平均风速
     */
    @ExcelProperty(value = "2分钟平均风速")
    private Long winSAvg2mi;

    /**
     * 最大风速的风向
     */
    @ExcelProperty(value = "最大风速的风向")
    private Long winDSMax;

    /**
     * 温度
     */
    @ExcelProperty(value = "温度")
    private Double tem;

    /**
     * 最高气温
     */
    @ExcelProperty(value = "最高气温")
    private Long temMax;

    /**
     * 最低气温
     */
    @ExcelProperty(value = "最低气温")
    private Long temMin;

    /**
     * 相对湿度
     */
    @ExcelProperty(value = "相对湿度")
    private Long rhu;

    /**
     * 最小相对湿度
     */
    @ExcelProperty(value = "最小相对湿度")
    private Long rhuMin;

    /**
     * 过去3小时降水量
     */
    @ExcelProperty(value = "过去3小时降水量")
    private Long pre3h;


}
