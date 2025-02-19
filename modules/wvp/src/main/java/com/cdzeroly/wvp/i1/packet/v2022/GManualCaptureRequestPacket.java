package com.cdzeroly.wvp.i1.packet.v2022;

import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 手动请求拍摄照片/短视频报 数据包
 *
 * @author MAGRY
 */
@Setter
@Getter
public class GManualCaptureRequestPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_MANUAL_CAPTURE_REQUEST = (byte) 0xEE;

     // 通道号
    private byte channelNo;

     // 预留字段，数据类型标识：0 表示照片，1 表示短视频  10个字节
    private byte[] reserve;


    public GManualCaptureRequestPacket() {
    }

    @Override
    public byte[] getFrameBytes() {
        ByteBuffer reserveBuffer = ByteBuffer.allocate(10)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(reserve);
        ByteBuffer buffer = ByteBuffer.allocate(11)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(reserveBuffer.array());

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_MANUAL_CAPTURE_REQUEST;
    }
}
