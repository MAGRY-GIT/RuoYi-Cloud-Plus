package com.cdzeroly.weather.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.cdzeroly.common.core.domain.GeoPoint;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 中国地面气象站点对象 w_ground_station
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Data
@TableName("w_ground_station")
public class GroundStation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 区站号
     */
    private Long id;

    /**
     * 省份
     */
    private String province;

    /**
     * 站名
     */
    private String stationName;

    /**
     * 站类
     */
    private String stationType;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 地理坐标
     */
    private GeoPoint geoPoint;

    /**
     * 坐标类型
     */
    private String geoType;

    /**
     * 观测场拔海高度（米）
     */
    private Long obsSeaLevel;

    /**
     * 气压传感器拔海高度（米）
     */
    private Long apSensorSeaLevel;


}
