package com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.response.cmd;

import com.cdzeroly.wvp.domain.bean.AudioBroadcastCatch;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.DeviceChannel;
import com.cdzeroly.wvp.domain.Platform;
import com.cdzeroly.wvp.gb28181.enums.AudioBroadcastCatchStatus;
import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import com.cdzeroly.wvp.gb28181.service.IPlayService;
import com.cdzeroly.wvp.gb28181.session.AudioBroadcastManager;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.IMessageHandler;
import com.cdzeroly.wvp.gb28181.transmit.event.request.impl.message.response.ResponseMessageHandler;
import gov.nist.javax.sip.message.SIPRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.message.Response;
import java.text.ParseException;

import static com.cdzeroly.wvp.gb28181.utils.XmlUtil.getText;

/**
 * @author MGARY
 */
@Slf4j
@Component
@AllArgsConstructor
public class BroadcastResponseMessageHandler extends SIPRequestProcessorParent implements InitializingBean, IMessageHandler {

    private final ResponseMessageHandler responseMessageHandler;

    private final IDeviceChannelService deviceChannelService;

    private final AudioBroadcastManager audioBroadcastManager;

    private final IPlayService playService;

    @Override
    public void afterPropertiesSet() throws Exception {
        String cmdType = "Broadcast";
        responseMessageHandler.addHandler(cmdType, this);
    }

    @Override
    public void handForDevice(RequestEvent evt, Device device, Element rootElement) {

        SIPRequest request = (SIPRequest) evt.getRequest();
        try {
            String channelId = getText(rootElement, "DeviceID");
            DeviceChannel channel = null;
            if (!channelId.equals(device.getDeviceId())) {
                channel = deviceChannelService.getOneBySourceId(device.getId(), channelId);
            }else {
                channel = deviceChannelService.getBroadcastChannel(device.getId());
            }
            if (channel == null) {
                log.info("[语音广播]回复： 未找到通道{}/{}", device.getDeviceId(), channelId );
                // 回复410
                responseAck((SIPRequest) evt.getRequest(), Response.NOT_FOUND);
                return;
            }
            if (!audioBroadcastManager.exit(channel.getId())) {
                // 回复410
                responseAck((SIPRequest) evt.getRequest(), Response.BUSY_HERE);
                return;
            }
            String result = getText(rootElement, "Result");
            Element infoElement = rootElement.element("Info");
            String reason = null;
            if (infoElement != null) {
                reason = getText(infoElement, "Reason");
            }
            log.info("[语音广播]回复：{}, {}/{}", reason == null? result : result + ": " + reason, device.getDeviceId(), channelId );

            // 回复200 OK
            responseAck(request, Response.OK);
            if ("OK".equalsIgnoreCase(result)) {
                AudioBroadcastCatch audioBroadcastCatch = audioBroadcastManager.get(channel.getId());
                audioBroadcastCatch.setStatus(AudioBroadcastCatchStatus.WaiteInvite);
                audioBroadcastManager.update(audioBroadcastCatch);
            }else {
                playService.stopAudioBroadcast(device, channel);
            }
        } catch (ParseException | SipException | InvalidArgumentException e) {
            log.error("[命令发送失败] 国标级联 语音喊话: {}", e.getMessage());
        }
    }

    @Override
    public void handForPlatform(RequestEvent evt, Platform parentPlatform, Element element) {

    }


}
