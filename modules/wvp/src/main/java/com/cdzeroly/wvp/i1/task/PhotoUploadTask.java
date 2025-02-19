package com.cdzeroly.wvp.i1.task;

import com.cdzeroly.wvp.i1.NetService;
import com.cdzeroly.wvp.i1.NettyChannelManager;
import com.cdzeroly.wvp.i1.packet.*;
import com.cdzeroly.wvp.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 图片上送定时任务
 *
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/17 11:54
 */
@Component
public class PhotoUploadTask extends Thread {

    private volatile boolean running = true;
    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Autowired
    private NetService netService;
    @Autowired
    private PhotoUploadHandler photoUploadHandler;

    @Override
    public void run() {


        while (running) {
            try {
                //等待接收上送照片
                Optional<GPhotoUploadRequestPacket> photoUploadRequestPacketOptional;
                photoUploadRequestPacketOptional = netService.getFilterPacket(e -> e instanceof GPhotoUploadRequestPacket);
                photoUploadRequestPacketOptional.ifPresent(gPhotoUploadRequestPacket -> {
                    //告警信息
                    photoUploadHandler.processPhotoUpload(gPhotoUploadRequestPacket);

                });
                // 避免CPU过度占用
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // 中断后退出循环
            } catch (Exception e) {
                // 记录日志或其他处理
                e.printStackTrace();
            }

        }
    }

    public void stopTask() {
        this.running = false;
    }

}
