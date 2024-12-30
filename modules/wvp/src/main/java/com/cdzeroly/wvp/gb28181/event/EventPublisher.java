package com.cdzeroly.wvp.gb28181.event;

import com.cdzeroly.common.core.utils.CollectionUtil;
import com.cdzeroly.wvp.gb28181.domian.CommonGbChannel;
import com.cdzeroly.wvp.gb28181.domian.DeviceAlarm;
import com.cdzeroly.wvp.gb28181.domian.MobilePosition;
import com.cdzeroly.wvp.gb28181.domian.bean.RecordInfo;
import com.cdzeroly.wvp.gb28181.event.alarm.AlarmEvent;
import com.cdzeroly.wvp.gb28181.event.device.RequestTimeoutEvent;
import com.cdzeroly.wvp.gb28181.event.record.RecordEndEvent;
import com.cdzeroly.wvp.gb28181.event.subscribe.catalog.CatalogEvent;
import com.cdzeroly.wvp.gb28181.event.subscribe.mobilePosition.MobilePositionEvent;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.event.mediaServer.MediaServerOfflineEvent;
import com.cdzeroly.wvp.media.event.mediaServer.MediaServerOnlineEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.sip.TimeoutEvent;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  Event事件通知推送器，支持推送在线事件、离线事件
 * @author swwheihei
 * @date:   2020年5月6日 上午11:30:50
 */
@Component
@AllArgsConstructor
public class EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

	/**
	 * 设备报警事件
	 * @param deviceAlarm
	 */
	public void deviceAlarmEventPublish(DeviceAlarm deviceAlarm) {
		AlarmEvent alarmEvent = new AlarmEvent(this);
		alarmEvent.setAlarmInfo(deviceAlarm);
		applicationEventPublisher.publishEvent(alarmEvent);
	}

    /**
     * Media Server 脱机事件发布
     * @param mediaServer mediaServer
     */
	public void mediaServerOfflineEventPublish(MediaServer mediaServer){
		MediaServerOfflineEvent outEvent = new MediaServerOfflineEvent(this);
		outEvent.setMediaServer(mediaServer);
		applicationEventPublisher.publishEvent(outEvent);
	}

    /**
     * Media Server 在线事件发布
     * @param mediaServer mediaServer
     */
	public void mediaServerOnlineEventPublish(MediaServer mediaServer) {
		MediaServerOnlineEvent outEvent = new MediaServerOnlineEvent(this);
		outEvent.setMediaServer(mediaServer);
		applicationEventPublisher.publishEvent(outEvent);
	}

    /**
     * 目录事件发布
     * @param platformId  平台ID
     * @param deviceChannel  设备通道
     * @param type  类型
     */
	public void catalogEventPublish(Long platformId, CommonGbChannel deviceChannel, String type) {
		List<CommonGbChannel> deviceChannelList = new ArrayList<>();
		deviceChannelList.add(deviceChannel);
		catalogEventPublish(platformId, deviceChannelList, type);
	}

    /**
     * 请求超时
     * @param timeoutEvent  超时事件
     */
	public void requestTimeOut(TimeoutEvent timeoutEvent) {
		RequestTimeoutEvent requestTimeoutEvent = new RequestTimeoutEvent(this);
		requestTimeoutEvent.setTimeoutEvent(timeoutEvent);
		applicationEventPublisher.publishEvent(requestTimeoutEvent);
	}


    /**
     * 目录事件发布
     * @param platformId  平台ID
     * @param deviceChannels  设备通道集合
     * @param type  类型
     */
	public void catalogEventPublish(Long platformId, List<CommonGbChannel> deviceChannels, String type) {
		CatalogEvent outEvent = new CatalogEvent(this);
        ArrayList<CommonGbChannel> channels = CollectionUtil.safeStream(deviceChannels).collect(Collectors.collectingAndThen(
            Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(CommonGbChannel::getGbDeviceId))), ArrayList::new));
        outEvent.setChannels(channels);
		outEvent.setType(type);
		outEvent.setPlatformId(platformId);
		applicationEventPublisher.publishEvent(outEvent);
	}

    /**
     * Mobile Position 事件发布
     * @param mobilePosition 手机位置
     */
	public void mobilePositionEventPublish(MobilePosition mobilePosition) {
		MobilePositionEvent event = new MobilePositionEvent(this);
		event.setMobilePosition(mobilePosition);
		applicationEventPublisher.publishEvent(event);
	}

    /**
     * 记录结束事件推送
     * @param recordInfo 记录信息
     */
	public void recordEndEventPush(RecordInfo recordInfo) {
		RecordEndEvent outEvent = new RecordEndEvent(this);
		outEvent.setRecordInfo(recordInfo);
		applicationEventPublisher.publishEvent(outEvent);
	}
}
