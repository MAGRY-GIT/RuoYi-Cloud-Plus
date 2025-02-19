package com.cdzeroly.wvp.i1.bean;

import java.util.List;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/18 15:42
 */
public class ImageAnalysisAlarmReport {
    // 通道号
    private byte channelNo;
    // 预置位号，无云台固定为255 (FFH)
    private byte presettingNo;
    // 告警时间，时间戳
    private int alarmTime;
    // 告警目标数量
    private byte alarmTargetCount;
    // 告警目标信息，每个目标占用7字节：告警类型(1字节) + 置信度(1字节) + 告警区域(4字节) + 告警区域(2字节)
    private List<AlarmTarget> alarmTargetsInfo;
}
