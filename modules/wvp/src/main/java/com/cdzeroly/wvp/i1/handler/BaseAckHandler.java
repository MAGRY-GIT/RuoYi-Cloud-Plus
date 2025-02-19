package com.cdzeroly.wvp.i1.handler;

import com.cdzeroly.wvp.i1.NetService;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author MGARY
 * @version 1.0
 * @description 基础应答处理器
 * @date 2020/12/13
 **/
@ChannelHandler.Sharable
@Component
public abstract class BaseAckHandler<T extends AbstractPacket> extends SimpleChannelInboundHandler<T> {
    @Autowired
    private NetService netService;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg){
        try {
            netService.getRecvQueue().put(msg);
        } catch (InterruptedException e) {
           new Throwable("InterruptedException---加入消息至队列失败");
           e.printStackTrace();
        }
        channelRead(ctx,msg);
    }

   protected abstract void channelRead(ChannelHandlerContext ctx, T msg);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            ctx.close();
        }
    }
}
