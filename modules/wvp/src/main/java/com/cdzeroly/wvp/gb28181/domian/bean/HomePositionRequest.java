package com.cdzeroly.wvp.gb28181.domian.bean;

import com.cdzeroly.wvp.gb28181.utils.MessageElement;
import lombok.Getter;
import lombok.Setter;

/**
 * 设备信息查询响应
 *
 * @author Y.G
 * @version 1.0
 * @date 2022/6/28 14:55
 */
@Setter
@Getter
public class HomePositionRequest {
    /**
     * 序列号
     */
    @MessageElement("SN")
    private String sn;

    @MessageElement("DeviceID")
    private String deviceId;

    @MessageElement(value = "HomePosition")
    private HomePosition homePosition;


    /**
     * 基本参数
     */
    @Setter
    @Getter
    public static class HomePosition {
        /**
         * 播放窗口长度像素值
         */
        @MessageElement("Enabled")
        protected String enabled;
        /**
         * 播放窗口宽度像素值
         */
        @MessageElement("ResetTime")
        protected String resetTime;
        /**
         * 拉框中心的横轴坐标像素值
         */
        @MessageElement("PresetIndex")
        protected String presetIndex;

    }

}
