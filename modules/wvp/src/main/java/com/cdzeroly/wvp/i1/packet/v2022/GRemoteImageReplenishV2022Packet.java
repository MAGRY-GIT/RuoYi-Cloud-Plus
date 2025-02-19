package com.cdzeroly.wvp.i1.packet.v2022;

import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 远程图像补包数据下发报 数据包
 * <p>
 * 该类用于构建远程图像补包数据下发报，包含通道号、补包包数和补包包号序列。
 * 该数据包用于通知监拍装置需要补传的图像数据包。
 *
 * @author MAGRY
 */
@Getter
@Setter
public class GRemoteImageReplenishV2022Packet extends AbstractPacket {
    public static final byte FRAME_TYPE_REMOTE_IMAGE_REPLENISH = (byte) 0xF2;

    // 通道号
    private byte channelNo;
    // 补包包数
    private short complementPackSum;
    // 补包包号序列
    private short[] complementPackNo;

    // 内容ID
    private int contentId;
    //    后4字节备用
    private int reserve;


    public GRemoteImageReplenishV2022Packet() {
    }

    /**
     * 构建数据包字节数组。
     * <p>
     * 该方法将当前对象的字段值序列化为字节数组，用于网络传输。
     *
     * @return 构建好的数据包字节数组
     */
    @Override
    public byte[] getFrameBytes() {
        // 计算总长度
        int totalLength = 12 + complementPackNo.length * 2;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .putShort(complementPackSum);

        for (short packNo : complementPackNo) {
            buffer.putShort(packNo);
        }

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

    /**
     * 获取帧类型。
     * <p>
     * 该方法返回当前数据包的帧类型。
     *
     * @return 帧类型
     */
    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    /**
     * 获取报文类型。
     * <p>
     * 该方法返回当前数据包的报文类型。
     *
     * @return 报文类型
     */
    @Override
    public byte getMessageType() {
        return FRAME_TYPE_REMOTE_IMAGE_REPLENISH;
    }
}
