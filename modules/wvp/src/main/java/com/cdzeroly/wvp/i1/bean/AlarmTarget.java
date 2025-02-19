package com.cdzeroly.wvp.i1.bean;

import lombok.Data;

/**
 * 告警目标信息类
 * <p>
 * 该类用于表示单个告警目标的信息，包括告警类型、置信度和告警区域。
 *
 * @author MAGRY
 */
@Data
public class AlarmTarget {
    // 告警类型
    private byte alarmType;
    // 置信度
    private byte confidence;
    // 告警区域左上角X坐标
    private byte xTopLeft;
    // 告警区域左上角Y坐标
    private byte yTopLeft;
    // 告警区域右下角X坐标
    private byte xBottomRight;
    // 告警区域右下角Y坐标
    private byte yBottomRight;

    public AlarmTarget(byte alarmType, byte confidence, byte xTopLeft, byte yTopLeft,
                       byte xBottomRight, byte yBottomRight) {
        this.alarmType = alarmType;
        this.confidence = confidence;
        this.xTopLeft = xTopLeft;
        this.yTopLeft = yTopLeft;
        this.xBottomRight = xBottomRight;
        this.yBottomRight = yBottomRight;
    }

    /**
     * 将告警目标信息序列化为字节数组。
     *
     * @return 序列化后的字节数组
     */
    public byte[] toBytes() {
        return new byte[]{
            alarmType,
            confidence,
            xTopLeft,
            yTopLeft,
            xBottomRight,
            yBottomRight
        };
    }
}
