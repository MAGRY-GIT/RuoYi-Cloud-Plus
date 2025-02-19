package com.cdzeroly.wvp.i1.packet;


import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 启动/终止摄像视频传输 数据包
 *
 * @author MAGRY
 */
@Getter
@Setter
public class GCameraVideoControlPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_CAMERA_VIDEO_CONTROL = (byte) 0xD1;

    // Getter 和 Setter 方法
    // 通道号
    private byte channelNo;
    // 控制位：0 表示关闭，1 表示启动
    private byte control;
    // 监测装置端口号
    private short port;

    public GCameraVideoControlPacket() {
    }

    @Override
    public byte[] getFrameBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(14)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(control)
            .putShort(port);

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

    @Override
    public byte getFrameType() {
        return FrameTypeConstant.REMOTE_IMAGE_CONTROL_NEWSPAPER;
    }


    @Override
    public byte getMessageType() {
        return FRAME_TYPE_CAMERA_VIDEO_CONTROL;
    }


}
