package com.cdzeroly.wvp.i1.packet.v2022;

import com.cdzeroly.wvp.i1.bean.AlarmLinkage;
import com.cdzeroly.wvp.i1.bean.AlarmLinkageParamsQuery;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * 监拍装置告警联动参数查询报 数据包
 * <p>
 * 该类用于构建监拍装置告警联动参数查询报，包含通道号和预置位号。
 * 该数据包用于查询监拍装置的告警联动参数。
 *
 * @author MAGRY
 */
@Getter
@Setter
public class GAlarmLinkageParamsQueryPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_ALARM_LINKAGE_PARAMS_QUERY = (byte) 0xF9;

    // 通道号
    private byte channelNo;
    // 预置位号，无云台固定为255 (FFH)
    private byte presettingNo;

    List<AlarmLinkage> linkageConfigs;


    public GAlarmLinkageParamsQueryPacket() {
    }

    public GAlarmLinkageParamsQueryPacket(ByteBuf data) {
        this.commandStatus = data.readBoolean();
        this.channelNo = data.readByte();
        this.presettingNo = data.readByte();
        int size = data.readByte();
        linkageConfigs = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            AlarmLinkage alarmLinkage = new AlarmLinkage( data.readByte(),data.readByte(), data.readShort(), data.readShort());

            linkageConfigs.add(alarmLinkage);
        }


    }

    public GAlarmLinkageParamsQueryPacket(List<AlarmLinkageParamsQuery> alarmLinkageParamsQueries) {
        int size = alarmLinkageParamsQueries.size();
        ByteBuf buffer = Unpooled.buffer(size * 2);

        alarmLinkageParamsQueries.forEach(alarmLinkageParamsQuery -> {
            buffer.writeBytes(alarmLinkageParamsQuery.toBytes());
        });
        this.content = buffer;
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

        return packet(monitoringDeviceId, serialNumber, content.array());
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
        return FrameTypeConstant.IMAGE_CONTROL_RESPONSE_MESSAGE;
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
        return FRAME_TYPE_ALARM_LINKAGE_PARAMS_QUERY;
    }
}
