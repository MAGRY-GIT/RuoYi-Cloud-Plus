package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.query;

import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.MessageHandlerAbstract;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.MessageRequestProcessor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 命令类型： 查询指令
 * 命令类型： 设备状态, 设备目录信息, 设备信息, 文件目录检索(TODO), 报警(TODO), 设备配置(TODO), 设备预置位(TODO), 移动设备位置数据(TODO)
 * @author MAGRY
 */
@Component
@AllArgsConstructor
public class QueryMessageHandler extends MessageHandlerAbstract implements InitializingBean  {

    private final MessageRequestProcessor messageRequestProcessor;

    @Override
    public void afterPropertiesSet() throws Exception {
        String messageType = "Query";
        messageRequestProcessor.addHandler(messageType, this);
    }
}
