package com.cdzeroly.wvp.i1.handler;


import com.cdzeroly.wvp.i1.packet.GHeartPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 心跳处理程序
 */
@ChannelHandler.Sharable
@Slf4j
public class GHeartHandler extends BaseAckHandler<GHeartPacket> {

    @Override
    protected void channelRead(ChannelHandlerContext ctx, GHeartPacket msg) {
        // 将心跳数据原样写回去
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if(channel.isActive()) {
            ctx.close();
        }
    }
}
