package com.cdzeroly.wvp.i1.codec;

import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.packet.GHeartPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;


class ServerFrameDecoderTest {

   public void main(String[] args) {
        // 创建一个 ByteBuf 实例
        ByteBuf data = Unpooled.buffer(256); // 创建一个初始容量为 256 的 ByteBuf
        data.writeInt(12345); // 写入一些数据

        // 根据 messageType 和 ByteBuf 创建具体的 Packet 实例
        byte messageType = GHeartPacket.FRAME_TYPE_HEART;
        ServerFrameDecoder serverFrameDecoder = new ServerFrameDecoder();
        AbstractPacket packet = serverFrameDecoder.createPacket(messageType, data);

        // 测试输出
        System.out.println("Packet Class: " + packet.getClass().getName());
        System.out.println("Frame Bytes: " + java.util.Arrays.toString(packet.getFrameBytes()));

    }
}
