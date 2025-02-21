package com.cdzeroly.wvp.i1.packet.v2020;

import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 图像OSD查询/设置报 数据包
 * <p>
 * 该类用于构建图像OSD查询/设置报，包含通道号、是否显示时间、是否显示文本和文本内容。
 * 该数据包用于查询或设置图像OSD（屏幕显示）参数。
 *
 * @author MAGRY
 */
@Getter
@Setter
public class GImageOSDPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_IMAGE_OSD = (byte) 0xF3;
    private byte   roquestSetFlag;
    // 通道号
    private byte channelNo;
    // 是否显示时间标识：0 不显示，1 显示
    private byte showTime;
    // 文本显示标识：0 不显示，1 显示
    private byte showText;
    // 文本内容，UTF-8编码格式，以'\0'结尾
    private String textContent;

    public GImageOSDPacket() {
    }

    /**
     * 构建数据包字节数组。
     * <p>
     * 该方法将当前对象的字段值序列化为字节数组，用于网络传输。
     *
     * @return 构建好的数据包字节数组
     */
    @Override
    public byte[] getFrameBytes() {
        // 计算总长度
        byte[] textBytes = (textContent + "\0").getBytes(java.nio.charset.StandardCharsets.UTF_8);
        int totalLength = 12 + textBytes.length;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(roquestSetFlag)
            .put(showTime)
            .put(showText)
            .put(textBytes);

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

    /**
     * 获取帧类型。
     * <p>
     * 该方法返回当前数据包的帧类型。
     *
     * @return 帧类型
     */
    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    /**
     * 获取报文类型。
     * <p>
     * 该方法返回当前数据包的报文类型。
     *
     * @return 报文类型
     */
    @Override
    public byte getMessageType() {
        return FRAME_TYPE_IMAGE_OSD;
    }
}
