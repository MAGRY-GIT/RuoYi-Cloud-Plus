package com.cdzeroly.wvp.i1.codec;

import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * TCP 数据帧编码器
 * @author MAGRY
 */
public class TCPFrameEncoder extends MessageToByteEncoder<AbstractPacket> {
    @Override
    protected void encode(ChannelHandlerContext cxt, AbstractPacket in, ByteBuf out) throws Exception {

        out.writeBytes(in.getFrameBytes());
    }
}
