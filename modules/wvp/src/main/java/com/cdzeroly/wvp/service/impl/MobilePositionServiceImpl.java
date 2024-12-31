package com.cdzeroly.wvp.service.impl;

import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.gb28181.domian.DeviceChannel;
import com.cdzeroly.wvp.gb28181.domian.MobilePosition;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.mapper.PlatformMapper;
import com.cdzeroly.wvp.service.IMobilePositionService;
import com.cdzeroly.wvp.mapper.DeviceChannelMapper;
import com.cdzeroly.wvp.mapper.DeviceMobilePositionMapper;
import com.cdzeroly.wvp.domain.bean.GPSMsgInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MGARY
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MobilePositionServiceImpl implements IMobilePositionService {

    private final DeviceChannelMapper channelMapper;

    private final DeviceMobilePositionMapper mobilePositionMapper;

    private final UserSetting userSetting;


    private final PlatformMapper platformMapper;

    private final RedisTemplate<String, MobilePosition> redisTemplate;

    private final String REDIS_MOBILE_POSITION_LIST = "redis_mobile_position_list";

    @Override
    public void add(MobilePosition mobilePosition) {
        List<MobilePosition> list = new ArrayList<>();
        list.add(mobilePosition);
        add(list);
    }

    @Override
    public void add(List<MobilePosition> mobilePositionList) {
        redisTemplate.opsForList().leftPushAll(REDIS_MOBILE_POSITION_LIST, mobilePositionList);
    }

    private List<MobilePosition> get(int length) {
        Long size = redisTemplate.opsForList().size(REDIS_MOBILE_POSITION_LIST);
        if (size == null || size == 0) {
            return new ArrayList<>();
        }
        return redisTemplate.opsForList().rightPop(REDIS_MOBILE_POSITION_LIST, Math.min(length, size));

    }



    /**
     * 查询移动位置轨迹
     */
    @Override
    public synchronized List<MobilePosition> queryMobilePositions(String deviceId, String channelId, String startTime, String endTime) {
        return mobilePositionMapper.queryPositionByDeviceIdAndTime(deviceId, channelId, startTime, endTime);
    }

    @Override
    public List<Platform> queryEnablePlatformListWithAsMessageChannel() {
        return platformMapper.queryByEnableAndAsMessageChannel(true,true);
    }

    /**
     * 查询最新移动位置
     * @param deviceId
     */
    @Override
    public MobilePosition queryLatestPosition(String deviceId) {
        return mobilePositionMapper.queryLatestPositionByDevice(deviceId);
    }

    @Override
    public void updateStreamGPS(List<GPSMsgInfo> gpsMsgInfoList) {
        channelMapper.updateStreamGPS(gpsMsgInfoList);
    }

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void executeTaskQueue() {
        int countLimit = 3000;
        List<MobilePosition> mobilePositions = get(countLimit);
        if (mobilePositions == null || mobilePositions.isEmpty()) {
            return;
        }
        if (userSetting.getSavePositionHistory()) {
            mobilePositionMapper.insert(mobilePositions);
        }
        log.info("[移动位置订阅]更新通道位置： {}", mobilePositions.size());
        Map<String, DeviceChannel> updateChannelMap = new HashMap<>();
        for (MobilePosition mobilePosition : mobilePositions) {
            DeviceChannel deviceChannel = new DeviceChannel();
            deviceChannel.setId(mobilePosition.getChannelId());
            deviceChannel.setDeviceId(mobilePosition.getDeviceId());
            deviceChannel.setLongitude(mobilePosition.getLongitude());
            deviceChannel.setLatitude(mobilePosition.getLatitude());
            deviceChannel.setGpsTime(mobilePosition.getTime());
            updateChannelMap.put(mobilePosition.getDeviceId() + mobilePosition.getChannelId(), deviceChannel);
        }
        List<DeviceChannel> channels = new ArrayList<>(updateChannelMap.values());
        channelMapper.batchUpdatePosition(channels);
    }

}
