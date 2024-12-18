package com.cdzeroly.wvp.gb28181.transmit.event.timeout.impl;

import com.cdzeroly.wvp.gb28181.event.SipSubscribe;
import com.cdzeroly.wvp.gb28181.event.sip.SipEvent;
import com.cdzeroly.wvp.gb28181.transmit.SIPProcessorObserver;
import com.cdzeroly.wvp.gb28181.transmit.event.timeout.ITimeoutProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sip.TimeoutEvent;
import javax.sip.header.CallIdHeader;

/**
 * 超时处理器实现
 * @author MGARY
 */
@Slf4j
@Component
@AllArgsConstructor
public class TimeoutProcessorImpl implements InitializingBean, ITimeoutProcessor {

    private final SIPProcessorObserver processorObserver;

    private final SipSubscribe sipSubscribe;

    @Override
    public void afterPropertiesSet() throws Exception {
        processorObserver.addTimeoutProcessor(this);
    }

    @Override
    public void process(TimeoutEvent event) {
        try {
            // TODO Auto-generated method stub
            CallIdHeader callIdHeader = event.getClientTransaction().getDialog().getCallId();
            String callId = callIdHeader.getCallId();
            SipEvent sipEvent = sipSubscribe.getSubscribe(callId);
            if (sipEvent != null && sipEvent.getErrorEvent() != null) {
                SipSubscribe.EventResult<TimeoutEvent> timeoutEventEventResult = new SipSubscribe.EventResult<>(event);
                sipEvent.getErrorEvent().response(timeoutEventEventResult);
                sipSubscribe.removeSubscribe(callId);
            }
        } catch (Exception e) {
            log.error("[超时事件失败]: {}", e.getMessage());
        }
    }
}
