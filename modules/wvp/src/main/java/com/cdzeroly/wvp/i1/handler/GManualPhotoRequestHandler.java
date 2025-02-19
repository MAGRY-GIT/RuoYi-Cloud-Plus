package com.cdzeroly.wvp.i1.handler;

import com.cdzeroly.wvp.i1.packet.GManualPhotoRequestPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 手动请求拍摄照片 数据包处理程序
 */
@Slf4j
@ChannelHandler.Sharable
public class GManualPhotoRequestHandler extends BaseAckHandler<GManualPhotoRequestPacket> {

    @Override
    protected void channelRead(ChannelHandlerContext ctx, GManualPhotoRequestPacket msg) {
        // 处理手动请求拍摄照片数据包
        log.info("Received manual photo request packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GManualPhotoRequestHandler", cause);
        ctx.close();
    }
}
