package com.cdzeroly.wvp.i1.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 告警联动参数类
 * <p>
 * 该类用于表示单个告警联动配置，包括告警类型、联动动作及其参数。
 *
 * @author MAGRY
 */
@NoArgsConstructor
@Data
public class AlarmLinkage {
    // 告警类型
    private Byte alarmType;
    // 联动动作
    private LinkageAction linkageAction;
    // 联动动作参数1
    private Short linkageParam1;
    // 联动动作参数2
    private Short linkageParam2;

    public AlarmLinkage(byte alarmType, byte linkageAction, short linkageParam1, short linkageParam2) {
        this.alarmType = alarmType;
        this.linkageAction = LinkageAction.fromValue(linkageAction);
        this.linkageParam1 = linkageParam1;
        this.linkageParam2 = linkageParam2;
    }

    public short getLinkageParam1() {
        return switch (linkageAction) {
            case NO_LINKAGE ->     (short) 0xFFFF;
            case UPLOAD_VIDEO  ,UPLOAD_PHOTO,IO_OUTPUT -> linkageParam1;
        };

    }

    public short getLinkageParam2() {
        return switch (linkageAction) {
            case NO_LINKAGE ,UPLOAD_VIDEO->     (short) 0xFFFF;
            case UPLOAD_PHOTO,IO_OUTPUT -> linkageParam1;
        };
    }

    /**
     * 将告警联动参数序列化为字节数组。
     *
     * @return 序列化后的字节数组
     */
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(6).order(ByteOrder.LITTLE_ENDIAN);
        buffer.put(alarmType);
        buffer.put((byte) linkageAction.getValue());
        buffer.putShort(linkageParam1);
        buffer.putShort(linkageParam2);
        return buffer.array();
    }
}
