package com.cdzeroly.wvp.i1.handler;


import com.cdzeroly.wvp.i1.NetService;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 空闲检测
 *
 * @author MGARY
 * @create 2018-10-25 16:21
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class ServerIdleStateHandler extends IdleStateHandler {

    @Autowired
    NetService netService;
    /**
     * 写操作空闲3秒
     */
    private final static int WRITER_IDLE_TIME_SECONDS = 6*60;
    /**
     * 读写全部空闲6秒
     */
    private final static int ALL_IDLE_TIME_SECONDS = 3*60;
    /**
     * 读操作空闲3秒
     */
    private static final int READER_IDLE_TIME = 3*60;

    public ServerIdleStateHandler() {
        super(READER_IDLE_TIME, WRITER_IDLE_TIME_SECONDS, ALL_IDLE_TIME_SECONDS, TimeUnit.SECONDS);
    }
    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        //信道空闲检测
        super.channelIdle(ctx, evt);
        log.info("{} 秒内没有读取到数据,关闭连接", READER_IDLE_TIME);
        //ctx.channel().close();
        // 线程空闲时清理垃圾
        clearTimeoutPacket(evt);

    }
    // 清除超时数据
    private void clearTimeoutPacket(IdleStateEvent evt) throws InterruptedException {
        if (evt.state() == IdleState.ALL_IDLE) {
            LinkedBlockingDeque<AbstractPacket> recvQueue = netService.getRecvQueue();
            int size = recvQueue.size();
            for (int i = 0; i < size; i++) {
                AbstractPacket packet = recvQueue.pollFirst();
                if (null == packet) {
                    break;
                }
                if (!isPacketTimeout(packet)) {
                    recvQueue.putLast(packet);
                } else {
                    System.out.println("清理掉一条垃圾数据 队列大小 = " + recvQueue.size());
                }
            }
        }
    }

    // 检测数据包是否超时
    private boolean isPacketTimeout(AbstractPacket packet) {
        return System.currentTimeMillis() - packet.getTime() > 5000;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //用户事件已触发
        super.userEventTriggered(ctx, evt);
        //远程主机强制关闭连接的处理方案
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        System.out.println("远程主机强制关闭连接的处理方案-->IP" + inetSocketAddress.getAddress().getHostAddress());


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //通道激活
        super.channelActive(ctx);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //通道未激活
        super.channelInactive(ctx);

    }


}
