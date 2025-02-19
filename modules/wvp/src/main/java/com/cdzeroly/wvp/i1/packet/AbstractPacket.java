package com.cdzeroly.wvp.i1.packet;


import com.cdzeroly.wvp.utils.BytesUtils;
import com.cdzeroly.wvp.utils.CRC16ModbusUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import lombok.Getter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 作用: 基础 TCP 数据帧解码器, 只对基本结构进行解析, 具体需要转换为什么类型,交由子类进行处理
 * 摘要: 基本的帧结构
 * +----------+----------+--------------------------------------------------------
 * |  大小    |  固定值   |  摘要
 * +----------+----------+--------------------------------------------------------
 * | 2 bytes  |0x5A 0xA5 |  包头
 * | 2 bytes  |          |  报文长度(内容长度,帧结构中报文长度一般不应大于 1417字节，其中短信方式中帧长度不应大于 140 字节，北斗卫星短报文方式中帧长度不应大于120字节)
 * | 17bytes  |          |  监测装置ID(“SG186 工程”生产管理系统设备 17位编码规范,状态监测装置ID为7位厂家编码+10位厂家生产序列号的17位原始ID,将状态监测装置ID 由原始ID更改为通信ID)
 * | 1 bytes  |          |  帧类型(按功能对数据帧进行区分、标识)
 * | 1 bytes  |          |  报文类型(按不同类型对数据帧进行区分、标识)
 * | 1 bytes  |          |  帧序列号(监测装置或者主站系统主动发送的报文的顺序流水号，以无符号整数表示，在确认或者响应报文中应返回该帧序列号，主动上传的数据报，字节的最高位应为 1； 召唤上送的数据报帧号与请求数据报的帧号相一致，请求数据报的帧号字节最高位为0)
 * |          |          |  内容(数据的字节长度不固定)
 * | 2 bytes  |          |  校验码 CRC 校验
 * | 1 bytes  |          |  报文尾(校验的内容包括报文中除报文头、校验位、报文尾外所有报文数据(包括报文长度+监测装置编码+帧类型+报文类型+帧序列号+报文内容))
 * +----------+----------+--------------------------------------------------------
 *
 * @author MGARY
 */
@Getter
public abstract class AbstractPacket {
    //--- 通用数据 -----------------------------------------------------------------------------------------------------
    /**
     * 帧头
     */
    public static final byte HEAD1 = (byte) 0xA5;
    public static final byte HEAD2 = (byte) 0x5A;

    /**
     * 帧尾
     */
    public static final byte END = (byte) 0x96;

    /**
     * 数据发送状态:
     * ①FFH 成功;
     * ②00H 失败
     */
    protected boolean commandStatus;

    //--- 通用数据结束 --------------------------------------------------------------------------------------------------
    /**
     * 监听装置ID
     */
    protected byte[] monitoringDeviceId;
    /**
     * 帧序列号
     */
    protected byte serialNumber;

    /**
     * 帧序类型
     */
    protected byte frameType;



    /**
     * 内容
     */
    protected ByteBuf content;



    protected AbstractPacket() {
        this.monitoringDeviceId = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10};
        this.serialNumber = 0x00;
    }

    public AbstractPacket(byte[] monitoringDeviceId, byte serialNumber) {
        this.monitoringDeviceId = monitoringDeviceId;
        this.serialNumber = serialNumber;
    }

    public void setCommandStatus(boolean commandStatus) {
        this.commandStatus = commandStatus;
    }

    public void setSerialNumber(byte serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setMonitoringDeviceId(byte[] monitoringDeviceId) {
        this.monitoringDeviceId = monitoringDeviceId;
    }

    public void setFrameType(byte frameType) {
        this.frameType = frameType;
    }





    /**
     * 获取帧数据(byte[]), 该数据可以直接通过 TCP 协议进行发送.
     *
     * @return 帧数据
     */
    public abstract byte[] getFrameBytes();

    /**
     * 获取帧类型
     *
     * @return 帧数据
     */
    public abstract byte getFrameType();
    /**
     * 获取帧类型
     *
     * @return 帧数据
     */
    public abstract byte getMessageType();



    /**
     * 原始数据转换到帧数据,CRC校验,头部和尾部以及相关信息.
     *
     * @param monitoringDeviceId 监听设备ID
     * @param serialNumber       序列号
     * @param data               数据
     * @return 帧数据
     */
    protected byte[] packet(byte[] monitoringDeviceId, byte serialNumber, byte[] data) {

        // 总长度
        int totalLen = 27;
        int dataLength = data.length;
        if (null != data) {
            totalLen += dataLength;
        }
        // 分配一个合适大小的区域
        ByteBuffer bufferFinal = ByteBuffer.allocate(totalLen);
        // 添加报文头
        bufferFinal.put(HEAD1);
        bufferFinal.put(HEAD2);
        //
        ByteBuffer crcBuffer = ByteBuffer.allocate(totalLen - 5);
        byte[] dataLengthByte = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).put((byte) dataLength).array();
        // 报文长度
        crcBuffer.put(dataLengthByte);
        // 监测装置ID
        crcBuffer.put(monitoringDeviceId);
        //命令类型
        crcBuffer.put(getFrameType());
        //报文类型
        crcBuffer.put(getMessageType());
        //帧序列号
        crcBuffer.put(serialNumber);
        //报文内容
        if (null != data) {
            // 添加数据
            crcBuffer.put(data);
        }
        byte[] crcBytes = crcBuffer.flip().array();

        // CRC 校验
        int crcValue = CRC16ModbusUtil.calculateCrc(crcBytes, crcBytes.length);
        byte[] crc = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putInt(crcValue).array();

        bufferFinal.put(crcBytes);
        bufferFinal.put(crc);
        bufferFinal.put(END);
        // 返回最终结果
        return bufferFinal.flip().array();
    }

    //--- 接收或者发送时间 -----------------------------------------------------------------------------------------------


    private long time = System.currentTimeMillis();

    public void updateTime() {
        time = System.currentTimeMillis();
    }

    protected long getMonitoringDeviceIdAsNumber() {
        if (monitoringDeviceId == null || monitoringDeviceId.length != 17) {
            throw new IllegalArgumentException("monitoringDeviceId must be 17 bytes long");
        }
        long number = 0;
        for (byte b : monitoringDeviceId) {
            number = (number << 8) | (b & 0xFF);
        }
        return number;
    }


}
