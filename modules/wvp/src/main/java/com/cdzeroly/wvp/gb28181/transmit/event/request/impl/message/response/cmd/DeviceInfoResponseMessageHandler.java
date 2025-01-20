package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.response.cmd;

import com.cdzeroly.wvp.common.NetProtocol;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.Platform;
import com.cdzeroly.wvp.gb28181.transmit.callback.DeferredResultHolder;
import com.cdzeroly.wvp.gb28181.transmit.callback.RequestMessage;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.response.ResponseMessageHandler;
import com.cdzeroly.wvp.gb28181.service.IDeviceService;
import gov.nist.javax.sip.message.SIPRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.message.Response;
import java.text.ParseException;

import static com.cdzeroly.wvp.gb28181.utils.XmlUtil.getText;

/**
 * @author lin
 */
@Slf4j
@Component
@AllArgsConstructor
public class DeviceInfoResponseMessageHandler extends SIPRequestProcessorParent implements InitializingBean, IMessageHandler {

    private final ResponseMessageHandler responseMessageHandler;

    private final DeferredResultHolder deferredResultHolder;


    private final IDeviceService deviceService;

    @Override
    public void afterPropertiesSet() throws Exception {
        String cmdType = "DeviceInfo";
        responseMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element rootElement) {
        log.debug("接收到DeviceInfo应答消息");
        // 检查设备是否存在， 不存在则不回复
        if (device == null || !device.isOnLine()) {
            log.warn("[接收到DeviceInfo应答消息,但是设备已经离线]：" + (device != null ? device.getDeviceId():"" ));
            return;
        }
        SIPRequest request = (SIPRequest) evt.getRequest();
        try {
            rootElement = getRootElement(evt, device.getCharset());

            if (rootElement == null) {
                log.warn("[ 接收到DeviceInfo应答消息 ] content cannot be null, {}", evt.getRequest());
                try {
                    responseAck((SIPRequest) evt.getRequest(), Response.BAD_REQUEST);
                } catch (SipException | InvalidArgumentException | ParseException e) {
                    log.error("[命令发送失败] DeviceInfo应答消息 BAD_REQUEST: {}", e.getMessage());
                }
                return;
            }
            Element deviceIdElement = rootElement.element("DeviceID");
            String channelId = deviceIdElement.getTextTrim();
            String key = DeferredResultHolder.CALLBACK_CMD_DEVICE_INFO + device.getDeviceId() + channelId;
            device.setName(getText(rootElement, "DeviceName"));

            device.setManufacturer(getText(rootElement, "Manufacturer"));
            device.setModel(getText(rootElement, "Model"));
            device.setFirmware(getText(rootElement, "Firmware"));
            if (ObjectUtils.isEmpty(device.getStreamMode())) {
                device.setStreamMode(NetProtocol.TCP_PASSIVE.name());
            }
            deviceService.updateDevice(device);

            RequestMessage msg = new RequestMessage();
            msg.setKey(key);
            msg.setData(device);
            deferredResultHolder.invokeAllResult(msg);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        try {
            // 回复200 OK
            responseAck(request, Response.OK);
        } catch (SipException | InvalidArgumentException | ParseException e) {
            log.error("[命令发送失败] DeviceInfo应答消息 200: {}", e.getMessage());
        }

    }

    @Override
    public void handForPlatform(RequestEvent evt, Platform parentPlatform, Element rootElement) {

    }
}
