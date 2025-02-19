package com.cdzeroly.wvp.i1.packet.v2022;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/13 17:21
 */

import com.cdzeroly.wvp.enums.ImageResolutionEnum;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 图片采集参数设置报 数据包
 *
 * @author MAGRY
 */
@Setter
@Getter
public class GImageCaptureSettingsPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_IMAGE_CAPTURE_SETTINGS = (byte) 0xEB;

     // 通道号
    private byte channelNo;

     // 参数配置类型标识：00H 查询配置信息，01H 设置配置信息
    private byte requestSetFlag;

     // 标识位：字节位由低向高依次对应9~13的各项选择请求或设置的参数项，置1表示选择，置0表示未选择
    private byte requestFlag;

     // 色彩选择：0为黑白，1为彩色
    private boolean colorSelect;

     // 自定义图像分辨率
    private ImageResolutionEnum resolution;

     // 亮度，取值范围：1~100
    private byte luminance;

     // 对比度，取值范围：1~100
    private byte contrast;

     // 饱和度，取值范围：1~100
    private byte saturation;


    public GImageCaptureSettingsPacket() {
    }

    @Override
    public byte[] getFrameBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(8)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(requestSetFlag)
            .put(requestFlag)
            .put((byte) (colorSelect ? 1 : 0))
            .put(resolution.getCode())
            .put(luminance)
            .put(contrast)
            .put(saturation);

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_IMAGE_CAPTURE_SETTINGS;
    }
}
