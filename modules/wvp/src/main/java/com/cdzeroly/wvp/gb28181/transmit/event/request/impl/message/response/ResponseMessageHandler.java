package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.response;

import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.MessageHandlerAbstract;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.MessageRequestProcessor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 命令类型： 请求动作的应答
 * 命令类型： 设备控制, 报警通知, 设备目录信息查询, 目录信息查询, 目录收到, 设备信息查询, 设备状态信息查询 ......
 * @author MGARY
 */
@Component
@AllArgsConstructor
public class ResponseMessageHandler extends MessageHandlerAbstract implements InitializingBean  {

    private final MessageRequestProcessor messageRequestProcessor;

    @Override
    public void afterPropertiesSet() throws Exception {
        String messageType = "Response";
        messageRequestProcessor.addHandler(messageType, this);
    }
}
