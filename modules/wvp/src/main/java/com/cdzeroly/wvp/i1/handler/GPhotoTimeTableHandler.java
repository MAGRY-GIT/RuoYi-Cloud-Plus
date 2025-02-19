package com.cdzeroly.wvp.i1.handler;

import com.cdzeroly.wvp.i1.packet.GPhotoTimeTablePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 拍照时间表设置 数据包处理程序
 */
@Slf4j
@ChannelHandler.Sharable
public class GPhotoTimeTableHandler extends BaseAckHandler<GPhotoTimeTablePacket> {

    @Override
    protected void channelRead(ChannelHandlerContext ctx, GPhotoTimeTablePacket msg) {
        // 处理拍照时间表设置数据包
        log.info("Received photo time table packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GPhotoTimeTableHandler", cause);
        ctx.close();
    }
}
