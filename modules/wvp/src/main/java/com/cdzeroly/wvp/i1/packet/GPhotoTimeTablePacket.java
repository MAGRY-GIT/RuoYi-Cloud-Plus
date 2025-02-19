package com.cdzeroly.wvp.i1.packet;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/12 18:01
 */

import com.cdzeroly.wvp.i1.bean.TimeTable;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/**
 * 拍照时间表设置 数据包
 *
 * @author MAGRY
 */

@Setter
@Getter
public class GPhotoTimeTablePacket extends AbstractPacket {
    public static final byte FRAME_TYPE_PHOTO_TIME_TABLE = (byte) 0xCA;

    /**
     * 参数配置类型标识：
     * ①00H查询配置信息
     * ②01H 设置配置信息。
     */
    private byte requestSetFlag;
    // 通道号
    private byte channelNo;
    // 组数
    private byte group;
    // 时间表，每组包含时、分、预置位号
    private List<TimeTable> timeTables;

    public GPhotoTimeTablePacket(ByteBuf byteBuf) {
    }
    public GPhotoTimeTablePacket() {
    }



    @Override
    public byte[] getFrameBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(16 + group * 3).order(ByteOrder.LITTLE_ENDIAN)
            .put(requestSetFlag)
            .put(channelNo)
            .put(group);

        for (TimeTable time : timeTables) {
            buffer.put(time.hour);
            buffer.put(time.minute);
            buffer.put(time.presettingNo);
        }

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_PHOTO_TIME_TABLE;
    }


}
