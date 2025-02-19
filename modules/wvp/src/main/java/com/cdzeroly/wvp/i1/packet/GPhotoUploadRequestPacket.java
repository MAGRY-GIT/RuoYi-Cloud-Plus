package com.cdzeroly.wvp.i1.packet;


import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 监测装置请求上送照片 数据包
 *
 * @author MAGRY
 */
@Getter
@Setter
public class GPhotoUploadRequestPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_PHOTO_UPLOAD_REQUEST = (byte) 0xCC;

    // 通道号
    private byte channelNo;

    // 预置位号
    private byte presettingNo;

    // 包数
    private int packet;



    public GPhotoUploadRequestPacket() {
    }
    public GPhotoUploadRequestPacket(ByteBuf byteBuf) {
        this.channelNo = byteBuf.readByte();
        this.presettingNo = byteBuf.readByte();
        this.packet = byteBuf.readUnsignedShort();
    }


    @Override
    public byte[] getFrameBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(14)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(presettingNo)
            .putShort((short) packet);

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }
    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_PHOTO_UPLOAD_REQUEST;
    }

}
