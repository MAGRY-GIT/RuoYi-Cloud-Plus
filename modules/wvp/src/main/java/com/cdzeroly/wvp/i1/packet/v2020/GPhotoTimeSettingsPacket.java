package com.cdzeroly.wvp.i1.packet.v2020;

import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 拍照时间设置报 数据包
 *
 * @author MAGRY
 */
@Setter
@Getter
public class GPhotoTimeSettingsPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_PHOTO_TIME_SETTINGS = (byte) 0xEE;

     // 通道号
    private byte channelNo;

     // 参数配置类型标识：00H 查询配置信息，01H 设置配置信息
    private byte requestSetFlag;

     // 时1：设备拍照时间间隔小时设置
    private byte hour1;

     // 分1：设备拍照时间间隔分钟设置
    private byte minute1;

     // 时2：设备开始工作时间小时设置
    private byte hour2;

     // 分2：设备开始工作时间分钟设置
    private byte minute2;

     // 时3：设备结束工作时间小时设置
    private byte hour3;

     // 分3：设备结束工作时间分钟设置
    private byte minute3;


    public GPhotoTimeSettingsPacket() {
    }

    @Override
    public byte[] getFrameBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(8)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(requestSetFlag)
            .put(hour1)
            .put(minute1)
            .put(hour2)
            .put(minute2)
            .put(hour3)
            .put(minute3);

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_PHOTO_TIME_SETTINGS;
    }
}
