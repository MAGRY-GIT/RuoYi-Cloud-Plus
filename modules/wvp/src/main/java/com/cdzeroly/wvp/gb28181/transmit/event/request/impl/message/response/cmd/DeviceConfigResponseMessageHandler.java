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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sip.RequestEvent;

import static com.cdzeroly.wvp.gb28181.utils.XmlUtil.getText;

/**
 * @author MGARY
 */
@Slf4j
@Component
@AllArgsConstructor
public class DeviceConfigResponseMessageHandler extends SIPRequestProcessorParent implements InitializingBean, IMessageHandler {

    private final ResponseMessageHandler responseMessageHandler;

    private final DeferredResultHolder deferredResultHolder;

    @Override
    public void afterPropertiesSet() throws Exception {
        String cmdType = "DeviceConfig";
        responseMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {
        JSONObject json = new JSONObject();
        XmlUtil.node2Json(element, json);
        String channelId = getText(element, "DeviceID");
        if (log.isDebugEnabled()) {
            log.debug(json.toJSONString());
        }
        String key = DeferredResultHolder.CALLBACK_CMD_DEVICECONFIG + device.getDeviceId() + channelId;
        RequestMessage msg = new RequestMessage();
        msg.setKey(key);
        msg.setData(json);
        deferredResultHolder.invokeAllResult(msg);
    }

    @Override
    public void handForPlatform(RequestEvent evt, Platform parentPlatform, Element rootElement) {
    }
}
