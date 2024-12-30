package com.cdzeroly.wvp.gb28181.event.subscribe.catalog;

import com.cdzeroly.wvp.domain.bean.SubscribeInfo;
import com.cdzeroly.wvp.gb28181.domian.CommonGbChannel;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.domain.bean.SubscribeHolder;
import com.cdzeroly.wvp.gb28181.service.IPlatformChannelService;
import com.cdzeroly.wvp.gb28181.service.IPlatformService;
import com.cdzeroly.wvp.gb28181.transmit.cmd.ISIPCommanderForPlatform;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * catalog事件
 * @author Administrator
 */
@Slf4j
@Component
@AllArgsConstructor
public class CatalogEventLister implements ApplicationListener<CatalogEvent> {

    private final IPlatformChannelService platformChannelService;

    private final IPlatformService platformService;

    private final ISIPCommanderForPlatform sipCommanderFroPlatform;

    private final SubscribeHolder subscribeHolder;

    @Override
    public void onApplicationEvent(CatalogEvent event) {
        SubscribeInfo subscribe = null;
        Platform parentPlatform = null;

        Map<String, List<Platform>> parentPlatformMap = new HashMap<>();
        Map<String, CommonGbChannel> channelMap = new HashMap<>();
        if (event.getPlatformId() != null) {
            parentPlatform = platformService.queryOne(event.getPlatformId());
            if (parentPlatform == null) {
                return;
            }
            subscribe = subscribeHolder.getCatalogSubscribe(parentPlatform.getServerGbId());
            if (subscribe == null) {
                return;
            }

        }else {
            // 获取所用订阅
            List<String> platforms = subscribeHolder.getAllCatalogSubscribePlatform();
            if (event.getChannels() != null) {
                if (!platforms.isEmpty()) {
                    for (CommonGbChannel deviceChannel : event.getChannels()) {
                        List<Platform> parentPlatformsForGb = platformChannelService.queryPlatFormListByChannelDeviceId(
                                deviceChannel.getGbId(), platforms);
                        parentPlatformMap.put(deviceChannel.getGbDeviceId(), parentPlatformsForGb);
                        channelMap.put(deviceChannel.getGbDeviceId(), deviceChannel);
                    }
                }
            }
        }
        switch (event.getType()) {
            case CatalogEvent.ON:
            case CatalogEvent.OFF:
            case CatalogEvent.DEL:

                if (parentPlatform != null) {
                    List<CommonGbChannel> deviceChannelList = new ArrayList<>();
                    if (event.getChannels() != null) {
                        deviceChannelList.addAll(event.getChannels());
                    }
                    if (!deviceChannelList.isEmpty()) {
                        log.info("[Catalog事件: {}]平台：{}，影响通道{}个", event.getType(), parentPlatform.getServerGbId(), deviceChannelList.size());
                        try {
                            sipCommanderFroPlatform.sendNotifyForCatalogOther(event.getType(), parentPlatform, deviceChannelList, subscribe, null);
                        } catch (InvalidArgumentException | ParseException | NoSuchFieldException | SipException |
                                 IllegalAccessException e) {
                            log.error("[命令发送失败] 国标级联 Catalog通知: {}", e.getMessage());
                        }
                    }
                }else if (!parentPlatformMap.keySet().isEmpty()) {
                    for (String gbId : parentPlatformMap.keySet()) {
                        List<Platform> parentPlatforms = parentPlatformMap.get(gbId);
                        if (parentPlatforms != null && !parentPlatforms.isEmpty()) {
                            for (Platform platform : parentPlatforms) {
                                SubscribeInfo subscribeInfo = subscribeHolder.getCatalogSubscribe(platform.getServerGbId());
                                if (subscribeInfo == null) {
                                    continue;
                                }
                                log.info("[Catalog事件: {}]平台：{}，影响通道{}", event.getType(), platform.getServerGbId(), gbId);
                                List<CommonGbChannel> deviceChannelList = new ArrayList<>();
                                CommonGbChannel deviceChannel = new CommonGbChannel();
                                deviceChannel.setGbDeviceId(gbId);
                                deviceChannelList.add(deviceChannel);
                                try {
                                    sipCommanderFroPlatform.sendNotifyForCatalogOther(event.getType(), platform, deviceChannelList, subscribeInfo, null);
                                } catch (InvalidArgumentException | ParseException | NoSuchFieldException | SipException |
                                         IllegalAccessException e) {
                                    log.error("[命令发送失败] 国标级联 Catalog通知: {}", e.getMessage());
                                }
                            }
                        }
                    }
                }
                break;
            case CatalogEvent.VLOST:
                break;
            case CatalogEvent.DEFECT:
                break;
            case CatalogEvent.ADD:
            case CatalogEvent.UPDATE:
                if (parentPlatform != null) {
                     List<CommonGbChannel> deviceChannelList = new ArrayList<>();
                     if (event.getChannels() != null) {
                         deviceChannelList.addAll(event.getChannels());
                     }
                    if (!deviceChannelList.isEmpty()) {
                        log.info("[Catalog事件: {}]平台：{}，影响通道{}个", event.getType(), parentPlatform.getServerGbId(), deviceChannelList.size());
                        try {
                            sipCommanderFroPlatform.sendNotifyForCatalogAddOrUpdate(event.getType(), parentPlatform, deviceChannelList, subscribe, null);
                        } catch (InvalidArgumentException | ParseException | NoSuchFieldException | SipException |
                                 IllegalAccessException e) {
                            log.error("[命令发送失败] 国标级联 Catalog通知: {}", e.getMessage());
                        }
                    }
                }else if (!parentPlatformMap.keySet().isEmpty()) {
                    for (String gbId : parentPlatformMap.keySet()) {
                        List<Platform> parentPlatforms = parentPlatformMap.get(gbId);
                        if (parentPlatforms != null && !parentPlatforms.isEmpty()) {
                            for (Platform platform : parentPlatforms) {
                                SubscribeInfo subscribeInfo = subscribeHolder.getCatalogSubscribe(platform.getServerGbId());
                                if (subscribeInfo == null) {
                                    continue;
                                }
                                log.info("[Catalog事件: {}]平台：{}，影响通道{}", event.getType(), platform.getServerGbId(), gbId);
                                List<CommonGbChannel> channelList = new ArrayList<>();
                                CommonGbChannel deviceChannel = channelMap.get(gbId);
                                channelList.add(deviceChannel);
                                try {
                                    sipCommanderFroPlatform.sendNotifyForCatalogAddOrUpdate(event.getType(), platform, channelList, subscribeInfo, null);
                                } catch (InvalidArgumentException | ParseException | NoSuchFieldException |
                                         SipException | IllegalAccessException e) {
                                    log.error("[命令发送失败] 国标级联 Catalog通知: {}", e.getMessage());
                                }
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}
