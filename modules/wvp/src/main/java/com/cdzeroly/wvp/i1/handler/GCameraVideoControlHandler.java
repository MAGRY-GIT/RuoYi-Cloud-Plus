package com.cdzeroly.wvp.i1.handler;

import com.cdzeroly.wvp.i1.packet.GCameraVideoControlPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 启动/终止摄像视频传输 数据包处理程序
 */
@ChannelHandler.Sharable
@Slf4j
public class GCameraVideoControlHandler extends BaseAckHandler<GCameraVideoControlPacket> {

    @Override
    protected void channelRead(ChannelHandlerContext ctx, GCameraVideoControlPacket msg) {
        // 处理启动/终止摄像视频传输数据包
        log.info("Received camera video control packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GCameraVideoControlHandler", cause);
        ctx.close();
    }
}
