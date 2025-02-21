package com.cdzeroly.wvp.i1.handler.v2022;

import com.cdzeroly.wvp.i1.handler.BaseAckHandler;
import com.cdzeroly.wvp.i1.packet.v2020.GRemoteImageDataEndPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 远程图像数据上送结束标记报 数据包处理程序
 */
@Slf4j
@ChannelHandler.Sharable
public class GRemoteImageDataEndHandler extends BaseAckHandler<GRemoteImageDataEndPacket> {

    @Override
    public void channelRead(ChannelHandlerContext ctx, GRemoteImageDataEndPacket msg) {
        // 处理远程图像数据上送结束标记数据包
        log.info("Received remote image data end packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GRemoteImageDataEndHandler", cause);
        ctx.close();
    }
}
