package com.cdzeroly.wvp.i1.handler.v2022;

import com.cdzeroly.wvp.i1.handler.BaseAckHandler;
import com.cdzeroly.wvp.i1.packet.v2022.GPhotoVideoUploadRequestPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 监测装置请求上送照片/短视频 数据包处理程序
 */
@Slf4j
@ChannelHandler.Sharable
public class GPhotoVideoUploadRequestHandler extends BaseAckHandler<GPhotoVideoUploadRequestPacket> {

    @Override
    public void channelRead(ChannelHandlerContext ctx, GPhotoVideoUploadRequestPacket msg) {
        // 处理监测装置请求上送照片/短视频数据包
        log.info("Received photo video upload request packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GPhotoVideoUploadRequestHandler", cause);
        ctx.close();
    }
}
