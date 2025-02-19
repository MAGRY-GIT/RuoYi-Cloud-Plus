package com.cdzeroly.wvp.i1.handler.v2022;

import com.cdzeroly.wvp.i1.handler.BaseAckHandler;
import com.cdzeroly.wvp.i1.packet.v2022.GRemoteImageDataPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 远程图像数据报 数据包处理程序
 */
@Slf4j
@ChannelHandler.Sharable
public class GRemoteImageDataHandler extends BaseAckHandler<GRemoteImageDataPacket> {

    @Override
    public void channelRead(ChannelHandlerContext ctx, GRemoteImageDataPacket msg) {
        // 处理远程图像数据报文
        log.info("Received remote image data packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GRemoteImageDataHandler", cause);
        ctx.close();
    }
}
