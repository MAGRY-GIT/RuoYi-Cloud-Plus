package com.cdzeroly.wvp.gb28181.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson2.JSON;
import com.cdzeroly.common.redis.utils.RedisUtils;
import com.cdzeroly.wvp.common.*;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.mapper.DeviceChannelMapper;
import com.cdzeroly.wvp.mapper.DeviceMapper;
import com.cdzeroly.wvp.gb28181.service.IInviteStreamService;
import com.cdzeroly.wvp.media.event.media.MediaDepartureEvent;
import com.cdzeroly.wvp.domain.bean.ErrorCallback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RMapReactive;
import org.redisson.api.options.MapCacheOptions;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Administrator
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InviteStreamServiceImpl implements IInviteStreamService {

    private final Map<String, List<ErrorCallback<StreamInfo>>> inviteErrorCallbackMap = new ConcurrentHashMap<>();

    private final RedisTemplate<Object, Object> redisTemplate;

    private final UserSetting userSetting;

    private final DeviceMapper deviceMapper;

    private final DeviceChannelMapper deviceChannelMapper;

    /**
     * 流离开的处理
     */
    @Async("taskExecutor")
    @EventListener
    public void onApplicationEvent(MediaDepartureEvent event) {
        if ("rtsp".equals(event.getSchema()) && "rtp".equals(event.getApp())) {
            InviteInfo inviteInfo = getInviteInfoByStream(null, event.getStream());
            if (inviteInfo != null && (inviteInfo.getType() == InviteSessionType.PLAY || inviteInfo.getType() == InviteSessionType.PLAYBACK)) {
                removeInviteInfo(inviteInfo);
                Device device = deviceMapper.getDeviceByDeviceId(inviteInfo.getDeviceId());
                if (device != null) {
                    deviceChannelMapper.stopPlayById(inviteInfo.getChannelId());
                }
            }
        }
    }

    @Override
    public void updateInviteInfo(InviteInfo inviteInfo) {
        if (InviteSessionStatus.READY == inviteInfo.getStatus()) {
            updateInviteInfo(inviteInfo, Long.valueOf(userSetting.getPlayTimeout()) * 2);
        } else {
            updateInviteInfo(inviteInfo, null);
        }
    }

    @Override
    public void updateInviteInfo(InviteInfo inviteInfo, Long time) {
        if (inviteInfo == null || (inviteInfo.getDeviceId() == null || inviteInfo.getChannelId() == null)) {
            log.warn("[更新Invite信息]，参数不全： {}", JSON.toJSON(inviteInfo));
            return;
        }
        InviteInfo inviteInfoForUpdate;

        if (InviteSessionStatus.READY == inviteInfo.getStatus()) {
            if (inviteInfo.getDeviceId() == null || inviteInfo.getChannelId() == null || inviteInfo.getType() == null || inviteInfo.getStream() == null) {
                return;
            }
            inviteInfoForUpdate = inviteInfo;
        } else {
            InviteInfo inviteInfoInRedis = getInviteInfo(inviteInfo.getType(), inviteInfo.getChannelId(), inviteInfo.getStream());
            if (inviteInfoInRedis == null) {
                log.warn("[更新Invite信息]，未从缓存中读取到Invite信息： deviceId: {}, channel: {}, stream: {}", inviteInfo.getDeviceId(), inviteInfo.getChannelId(), inviteInfo.getStream());
                return;
            }
            if (inviteInfo.getStreamInfo() != null) {
                inviteInfoInRedis.setStreamInfo(inviteInfo.getStreamInfo());
            }
            if (inviteInfo.getSsrcInfo() != null) {
                inviteInfoInRedis.setSsrcInfo(inviteInfo.getSsrcInfo());
            }
            if (inviteInfo.getStreamMode() != null) {
                inviteInfoInRedis.setStreamMode(inviteInfo.getStreamMode());
            }
            if (inviteInfo.getReceiveIp() != null) {
                inviteInfoInRedis.setReceiveIp(inviteInfo.getReceiveIp());
            }
            if (inviteInfo.getReceivePort() != null) {
                inviteInfoInRedis.setReceivePort(inviteInfo.getReceivePort());
            }
            if (inviteInfo.getStatus() != null) {
                inviteInfoInRedis.setStatus(inviteInfo.getStatus());
            }

            inviteInfoForUpdate = inviteInfoInRedis;

        }
        if (inviteInfoForUpdate.getCreateTime() == null) {
            inviteInfoForUpdate.setCreateTime(System.currentTimeMillis());
        }
        String objectKey = inviteInfoForUpdate.getType() + ":" + inviteInfoForUpdate.getChannelId() + ":" + inviteInfoForUpdate.getStream();
        if (time != null && time > 0) {
            inviteInfoForUpdate.setExpirationTime(time);
        }
        RedisUtils.setCacheMapValue(VideoManagerConstants.INVITE_PREFIX, objectKey, inviteInfoForUpdate);
    }

    @Override
    public InviteInfo updateInviteInfoForStream(InviteInfo inviteInfo, String stream) {

        InviteInfo inviteInfoInDb = getInviteInfo(inviteInfo.getType(), inviteInfo.getChannelId(), inviteInfo.getStream());
        if (inviteInfoInDb == null) {
            return null;
        }
        removeInviteInfo(inviteInfoInDb);
        String objectKey = inviteInfo.getType() + ":" + inviteInfo.getChannelId() + ":" + stream;
        inviteInfoInDb.setStream(stream);
        if (inviteInfoInDb.getSsrcInfo() != null) {
            inviteInfoInDb.getSsrcInfo().setString(stream);
        }
        if (InviteSessionStatus.READY == inviteInfo.getStatus()) {
            inviteInfoInDb.setExpirationTime((long) (userSetting.getPlayTimeout() * 2));
        }
        if (inviteInfoInDb.getCreateTime() == null) {
            inviteInfoInDb.setCreateTime(System.currentTimeMillis());
        }
        RedisUtils.setCacheMapValue(VideoManagerConstants.INVITE_PREFIX, objectKey, inviteInfoInDb);

        return inviteInfoInDb;
    }

    @Override
    public InviteInfo getInviteInfo(InviteSessionType type, Long channelId, String stream) {
        String keyPattern = "^"+ (type != null ? type : "(\\w+)") + ":" + (channelId != null ? channelId : "(\\w+)") + ":" + (stream != null ? stream : "(\\w+)")+"$";

        Map<String, InviteInfo> map = RedisUtils.getCacheMap(VideoManagerConstants.INVITE_PREFIX);

        if (map == null) {
            return null;
        }
        Map<String, InviteInfo> filter = MapUtil.filter(map, entry -> entry.getKey().matches(keyPattern));
        if (filter.isEmpty()) {
            return null;
        }
        return filter.get(0);
    }

    @Override
    public List<InviteInfo> getAllInviteInfo() {
        List<InviteInfo> result = new ArrayList<>();
        Collection<Object> values = RedisUtils.getCacheMapValue(VideoManagerConstants.INVITE_PREFIX);

        if (values.isEmpty()) {
            return result;
        }
        for (Object value : values) {
            result.add((InviteInfo) value);
        }
        return result;
    }

    @Override
    public InviteInfo getInviteInfoByDeviceAndChannel(InviteSessionType type, Long channelId) {
        return getInviteInfo(type, channelId, null);
    }

    @Override
    public InviteInfo getInviteInfoByStream(InviteSessionType type, String stream) {
        return getInviteInfo(type, null, stream);
    }

    @Override
    public void removeInviteInfo(InviteSessionType type, Long channelId, String stream) {
        String key = VideoManagerConstants.INVITE_PREFIX;
        if (type == null && channelId == null && stream == null) {
            RedisUtils.deleteObject(key);
            return;
        }
        InviteInfo inviteInfo = getInviteInfo(type, channelId, stream);
        if (inviteInfo != null) {
            String objectKey = inviteInfo.getType() + ":" + inviteInfo.getChannelId() + ":" + inviteInfo.getStream();
            RedisUtils.delCacheMapValue(key, objectKey);
        }
    }

    @Override
    public void removeInviteInfoByDeviceAndChannel(InviteSessionType inviteSessionType, Long channelId) {
        removeInviteInfo(inviteSessionType, channelId, null);
    }

    @Override
    public void removeInviteInfo(InviteInfo inviteInfo) {
        removeInviteInfo(inviteInfo.getType(), inviteInfo.getChannelId(), inviteInfo.getStream());
    }

    @Override
    public void once(InviteSessionType type, Long channelId, String stream, ErrorCallback<StreamInfo> callback) {
        String key = buildKey(type, channelId, stream);
        List<ErrorCallback<StreamInfo>> callbacks = inviteErrorCallbackMap.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>());
        callbacks.add(callback);

    }

    private String buildKey(InviteSessionType type, Long channelId, String stream) {
        String key = type + ":" + channelId;
        // 如果ssrc未null那么可以实现一个通道只能一次操作，ssrc不为null则可以支持一个通道多次invite
        if (stream != null) {
            key += (":" + stream);
        }
        return key;
    }


    @Override
    public void clearInviteInfo(String deviceId) {
        List<InviteInfo> inviteInfoList = getAllInviteInfo();
        for (InviteInfo inviteInfo : inviteInfoList) {
            if (inviteInfo.getDeviceId().equals(deviceId)) {
                removeInviteInfo(inviteInfo);
            }
        }
    }

    @Override
    public int getStreamInfoCount(String mediaServerId) {
        int count = 0;
        Collection<Object> values = RedisUtils.getCacheMapValue(VideoManagerConstants.INVITE_PREFIX);
        if (values.isEmpty()) {
            return count;
        }
        for (Object value : values) {
            InviteInfo inviteInfo = (InviteInfo) value;
            if (inviteInfo != null && inviteInfo.getStreamInfo() != null && inviteInfo.getStreamInfo().getMediaServer() != null && inviteInfo.getStreamInfo().getMediaServer().getId().equals(mediaServerId)) {
                if (inviteInfo.getType().equals(InviteSessionType.DOWNLOAD) && inviteInfo.getStreamInfo().getProgress() == 1) {
                    continue;
                }
                count++;
            }
        }
        return count;
    }

    @Override
    public void call(InviteSessionType type, Long channelId, String stream, int code, String msg, StreamInfo data) {
        String key = buildSubStreamKey(type, channelId, stream);
        List<ErrorCallback<StreamInfo>> callbacks = inviteErrorCallbackMap.get(key);
        if (callbacks == null || callbacks.isEmpty()) {
            return;
        }
        for (ErrorCallback<StreamInfo> callback : callbacks) {
            if (callback != null) {
                callback.run(code, msg, data);
            }
        }
        inviteErrorCallbackMap.remove(key);
    }


    private String buildSubStreamKey(InviteSessionType type, Long channelId, String stream) {
        String key = type + ":" + channelId;
        if (stream != null) {
            key += (":" + stream);
        }
        return key;
    }

    @Override
    public InviteInfo getInviteInfoBySsrc(String ssrc) {
        List<InviteInfo> inviteInfoList = getAllInviteInfo();
        if (inviteInfoList.isEmpty()) {
            return null;
        }
        for (InviteInfo inviteInfo : inviteInfoList) {
            if (inviteInfo.getSsrcInfo() != null && ssrc.equals(inviteInfo.getSsrcInfo().getSsrc())) {
                return inviteInfo;
            }
        }
        return null;
    }

    @Override
    public InviteInfo updateInviteInfoForSsrc(InviteInfo inviteInfo, String ssrc) {
        InviteInfo inviteInfoInDb = getInviteInfo(inviteInfo.getType(), inviteInfo.getChannelId(), inviteInfo.getStream());
        if (inviteInfoInDb == null) {
            return null;
        }
        removeInviteInfo(inviteInfoInDb);
        String objectKey = inviteInfo.getType() + ":" + inviteInfo.getChannelId() + ":" + inviteInfo.getStream();
        if (inviteInfoInDb.getSsrcInfo() != null) {
            inviteInfoInDb.getSsrcInfo().setSsrc(ssrc);
        }
        RedisUtils.setCacheMapValue(VideoManagerConstants.INVITE_PREFIX, objectKey, inviteInfoInDb);
        return inviteInfoInDb;
    }

    @Scheduled(fixedRate = 10000)   // 定时检测,清理错误的redis数据,防止因为错误数据导致的点播不可用
    public void execute() {
        String key = VideoManagerConstants.INVITE_PREFIX;
        if ( !RedisUtils.isExistsObject(key)) {
            return;
        }
        Collection<Object> values =RedisUtils.getCacheMapValue( key);
        for (Object value : values) {
            InviteInfo inviteInfo = (InviteInfo) value;
            if (inviteInfo.getStreamInfo() != null) {
                continue;
            }
            if (inviteInfo.getCreateTime() == null || inviteInfo.getExpirationTime() == 0) {
                removeInviteInfo(inviteInfo);
            }
            long time = inviteInfo.getCreateTime() + inviteInfo.getExpirationTime();
            if (System.currentTimeMillis() > time) {
                removeInviteInfo(inviteInfo);
            }
        }
    }
}
