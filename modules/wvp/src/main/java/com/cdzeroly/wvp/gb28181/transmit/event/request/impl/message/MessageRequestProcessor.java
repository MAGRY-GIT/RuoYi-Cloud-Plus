package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message;

import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.event.device.DeviceNotFoundEvent;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.domain.bean.SsrcTransaction;
import com.cdzeroly.wvp.gb28181.event.SipSubscribe;
import com.cdzeroly.wvp.gb28181.event.sip.SipEvent;
import com.cdzeroly.wvp.gb28181.service.IPlatformService;
import com.cdzeroly.wvp.gb28181.session.SipInviteSessionManager;
import com.cdzeroly.wvp.gb28181.transmit.SIPProcessorObserver;
import com.cdzeroly.wvp.gb28181.transmit.event.request.ISIPRequestProcessor;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.cdzeroly.wvp.gb28181.utils.SipUtils;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import gov.nist.javax.sip.message.SIPRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.header.CallIdHeader;
import javax.sip.message.Response;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息请求处理器
 * @author MGARY
 */
@Slf4j
@Component
@AllArgsConstructor
public class MessageRequestProcessor extends SIPRequestProcessorParent implements InitializingBean, ISIPRequestProcessor {

    private static final Map<String, IMessageHandler> MESSAGE_HANDLER_MAP = new ConcurrentHashMap<>();

    private final SIPProcessorObserver sipProcessorObserver;

    private final IPlatformService platformService;

    private final SipSubscribe sipSubscribe;

    private final IRedisCatchStorage redisCatchStorage;

    private final SipInviteSessionManager sessionManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 添加消息处理的订阅
        String method = "MESSAGE";
        sipProcessorObserver.addRequestProcessor(method, this);
    }

    public void addHandler(String name, IMessageHandler handler) {
        MESSAGE_HANDLER_MAP.put(name, handler);
    }

    @Override
    public void process(RequestEvent evt) {
        SIPRequest sipRequest = (SIPRequest)evt.getRequest();
//        logger.info("接收到消息：" + evt.getRequest());
        String deviceId = SipUtils.getUserIdFromFromHeader(evt.getRequest());
        CallIdHeader callIdHeader = sipRequest.getCallIdHeader();
        // 先从会话内查找
        SsrcTransaction ssrcTransaction = sessionManager.getSsrcTransactionByCallId(callIdHeader.getCallId());
        // 兼容海康 媒体通知 消息from字段不是设备ID的问题
        if (ssrcTransaction != null) {
            deviceId = ssrcTransaction.getDeviceId();
        }
        SIPRequest request = (SIPRequest) evt.getRequest();
        // 查询设备是否存在
        Device device = redisCatchStorage.getDevice(deviceId);
        // 查询上级平台是否存在
        Platform parentPlatform = platformService.queryPlatformByServerGBId(deviceId);
        try {
            if (device != null && parentPlatform != null) {
                String hostAddress = request.getRemoteAddress().getHostAddress();
                int remotePort = request.getRemotePort();
                if (device.getHostAddress().equals(hostAddress + ":" + remotePort)) {
                    parentPlatform = null;
                }else {
                    device = null;
                }
            }
            if (device == null && parentPlatform == null) {
                // 不存在则回复404
                responseAck(request, Response.NOT_FOUND, "device "+ deviceId +" not found");
                log.warn("[设备未找到 ]deviceId: {}, callId: {}", deviceId, callIdHeader.getCallId());
                SipEvent sipEvent = sipSubscribe.getSubscribe(callIdHeader.getCallId());
                if (sipEvent != null && sipEvent.getErrorEvent() != null){
                    DeviceNotFoundEvent deviceNotFoundEvent = new DeviceNotFoundEvent(evt.getDialog());
                    deviceNotFoundEvent.setCallId(callIdHeader.getCallId());
                    SipSubscribe.EventResult eventResult = new SipSubscribe.EventResult(deviceNotFoundEvent);
                    sipEvent.getErrorEvent().response(eventResult);
                }
            }else {
                Element rootElement;
                try {
                    rootElement = getRootElement(evt);
                    if (rootElement == null) {
                        log.error("处理MESSAGE请求  未获取到消息体{}", evt.getRequest());
                        responseAck(request, Response.BAD_REQUEST, "content is null");
                        return;
                    }
                    String name = rootElement.getName();
                    IMessageHandler messageHandler = MESSAGE_HANDLER_MAP.get(name);
                    if (messageHandler != null) {
                        if (device != null) {
                            messageHandler.handForDevice(evt, device, rootElement);
                        }else { // 由于上面已经判断都为null则直接返回，所以这里device和parentPlatform必有一个不为null
                            messageHandler.handForPlatform(evt, parentPlatform, rootElement);
                        }
                    }else {
                        // 不支持的message
                        // 不存在则回复415
                        responseAck(request, Response.UNSUPPORTED_MEDIA_TYPE, "Unsupported message type, must Control/Notify/Query/Response");
                    }
                } catch (DocumentException e) {
                    log.warn("解析XML消息内容异常", e);
                    // 不存在则回复404
                    responseAck(request, Response.BAD_REQUEST, e.getMessage());
                }
            }
        } catch (SipException e) {
            log.warn("SIP 回复错误", e);
        } catch (InvalidArgumentException e) {
            log.warn("参数无效", e);
        } catch (ParseException e) {
            log.warn("SIP回复时解析异常", e);
        }
    }


}
