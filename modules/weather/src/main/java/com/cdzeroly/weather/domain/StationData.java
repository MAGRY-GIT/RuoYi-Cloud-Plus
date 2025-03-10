package com.cdzeroly.weather.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.cdzeroly.common.tenant.core.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 地面站点数据对象 station_data
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("station_data")
public class StationData extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;



    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 区站号
     */
    private Long stationId;

    /**
     * 气压
     */
    private Double prs;

    /**
     * 海平面气压
     */
    private Double prsSea;

    /**
     * 最高气压
     */
    private Double prsMax;

    /**
     * 最低气压
     */
    private Double prsMin;

    /**
     * 最大风速
     */
    private Double winSMax;

    /**
     * 极大风速
     */
    private Double winSInstMax;

    /**
     * 极大风速的风向
     */
    private Double winDInstMax;

    /**
     * 2分钟平均风向
     */
    private Double winDAvg2mi;

    /**
     * 2分钟平均风速
     */
    private Double winSAvg2mi;

    /**
     * 最大风速的风向
     */
    private Double winDSMax;

    /**
     * 温度
     */
    private Double tem;

    /**
     * 最高气温
     */
    private Double temMax;

    /**
     * 最低气温
     */
    private Double temMin;

    /**
     * 相对湿度
     */
    private Double rhu;

    /**
     * 最小相对湿度
     */
    private Double rhuMin;

    /**
     * 过去3小时降水量
     */
    private Double pre3h;


}
