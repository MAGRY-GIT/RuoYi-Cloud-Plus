package com.cdzeroly.wvp.i1.handler;

import com.cdzeroly.wvp.i1.packet.GRemoteImageReplenishPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 远程图像补包数据下发 数据包处理程序
 */
@Slf4j
@ChannelHandler.Sharable
public class GRemoteImageReplenishHandler extends BaseAckHandler<GRemoteImageReplenishPacket> {

    @Override
    protected void channelRead(ChannelHandlerContext ctx, GRemoteImageReplenishPacket msg) {
        // 处理远程图像补包数据下发数据包
        log.info("Received remote image replenish packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GRemoteImageReplenishHandler", cause);
        ctx.close();
    }
}
