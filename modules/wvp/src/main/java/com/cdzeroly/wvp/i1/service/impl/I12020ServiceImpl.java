package com.cdzeroly.wvp.i1.service.impl;

import com.cdzeroly.wvp.i1.NetService;
import com.cdzeroly.wvp.i1.NettyChannelManager;
import com.cdzeroly.wvp.i1.bean.*;
import com.cdzeroly.wvp.i1.packet.*;
import com.cdzeroly.wvp.i1.packet.v2020.*;
import com.cdzeroly.wvp.i1.service.I12020Service;
import com.cdzeroly.wvp.utils.BytesUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/14 16:51
 */
@Service
public class I12020ServiceImpl implements I12020Service {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Autowired
    private NetService netService;

    @Override
    public String getScreenshots(String monitoringDeviceId, byte channelNo, byte[] reserve) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> future = new CompletableFuture<>();

        GManualCaptureRequestPacket sendPacket = new GManualCaptureRequestPacket();
        byte[] monitoringDeviceIdFinal = BytesUtils.s2b(monitoringDeviceId);
        sendPacket.setMonitoringDeviceId(monitoringDeviceIdFinal);
        sendPacket.setReserve(reserve);
        sendPacket.setChannelNo(channelNo);
        netService.sendPacket(sendPacket, ack -> ack instanceof GManualCaptureRequestPacket).subscribe(ack -> {
            //成功收到数据
            //成功收到数据
            if (ack.isCommandStatus()) {
                future.complete("拍照请求发送成功");
            }
            future.complete("拍照回复未收到");

        }, Throwable::printStackTrace);
        return future.get(3000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void cameraVideoTransmissionSettings(String monitoringDeviceId, byte channelNo, byte control) {
        GCameraVideoControlPacket sendPacket = new GCameraVideoControlPacket();
        byte[] monitoringDeviceIdFinal = BytesUtils.s2b(monitoringDeviceId);
        sendPacket.setMonitoringDeviceId(monitoringDeviceIdFinal);
        sendPacket.setControl(control);
        sendPacket.setChannelNo(channelNo);
        netService.sendPacket(sendPacket);
    }

    @Override
    public GCameraSchedulePacket cameraTimerWorkScheduleSettings(String monitoringDeviceId, byte requestSetFlag, List<Integer> startTimes, List<Integer> endTimes) throws ExecutionException, InterruptedException, TimeoutException {

        GCameraSchedulePacket sendPacket = new GCameraSchedulePacket();
        byte[] monitoringDeviceIdFinal = BytesUtils.s2b(monitoringDeviceId);
        sendPacket.setMonitoringDeviceId(monitoringDeviceIdFinal);
        sendPacket.setStartTimes(startTimes);
        sendPacket.setEndTimes(endTimes);
        CompletableFuture<GCameraSchedulePacket> future = new CompletableFuture<>();

        netService.sendPacket(sendPacket, ack -> ack instanceof GCameraSchedulePacket).subscribe(ack -> {
            future.complete((GCameraSchedulePacket) ack);
        });
        return future.get(1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public PhotoTimeTableV2020 photoScheduleSettings(String monitoringDeviceId, byte requestSetFlag, PhotoTimeTableV2020 photoTimeTable) throws ExecutionException, InterruptedException, TimeoutException {
        GPhotoTimeSettingsPacket gPhotoTimeSettingsPacket = new GPhotoTimeSettingsPacket();
        BeanUtils.copyProperties(photoTimeTable, gPhotoTimeSettingsPacket);
        byte[] monitoringDeviceIdFinal = BytesUtils.s2b(monitoringDeviceId);
        gPhotoTimeSettingsPacket.setMonitoringDeviceId(monitoringDeviceIdFinal);
        gPhotoTimeSettingsPacket.setRequestSetFlag(requestSetFlag);
        CompletableFuture<PhotoTimeTableV2020> future = new CompletableFuture<>();

        netService.sendPacket(gPhotoTimeSettingsPacket, ack -> ack instanceof GPhotoTimeSettingsPacket).subscribe(ack -> {

            PhotoTimeTableV2020 photoTimeTableV2022 = new PhotoTimeTableV2020();
            BeanUtils.copyProperties(ack, photoTimeTableV2022);
            future.complete(photoTimeTableV2022);
        });
        return future.get(1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public ImageAcquisitionDto imageAcquisitionSettings(String monitoringDeviceId, byte requestSetFlag, ImageAcquisitionDto imageAcquisition) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<ImageAcquisitionDto> future = new CompletableFuture<>();

        GImageCaptureSettingsPacket imageCaptureSettingsPacket = new GImageCaptureSettingsPacket();
        BeanUtils.copyProperties(imageAcquisition, imageCaptureSettingsPacket);
        netService.sendPacket(imageCaptureSettingsPacket, ack -> ack instanceof GImageAcquisitionPacket).subscribe(ack -> {
            ImageAcquisitionDto gImageAcquisition = new ImageAcquisitionDto();
            BeanUtils.copyProperties(ack, gImageAcquisition);
            future.complete(gImageAcquisition);
        });

        return future.get(1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public ImageOSD imageOsdSettings(String monitoringDeviceId, byte requestSetFlag, ImageOSD imageOsd) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<ImageOSD> future = new CompletableFuture<>();

        GImageOSDPacket imageOSDPacket = new GImageOSDPacket();
        BeanUtils.copyProperties(imageOsd, imageOSDPacket);
        imageOSDPacket.setMonitoringDeviceId(BytesUtils.h2b(monitoringDeviceId, ""));
        imageOSDPacket.setRoquestSetFlag(requestSetFlag);
        netService.sendPacket(imageOSDPacket, ack -> ack instanceof GImageOSDPacket).subscribe(ack -> {
            ImageOSD osd = new ImageOSD();
            BeanUtils.copyProperties(ack, osd);
            future.complete(osd);
        });

        return future.get(1000, TimeUnit.MILLISECONDS);
    }


    @Override
    public VideoCaptureSettingsDto videoCaptureSettings(String monitoringDeviceId, byte requestSetFlag, VideoCaptureSettingsDto videoCaptureSettingsDto) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<VideoCaptureSettingsDto> future = new CompletableFuture<>();

        GVideoCaptureSettingsPacket gVideoCaptureSettingsPacket = new GVideoCaptureSettingsPacket();
        BeanUtils.copyProperties(videoCaptureSettingsDto, gVideoCaptureSettingsPacket);
        netService.sendPacket(gVideoCaptureSettingsPacket, ack -> ack instanceof GVideoCaptureSettingsPacket).subscribe(ack -> {
            VideoCaptureSettingsDto videoCaptureSettings = new VideoCaptureSettingsDto();
            BeanUtils.copyProperties(ack, videoCaptureSettings);
            future.complete(videoCaptureSettings);
        });

        return future.get(1000, TimeUnit.MILLISECONDS);


    }

    @Override
    public GImageAnalysisTypeQueryPacket imageAnalysisTypeQuery(String monitoringDeviceId, byte channelNo, byte[] dataSources) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<GImageAnalysisTypeQueryPacket> future = new CompletableFuture<>();

        GImageAnalysisTypeQueryPacket imageAnalysisTypeQueryPacket = new GImageAnalysisTypeQueryPacket();
        imageAnalysisTypeQueryPacket.setDataSources(dataSources);
        imageAnalysisTypeQueryPacket.setChannelNo(channelNo);
        imageAnalysisTypeQueryPacket.setMonitoringDeviceId(BytesUtils.h2b(monitoringDeviceId, ""));

        netService.sendPacket(imageAnalysisTypeQueryPacket, ack -> ack instanceof GImageAnalysisTypeQueryPacket).subscribe(ack -> {
            future.complete((GImageAnalysisTypeQueryPacket)ack);
        });

        return future.get(1000, TimeUnit.MILLISECONDS);
    }



    @Override
    public ImageAnalysisParamsDto imageAnalysisParamsQuery(String monitoringDeviceId, List<ImageAnalysisParamsQuery> imageAnalysisParamsQueryList) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<ImageAnalysisParamsDto> future = new CompletableFuture<>();

        GImageAnalysisParamsQueryPacket packet = new GImageAnalysisParamsQueryPacket(imageAnalysisParamsQueryList);
        packet.setMonitoringDeviceId(BytesUtils.h2b(monitoringDeviceId, ""));

        netService.sendPacket(packet, ack -> ack instanceof GImageAnalysisTypeQueryPacket).subscribe(ack -> {
            future.complete(((GImageAnalysisParamsQueryPacket)ack).getImageAnalysisParams());
        });

        return future.get(1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public ImageAnalysisParamsDto imageAnalysisParamsSettings(String monitoringDeviceId, ImageAnalysisParamsDto imageAnalysisParams) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<ImageAnalysisParamsDto> future = new CompletableFuture<>();

        GImageAnalysisParamsPacket packet = new GImageAnalysisParamsPacket(imageAnalysisParams);
        packet.setMonitoringDeviceId(BytesUtils.h2b(monitoringDeviceId, ""));

        netService.sendPacket(packet, ack -> ack instanceof GImageAnalysisTypeQueryPacket).subscribe(ack -> {
            future.complete(((GImageAnalysisParamsPacket)ack).getImageAnalysisParams());
        });

        return future.get(1000, TimeUnit.MILLISECONDS);

    }

    @Override
    public AlarmLinkageConfigDto alarmLinkageConfig(String monitoringDeviceId,  AlarmLinkageConfigDto alarmLinkageConfig) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<AlarmLinkageConfigDto> future = new CompletableFuture<>();

        GAlarmLinkageConfigPacket packet = new GAlarmLinkageConfigPacket();
        BeanUtils.copyProperties(alarmLinkageConfig, packet);
        packet.setMonitoringDeviceId(BytesUtils.h2b(monitoringDeviceId, ""));

        netService.sendPacket(packet, ack -> ack instanceof GAlarmLinkageConfigPacket).subscribe(ack -> {

            AlarmLinkageConfigDto linkageConfig = new AlarmLinkageConfigDto();
            BeanUtils.copyProperties(ack, linkageConfig);
            future.complete(linkageConfig);
        });

        return future.get(1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public AlarmLinkageConfigDto alarmLinkageParamsQuery(String monitoringDeviceId, List<AlarmLinkageParamsQuery> alarmLinkageParamsQueries) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<AlarmLinkageConfigDto> future = new CompletableFuture<>();

        GAlarmLinkageParamsQueryPacket packet = new GAlarmLinkageParamsQueryPacket(alarmLinkageParamsQueries);
        packet.setMonitoringDeviceId(BytesUtils.h2b(monitoringDeviceId, ""));
        netService.sendPacket(packet, ack -> ack instanceof GAlarmLinkageParamsQueryPacket).subscribe(ack -> {

            AlarmLinkageConfigDto linkageConfig = new AlarmLinkageConfigDto();
            BeanUtils.copyProperties(ack, linkageConfig);
            future.complete(linkageConfig);
        });

        return future.get(1000, TimeUnit.MILLISECONDS);
    }


}
