package com.cdzeroly.wvp.i1.handler;

import com.cdzeroly.wvp.i1.packet.GCameraControlPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 摄像头控制处理程序
 */
@ChannelHandler.Sharable
@Slf4j
public class GCameraControlHandler extends BaseAckHandler<GCameraControlPacket> {

    @Override
    protected void channelRead(ChannelHandlerContext ctx, GCameraControlPacket msg) {
        // 处理摄像头控制数据
        log.info("Received camera control packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GCameraControlHandler", cause);
        ctx.close();
    }
}
