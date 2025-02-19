package com.cdzeroly.wvp.i1.task.v2022;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.cdzeroly.wvp.i1.NetService;
import com.cdzeroly.wvp.i1.packet.*;
import com.cdzeroly.wvp.i1.packet.v2022.GPhotoVideoUploadRequestPacket;
import com.cdzeroly.wvp.i1.packet.v2022.GRemoteImageDataEndPacket;
import com.cdzeroly.wvp.i1.packet.v2022.GRemoteImageDataPacket;
import com.cdzeroly.wvp.i1.packet.v2022.GRemoteImageReplenishV2022Packet;
import com.cdzeroly.wvp.utils.BytesUtils;
import com.cdzeroly.wvp.utils.ImageUtil;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/17 12:14
 */
@Component
@Slf4j
public class PhotoVideoUploadHandler {
    // 根据需要调整线程池大小
    private final ExecutorService photoUploadExecutor = Executors.newFixedThreadPool(10);

    private final NetService netService;

    public PhotoVideoUploadHandler(NetService netService) {
        this.netService = netService;
    }


    public void processPhotoUpload(GPhotoVideoUploadRequestPacket gPhotoUploadRequestPacket) {
        photoUploadExecutor.submit(() -> {

            List<GRemoteImageDataPacket> remoteImagePacketList =  new ArrayList<>();

            GRemoteImageDataEndPacket remoteImagePacket = getRemoteImagePacket((int) gPhotoUploadRequestPacket.getPacket(), gPhotoUploadRequestPacket.getContentId(), gPhotoUploadRequestPacket.getMonitoringDeviceId(), remoteImagePacketList);

            short poorPackage = 0;
            // 补包命令
            GRemoteImageReplenishV2022Packet gRemoteImageReplenishV2022Packet = new GRemoteImageReplenishV2022Packet();
            gRemoteImageReplenishV2022Packet.setChannelNo(gPhotoUploadRequestPacket.getChannelNo());
            if (remoteImagePacketList.size() == gPhotoUploadRequestPacket.getPacket()) {
                gRemoteImageReplenishV2022Packet.setComplementPackSum(poorPackage);
            } else {
                poorPackage = (short) (gPhotoUploadRequestPacket.getPacket() - remoteImagePacketList.size());
                gRemoteImageReplenishV2022Packet.setComplementPackSum(poorPackage);
                List<Short> missingIntegers = findMissingIntegers(remoteImagePacketList.stream().map(GRemoteImageDataPacket::getSubPacket).collect(Collectors.toList()), gPhotoUploadRequestPacket.getPacket());
                short[] complementPackNoArray = new short[missingIntegers.size()];
                for (int i = 0; i < missingIntegers.size(); i++) {
                    complementPackNoArray[i] = missingIntegers.get(i);
                }
                gRemoteImageReplenishV2022Packet.setComplementPackNo(complementPackNoArray);
                gRemoteImageReplenishV2022Packet.setContentId(gPhotoUploadRequestPacket.getContentId());
            }
            netService.sendPacket(gRemoteImageReplenishV2022Packet);
            // 等待图片写入完成

            while (poorPackage > 0) {

                remoteImagePacketList =  new ArrayList<>();
                 getRemoteImagePacket((int)poorPackage, gPhotoUploadRequestPacket.getContentId(), gPhotoUploadRequestPacket.getMonitoringDeviceId(),remoteImagePacketList);
                poorPackage = (short) (gPhotoUploadRequestPacket.getPacket() - remoteImagePacketList.size());
            }
            byte[] mergedBytes = remoteImagePacketList.stream().sorted(Comparator.comparingInt(GRemoteImageDataPacket::getSubPacket)).flatMapToInt(packet -> IntStream.range(0, packet.getImageData().length).map(i -> packet.getImageData()[i] & 0xff)).collect(ByteArrayOutputStream::new, (baos, i) -> baos.write((byte) i), (baos1, baos2) -> baos1.write(baos2.toByteArray(), 0, baos2.size())).toByteArray();
            BufferedImage bufferedImage = null;
            try {
                String s = DigestUtil.md5Hex(mergedBytes);
                if (remoteImagePacket.getMd5() != s) {
                    log.error("校验失败");
                    return;
                }
                bufferedImage = ImageUtil.convertBytesToImage(mergedBytes);
                ImageUtil.saveImageToFile(bufferedImage, "D:\\test.jpg", "jpg");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }


    /**
     * 获取远程图片数据包
     *
     * @param packetSize              获取数量
     * @param monitoringDeviceIdFinal 设备id
     * @return List<GRemoteImagePacket>
     */
    private GRemoteImageDataEndPacket getRemoteImagePacket(Integer packetSize, int contentId, byte[] monitoringDeviceIdFinal, List<GRemoteImageDataPacket> remoteImagePacketList) {
        //等待接收上送照片
        for (int i = 0; i < packetSize + 1; i++) {
            Optional<AbstractPacket> optionalPhotoUploadRequestPacket = netService.getFilterPacket((e) -> {
                ByteBuf content = e.getContent();
                boolean photoUploadRequestPacket = content.readInt() == contentId && e.getMonitoringDeviceId() == monitoringDeviceIdFinal && e instanceof GRemoteImageDataPacket;
                content.release();
                boolean remoteImageDataEnd = content.readInt() == contentId && e.getMonitoringDeviceId() == monitoringDeviceIdFinal && e instanceof GRemoteImageDataEndPacket;

                return photoUploadRequestPacket || remoteImageDataEnd;
            });
            if (optionalPhotoUploadRequestPacket.isPresent()) {
                if (optionalPhotoUploadRequestPacket.get() instanceof GRemoteImageDataPacket) {
                    remoteImagePacketList.add((GRemoteImageDataPacket) optionalPhotoUploadRequestPacket.get());
                } else if (optionalPhotoUploadRequestPacket.get() instanceof GRemoteImageDataEndPacket) {
                    return (GRemoteImageDataEndPacket) optionalPhotoUploadRequestPacket.get();
                }
            }
        }
        return null;
    }

    /**
     * 获取缺失的整数
     *
     * @param objects
     * @param size
     * @return
     */
    public static List<Short> findMissingIntegers(List<Integer> objects, Short size) {
        // 创建一个包含1到10的整数集合
        Set<Short> fullSet = new HashSet<>();
        for (int i = 1; i <= size; i++) {
            fullSet.add((short) i);
        }

        // 从集合中移除已经存在的整数
        for (Integer obj : objects) {
            fullSet.remove(obj);
        }

        // 将剩余的整数转换为列表并返回
        return new ArrayList<>(fullSet);
    }

    // 关闭线程池的方法
    public void shutdownExecutor() {
        photoUploadExecutor.shutdown();
    }
}
