package com.cdzeroly.wvp.i1.packet;


import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/**
 * 远程图像补包数据下发 数据包
 *
 * @author MAGRY
 */
@Setter
@Getter
public class GRemoteImageReplenishPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_REMOTE_IMAGE_REPLENISH = (byte) 0xCF;

    // Getter 和 Setter 方法
    // 通道号
    private byte channelNo;

     // 预置位号
    private byte presettingNo;

     // 补包包数
    private short complementPackSum;

     // 补包包号序列
    private List<Short> complementPackNo;


    public GRemoteImageReplenishPacket() {
    }

    @Override
    public byte[] getFrameBytes() {
        // 计算总长度
        int totalLength = 16 + complementPackNo.size() * 2;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(presettingNo)
            .putShort(complementPackSum);

        for (short packNo : complementPackNo) {
            buffer.putShort(packNo);
        }

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }
    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_REMOTE_IMAGE_REPLENISH;
    }

}
