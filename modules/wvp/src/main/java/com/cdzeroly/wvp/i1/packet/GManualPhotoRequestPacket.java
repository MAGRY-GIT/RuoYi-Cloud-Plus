package com.cdzeroly.wvp.i1.packet;


import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 手动请求拍摄照片 数据包
 *
 * @author MAGRY
 */
@Setter
@Getter
public class GManualPhotoRequestPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_MANUAL_PHOTO_REQUEST = (byte) 0xCB;
    // 通道号
    private byte channelNo;
    // 预置位号
    private byte presettingNo;

    public GManualPhotoRequestPacket() {
    }

    public GManualPhotoRequestPacket(ByteBuf byteBuf) {
        byte commandStatus = byteBuf.readByte();
        this.commandStatus = commandStatus ==(byte) 0xFF;


    }

    @Override
    public byte[] getFrameBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(12)
            .order(ByteOrder.LITTLE_ENDIAN)
            // 通道号
            .put(channelNo)
            // 预置位号
            .put(presettingNo);

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_MANUAL_PHOTO_REQUEST;
    }
}
