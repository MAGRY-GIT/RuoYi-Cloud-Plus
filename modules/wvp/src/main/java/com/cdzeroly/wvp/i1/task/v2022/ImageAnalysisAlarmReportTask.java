package com.cdzeroly.wvp.i1.task.v2022;

import com.cdzeroly.wvp.i1.NetService;
import com.cdzeroly.wvp.i1.NettyChannelManager;
import com.cdzeroly.wvp.i1.packet.v2022.GImageAnalysisAlarmReportPacket;
import com.cdzeroly.wvp.i1.packet.v2022.GPhotoVideoUploadRequestPacket;
import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisAlarmReport;
import com.cdzeroly.wvp.i1.temp.mapper.ImageAnalysisAlarmReportMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 图片上送定时任务
 *
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/17 11:54
 */
@Component
public class ImageAnalysisAlarmReportTask extends Thread {
    private volatile boolean running = true;

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Autowired
    private NetService netService;

    @Autowired
    private  ImageAnalysisAlarmReportMapper baseMapper;

    @Override
    public void run() {
        try {
            while (running) {
                //等待接收上送照片
                Optional<GImageAnalysisAlarmReportPacket> photoVideoUploadRequestPacket;
                photoVideoUploadRequestPacket = netService.getFilterPacket(e -> e instanceof GImageAnalysisAlarmReportPacket);
                photoVideoUploadRequestPacket.ifPresent(videoUploadRequestPacket -> {
                    ImageAnalysisAlarmReport imageAnalysisAlarmReport = new ImageAnalysisAlarmReport();
                    BeanUtils.copyProperties(videoUploadRequestPacket, imageAnalysisAlarmReport);
                    baseMapper.insert(imageAnalysisAlarmReport);
                    videoUploadRequestPacket.setCommandStatus(true);
                    netService.sendPacket(videoUploadRequestPacket);
                });
                // 避免CPU过度占用
                Thread.sleep(1000);

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopTask() {
        this.running = false;
    }

}
