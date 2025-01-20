package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message;

import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.Platform;
import com.cdzeroly.wvp.gb28181.service.IPlatformService;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.query.cmd.CatalogQueryMessageHandler;
import gov.nist.javax.sip.message.SIPRequest;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.message.Response;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.cdzeroly.wvp.gb28181.utils.XmlUtil.getText;

/**
 * @author MGARY
 */
@Slf4j
public abstract class MessageHandlerAbstract extends SIPRequestProcessorParent implements IMessageHandler{

     final Map<String, IMessageHandler> MESSAGE_HANDLER_MAP = new ConcurrentHashMap<>();

    @Autowired
    private IPlatformService platformService;

    public void addHandler(String cmdType, IMessageHandler messageHandler) {
        MESSAGE_HANDLER_MAP.put(cmdType, messageHandler);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {
        String cmd = getText(element, "CmdType");
        if (cmd == null) {
            try {
                responseAck((SIPRequest) evt.getRequest(), Response.OK);
            } catch (SipException | InvalidArgumentException | ParseException e) {
                log.error("[命令发送失败] 回复200 OK: {}", e.getMessage());
            }
            return;
        }
        IMessageHandler messageHandler = MESSAGE_HANDLER_MAP.get(cmd);

        if (messageHandler != null) {
            //两个国标平台互相级联时由于上一步判断导致本该在平台处理的消息 放到了设备的处理逻辑
            //所以对目录查询单独做了校验
            if(messageHandler instanceof CatalogQueryMessageHandler){
                Platform parentPlatform = platformService.queryPlatformByServerGBId(device.getDeviceId());
                messageHandler.handForPlatform(evt, parentPlatform, element);
                return;
            }
            messageHandler.handForDevice(evt, device, element);
        }
    }

    @Override
    public void handForPlatform(RequestEvent evt, Platform parentPlatform, Element element) {
        String cmd = getText(element, "CmdType");
        IMessageHandler messageHandler = MESSAGE_HANDLER_MAP.get(cmd);
        if (messageHandler != null) {
            messageHandler.handForPlatform(evt, parentPlatform, element);
        }
    }
}
