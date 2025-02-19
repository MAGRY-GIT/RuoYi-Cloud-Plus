package com.cdzeroly.wvp.i1.codec;


import cn.hutool.core.util.ObjectUtil;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.packet.*;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * @author MGARY
 */
@Slf4j
public class ServerFrameDecoder extends BaseFrameDecoder {
    private static final Map<Byte, Class<? extends AbstractPacket>> PACKET_MAP = new HashMap<>();

    static {
        try (ScanResult scanResult = new ClassGraph().enableClassInfo().acceptPackages("com.cdzeroly.wvp.i1.packet").scan()) {
            ClassInfoList classInfoList = scanResult.getSubclasses(AbstractPacket.class.getName());

            for (ClassInfo classInfo : classInfoList) {
                Class<?> clazz = Class.forName(classInfo.getName());
                if (AbstractPacket.class.isAssignableFrom(clazz)) {
                    AbstractPacket instance = (AbstractPacket) clazz.getDeclaredConstructor().newInstance();
                    PACKET_MAP.put(instance.getMessageType(), clazz.asSubclass(AbstractPacket.class));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static Optional<AbstractPacket> createPacket(Byte type, ByteBuf data) {
        Class<? extends AbstractPacket> packetClass = PACKET_MAP.get(type);
        if (packetClass == null) {
            return Optional.empty();
        }
        try {
            if (ObjectUtil.isNull(data)) {
                // 通过反射调用构造方法，传入 ByteBuf 参数
                return Optional.of(packetClass.getConstructor(ByteBuf.class).newInstance(data));
            } else {
                return Optional.of(packetClass.getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create packet instance", e);
        }
    }

    public ServerFrameDecoder() {


    }


    @Override
    public Optional<AbstractPacket> decodeData(byte messageType, ByteBuf data, byte[] monitoringDeviceId, byte frameType, byte serialNumber) {
        Optional<AbstractPacket> packet;
        try {
          packet = createPacket(messageType, data);
        } finally {
            // 显式释放 ByteBuf
            if (data != null) {
                ReferenceCountUtil.release(data);
            }
        }

        packet.ifPresent(packetData -> {
            packetData.setMonitoringDeviceId(monitoringDeviceId);
            packetData.setSerialNumber(serialNumber);
            packetData.setFrameType(serialNumber);
        });

        return packet;
    }


}
