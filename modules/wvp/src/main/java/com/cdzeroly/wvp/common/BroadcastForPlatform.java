package com.cdzeroly.wvp.common;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;


/**
 * @author MGARY
 * 国标级联语音喊话发流模式
 * UDP:udp传输
 * TCP-ACTIVE：tcp主动模式
 * TCP-PASSIVE：tcp被动模式
 */
@Getter
public enum BroadcastForPlatform  implements IEnum<String> {

    /**
     * udp传输
     */
    UDP("UDP",0),
    /**
     * tcp被动模式
     */
    TCP_PASSIVE("TCP-PASSIVE",1),
    /**
     * tcp主动模式
     */
    TCP_ACTIVE("TCP-ACTIVE",2);


    /**
     * 协议类型
     */
    public final String protocol;
    public final Integer tcpMode;

    BroadcastForPlatform(String protocol,Integer tcpMode) {
        this.protocol = protocol;
        this.tcpMode = tcpMode;
    }

     public static Optional<BroadcastForPlatform> protocol(String protocol) {
        return Arrays.stream(BroadcastForPlatform.values()).filter(broadcastForPlatform -> broadcastForPlatform.protocol.equals(protocol)).findFirst();
     }


    @Override
    public String getValue() {
        return this.protocol;
    }
}
