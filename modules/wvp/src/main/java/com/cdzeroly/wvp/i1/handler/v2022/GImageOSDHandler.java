package com.cdzeroly.wvp.i1.handler.v2022;

import com.cdzeroly.wvp.i1.handler.BaseAckHandler;
import com.cdzeroly.wvp.i1.packet.v2020.GImageOSDPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 图像OSD查询/设置报 数据包处理程序
 */
@Slf4j
@ChannelHandler.Sharable
public class GImageOSDHandler extends BaseAckHandler<GImageOSDPacket> {

    @Override
    public void channelRead(ChannelHandlerContext ctx, GImageOSDPacket msg) {
        // 处理图像OSD查询/设置数据包
        log.info("Received image OSD packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GImageOSDHandler", cause);
        ctx.close();
    }
}
