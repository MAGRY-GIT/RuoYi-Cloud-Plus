package com.cdzeroly.wvp.i1.service.impl;

import com.cdzeroly.wvp.i1.NetService;
import com.cdzeroly.wvp.i1.bean.ImageAcquisitionDto;
import com.cdzeroly.wvp.i1.bean.PhotoTimeTable;
import com.cdzeroly.wvp.i1.packet.*;
import com.cdzeroly.wvp.i1.service.I1Service;
import com.cdzeroly.wvp.utils.BytesUtils;
import com.cdzeroly.wvp.i1.NettyChannelManager;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import lombok.SneakyThrows;
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
public class I1ServiceImpl implements I1Service {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Autowired
    private NetService netService;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @SneakyThrows
    @Override
    public String getScreenshots(String monitoringDeviceId, byte channelNo, byte presettingNo) {
        CompletableFuture<String> future = new CompletableFuture<>();
        GManualPhotoRequestPacket sendPacket = new GManualPhotoRequestPacket();
        byte[] monitoringDeviceIdFinal = BytesUtils.s2b(monitoringDeviceId);
        sendPacket.setMonitoringDeviceId(monitoringDeviceIdFinal);
        sendPacket.setPresettingNo(presettingNo);
        sendPacket.setChannelNo(channelNo);
        netService.sendPacket(sendPacket, ack -> ack instanceof GManualPhotoRequestPacket).subscribe(ack -> {
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
            future.complete((GCameraSchedulePacket)ack);
        });
        return future.get(1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public PhotoTimeTable photoScheduleSettings(String monitoringDeviceId, byte channelNo,byte group,byte requestSetFlag, PhotoTimeTable photoTimeTable) throws ExecutionException, InterruptedException, TimeoutException {
        GPhotoTimeTablePacket gPhotoTimeTablePacket = new GPhotoTimeTablePacket();
        BeanUtils.copyProperties(photoTimeTable, gPhotoTimeTablePacket);
        byte[] monitoringDeviceIdFinal = BytesUtils.s2b(monitoringDeviceId);
        gPhotoTimeTablePacket.setMonitoringDeviceId(monitoringDeviceIdFinal);
        gPhotoTimeTablePacket.setRequestSetFlag(requestSetFlag);
        gPhotoTimeTablePacket.setChannelNo(channelNo);
        gPhotoTimeTablePacket.setGroup(group);
        CompletableFuture<PhotoTimeTable> future = new CompletableFuture<>();

        netService.sendPacket(gPhotoTimeTablePacket, ack -> ack instanceof GPhotoTimeTablePacket).subscribe(ack -> {

            PhotoTimeTable gPhotoTimeTable = new PhotoTimeTable();
            BeanUtils.copyProperties(ack, gPhotoTimeTable);
            future.complete(gPhotoTimeTable);
        });
        return future.get(1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public ImageAcquisitionDto imageAcquisitionSettings(String monitoringDeviceId, byte requestSetFlag, ImageAcquisitionDto imageAcquisition) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<ImageAcquisitionDto> future = new CompletableFuture<>();

        GImageAcquisitionPacket gImageAcquisitionPacket = new GImageAcquisitionPacket();
        BeanUtils.copyProperties(imageAcquisition, gImageAcquisitionPacket);
        netService.sendPacket(gImageAcquisitionPacket, ack -> ack instanceof GImageAcquisitionPacket).subscribe(ack -> {
            ImageAcquisitionDto gImageAcquisition = new ImageAcquisitionDto();
            BeanUtils.copyProperties(ack, gImageAcquisition);
            future.complete(gImageAcquisition);
        });

        return future.get(1000, TimeUnit.MILLISECONDS);
    }






}
