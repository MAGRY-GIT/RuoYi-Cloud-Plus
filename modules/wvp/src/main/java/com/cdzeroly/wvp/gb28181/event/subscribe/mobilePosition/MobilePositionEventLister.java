package com.cdzeroly.wvp.gb28181.event.subscribe.mobilePosition;

import com.cdzeroly.wvp.domain.bean.SubscribeInfo;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.domain.bean.SubscribeHolder;
import com.cdzeroly.wvp.gb28181.service.IPlatformChannelService;
import com.cdzeroly.wvp.gb28181.transmit.cmd.impl.SIPCommanderForPlatform;
import com.cdzeroly.wvp.domain.bean.GPSMsgInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import java.text.ParseException;
import java.util.List;

/**
 * 移动位置通知消息转发
 * @author MAGRY
 */
@Slf4j
@Component
@AllArgsConstructor
public class MobilePositionEventLister implements ApplicationListener<MobilePositionEvent> {

    private final IPlatformChannelService platformChannelService;

    private final SIPCommanderForPlatform sipCommanderForPlatform;

    private final SubscribeHolder subscribeHolder;

    @Override
    public void onApplicationEvent(MobilePositionEvent event) {
        if (event.getMobilePosition().getChannelId() == 0) {
            return;
        }

        // 获取所用订阅
        List<String> platforms = subscribeHolder.getAllMobilePositionSubscribePlatform();
        if (platforms.isEmpty()) {
            return;
        }
        List<Platform> platformsForGB = platformChannelService.queryPlatFormListByChannelDeviceId(event.getMobilePosition().getChannelId(), platforms);

        for (Platform platform : platformsForGB) {
            if (log.isDebugEnabled()){
                log.debug("[向上级发送MobilePosition] 通道：{}，平台：{}， 位置： {}:{}", event.getMobilePosition().getChannelId(),
                        platform.getServerGbId(), event.getMobilePosition().getLongitude(), event.getMobilePosition().getLatitude());
            }
            SubscribeInfo subscribe = subscribeHolder.getMobilePositionSubscribe(platform.getServerGbId());
            try {
                GPSMsgInfo gpsMsgInfo = GPSMsgInfo.getInstance(event.getMobilePosition());
                // 获取通道编号
                CommonGBChannel commonGBChannel = platformChannelService.queryChannelByPlatformIdAndChannelId(platform.getId(), event.getMobilePosition().getChannelId());
                sipCommanderForPlatform.sendNotifyMobilePosition(platform, gpsMsgInfo, commonGBChannel,
                        subscribe);
            } catch (InvalidArgumentException | ParseException | NoSuchFieldException | SipException |
                     IllegalAccessException e) {
                log.error("[命令发送失败] 国标级联 Catalog通知: {}", e.getMessage());
            }
        }
    }
}
