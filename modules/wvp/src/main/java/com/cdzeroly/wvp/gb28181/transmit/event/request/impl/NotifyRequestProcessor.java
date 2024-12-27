package com.cdzeroly.wvp.gb28181.transmit.event.request.impl;

import com.cdzeroly.wvp.conf.SipConfig;
import com.cdzeroly.wvp.domain.bean.CmdType;
import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.domian.DeviceAlarm;
import com.cdzeroly.wvp.gb28181.domian.DeviceChannel;
import com.cdzeroly.wvp.gb28181.domian.MobilePosition;
import com.cdzeroly.wvp.gb28181.event.EventPublisher;
import com.cdzeroly.wvp.gb28181.transmit.SIPProcessorObserver;
import com.cdzeroly.wvp.gb28181.transmit.event.request.ISIPRequestProcessor;
import com.cdzeroly.wvp.gb28181.transmit.event.request.SIPRequestProcessorParent;
import com.cdzeroly.wvp.gb28181.utils.NumericUtil;
import com.cdzeroly.wvp.gb28181.utils.SipUtils;
import com.cdzeroly.wvp.gb28181.utils.XmlUtil;
import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import com.cdzeroly.wvp.utils.DateUtil;
import gov.nist.javax.sip.message.SIPRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import javax.sip.header.FromHeader;
import javax.sip.message.Response;
import java.text.ParseException;

/**
 * SIP命令类型： NOTIFY请求,这是作为上级发送订阅请求后，设备才会响应的
 * @author ＭＧＡＲＹ
 */
@Slf4j
@Component
@AllArgsConstructor
public class NotifyRequestProcessor extends SIPRequestProcessorParent implements InitializingBean, ISIPRequestProcessor {

	private final SipConfig sipConfig;

	private final IRedisCatchStorage redisCatchStorage;

	private final EventPublisher publisher;

    private final SIPProcessorObserver sipProcessorObserver;

	private final IDeviceChannelService deviceChannelService;

	private final NotifyRequestForCatalogProcessor notifyRequestForCatalogProcessor;

	private final NotifyRequestForMobilePositionProcessor notifyRequestForMobilePositionProcessor;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 添加消息处理的订阅
        String method = "NOTIFY";
        sipProcessorObserver.addRequestProcessor(method, this);
	}

	@Override
	public void process(RequestEvent evt) {
		try {
			responseAck((SIPRequest) evt.getRequest(), Response.OK, null, null);
			Element rootElement = getRootElement(evt);
			if (rootElement == null) {
				log.error("处理NOTIFY消息时未获取到消息体,{}", evt.getRequest());
				responseAck((SIPRequest) evt.getRequest(), Response.OK, null, null);
				return;
			}
			String cmd = XmlUtil.getText(rootElement, "CmdType");

			if (CmdType.CATALOG.equals(cmd)) {
				notifyRequestForCatalogProcessor.process(evt);
			} else if (CmdType.ALARM.equals(cmd)) {
				processNotifyAlarm(evt);
			} else if (CmdType.MOBILE_POSITION.equals(cmd)) {
				notifyRequestForMobilePositionProcessor.process(evt);
			} else {
				log.info("接收到消息：" + cmd);
			}
		} catch (SipException | InvalidArgumentException | ParseException e) {
			log.error("未处理的异常 ", e);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}

	}
	/***
	 * 处理alarm设备报警Notify
	 */
	private void processNotifyAlarm(RequestEvent evt) {
		if (!sipConfig.isAlarm()) {
			return;
		}
		try {
			FromHeader fromHeader = (FromHeader) evt.getRequest().getHeader(FromHeader.NAME);
			String deviceId = SipUtils.getUserIdFromFromHeader(fromHeader);

			Element rootElement = getRootElement(evt);
			if (rootElement == null) {
				log.error("处理alarm设备报警Notify时未获取到消息体{}", evt.getRequest());
				return;
			}
			Element deviceIdElement = rootElement.element("DeviceID");
			String channelId = deviceIdElement.getText().toString();

			Device device = redisCatchStorage.getDevice(deviceId);
			if (device == null) {
				log.warn("[ NotifyAlarm ] 未找到设备：{}", deviceId);
				return;
			}
			rootElement = getRootElement(evt, device.getCharset());
			if (rootElement == null) {
				log.warn("[ NotifyAlarm ] content cannot be null, {}", evt.getRequest());
				return;
			}
			DeviceAlarm deviceAlarm = new DeviceAlarm();
			deviceAlarm.setDeviceId(deviceId);
			deviceAlarm.setDeviceName(device.getName());
			deviceAlarm.setAlarmPriority(XmlUtil.getText(rootElement, "AlarmPriority"));
			deviceAlarm.setAlarmMethod(XmlUtil.getText(rootElement, "AlarmMethod"));
			String alarmTime = XmlUtil.getText(rootElement, "AlarmTime");
			if (alarmTime == null) {
				log.warn("[ NotifyAlarm ] AlarmTime cannot be null");
				return;
			}
			deviceAlarm.setAlarmTime(DateUtil.iso8601ToYyyyMmDdHhMmSs(alarmTime));
			if (XmlUtil.getText(rootElement, "AlarmDescription") == null) {
				deviceAlarm.setAlarmDescription("");
			} else {
				deviceAlarm.setAlarmDescription(XmlUtil.getText(rootElement, "AlarmDescription"));
			}
			if (NumericUtil.isDouble(XmlUtil.getText(rootElement, "Longitude"))) {
				deviceAlarm.setLongitude(Double.parseDouble(XmlUtil.getText(rootElement, "Longitude")));
			} else {
				deviceAlarm.setLongitude(0.00);
			}
			if (NumericUtil.isDouble(XmlUtil.getText(rootElement, "Latitude"))) {
				deviceAlarm.setLatitude(Double.parseDouble(XmlUtil.getText(rootElement, "Latitude")));
			} else {
				deviceAlarm.setLatitude(0.00);
			}
			log.info("[收到Notify-Alarm]：{}/{}", device.getDeviceId(), deviceAlarm.getChannelId());
			if ("4".equals(deviceAlarm.getAlarmMethod())) {
				DeviceChannel deviceChannel = deviceChannelService.getOne(device.getDeviceId(), channelId);
				if (deviceChannel == null) {
					log.warn("[解析报警通知] 未找到通道：{}/{}", device.getDeviceId(), channelId);
				}else {
					MobilePosition mobilePosition = new MobilePosition();
					mobilePosition.setChannelId(deviceChannel.getId());
					mobilePosition.setDeviceId(deviceAlarm.getDeviceId());
					mobilePosition.setTime(deviceAlarm.getAlarmTime());
					mobilePosition.setLongitude(deviceAlarm.getLongitude());
					mobilePosition.setLatitude(deviceAlarm.getLatitude());
					mobilePosition.setReportSource("GPS Alarm");

					// 更新device channel 的经纬度
					deviceChannel.setLongitude(mobilePosition.getLongitude());
					deviceChannel.setLatitude(mobilePosition.getLatitude());
					deviceChannel.setGpsTime(mobilePosition.getTime());

					deviceChannelService.updateChannelGPS(device, deviceChannel, mobilePosition);
				}
			}

			// 回复200 OK
			if (redisCatchStorage.deviceIsOnline(deviceId)) {
				publisher.deviceAlarmEventPublish(deviceAlarm);
			}
		} catch (DocumentException e) {
			log.error("未处理的异常 ", e);
		}
	}


}
