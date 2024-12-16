package com.cdzeroly.wvp.streamPush.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.conf.DynamicTask;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.media.bean.MediaInfo;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.media.zlm.dto.StreamAuthorityInfo;
import com.cdzeroly.wvp.service.bean.ErrorCallback;
import com.cdzeroly.wvp.service.bean.MessageForPushChannel;
import com.cdzeroly.wvp.service.redisMsg.IRedisRpcService;
import com.cdzeroly.wvp.service.redisMsg.RedisPushStreamResponseListener;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import com.cdzeroly.wvp.streamPush.domian.vo.StreamPushVo;
import com.cdzeroly.wvp.streamPush.mapper.StreamPushMapper;
import com.cdzeroly.wvp.streamPush.service.IStreamPushPlayService;
import com.cdzeroly.wvp.vmanager.bean.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.UUID;

@Service
@Slf4j
@DS("master")
public class StreamPushPlayServiceImpl implements IStreamPushPlayService {

    @Autowired
    private StreamPushMapper streamPushMapper;

    @Autowired
    private IMediaServerService mediaServerService;

    @Autowired
    private IRedisCatchStorage redisCatchStorage;

    @Autowired
    private UserSetting userSetting;

    @Autowired
    private DynamicTask dynamicTask;

    @Autowired
    private IRedisRpcService redisRpcService;

    @Autowired
    private RedisPushStreamResponseListener redisPushStreamResponseListener;

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
            callback.run(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), mediaServerService.getStreamInfoByAppAndStream(mediaInfo.getMediaServer(),
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
            callback.run(ErrorCode.ERROR100.getCode(), "timeout", null);

        }, userSetting.getPlatformPlayTimeout());
        //
        long key = redisRpcService.onStreamOnlineEvent(streamPushVo.getApp(), streamPushVo.getStream(), (streamInfo) -> {
            dynamicTask.stop(timeOutTaskKey);
            if (streamInfo == null) {
                log.warn("等待推流得到结果未空： {}/{}", streamPushVo.getApp(), streamPushVo.getStream());
                callback.run(ErrorCode.ERROR100.getCode(), "fail", null);
            }else {
                callback.run(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), streamInfo);
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
