package com.cdzeroly.wvp.i1;


import com.cdzeroly.wvp.i1.codec.ServerFrameDecoder;
import com.cdzeroly.wvp.i1.codec.TCPFrameEncoder;
import com.cdzeroly.wvp.i1.handler.GHeartHandler;
import com.cdzeroly.wvp.i1.handler.GPhotoUploadRequestHandler;
import com.cdzeroly.wvp.i1.handler.IdleServerHandler;
import com.cdzeroly.wvp.i1.handler.ServerIdleStateHandler;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 初始化频道
 *
 * @author Administrator
 */
@Component
@Slf4j
@Qualifier("serverChannelInitializer")
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static final Map<String, Class<? extends ChannelHandler>> PACKET_MAP = new HashMap<>();
    static {
        try (ScanResult scanResult = new ClassGraph()
                .enableClassInfo()
                .acceptPackages("com.cdzeroly.wvp.i1.handler")
                // 忽略 ServerIdleStateHandler 类
                .rejectClasses(ServerIdleStateHandler.class.getName())
                .rejectClasses(GPhotoUploadRequestHandler.class.getName())
                .scan()) {
            ClassInfoList classInfoList = scanResult.getClassesImplementing(ChannelHandler.class.getName());

            for (ClassInfo classInfo : classInfoList) {
                Class<? > clazz = Class.forName(classInfo.getName());
                if (ChannelHandler.class.isAssignableFrom(clazz)) {
                    @SuppressWarnings("unchecked")
                    Class<? extends ChannelHandler> handlerClass = (Class<? extends ChannelHandler>) clazz;
                    PACKET_MAP.put(classInfo.getSimpleName(), handlerClass);
                } else {
                    log.warn("Class {} does not implement ChannelHandler", classInfo.getName());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Autowired
    private ServerIdleStateHandler serverIdleStateHandler;

    @Autowired
    private GPhotoUploadRequestHandler gPhotoUploadRequestHandler;


    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline p = socketChannel.pipeline();

        //空闲检测
        p.addLast("idleStateHandler", serverIdleStateHandler);
        p.addLast("idleTimeoutHandler", new IdleServerHandler());
        //编码解码器
        p.addLast(new LengthFieldBasedFrameDecoder(1444, 2, 2, 22, 4, true));
        p.addLast(new ServerFrameDecoder());
        p.addLast(new TCPFrameEncoder());

        p.addLast(new GHeartHandler());
        PACKET_MAP.forEach((key, value) -> {
            try {
                p.addLast(key, value.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException("Failed to create packet instance", e);
            }
        });

        p.addLast(gPhotoUploadRequestHandler);
    }
}
