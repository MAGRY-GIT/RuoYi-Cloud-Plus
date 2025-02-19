package com.cdzeroly.wvp.i1.codec;


import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.utils.BytesUtils;
import com.cdzeroly.wvp.utils.CRC16ModbusUtil;
import com.cdzeroly.wvp.utils.CRC8Utils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;


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
@Slf4j
public abstract class BaseFrameDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) {
        int dateLen = 0;
        while (in.readerIndex() < in.readableBytes() && in.readableBytes() >= dateLen) {
            Optional<AbstractPacket> packet = Optional.empty();

            try {

                //判断报头
                byte head1 = in.getByte(0);
                byte head2 = in.getByte(1);

                if (head1 == AbstractPacket.HEAD1 && head2 == AbstractPacket.HEAD2) {
                    //获取长度
                    dateLen = in.getUnsignedShort(2);

                    //监测装置唯一标识，遵
                    // 循国家电网公司“SG186 工程”生产管理系统设备 17位编码规范，
                    // 当设备出厂时，状态监测装置ID为7位厂家编码+10位厂家生产序列号的17位原始ID：
                    // 当设备上线运行后需要通过接收上级设备的命令，将状态监测装置ID 由原始ID更改为通信ID。
                    // 由多个或者多种监测类型组成的复合装置，需要配置多个不重复的ID；
                    byte[] monitoringDeviceId = new byte[17];
                    in.getBytes(4, monitoringDeviceId);
                    //获取帧类型
                    byte frameType = in.getByte(22);
                    //获取命令消息类型
                    byte messageType = in.getByte(23);
                    byte serialNumber = in.getByte(24);

                    ByteBuf byteBufs = Unpooled.buffer(dateLen);
                    //获取内容数据
                    in.getBytes(25, byteBufs);
                    //校验位
                    int crc = in.getUnsignedShort(23 + dateLen);
                    //全部数据
                    ByteBuf bytes = Unpooled.buffer(22);
                    in.getBytes(2, bytes);

                    byte[] cent = new byte[byteBufs.readableBytes()];
                    byteBufs.getBytes(0, cent);
                    if (checkCrc(bytes.array(), crc)) {
                        packet = decodeData(messageType, byteBufs,monitoringDeviceId,frameType,serialNumber);
                    }
                    in.skipBytes(dateLen);
                    in.discardReadBytes();
                } else {
                    in.readByte();
                    in.discardReadBytes();
                }
                packet.ifPresent(list::add);
            } catch (IndexOutOfBoundsException e) {
                log.info(e.getMessage());
            }
        }

    }


    private boolean checkCrc(byte[] content, int b) {

        int sum = CRC16ModbusUtil.calculateCrc(content, content.length);
        if (sum != b) {
            System.out.println("校验失败---数据：" + BytesUtils.b2h(content));
            return false;
        }
        return true;
    }

    /**
     * 解析内容数据, 只有通过了数据帧校验和 CRC 校验的数据才会被送到这里进行解析 帧头, 帧尾, 校验码, 没有进行传递.
     *
     * @param frameType 帧类型
     * @param data      数据
     * @return 数据包
     */
    @Nullable
    public abstract Optional<AbstractPacket> decodeData(byte messageType, ByteBuf data, byte[] monitoringDeviceId, byte frameType, byte serialNumber);
}
