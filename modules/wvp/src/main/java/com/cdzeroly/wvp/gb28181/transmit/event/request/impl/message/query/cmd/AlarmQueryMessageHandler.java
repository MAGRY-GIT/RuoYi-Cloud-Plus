package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.query.cmd;

import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.query.QueryMessageHandler;
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
public class AlarmQueryMessageHandler extends SIPRequestProcessorParent implements InitializingBean, IMessageHandler {

    private final QueryMessageHandler queryMessageHandler;

    @Override
    public void afterPropertiesSet() throws Exception {
        String cmdType = "Alarm";
        queryMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {

    }

    @Override
    public void handForPlatform(RequestEvent evt, Platform parentPlatform, Element rootElement) {

        log.info("不支持alarm查询");
        try {
             responseAck((SIPRequest) evt.getRequest(), Response.NOT_FOUND, "not support alarm query");
        } catch (SipException | InvalidArgumentException | ParseException e) {
            log.error("[命令发送失败] 国标级联 alarm查询回复200OK: {}", e.getMessage());
        }

    }
}
