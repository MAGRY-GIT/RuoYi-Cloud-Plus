package com.cdzeroly.wvp.i1.service;

import com.cdzeroly.wvp.i1.bean.*;
import com.cdzeroly.wvp.i1.packet.GCameraSchedulePacket;
import com.cdzeroly.wvp.i1.packet.v2020.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 2020版协议
 * @author MAGRY
 */
public interface I12020Service {
    /**
     * 根据监听设备ID获取照片/短视频
     * @param monitoringDeviceId 监测设备ID
     * @return  路径
     */
    String getScreenshots(String monitoringDeviceId,byte channelNo, byte[] reserve) throws ExecutionException, InterruptedException, TimeoutException;

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
    PhotoTimeTableV2020 photoScheduleSettings(String monitoringDeviceId, byte requestSetFlag, PhotoTimeTableV2020 photoTimeTable) throws ExecutionException, InterruptedException, TimeoutException;



    /**
     * 图像采集参数查询/设置
     * @param monitoringDeviceId  设备ID
     * @param requestSetFlag  ① 00H 查询配置信息 ② 01H 设置配置信息
     */
    ImageAcquisitionDto imageAcquisitionSettings(String monitoringDeviceId, byte requestSetFlag, ImageAcquisitionDto imageAcquisition) throws ExecutionException, InterruptedException, TimeoutException;



    /**
     * 短视频采集参数设置报
     * @param monitoringDeviceId  设备ID
     * @param requestSetFlag  ① 00H 查询配置信息 ② 01H 设置配置信息
     */
    VideoCaptureSettingsDto videoCaptureSettings(String monitoringDeviceId, byte requestSetFlag, VideoCaptureSettingsDto imageAcquisition) throws ExecutionException, InterruptedException, TimeoutException;



    /**
     * 图像OSD查询/设置
     * @param monitoringDeviceId 设备ID
     * @param requestSetFlag ① 00H 查询配置信息 ② 01H 设置配置信息
     * @return 响应结果
     */
    ImageOSD imageOsdSettings(String monitoringDeviceId, byte requestSetFlag, ImageOSD imageOsd) throws ExecutionException, InterruptedException, TimeoutException;

    /**
     * 图像分析类型查询
     *
     * @param monitoringDeviceId 设备ID
     * @return 响应结果
     */
    GImageAnalysisTypeQueryPacket imageAnalysisTypeQuery(String monitoringDeviceId,  byte channelNo, byte[] dataSources) throws ExecutionException, InterruptedException, TimeoutException;

    /**
     * 图像分析参数查询报
     *
     * @param monitoringDeviceId 设备ID
     * @return 响应结果
     */
    ImageAnalysisParamsDto imageAnalysisParamsQuery(String monitoringDeviceId, List<ImageAnalysisParamsQuery> imageAnalysisParamsQueryList) throws ExecutionException, InterruptedException, TimeoutException;


    /**
     * 图像分析参数设置报
     *
     * @param monitoringDeviceId 设备ID
     * @return 响应结果
     */
    ImageAnalysisParamsDto imageAnalysisParamsSettings(String monitoringDeviceId, ImageAnalysisParamsDto imageAnalysisParams) throws ExecutionException, InterruptedException, TimeoutException;


    /**
     * 监拍装置告警联动参数配置报
     * @param monitoringDeviceId 设备ID
     * @return 响应结果
     */
    AlarmLinkageConfigDto alarmLinkageConfig(String monitoringDeviceId, AlarmLinkageConfigDto imageAnalysisParams) throws ExecutionException, InterruptedException, TimeoutException;


    /**
     * 监拍装置告警联动参数查询报
     * @param monitoringDeviceId 设备ID
     * @return 响应结果
     */
    AlarmLinkageConfigDto alarmLinkageParamsQuery(String monitoringDeviceId, List<AlarmLinkageParamsQuery> alarmLinkageParamsQueries) throws ExecutionException, InterruptedException, TimeoutException;




}

