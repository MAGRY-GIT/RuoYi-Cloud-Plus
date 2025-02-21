package com.cdzeroly.wvp.i1.bean;

import com.cdzeroly.wvp.enums.VideoFormatEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 短视频采集参数设置报 数据包
 *
 * @author MAGRY
 */
@Setter
@Getter
public class VideoCaptureSettingsDto {

    // 通道号
    private byte channelNo;

    // 参数配置类型标识：00H 查询配置信息，01H 设置配置信息
    private byte requestSetFlag;

    // 视频格式
    private VideoFormatEnum videoFormat;

    // 视频录制时间（单位为秒）
    private int videoTime;


    public VideoCaptureSettingsDto() {
    }


}
