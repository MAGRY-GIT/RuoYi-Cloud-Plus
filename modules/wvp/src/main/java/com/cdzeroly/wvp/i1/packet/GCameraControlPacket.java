package com.cdzeroly.wvp.i1.packet;


import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 摄像机远程调节 数据包
 *
 * @author MAGRY
 */
@Setter
@Getter
public class GCameraControlPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_CAMERA_CONTROL = (byte) 0xD0;
    // 通道号
    private byte channelNo;
    // 预置位号
    private byte presettingNo;

    // 动作指令
    private byte action;

    public GCameraControlPacket() {
    }

    @Override
    public byte[] getFrameBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(12)
            .order(ByteOrder.LITTLE_ENDIAN)
            // 通道号
            .put(channelNo)
            // 预置位号
            .put(presettingNo)
            // 动作指令
            .put(action);

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }


    @Override
    public byte getFrameType() {
        return FrameTypeConstant.REMOTE_IMAGE_CONTROL_NEWSPAPER;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_CAMERA_CONTROL;
    }

}
