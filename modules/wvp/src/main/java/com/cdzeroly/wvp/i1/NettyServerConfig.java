package com.cdzeroly.wvp.i1;


import com.cdzeroly.wvp.conf.ProtocolAutoConfig;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.packet.GPhotoUploadRequestPacket;
import com.cdzeroly.wvp.i1.packet.GRemoteImagePacket;
import com.cdzeroly.wvp.i1.packet.GRemoteImageReplenishPacket;
import com.cdzeroly.wvp.i1.task.PhotoUploadTask;
import com.cdzeroly.wvp.utils.BytesUtils;
import com.cdzeroly.wvp.utils.ImageUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * @author MGARY
 * @date 2019-11-01 14:43
 */
@Component
public class NettyServerConfig {
    private final AtomicBoolean running = new AtomicBoolean(true);
    @Autowired
    @Qualifier("serverChannelInitializer")
    private ServerChannelInitializer serverChannelInitializer;


    @Autowired
    private ProtocolAutoConfig protocolAutoConfig;

    @Autowired
    private NettyChannelManager nettyChannelManager;
    @Autowired
    private PhotoUploadTask photoUploadTask;


    /**
     * 定义Channel接口，用于业务关闭
     */
    private Channel serverChannel;

    @Autowired
    private NetService netService;


    /**
     * 配置Netty启动
     */
    public ServerBootstrap bootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //  boss 线程组用于处理连接工作-- work 线程组用于数据处理
        serverBootstrap.group(new NioEventLoopGroup(protocolAutoConfig.getBoss()), new NioEventLoopGroup(protocolAutoConfig.getWorker()))
            // 指定Channel
            .channel(NioServerSocketChannel.class)

            //使用指定的端口设置套接字地址
            .localAddress(new InetSocketAddress(protocolAutoConfig.getPort()))

            .handler(new LoggingHandler(LogLevel.DEBUG))

            //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
            .option(ChannelOption.SO_BACKLOG, protocolAutoConfig.getSoBacklog())

            //将小的数据包包装成更大的帧进行传送，提高网络的负载
            .childOption(ChannelOption.TCP_NODELAY, true)

            //设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
            .childOption(ChannelOption.SO_KEEPALIVE, protocolAutoConfig.getSoKeepAlive())
            //业务处理
            .childHandler(serverChannelInitializer);
        return serverBootstrap;
    }

    @PostConstruct
    public void start() throws Exception {
        serverChannel = bootstrap().bind().sync().channel();
        //定义数据发送任务
        new Thread(() -> {
            try {
                // 清空发送数据队列
                LinkedBlockingDeque<AbstractPacket> sendQueue = netService.getSendQueue();
                sendQueue.clear();
                while (running.get()) {
                    AbstractPacket packet = sendQueue.take();
                    String monitoringDeviceId = BytesUtils.b2d("", packet.getMonitoringDeviceId());
                    //发送数据包
                    nettyChannelManager.getChannel(monitoringDeviceId).ifPresent(channel -> channel.writeAndFlush(packet));

                }
            } catch (InterruptedException e) {
                System.out.println("服务被强制关闭!");
            } catch (Exception ignored) {
            }
        }).start();
        //图片定时任务
        photoUploadTask.start();



    }


    @PreDestroy
    public void stop() throws Exception {
        // 清空发送数据队列
        netService.getRecvQueue().clear();
        running.set(false);
        serverChannel.close();
        if (serverChannel != null && serverChannel.isActive()) {
            serverChannel.close().sync();
        }
        if (serverChannel != null && serverChannel.parent() != null && serverChannel.parent().isActive()) {
            serverChannel.parent().close().sync();
        }
    }

}
