package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.query.cmd;

import com.cdzeroly.wvp.common.enums.ChannelDataType;
import com.cdzeroly.wvp.domain.CommonGbChannel;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.DeviceChannel;
import com.cdzeroly.wvp.domain.Platform;
import com.cdzeroly.wvp.gb28181.event.record.RecordEndEventListener;
import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import com.cdzeroly.wvp.gb28181.service.IDeviceService;
import com.cdzeroly.wvp.gb28181.service.IGbChannelService;
import com.cdzeroly.wvp.gb28181.transmit.cmd.impl.SIPCommander;
import com.cdzeroly.wvp.gb28181.transmit.cmd.impl.SIPCommanderForPlatform;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.query.QueryMessageHandler;
import com.cdzeroly.wvp.utils.DateUtil;
import gov.nist.javax.sip.message.SIPRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.message.Response;
import java.text.ParseException;

/**
 * @author Administrator
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RecordInfoQueryMessageHandler extends SIPRequestProcessorParent implements InitializingBean, IMessageHandler {

    private final QueryMessageHandler queryMessageHandler;

    private final IGbChannelService channelService;

    private final IDeviceService deviceService;

    private final IDeviceChannelService deviceChannelService;

    private final SIPCommanderForPlatform cmderFroPlatform;

    private final SIPCommander commander;

    private final RecordEndEventListener recordEndEventListener;

    @Override
    public void afterPropertiesSet() throws Exception {
         String cmdType = "RecordInfo";
        queryMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element element) {

    }

    @Override
    public void handForPlatform(RequestEvent evt, Platform platform, Element rootElement) {

        SIPRequest request = (SIPRequest) evt.getRequest();
        Element snElement = rootElement.element("SN");
        int sn = Integer.parseInt(snElement.getText());
        Element deviceIDElement = rootElement.element("DeviceID");
        String channelId = deviceIDElement.getText();
        Element startTimeElement = rootElement.element("StartTime");
        String startTime = null;
        if (startTimeElement != null) {
            startTime = startTimeElement.getText();
        }
        Element endTimeElement = rootElement.element("EndTime");
        String endTime = null;
        if (endTimeElement != null) {
            endTime = endTimeElement.getText();
        }
        Element secrecyElement = rootElement.element("Secrecy");
        int secrecy = 0;
        if (secrecyElement != null) {
            secrecy = Integer.parseInt(secrecyElement.getText().trim());
        }
        String type = "all";
        Element typeElement = rootElement.element("Type");
        if (typeElement != null) {
            type =  typeElement.getText();
        }

        // 向国标设备请求录像数据
        CommonGbChannel channel = channelService.queryOneWithPlatform(platform.getId(), channelId);
        if (channel == null) {
            log.info("[平台查询录像记录] 未找到通道 {}/{}", platform.getName(), channelId );
            try {
                responseAck(request, Response.BAD_REQUEST);
            } catch (SipException | InvalidArgumentException | ParseException e) {
                log.error("[命令发送失败] [平台查询录像记录] 未找到通道: {}", e.getMessage());
            }
            return;
        }
        if (channel.getDataType() != ChannelDataType.GB28181.value) {
            log.info("[平台查询录像记录] 只支持查询国标28181的录像数据 {}/{}", platform.getName(), channelId );
            try {
                // 回复未实现
                responseAck(request, Response.NOT_IMPLEMENTED);
            } catch (SipException | InvalidArgumentException | ParseException e) {
                log.error("[命令发送失败] 平台查询录像记录: {}", e.getMessage());
            }
            return;
        }
        Device device = deviceService.getDevice(channel.getDataDeviceId());
        if (device == null) {
            log.warn("[平台查询录像记录] 未找到通道对应的设备 {}/{}", platform.getName(), channelId );
            try {
                responseAck(request, Response.BAD_REQUEST);
            } catch (SipException | InvalidArgumentException | ParseException e) {
                log.error("[命令发送失败] [平台查询录像记录] 未找到通道对应的设备: {}", e.getMessage());
            }
            return;
        }
        // 获取通道的原始信息
        DeviceChannel deviceChannel = deviceChannelService.getOneForSourceById(channel.getGbId());
        // 接收录像数据
        recordEndEventListener.addEndEventHandler(device.getDeviceId(), deviceChannel.getDeviceId(), (recordInfo)->{
            try {
                log.info("[国标级联] 录像查询收到数据， 通道： {}，准备转发===", channelId);
                cmderFroPlatform.recordInfo(channel, platform, request.getFromTag(), recordInfo);
            } catch (SipException | InvalidArgumentException | ParseException e) {
                log.error("[命令发送失败] 国标级联 回复录像数据: {}", e.getMessage());
            }
        });
        try {
            commander.recordInfoQuery(device, deviceChannel.getDeviceId(), DateUtil.iso8601ToYyyyMmDdHhMmSs(startTime),
                    DateUtil.iso8601ToYyyyMmDdHhMmSs(endTime), sn, secrecy, type, (eventResult -> {
                        // 回复200 OK
                        try {
                            responseAck(request, Response.OK);
                        } catch (SipException | InvalidArgumentException | ParseException e) {
                            log.error("[命令发送失败] 录像查询回复: {}", e.getMessage());
                        }
                    }),(eventResult -> {
                        // 查询失败
                        try {
                            responseAck(request, eventResult.statusCode, eventResult.msg);
                        } catch (SipException | InvalidArgumentException | ParseException e) {
                            log.error("[命令发送失败] 录像查询回复: {}", e.getMessage());
                        }
                    }));
        } catch (InvalidArgumentException | ParseException | SipException e) {
            log.error("[命令发送失败] 录像查询: {}", e.getMessage());
        }
    }
}
