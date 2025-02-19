package com.cdzeroly.wvp.i1.handler;

import com.cdzeroly.wvp.i1.packet.GCameraSchedulePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 摄像机定时工作时间表设置 数据包处理程序
 */
@ChannelHandler.Sharable
@Slf4j
public class GCameraScheduleHandler extends BaseAckHandler<GCameraSchedulePacket> {

    @Override
    protected void channelRead(ChannelHandlerContext ctx, GCameraSchedulePacket msg) {
        // 处理摄像机定时工作时间表设置数据包
        log.info("Received camera schedule packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GCameraScheduleHandler", cause);
        ctx.close();
    }
}
