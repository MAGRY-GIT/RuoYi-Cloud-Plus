package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.response.cmd;

import com.alibaba.fastjson2.JSONObject;
import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.gb28181.transmit.callback.DeferredResultHolder;
import com.cdzeroly.wvp.gb28181.transmit.callback.RequestMessage;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.response.ResponseMessageHandler;
import com.cdzeroly.wvp.gb28181.utils.XmlUtil;
import com.cdzeroly.wvp.gb28181.service.IDeviceService;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import gov.nist.javax.sip.message.SIPRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.message.Response;
import java.text.ParseException;

/**
 * @author MGARY
 */
@Slf4j
@Component
@AllArgsConstructor
public class DeviceStatusResponseMessageHandler extends SIPRequestProcessorParent implements InitializingBean, IMessageHandler {

    private final ResponseMessageHandler responseMessageHandler;

    private final DeferredResultHolder deferredResultHolder;

    private final IDeviceService deviceService;

    private final IRedisCatchStorage redisCatchStorage;

    @Override
    public void afterPropertiesSet() throws Exception {
        String cmdType = "DeviceStatus";
        responseMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {
        log.info("接收到DeviceStatus应答消息");
        // 检查设备是否存在， 不存在则不回复
        if (device == null) {
            return;
        }
        // 回复200 OK
        try {
             responseAck((SIPRequest) evt.getRequest(), Response.OK);
        } catch (SipException | InvalidArgumentException | ParseException e) {
            log.error("[命令发送失败] 国标级联 设备状态应答回复200OK: {}", e.getMessage());
        }
        Element deviceIdElement = element.element("DeviceID");
        Element onlineElement = element.element("Online");
        String channelId = deviceIdElement.getText();
        JSONObject json = new JSONObject();
        XmlUtil.node2Json(element, json);
        if (log.isDebugEnabled()) {
            log.debug(json.toJSONString());
        }
        String text = onlineElement.getText();
        if ("ONLINE".equalsIgnoreCase(text.trim())) {
            deviceService.online(device, null);
        }else {
            deviceService.offline(device.getDeviceId(), "设备状态查询结果：" + text.trim());
        }
        RequestMessage msg = new RequestMessage();
        msg.setKey(DeferredResultHolder.CALLBACK_CMD_DEVICESTATUS + device.getDeviceId());
        msg.setData(json);
        deferredResultHolder.invokeAllResult(msg);
    }

    @Override
    public void handForPlatform(RequestEvent evt, Platform parentPlatform, Element rootElement) {


    }
}
