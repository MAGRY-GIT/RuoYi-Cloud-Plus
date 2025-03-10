package com.cdzeroly.weather.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.cdzeroly.common.core.domain.GeoPoint;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 气象数据对象 w_ground_info
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Data
@TableName("w_ground_info")
public class GroundInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;


    /**
     * 站点主键
     */
    private Long stationId;

    /**
     * 地理坐标
     */
    private GeoPoint geoPoint;


    /**
     * 天气情况
     */
    private String wea;

    /**
     * 天气对应图标
     */
    private String weaImg;

    /**
     * 实时温度
     */
    private String tem;

    /**
     * 高温
     */
    private String tem1;

    /**
     * 低温
     */
    private String tem2;

    /**
     * 风向
     */
    private String win;

    /**
     * 风力等级
     */
    private String winSpeed;

    /**
     * 风速
     */
    private String winMeter;

    /**
     * 湿度
     */
    private String humidity;

    /**
     * 能见度
     */
    private String visibility;

    /**
     * 气压hPa
     */
    private String pressure;

    /**
     * 今日降雨量数值
     */
    private String rainPcpn;

    /**
     * 空气质量
     */
    private String air;

    /**
     * 空气质量等级
     */
    private String airLevel;

    /**
     * 空气质量描述
     */
    private String airTips;


    /**
     * 空气质量描述
     */
    private Long dataTime;
}
