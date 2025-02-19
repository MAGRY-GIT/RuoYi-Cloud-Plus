package com.cdzeroly.wvp.i1.bean.constant;

/**
 * 帧 类 型 常量
 * @author MAGRY
 */
public interface FrameTypeConstant {
    /**
     * 监测数据报(监测装置→主站系统)
     */
    public final Byte MONITORING_DATA = 0x01;

    /**
     * 数据响应报(主站系统→监测装置)
     */
    public final Byte DATA_RESPONSE_REPORT = 0x02;

    /**
     * 控制数据报(主站系统→监测装置)
     */
    public final Byte CONTROL_DATAGRAMS = 0x03;

    /**
     * 控制响应报(监测装置→主站系统)
     */
    public final Byte CONTROL_RESPONSE_MESSAGE = 0x04;

    /**
     * 远程图像数据报(监测装置→主站系统)
     */
    public final Byte REMOTE_IMAGE_DATAGRAMS = 0x05;

    /**
     * 图像数据啊应报(主站系统→监测装置)
     */
    public final Byte IMAGE_DATA_SHOULD_BE_REPORTED = 0x06;

    /**
     * 远程图像控制报(主站系统→监测装置)
     */
    public final Byte REMOTE_IMAGE_CONTROL_NEWSPAPER = 0x07;

    /**
     * 图像控制响应报(监测装置→主站系统)
     */
    public final Byte IMAGE_CONTROL_RESPONSE_MESSAGE = 0x08;

    /**
     * 工作状态报(监测装置→主站系统)
     */
    public final Byte WORK_STATUS_REPORT = 0x09;

    /**
     * 工作状态响应报(主站系统→监测装置)
     */
    public final Byte WORK_STATUS_RESPONSE_REPORT = 0x0A;

    /**
     * 同步数据(两个监测系统的数据同步)
     */
    public final Byte SYNC_DATA = (byte) 0xFF;

}
