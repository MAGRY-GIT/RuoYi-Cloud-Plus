package com.cdzeroly.wvp.i1.packet.v2020;

import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

/**
 * 远程图像数据报 数据包
 * <p>
 * 该类用于构建远程图像数据报，包含通道号、总包数、子包序号和图像数据。
 * 该数据包用于将图像数据从监拍装置上传到主站系统。
 *
 * @author MAGRY
 */
@Getter
@Setter
public class GRemoteImageDataPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_REMOTE_IMAGE_DATA = (byte) 0xF0;

    // 通道号
    private byte channelNo;
    // 总包数
    private int packet;
    // 子包序号
    private int subPacket;
    // 图像数据
    private byte[] imageData;

    public GRemoteImageDataPacket() {
    }


    public GRemoteImageDataPacket(ByteBuf data) {
        super.content = data.copy();
        this.channelNo = data.readByte();
        this.packet = data.readUnsignedShort();
        this.subPacket = data.readUnsignedShort();
        this.imageData = data.readBytes(data.readableBytes()).array();


    }

    @Override
    public byte[] getFrameBytes() {
        return packet(monitoringDeviceId, serialNumber, null);
    }

    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_REMOTE_IMAGE_DATA;
    }
}
