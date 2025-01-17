package com.cdzeroly.wvp.gb28181.transmit.event.response.impl;

import com.cdzeroly.wvp.gb28181.transmit.SIPProcessorObserver;
import com.cdzeroly.wvp.gb28181.transmit.event.response.SIPResponseProcessorAbstract;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sip.ResponseEvent;

/**
 * @author panlinlin
 * @description: CANCEL响应处理器
 * @date 2021年11月5日 16:35
 */
@Component
@RequiredArgsConstructor
public class CancelResponseProcessor extends SIPResponseProcessorAbstract {


    private final SIPProcessorObserver sipProcessorObserver;

    @Override
    public void afterPropertiesSet() throws Exception {
        String method = "CANCEL";

        // 添加消息处理的订阅
        sipProcessorObserver.addResponseProcessor(method, this);
    }

    /**
     * 处理CANCEL响应
     *
     * @param evt
     */
    @Override
    public void process(ResponseEvent evt) {
        // TODO Auto-generated method stub

    }

}
