package com.cdzeroly.wvp.i1.packet.v2022;

import com.cdzeroly.wvp.enums.VideoFormatEnum;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 短视频采集参数设置报 数据包
 *
 * @author MAGRY
 */
@Setter
@Getter
public class GVideoCaptureSettingsPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_VIDEO_CAPTURE_SETTINGS = (byte) 0xEC;

    // 通道号
    private byte channelNo;

    // 参数配置类型标识：00H 查询配置信息，01H 设置配置信息
    private byte requestSetFlag;

    // 视频格式
    private VideoFormatEnum videoFormat;

    // 视频录制时间（单位为秒）
    private int videoTime;


    public GVideoCaptureSettingsPacket() {
    }

    @Override
    public byte[] getFrameBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(5)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(requestSetFlag)
            .put((byte) videoFormat.getCode())
            .putShort((short)videoTime);

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_VIDEO_CAPTURE_SETTINGS;
    }
}
