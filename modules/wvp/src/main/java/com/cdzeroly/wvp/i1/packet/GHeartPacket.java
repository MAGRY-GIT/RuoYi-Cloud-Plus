package com.cdzeroly.wvp.i1.packet;

import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


/**
 * 心跳 数据包
 *
 * @author MAGRY
 */
public class GHeartPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_HEART = (byte) 0xE6;

    public GHeartPacket() {
    }


    @Override
    public byte[] getFrameBytes() {
        byte[] date = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt((int) System.currentTimeMillis()).array();
        return packet(monitoringDeviceId, serialNumber, date);
    }

    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_HEART;
    }

}
