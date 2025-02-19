package com.cdzeroly.wvp.i1;


import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author MAGRY
 */
@Component
@Slf4j
public class NettyChannelManager {

    /**
     * {@link Channel#attr(AttributeKey)} 属性中，表示 Channel 对应的用户
     */
    private static final AttributeKey<String> CHANNEL_ATTR_KEY_VEHICLE_NUMBER = AttributeKey.newInstance("vehicleNumber");


    /**
     * Channel 映射
     */
    private final ConcurrentMap<ChannelId, Channel> CHANNELS = new ConcurrentHashMap<>();
    /**
     * 用户与 Channel 的映射。
     *
     * 通过它，可以获取用户对应的 Channel。这样，我们可以向指定用户发送消息。
     */
    private final ConcurrentMap<String, Channel> MONITORING_DEVICE_ID_CHANNELS = new ConcurrentHashMap<>();

    /**
     * 添加 Channel 到 {@link #CHANNELS} 中
     *
     * @param channel Channel
     */
    public void add(Channel channel) {
        CHANNELS.put(channel.id(), channel);
        log.info("[add][一个连接({})加入]", channel.id());
    }

    /**
     * 添加指定用户到 {@link #MONITORING_DEVICE_ID_CHANNELS} 中
     *
     * @param channel Channel
     * @param monitoringDeviceId 用户
     */
    public void addMonitoringDeviceId(Channel channel, String  monitoringDeviceId) {
        Channel existChannel = CHANNELS.get(channel.id());
        if (existChannel == null) {
            log.error("[addUser][连接({}) 不存在]", channel.id());
            return;
        }
        // 设置属性
        channel.attr(CHANNEL_ATTR_KEY_VEHICLE_NUMBER).set(monitoringDeviceId);
        // 添加到 userChannels
        MONITORING_DEVICE_ID_CHANNELS.put(monitoringDeviceId, channel);
    }

    /**
     *
     * @param monitoringDeviceId 用户
     */
    public Optional<Channel> getChannel(String  monitoringDeviceId) {
        Channel channel = MONITORING_DEVICE_ID_CHANNELS.get(monitoringDeviceId);
        if (channel == null) {
            return Optional.empty();
        }
        // 设置属性
        // 添加到 userChannels
       return Optional.of(channel);
    }


    /**
     * 将 Channel 从 {@link #CHANNELS} 和 {@link #MONITORING_DEVICE_ID_CHANNELS} 中移除
     *
     * @param channel Channel
     */
    public void remove(Channel channel) {
        // 移除 channels
        CHANNELS.remove(channel.id());
        // 移除 userChannels
        if (channel.hasAttr(CHANNEL_ATTR_KEY_VEHICLE_NUMBER)) {
            MONITORING_DEVICE_ID_CHANNELS.remove(channel.attr(CHANNEL_ATTR_KEY_VEHICLE_NUMBER).get());
        }
        log.info("[remove][一个连接({})离开]", channel.id());
    }
}
