package com.cdzeroly.wvp.i1.packet.v2022;

import com.cdzeroly.wvp.i1.bean.AlarmTarget;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * 图像分析告警上报报 数据包
 * <p>
 * 该类用于构建图像分析告警上报报，包含通道号、预置位号、告警时间、告警目标数量及其相关信息。
 * 该数据包用于上报图像分析中检测到的告警信息。
 *
 * @author MAGRY
 */
@Getter
@Setter
public class GImageAnalysisAlarmReportPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_IMAGE_ANALYSIS_ALARM_REPORT = (byte) 0xF7;

    // 通道号
    private byte channelNo;
    // 预置位号，无云台固定为255 (FFH)
    private byte presettingNo;
    // 告警时间，时间戳
    private int alarmTime;
    // 告警目标数量
    private byte alarmTargetCount;
    // 告警目标信息，每个目标占用7字节：告警类型(1字节) + 置信度(1字节) + 告警区域(4字节)
    private List<AlarmTarget> alarmTargetsInfo;

    public GImageAnalysisAlarmReportPacket() {
    }



    public GImageAnalysisAlarmReportPacket(ByteBuf data) {

      this.channelNo = data.readByte();
      this.presettingNo = data.readByte();
      this.alarmTime = data.readInt();
      this.alarmTargetCount = data.readByte();
        alarmTargetsInfo = new ArrayList<>(alarmTargetCount);
      for (int i = 0; i < alarmTargetCount; i++) {
        AlarmTarget alarmTarget = new AlarmTarget(data.readByte(), data.readByte(), data.readByte(), data.readByte(), data.readByte(), data.readByte());
        alarmTargetsInfo.add(alarmTarget);
      }
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
       byte  commandStatus = super.commandStatus? (byte) 0xFF: (byte) 0x00;

        // 计算总长度
        ByteBuffer buffer = ByteBuffer.allocate(7)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(presettingNo)
            .putInt(alarmTime)
            .put(commandStatus);

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
        return FRAME_TYPE_IMAGE_ANALYSIS_ALARM_REPORT;
    }
}
