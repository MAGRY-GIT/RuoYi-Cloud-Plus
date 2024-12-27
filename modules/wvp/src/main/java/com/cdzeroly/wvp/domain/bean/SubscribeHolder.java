package com.cdzeroly.wvp.domain.bean;

import com.cdzeroly.wvp.common.VideoManagerConstants;
import com.cdzeroly.wvp.conf.task.DynamicTask;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.gb28181.task.ISubscribeTask;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lin
 */
@AllArgsConstructor
@Component
public class SubscribeHolder {

    private final DynamicTask dynamicTask;

    private final UserSetting userSetting;

    private final String taskOverduePrefix = "subscribe_overdue_";

    private static final ConcurrentHashMap<String, SubscribeInfo> CATALOG_MAP = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, SubscribeInfo> MOBILE_POSITION_MAP = new ConcurrentHashMap<>();


    public void putCatalogSubscribe(String platformId, SubscribeInfo subscribeInfo) {
        CATALOG_MAP.put(platformId, subscribeInfo);
        if (subscribeInfo.getExpires() > 0) {
            // 添加订阅到期
            String taskOverdueKey = taskOverduePrefix +  "catalog_" + platformId;
            // 添加任务处理订阅过期
            dynamicTask.startDelay(taskOverdueKey, () -> removeCatalogSubscribe(subscribeInfo.getId()),
                    subscribeInfo.getExpires() * 1000);
        }
    }

    public SubscribeInfo getCatalogSubscribe(String platformId) {
        return CATALOG_MAP.get(platformId);
    }

    public void removeCatalogSubscribe(String platformId) {

        CATALOG_MAP.remove(platformId);
        String taskOverdueKey = taskOverduePrefix +  "catalog_" + platformId;
        Runnable runnable = dynamicTask.get(taskOverdueKey);
        if (runnable instanceof ISubscribeTask) {
            ISubscribeTask subscribeTask = (ISubscribeTask) runnable;
            subscribeTask.stop(null);
        }
        // 添加任务处理订阅过期
        dynamicTask.stop(taskOverdueKey);
    }

    public void putMobilePositionSubscribe(String platformId, SubscribeInfo subscribeInfo, Runnable gpsTask) {
        MOBILE_POSITION_MAP.put(platformId, subscribeInfo);
        String key = VideoManagerConstants.SIP_SUBSCRIBE_PREFIX + userSetting.getServerId() + "MobilePosition_" + platformId;
        // 添加任务处理GPS定时推送

        int cycleForCatalog;
        if (subscribeInfo.getGpsInterval() <= 0) {
            cycleForCatalog = 5;
        }else {
            cycleForCatalog = subscribeInfo.getGpsInterval();
        }
        dynamicTask.startCron(key, gpsTask,
                cycleForCatalog * 1000);
        String taskOverdueKey = taskOverduePrefix +  "MobilePosition_" + platformId;
        if (subscribeInfo.getExpires() > 0) {
            // 添加任务处理订阅过期
            dynamicTask.startDelay(taskOverdueKey, () -> {
                        removeMobilePositionSubscribe(subscribeInfo.getId());
                    },
                    subscribeInfo.getExpires() * 1000);
        }
    }

    public SubscribeInfo getMobilePositionSubscribe(String platformId) {
        return MOBILE_POSITION_MAP.get(platformId);
    }

    public void removeMobilePositionSubscribe(String platformId) {
        MOBILE_POSITION_MAP.remove(platformId);
        String key = VideoManagerConstants.SIP_SUBSCRIBE_PREFIX + userSetting.getServerId() + "MobilePosition_" + platformId;
        // 结束任务处理GPS定时推送
        dynamicTask.stop(key);
        String taskOverdueKey = taskOverduePrefix +  "MobilePosition_" + platformId;
        Runnable runnable = dynamicTask.get(taskOverdueKey);
        if (runnable instanceof ISubscribeTask) {
            ISubscribeTask subscribeTask = (ISubscribeTask) runnable;
            subscribeTask.stop(null);
        }
        // 添加任务处理订阅过期
        dynamicTask.stop(taskOverdueKey);
    }

    public List<String> getAllCatalogSubscribePlatform() {
        List<String> platforms = new ArrayList<>();
        if(CATALOG_MAP.size() > 0) {
            for (String key : CATALOG_MAP.keySet()) {
                platforms.add(CATALOG_MAP.get(key).getId());
            }
        }
        return platforms;
    }

    public List<String> getAllMobilePositionSubscribePlatform() {
        List<String> platforms = new ArrayList<>();
        if(!MOBILE_POSITION_MAP.isEmpty()) {
            for (String key : MOBILE_POSITION_MAP.keySet()) {
                platforms.add(MOBILE_POSITION_MAP.get(key).getId());
            }
        }
        return platforms;
    }

    public void removeAllSubscribe(String platformId) {
        removeMobilePositionSubscribe(platformId);
        removeCatalogSubscribe(platformId);
    }
}
