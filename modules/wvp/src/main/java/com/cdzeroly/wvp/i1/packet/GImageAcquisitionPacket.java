package com.cdzeroly.wvp.i1.packet;

import com.cdzeroly.wvp.enums.ImageResolutionEnum;
import com.cdzeroly.wvp.i1.bean.ImageAcquisition;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


/**
 * 图像采集参数查询/设置 数据包
 *
 * @author MAGRY
 */
@Data

public class GImageAcquisitionPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_HEART = (byte) 0xE6;

    public GImageAcquisitionPacket() {
    }

    public GImageAcquisitionPacket(ByteBuf byteBuf) {
    }



    /**
     * 参数配置类型标识：
     * ①00H查询配置信息
     * ②01H 设置配置信息。
     */
    private byte requestSetFlag;

    /**
     * 标识位：字节位自低向高依次对应 9~13 的各选择请求或设置的参数项，
     * 置1 表示选择，
     * 置0  表示未选择
     */
    private byte requestFlag;

    /**
     * 色彩选择： 0为黑白， 1为彩色
     */
    private boolean colorSelect;

    /**
     * 自定义图像分辨率，采用以下几组：
     * ①320×240为1:
     * ②640×480为2;
     * ③704×576为3;
     * ④800×600为4;
     * ⑤1024×768为5;
     * ⑥1280×1024为6;
     * ⑦1280×720为7;
     * ⑧1920×1080为8;
     * ⑨2560×1440为9;
     * ⑩3840×2160为10
     */
    private ImageResolutionEnum resolution;

    /**
     * 亮度, 取值范围: 1~100
     */
    private byte luminance;

    /**
     * 对比度, 取值范围: 1~100
     */
    private byte contrast;

    /**
     * 饱和度, 取值范围: 1~100
     */
    private byte saturation;


    @Override
    public byte[] getFrameBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(16)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(requestSetFlag) // 参数配置类型标识
            .put(requestFlag) // 标识位
            .put((byte) (colorSelect ? 1 : 0)) // 色彩选择
            .put(resolution.getCode()) // 图像分辨率
            .put(luminance) // 亮度
            .put(contrast) // 对比度
            .put(saturation); // 饱和度
        byte[] data = buffer.array();
        return packet(monitoringDeviceId, serialNumber, data);
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
