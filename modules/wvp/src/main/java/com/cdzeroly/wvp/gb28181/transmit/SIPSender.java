package com.cdzeroly.wvp.gb28181.transmit;

import com.cdzeroly.wvp.common.NetProtocol;
import com.cdzeroly.wvp.conf.SipConfig;
import com.cdzeroly.wvp.gb28181.SipLayer;
import com.cdzeroly.wvp.gb28181.event.SipSubscribe;
import com.cdzeroly.wvp.gb28181.event.sip.SipEvent;
import com.cdzeroly.wvp.gb28181.utils.SipUtils;
import com.cdzeroly.wvp.utils.GitUtil;
import gov.nist.javax.sip.SipProviderImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.sip.SipException;
import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.UserAgentHeader;
import javax.sip.header.ViaHeader;
import javax.sip.message.Message;
import javax.sip.message.Request;
import javax.sip.message.Response;
import java.text.ParseException;

/**
 * 发送SIP消息
 *
 * @author lin
 */
@Slf4j
@Component
@AllArgsConstructor
public class SIPSender {

    private final SipLayer sipLayer;

    private final GitUtil gitUtil;

    private final SipSubscribe sipSubscribe;

    private final SipConfig sipConfig;

    /**
     * 发送请求
     * @param ip IP
     * @param message  消息
     * @throws SipException  SIP异常
     * @throws ParseException 解析异常
     */
    public void transmitRequest(String ip, Message message) throws SipException, ParseException {
        transmitRequest(ip, message, null, null, null);
    }

    /**
     *  发送请求
     * @param ip IP
     * @param message 消息
     * @param errorEvent  错误事件
     * @throws SipException SIP异常
     * @throws ParseException  解析异常
     */
    public void transmitRequest(String ip, Message message, SipSubscribe.Event errorEvent) throws SipException, ParseException {
        transmitRequest(ip, message, errorEvent, null, null);
    }

    /**
     *  发送请求
     * @param ip IP
     * @param message 消息
     * @param errorEvent 错误事件
     * @param okEvent 正确事件
     * @throws SipException 异常
     */
    public void transmitRequest(String ip, Message message, SipSubscribe.Event errorEvent, SipSubscribe.Event okEvent) throws SipException {
        transmitRequest(ip, message, errorEvent, okEvent, null);
    }

    /**
     * 传输请求
     * @param ip  IP
     * @param message 消息
     * @param errorEvent 错误事件
     * @param okEvent  正确事件
     * @param timeout  超时时间
     * @throws SipException  异常
     */
    public void transmitRequest(String ip, Message message, SipSubscribe.Event errorEvent, SipSubscribe.Event okEvent, Long timeout) throws SipException {
        ViaHeader viaHeader = (ViaHeader) message.getHeader(ViaHeader.NAME);
        String transport = NetProtocol.UDP.name();
        if (viaHeader == null) {
            log.warn("[消息头缺失]： ViaHeader， 使用默认的UDP方式处理数据");
        } else {
            transport = viaHeader.getTransport();
        }
        if (message.getHeader(UserAgentHeader.NAME) == null) {
            try {
                message.addHeader(SipUtils.createUserAgentHeader(gitUtil));
            } catch (ParseException e) {
                log.error("添加UserAgentHeader失败", e);
            }
        }

        if (okEvent != null || errorEvent != null) {
            CallIdHeader callIdHeader = (CallIdHeader) message.getHeader(CallIdHeader.NAME);
            CSeqHeader cSeqHeader = (CSeqHeader) message.getHeader(CSeqHeader.NAME);
            String key = callIdHeader.getCallId() + cSeqHeader.getSeqNumber();

            SipEvent sipEvent = SipEvent.getInstance(key, eventResult -> {
                if(okEvent != null) {
                    okEvent.response(eventResult);
                }
            }, (eventResult -> {
                sipSubscribe.removeSubscribe(callIdHeader.getCallId());
                if (errorEvent != null) {
                    errorEvent.response(eventResult);
                }
            }), timeout == null ? sipConfig.getTimeout() : timeout);

            //添加事件至订阅
            sipSubscribe.addSubscribe(key, sipEvent);
        }

        if (NetProtocol.TCP.name().equals(transport)) {
            SipProviderImpl tcpSipProvider = sipLayer.getTcpSipProvider(ip);
            if (tcpSipProvider == null) {
                log.error("[发送信息失败] 未找到tcp://{}的监听信息", ip);
                return;
            }
            if (message instanceof Request) {
                tcpSipProvider.sendRequest((Request) message);
            } else if (message instanceof Response) {
                tcpSipProvider.sendResponse((Response) message);
            }

        } else if (NetProtocol.UDP.name().equals(transport)) {
            SipProviderImpl sipProvider = sipLayer.getUdpSipProvider(ip);
            if (sipProvider == null) {
                log.error("[发送信息失败] 未找到udp://{}的监听信息", ip);
                return;
            }
            if (message instanceof Request) {
                sipProvider.sendRequest((Request) message);
            } else if (message instanceof Response) {
                sipProvider.sendResponse((Response) message);
            }
        }
    }

    /**
     * 获取新的CallId标头
     * @param ip  IP
     * @param transport  协议类型
     * @return CallIdHeader
     */
    public CallIdHeader getNewCallIdHeader(String ip, String transport) {
        if (ObjectUtils.isEmpty(transport)) {
            return sipLayer.getUdpSipProvider().getNewCallId();
        }
        SipProviderImpl sipProvider;
        if (ObjectUtils.isEmpty(ip)) {
            sipProvider = NetProtocol.TCP.name().equalsIgnoreCase(transport) ? sipLayer.getTcpSipProvider()
                    : sipLayer.getUdpSipProvider();
        } else {
            sipProvider = NetProtocol.TCP.name().equalsIgnoreCase(transport) ? sipLayer.getTcpSipProvider(ip)
                    : sipLayer.getUdpSipProvider(ip);
        }

        if (sipProvider == null) {
            sipProvider = sipLayer.getUdpSipProvider();
        }

        if (sipProvider != null) {
            return sipProvider.getNewCallId();
        } else {
            log.warn("[新建CallIdHeader失败]， ip={}, transport={}", ip, transport);
            return null;
        }
    }


}
