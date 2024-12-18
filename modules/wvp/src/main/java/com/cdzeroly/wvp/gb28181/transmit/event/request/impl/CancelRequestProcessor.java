package com.cdzeroly.wvp.gb28181.transmit.event.request.impl;

import com.cdzeroly.wvp.gb28181.transmit.SIPProcessorObserver;
import com.cdzeroly.wvp.gb28181.transmit.event.request.ISIPRequestProcessor;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sip.RequestEvent;

/**
 * SIP命令类型： CANCEL请求
 * @author MGARY
 */
@Component
@AllArgsConstructor
public class CancelRequestProcessor extends SIPRequestProcessorParent implements InitializingBean, ISIPRequestProcessor {

	private final SIPProcessorObserver sipProcessorObserver;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 添加消息处理的订阅
        String method = "CANCEL";
        sipProcessorObserver.addRequestProcessor(method, this);
	}

	/**
	 * 处理CANCEL请求
	 *
	 * @param evt 事件
	 */
	@Override
	public void process(RequestEvent evt) {
		// TODO 优先级99 Cancel Request消息实现，此消息一般为级联消息，上级给下级发送请求取消指令

	}

}
