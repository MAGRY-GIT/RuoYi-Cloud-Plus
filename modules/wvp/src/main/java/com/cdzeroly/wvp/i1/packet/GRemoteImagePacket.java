package com.cdzeroly.wvp.i1.packet;

import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/**
 * 远程图像数据报文
 *
 * @author MAGRY
 */
@Setter
@Getter
public class GRemoteImagePacket extends AbstractPacket {
    public static final byte FRAME_TYPE_REMOTE_IMAGE = (byte) 0xCD;

    // 通道号
    private byte channelNo;

    // 预置位号
    private byte presettingNo;

    // 总包数
    private short packetNo;

    // 子包序号
    private short subPacketNo;

    // 图像数据
    private byte[] imageData;


    public GRemoteImagePacket() {
    }

    @Override
    public byte[] getFrameBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(16 + imageData.length)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(presettingNo)
            .putShort(packetNo)
            .putShort(subPacketNo)
            .put(imageData);

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }
    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_REMOTE_IMAGE;
    }

}
