package com.cdzeroly.wvp.gb28181.domian;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cdzeroly.common.tenant.core.TenantEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 移动位置bean
 * @author lawrencehj
 * @date: 2021年1月23日
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("wvp_device_mobile_position")
public class MobilePosition extends TenantEntity {


    @Schema(description = "ID(数据库中)")
    @TableId
    private Integer id;
    /**
     * 设备Id
     */
    private String deviceId;

    /**
     * 通道Id
     */
    private Integer channelId;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 通知时间
     */
    private String time;

    /**
     * 经度
     */
    private double longitude;

    /**
     * 纬度
     */
    private double latitude;

    /**
     * 海拔高度
     */
    private double altitude;

    /**
     * 速度
     */
    private double speed;

    /**
     * 方向
     */
    private double direction;

    /**
     * 位置信息上报来源（Mobile Position、GPS Alarm）
     */
    private String reportSource;

}
