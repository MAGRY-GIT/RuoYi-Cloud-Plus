package com.cdzeroly.wvp.i1.packet.v2020;

import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import com.cdzeroly.wvp.utils.BytesUtils;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 远程图像数据上送结束标记报 数据包
 * <p>
 * 该类用于构建远程图像数据上送结束标记报，包含通道号和时间戳。
 * 该数据包用于通知主站系统图像数据上送完成。
 *
 * @author MAGRY
 */
@Getter
@Setter
public class GRemoteImageDataEndPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_REMOTE_IMAGE_DATA_END = (byte) 0xF1;

    // 通道号
    private byte channelNo;
    // 时间戳
    private int timeStamp;
    //文件MD5码
    private String   md5;
    //前1字节表示文件类型： 0图片，1视频。后7字节备用
    private long    reserve;

    public GRemoteImageDataEndPacket() {
    }

    public GRemoteImageDataEndPacket(ByteBuf data) {
        this.channelNo = data.readByte();
       this. timeStamp = data.readInt();
       byte[]  md5 = new byte[32];
        data.readBytes(md5);
        this.md5 = BytesUtils.b2h(md5);
        this.reserve = data.readLong();
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
        ByteBuffer buffer = ByteBuffer.allocate(10)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .putInt(timeStamp);

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
        return FRAME_TYPE_REMOTE_IMAGE_DATA_END;
    }
}
