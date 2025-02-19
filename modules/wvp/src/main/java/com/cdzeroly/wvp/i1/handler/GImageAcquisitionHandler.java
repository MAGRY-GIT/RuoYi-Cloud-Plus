package com.cdzeroly.wvp.i1.handler;

import com.cdzeroly.wvp.i1.packet.GImageAcquisitionPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 图像采集参数查询/设置 数据包处理程序
 */
@Slf4j
@ChannelHandler.Sharable
public class GImageAcquisitionHandler extends BaseAckHandler<GImageAcquisitionPacket> {

    @Override
    protected void channelRead(ChannelHandlerContext ctx, GImageAcquisitionPacket msg) {
        // 处理图像采集参数查询/设置数据包
        log.info("Received image acquisition packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GImageAcquisitionHandler", cause);
        ctx.close();
    }
}
