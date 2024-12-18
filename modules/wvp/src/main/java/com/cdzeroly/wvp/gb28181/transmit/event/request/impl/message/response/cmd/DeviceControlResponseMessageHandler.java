package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.response.cmd;

import com.alibaba.fastjson2.JSONObject;
import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.gb28181.transmit.callback.DeferredResultHolder;
import com.cdzeroly.wvp.gb28181.transmit.callback.RequestMessage;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.response.ResponseMessageHandler;
import com.cdzeroly.wvp.vmanager.bean.ErrorCode;
import com.cdzeroly.wvp.vmanager.bean.WVPResult;
import gov.nist.javax.sip.message.SIPRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.message.Response;
import java.text.ParseException;

import static com.cdzeroly.wvp.gb28181.utils.XmlUtil.getText;

/**
 * @author MGARY
 */
@Slf4j
@Component
@AllArgsConstructor
public class DeviceControlResponseMessageHandler extends SIPRequestProcessorParent implements InitializingBean, IMessageHandler {

    private final ResponseMessageHandler responseMessageHandler;

    private final DeferredResultHolder deferredResultHolder;

    @Override
    public void afterPropertiesSet() throws Exception {
        String cmdType = "DeviceControl";
        responseMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {
        // 此处是对本平台发出DeviceControl指令的应答
        try {
             responseAck((SIPRequest) evt.getRequest(), Response.OK);
        } catch (SipException | InvalidArgumentException | ParseException e) {
            log.error("[命令发送失败] 国标级联 设备控制: {}", e.getMessage());
        }
        JSONObject json = new JSONObject();
        String channelId = getText(element, "DeviceID");
        String result = getText(element, "Result");

        RequestMessage msg = new RequestMessage();
        String key = DeferredResultHolder.CALLBACK_CMD_DEVICECONTROL +  device.getDeviceId() + channelId;
        msg.setKey(key);
        if ("OK".equalsIgnoreCase(result)) {
            msg.setData(WVPResult.success());
        }else {
            msg.setData(WVPResult.fail(ErrorCode.ERROR100));
        }
        if (log.isDebugEnabled()) {
            log.debug(json.toJSONString());
        }
        deferredResultHolder.invokeAllResult(msg);

    }

    @Override
    public void handForPlatform(RequestEvent evt, Platform parentPlatform, Element rootElement) {
    }
}
