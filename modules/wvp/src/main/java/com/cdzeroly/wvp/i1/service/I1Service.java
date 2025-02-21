package com.cdzeroly.wvp.i1.service;

import com.cdzeroly.wvp.i1.bean.ImageAcquisitionDto;
import com.cdzeroly.wvp.i1.bean.PhotoTimeTable;
import com.cdzeroly.wvp.i1.packet.GCameraSchedulePacket;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author MAGRY
 */
public interface I1Service {
    /**
     * 根据监听设备ID获取截图
     * @param monitoringDeviceId 监测设备ID
     * @return  路径
     */
    String getScreenshots(String monitoringDeviceId,byte channelNo,byte presettingNo);

    /**
     * 相机视频传输设置
     * @param monitoringDeviceId 监测设备ID
     */
       void cameraVideoTransmissionSettings(String monitoringDeviceId, byte channelNo, byte control);

    /**
     * 摄像机定时工作时间表设置
     * @param monitoringDeviceId  设备ID
     * @param requestSetFlag  ① 00H 查询配置信息 ② 01H 设置配置信息
     * @param startTimes 开始事件  数组大小为4
     * @param endTimes  结束事件 数组大小为4
     */
    GCameraSchedulePacket cameraTimerWorkScheduleSettings(String monitoringDeviceId, byte requestSetFlag, List<Integer>  startTimes , List<Integer>  endTimes) throws ExecutionException, InterruptedException, TimeoutException;    /**


    /**
     * 拍照时间表设置
     * @param monitoringDeviceId  设备ID
     * @param requestSetFlag  ① 00H 查询配置信息 ② 01H 设置配置信息
     */
    PhotoTimeTable photoScheduleSettings(String monitoringDeviceId, byte channelNo,byte group,byte requestSetFlag, PhotoTimeTable photoTimeTable) throws ExecutionException, InterruptedException, TimeoutException;    /**


     * 图像采集参数查询/设置
     * @param monitoringDeviceId  设备ID
     * @param requestSetFlag  ① 00H 查询配置信息 ② 01H 设置配置信息
     */
    ImageAcquisitionDto imageAcquisitionSettings(String monitoringDeviceId, byte requestSetFlag, ImageAcquisitionDto imageAcquisition) throws ExecutionException, InterruptedException, TimeoutException;
}
