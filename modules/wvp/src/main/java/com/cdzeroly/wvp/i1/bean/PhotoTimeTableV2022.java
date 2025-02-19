package com.cdzeroly.wvp.i1.bean;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/12 18:01
 */

import java.util.List;

/**
 * 拍照时间表设置 数据包
 *
 * @author MAGRY
 */

public class PhotoTimeTableV2022 {

    // 通道号
    private byte channelNo;

    // 参数配置类型标识：00H 查询配置信息，01H 设置配置信息
    private byte requestSetFlag;

    // 时1：设备拍照时间间隔小时设置
    private byte hour1;

    // 分1：设备拍照时间间隔分钟设置
    private byte minute1;

    // 时2：设备开始工作时间小时设置
    private byte hour2;

    // 分2：设备开始工作时间分钟设置
    private byte minute2;

    // 时3：设备结束工作时间小时设置
    private byte hour3;

    // 分3：设备结束工作时间分钟设置
    private byte minute3;


}
