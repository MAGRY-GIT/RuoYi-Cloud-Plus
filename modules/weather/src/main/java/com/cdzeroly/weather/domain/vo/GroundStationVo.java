package com.cdzeroly.weather.domain.vo;

import com.cdzeroly.common.core.domain.GeoPoint;
import com.cdzeroly.weather.domain.GroundStation;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 中国地面气象站点视图对象 w_ground_station
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GroundStation.class)
public class GroundStationVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 区站号
     */
    @ExcelProperty(value = "区站号")
    private Long id;

    /**
     * 省份
     */
    @ExcelProperty(value = "省份")
    private String province;

    /**
     * 站名
     */
    @ExcelProperty(value = "站名")
    private String stationName;

    /**
     * 站类
     */
    @ExcelProperty(value = "站类")
    private String stationType;

    /**
     * 经度
     */
    @ExcelProperty(value = "经度")
    private Long longitude;

    /**
     * 纬度
     */
    @ExcelProperty(value = "纬度")
    private Long latitude;

    /**
     * 地理坐标
     */
    @ExcelProperty(value = "地理坐标")
    private GeoPoint geoPoint;

    /**
     * 坐标类型
     */
    @ExcelProperty(value = "坐标类型")
    private String geoType;

    /**
     * 观测场拔海高度（米）
     */
    @ExcelProperty(value = "观测场拔海高度", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "米=")
    private Long obsSeaLevel;

    /**
     * 气压传感器拔海高度（米）
     */
    @ExcelProperty(value = "气压传感器拔海高度", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "米=")
    private Long apSensorSeaLevel;


}
