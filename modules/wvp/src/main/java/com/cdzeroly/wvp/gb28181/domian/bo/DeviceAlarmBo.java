package com.cdzeroly.wvp.gb28181.domian.bo;

import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author : MGARY
 * @description : 报警信息管理
 * @createDate : 2024/12/12 11:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceAlarmBo extends BaseEntity {


    @Schema(description = "设备的国标编号")
    private String deviceId;

    @Schema(description = "报警级别, 1为一级警情, 2为二级警情, 3为三级警情, 4为四级警情")
    private String alarmPriority;

    /**
     * 报警方式 , 1为电话报警, 2为设备报警, 3为短信报警, 4为 GPS报警, 5为视频报警, 6为设备故障报警,
     * 7其他报警;可以为直接组合如12为电话报警或 设备报警-
     */
    @Schema(description = "报警方式 , 1为电话报警, 2为设备报警, 3为短信报警, 4为 GPS报警, 5为视频报警, 6为设备故障报警,\n" +
        "\t * 7其他报警;可以为直接组合如12为电话报警或设备报警")
    private String alarmMethod;

    /**
     * 报警类型,
     * 报警方式为2时,不携带 AlarmType为默认的报警设备报警,
     * 携带 AlarmType取值及对应报警类型如下:
     * 1-视频丢失报警;
     * 2-设备防拆报警;
     * 3-存储设备磁盘满报警;
     * 4-设备高温报警;
     * 5-设备低温报警。
     * 报警方式为5时,取值如下:
     * 1-人工视频报警;
     * 2-运动目标检测报警;
     * 3-遗留物检测报警;
     * 4-物体移除检测报警;
     * 5-绊线检测报警;
     * 6-入侵检测报警;
     * 7-逆行检测报警;
     * 8-徘徊检测报警;
     * 9-流量统计报警;
     * 10-密度检测报警;
     * 11-视频异常检测报警;
     * 12-快速移动报警。
     * 报警方式为6时,取值下:
     * 1-存储设备磁盘故障报警;
     * 2-存储设备风扇故障报警。
     */
    @Schema(description = "报警类型")
    private String alarmType;

    @Schema(description = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @Schema(description = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

}
