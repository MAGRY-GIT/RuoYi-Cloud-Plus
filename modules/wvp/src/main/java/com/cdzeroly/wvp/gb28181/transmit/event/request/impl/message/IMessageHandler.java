package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message;

import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.Platform;
import org.dom4j.Element;

import javax.sip.RequestEvent;

/**
 * @author MGARY
 */
public interface IMessageHandler {
    /**
     * 处理来自设备的信息
     * @param evt 请求事件
     * @param device 设备
     */
    void handForDevice(RequestEvent evt, Device device, Element element);

    /**
     * 处理来自平台的信息
     * @param evt  事件
     * @param parentPlatform 平台
     */
    void handForPlatform(RequestEvent evt, Platform parentPlatform, Element element);
}
