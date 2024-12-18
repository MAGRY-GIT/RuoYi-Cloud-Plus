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
public class DragZoomRequest {
    /**
     * 序列号
     */
    @MessageElement("SN")
    private String sn;

    @MessageElement("DeviceID")
    private String deviceId;

    @MessageElement(value = "DragZoomIn")
    private DragZoom dragZoomIn;

    @MessageElement(value = "DragZoomOut")
    private DragZoom dragZoomOut;

    /**
     * 基本参数
     */
    @Setter
    @Getter
    public static class DragZoom {
        /**
         * 播放窗口长度像素值
         */
        @MessageElement("Length")
        protected Integer length;
        /**
         * 播放窗口宽度像素值
         */
        @MessageElement("Width")
        protected Integer width;
        /**
         * 拉框中心的横轴坐标像素值
         */
        @MessageElement("MidPointX")
        protected Integer midPointX;
        /**
         * 拉框中心的纵轴坐标像素值
         */
        @MessageElement("MidPointY")
        protected Integer midPointY;
        /**
         * 拉框长度像素值
         */
        @MessageElement("LengthX")
        protected Integer lengthX;
        /**
         * 拉框宽度像素值
         */
        @MessageElement("LengthY")
        protected Integer lengthY;

    }

}
