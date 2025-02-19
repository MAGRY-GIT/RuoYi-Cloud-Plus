package com.cdzeroly.wvp.i1.packet;


import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 远程图像数据上送结束标记 数据包
 *
 * @author MAGRY
 */
@Setter
@Getter
public class GRemoteImageEndPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_REMOTE_IMAGE_END = (byte) 0xCE;
    // Getter 和 Setter 方法
    // 通道号
    private byte channelNo;
    // 预置位号
    private byte presettingNo;
    // 时间戳
    private int timeStamp;

    public GRemoteImageEndPacket() {
    }

    @Override
    public byte[] getFrameBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(16)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(presettingNo)
            .putInt(timeStamp);

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }
    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_REMOTE_IMAGE_END;
    }

}
