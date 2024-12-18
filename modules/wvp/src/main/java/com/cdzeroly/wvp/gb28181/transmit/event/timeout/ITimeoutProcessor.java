package com.cdzeroly.wvp.gb28181.transmit.event.timeout;

import javax.sip.TimeoutEvent;

/**
 * 超时处理器
 * @author MGARY
 */
public interface ITimeoutProcessor {
    /**
     * 处理
     * @param event  事件
     */
    void process(TimeoutEvent event);
}
