package com.cdzeroly.wvp.i1.handler;

import com.cdzeroly.wvp.i1.NetService;
import com.cdzeroly.wvp.i1.packet.*;
import com.cdzeroly.wvp.utils.ImageUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.IntStream;

/**
 * 监测装置请求上送照片 数据包处理程序
 * @author MAGRY
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class GPhotoUploadRequestHandler extends BaseAckHandler<GPhotoUploadRequestPacket> {

    @Autowired
    NetService netService;

    @Override
    protected void channelRead(ChannelHandlerContext ctx, GPhotoUploadRequestPacket msg) {
        // 处理监测装置请求上送照片数据包
        log.info("Received photo upload request packet: {}", msg);
        byte[] monitoringDeviceIdFinal = msg.getMonitoringDeviceId();

        //等待接收上送照片
        Optional<GPhotoUploadRequestPacket> photoUploadRequestPacketOptional = null;
        try {
            photoUploadRequestPacketOptional = netService.getFilterPacket(e -> {
                return e.getMonitoringDeviceId() == monitoringDeviceIdFinal && e instanceof GPhotoUploadRequestPacket;
            });

            if (photoUploadRequestPacketOptional.isPresent()) {
                GPhotoUploadRequestPacket gPhotoUploadRequestPacket = photoUploadRequestPacketOptional.get();


                List<GRemoteImagePacket> remoteImagePacketList = getRemoteImagePacket(gPhotoUploadRequestPacket.getPacket(), monitoringDeviceIdFinal);

                short poorPackage = 0;
                //补包命令
                GRemoteImageReplenishPacket gRemoteImageReplenishPacket = new GRemoteImageReplenishPacket();
                gRemoteImageReplenishPacket.setChannelNo(gPhotoUploadRequestPacket.getChannelNo());
                gRemoteImageReplenishPacket.setPresettingNo(gPhotoUploadRequestPacket.getPresettingNo());
                if (remoteImagePacketList.size() == gPhotoUploadRequestPacket.getPacket()) {
                    gRemoteImageReplenishPacket.setComplementPackSum(poorPackage);
                } else {
                    poorPackage = (short) (gPhotoUploadRequestPacket.getPacket() - remoteImagePacketList.size());
                    gRemoteImageReplenishPacket.setComplementPackSum(poorPackage);
                    List<Short> missingIntegers = findMissingIntegers(remoteImagePacketList.stream().map(GRemoteImagePacket::getSubPacketNo).toList(), gPhotoUploadRequestPacket.getPacket());
                    gRemoteImageReplenishPacket.setComplementPackNo(missingIntegers);
                }
                netService.sendPacket(gRemoteImageReplenishPacket);
                //等待图片写入完成

                while (poorPackage > 0) {
                    remoteImagePacketList = getRemoteImagePacket(gPhotoUploadRequestPacket.getPacket(), monitoringDeviceIdFinal);

                    poorPackage = (short) (gPhotoUploadRequestPacket.getPacket() - remoteImagePacketList.size());
                }
                byte[] mergedBytes = remoteImagePacketList.stream().sorted(Comparator.comparingInt(GRemoteImagePacket::getSubPacketNo)).flatMapToInt(packet -> IntStream.range(0, packet.getImageData().length).map(i -> packet.getImageData()[i] & 0xff)).collect(ByteArrayOutputStream::new, (baos, i) -> baos.write((byte) i), (baos1, baos2) -> baos1.write(baos2.toByteArray(), 0, baos2.size())).toByteArray();
                BufferedImage bufferedImage = ImageUtil.convertBytesToImage(mergedBytes);
                ImageUtil.saveImageToFile(bufferedImage, "D:\\test.jpg", "jpg");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取远程图片数据包
     *
     * @param packetSize              获取数量
     * @param monitoringDeviceIdFinal 设备id
     * @return List<GRemoteImagePacket>
     * @throws InterruptedException
     */
    private List<GRemoteImagePacket> getRemoteImagePacket(Integer packetSize, byte[] monitoringDeviceIdFinal) throws InterruptedException {
        List<GRemoteImagePacket> remoteImagePacketList = new ArrayList<>();
        //等待接收上送照片
        for (int i = 0; i < packetSize + 1; i++) {
            Optional<AbstractPacket> optionalPhotoUploadRequestPacket = netService.getFilterPacket(e -> {
                return e.getMonitoringDeviceId() == monitoringDeviceIdFinal && (e instanceof GRemoteImagePacket || e instanceof GRemoteImageEndPacket);
            });
            if (optionalPhotoUploadRequestPacket.isPresent()) {
                if (optionalPhotoUploadRequestPacket.get() instanceof GRemoteImagePacket) {
                    remoteImagePacketList.add((GRemoteImagePacket) optionalPhotoUploadRequestPacket.get());
                } else {
                    break;
                }
            }
        }
        return remoteImagePacketList;
    }


    /**
     * 获取缺失的整数
     *
     * @param objects
     * @param size
     * @return
     */
    public static List<Short> findMissingIntegers(List<Short> objects, Integer size) {
        // 创建一个包含1到10的整数集合
        Set<Short> fullSet = new HashSet<>();
        for (int i = 1; i <= size; i++) {
            fullSet.add((short) i);
        }

        // 从集合中移除已经存在的整数
        for (Short obj : objects) {
            fullSet.remove(obj);
        }

        // 将剩余的整数转换为列表并返回
        return new ArrayList<>(fullSet);
    }
}
