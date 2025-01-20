package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.query.cmd;

import com.cdzeroly.wvp.domain.CommonGbChannel;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.Platform;
import com.cdzeroly.wvp.gb28181.service.IGbChannelService;
import com.cdzeroly.wvp.gb28181.service.IPlatformChannelService;
import com.cdzeroly.wvp.gb28181.transmit.cmd.impl.SIPCommanderForPlatform;
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
import javax.sip.header.FromHeader;
import javax.sip.message.Response;
import java.text.ParseException;
import java.util.List;

/**
 * 目录查询消息处理程序
 * @author MGARY
 */
@Slf4j
@Component
@AllArgsConstructor
public class CatalogQueryMessageHandler extends SIPRequestProcessorParent implements InitializingBean, IMessageHandler {

    private final QueryMessageHandler queryMessageHandler;

    private final IGbChannelService channelService;

    private final IPlatformChannelService platformChannelService;

    private final SIPCommanderForPlatform cmderFroPlatform;


    @Override
    public void afterPropertiesSet() throws Exception {
        String cmdType = "Catalog";
        queryMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {
        try {
            // 回复200 OK
            responseAck((SIPRequest) evt.getRequest(), Response.FORBIDDEN);
        } catch (SipException | InvalidArgumentException | ParseException ignored) {}
    }

    @Override
    public void handForPlatform(RequestEvent evt, Platform platform, Element rootElement) {

        FromHeader fromHeader = (FromHeader) evt.getRequest().getHeader(FromHeader.NAME);
        try {
            // 回复200 OK
             responseAck((SIPRequest) evt.getRequest(), Response.OK);
        } catch (SipException | InvalidArgumentException | ParseException e) {
            log.error("[命令发送失败] 国标级联 目录查询回复200OK: {}", e.getMessage());
        }
        Element snElement = rootElement.element("SN");
        String sn = snElement.getText();
        List<CommonGbChannel> channelList = platformChannelService.queryByPlatform(platform);

        try {
            if (!channelList.isEmpty()) {
                cmderFroPlatform.catalogQuery(channelList, platform, sn, fromHeader.getTag());
            }else {
                // 回复无通道
                cmderFroPlatform.catalogQuery(null, platform, sn, fromHeader.getTag(), 0);
            }
        } catch (SipException | InvalidArgumentException | ParseException e) {
            log.error("[命令发送失败] 国标级联 目录查询回复: {}", e.getMessage());
        }
    }
}
