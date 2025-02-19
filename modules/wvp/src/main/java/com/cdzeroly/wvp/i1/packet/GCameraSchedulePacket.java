package com.cdzeroly.wvp.i1.packet;


import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * 摄像机定时工作时间表设置 数据包
 *
 * @author MAGRY
 */
@Setter
@Getter

public class GCameraSchedulePacket extends AbstractPacket {
    public static final byte FRAME_TYPE_CAMERA_SCHEDULE = (byte) 0xD5;
    // 参数配置类型标识：00H 查询配置信息，01H 设置配置信息
    private byte requestSetFlag;
    // 工作起始时间数组
    private List<Integer> startTimes;
    // 工作结束时间数组
    private List<Integer> endTimes;

    public GCameraSchedulePacket() {

    }

    public GCameraSchedulePacket(ByteBuf data) {
        this.requestSetFlag = data.readByte();
         startTimes = new ArrayList<>();
        endTimes = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            startTimes.add(data.readInt());
            endTimes.add(data.readInt());

        }


    }


    @Override
    public byte getFrameType() {
        return FrameTypeConstant.REMOTE_IMAGE_CONTROL_NEWSPAPER;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_CAMERA_SCHEDULE;
    }

    @Override
    public byte[] getFrameBytes() {
        // 参数配置类型标识
        ByteBuffer buffer = ByteBuffer.allocate(32)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(requestSetFlag);
        // 工作起始时间
        for (int i = 0; i < 4; i++) {
            Integer startTime = startTimes.get(i);
            Integer endTime = endTimes.get(i);
            buffer.putInt(startTime);
            buffer.putInt(endTime);
        }
        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

}
