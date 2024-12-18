package com.cdzeroly.wvp.streamPush.service.impl;

import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.conf.task.DynamicTask;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.media.domian.bean.MediaInfo;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.media.zlm.dto.StreamAuthorityInfo;
import com.cdzeroly.wvp.service.domian.bean.ErrorCallback;
import com.cdzeroly.wvp.service.domian.bean.MessageForPushChannel;
import com.cdzeroly.wvp.service.redisMsg.IRedisRpcService;
import com.cdzeroly.wvp.service.redisMsg.RedisPushStreamResponseListener;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import com.cdzeroly.wvp.streamPush.domian.vo.StreamPushVo;
import com.cdzeroly.wvp.streamPush.mapper.StreamPushMapper;
import com.cdzeroly.wvp.streamPush.service.IStreamPushPlayService;
import com.cdzeroly.wvp.vmanager.bean.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * @author MGARY
 */
@Service
@Slf4j
@AllArgsConstructor
public class StreamPushPlayServiceImpl implements IStreamPushPlayService {

    private final StreamPushMapper streamPushMapper;

    private final IMediaServerService mediaServerService;

    private final IRedisCatchStorage redisCatchStorage;

    private final UserSetting userSetting;

    private final DynamicTask dynamicTask;

    private final IRedisRpcService redisRpcService;

    private final RedisPushStreamResponseListener redisPushStreamResponseListener;

    @Override
    public void start(Integer id, ErrorCallback<StreamInfo> callback, String platformDeviceId, String platformName ) {
        StreamPushVo streamPushVo = streamPushMapper.queryOne(id);
        Assert.notNull(streamPushVo, "推流信息未找到");
        MediaInfo mediaInfo = redisCatchStorage.getPushListItem(streamPushVo.getApp(), streamPushVo.getStream());
        if (mediaInfo != null) {
            String callId = null;
            StreamAuthorityInfo streamAuthorityInfo = redisCatchStorage.getStreamAuthorityInfo(streamPushVo.getApp(), streamPushVo.getStream());
            if (streamAuthorityInfo != null) {
                callId = streamAuthorityInfo.getCallId();
            }
            callback.run(R.SUCCESS, "SUCCESS", mediaServerService.getStreamInfoByAppAndStream(mediaInfo.getMediaServer(),
                    streamPushVo.getApp(), streamPushVo.getStream(), mediaInfo, callId));
            return;
        }
        Assert.isTrue(streamPushVo.isStartOfflinePush(), "通道未推流");
        // 发送redis消息以使设备上线，流上线后被
        log.info("[ app={}, stream={} ]通道未推流，发送redis信息控制设备开始推流", streamPushVo.getApp(), streamPushVo.getStream());
        MessageForPushChannel messageForPushChannel = MessageForPushChannel.getInstance(1,
                streamPushVo.getApp(), streamPushVo.getStream(), streamPushVo.getGbDeviceId(), platformDeviceId,
                platformName, userSetting.getServerId(), null);
        redisCatchStorage.sendStreamPushRequestedMsg(messageForPushChannel);
        // 设置超时
        String timeOutTaskKey = UUID.randomUUID().toString();
        dynamicTask.startDelay(timeOutTaskKey, () -> {
            redisRpcService.unPushStreamOnlineEvent(streamPushVo.getApp(), streamPushVo.getStream());
            log.info("[ app={}, stream={} ] 等待设备开始推流超时", streamPushVo.getApp(), streamPushVo.getStream());
            callback.run(R.FAIL, "timeout", null);

        }, userSetting.getPlatformPlayTimeout());
        //
        long key = redisRpcService.onStreamOnlineEvent(streamPushVo.getApp(), streamPushVo.getStream(), (streamInfo) -> {
            dynamicTask.stop(timeOutTaskKey);
            if (streamInfo == null) {
                log.warn("等待推流得到结果未空： {}/{}", streamPushVo.getApp(), streamPushVo.getStream());
                callback.run(R.FAIL, "fail", null);
            }else {
                callback.run(R.SUCCESS, "SUCCESS", streamInfo);
            }
        });
        // 添加回复的拒绝或者错误的通知
        // redis消息例如： PUBLISH VM_MSG_STREAM_PUSH_RESPONSE  '{"code":1,"msg":"失败","app":"1","stream":"2"}'
        redisPushStreamResponseListener.addEvent(streamPushVo.getApp(), streamPushVo.getStream(), response -> {
            if (response.getCode() != 0) {
                dynamicTask.stop(timeOutTaskKey);
                redisRpcService.unPushStreamOnlineEvent(streamPushVo.getApp(), streamPushVo.getStream());
                redisRpcService.removeCallback(key);
                callback.run(response.getCode(), response.getMsg(), null);
            }
        });
    }
}
