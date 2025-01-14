package com.cdzeroly.wvp.service.impl;

import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.core.utils.AssertUtils;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.domain.bean.ErrorCallback;
import com.cdzeroly.wvp.domain.bean.InviteErrorCode;
import com.cdzeroly.wvp.domain.bean.MediaInfo;
import com.cdzeroly.wvp.media.event.media.MediaArrivalEvent;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.domain.StreamProxy;
import com.cdzeroly.wvp.domain.vo.StreamProxyVo;
import com.cdzeroly.wvp.mapper.StreamProxyMapper;
import com.cdzeroly.wvp.service.IStreamProxyPlayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.sip.message.Response;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 视频代理业务
 * @author MGARY
 */
@Slf4j
@Service
@AllArgsConstructor
public class StreamProxyPlayServiceImpl implements IStreamProxyPlayService {

    private final StreamProxyMapper streamProxyMapper;

    private final IMediaServerService mediaServerService;

    private final ConcurrentHashMap<Long, ErrorCallback<StreamInfo>> CALLBACK_MAP = new ConcurrentHashMap<>();


    private final ConcurrentHashMap<Long, StreamInfo> STREAM_INFO_MAP = new ConcurrentHashMap<>();
    /**
     * 流到来的处理
     */
    @Async("taskExecutor")
    @Transactional
    @EventListener
    public void onApplicationEvent(MediaArrivalEvent event) {
        if ("rtsp".equals(event.getSchema())) {
            StreamProxyVo streamProxy = streamProxyMapper.selectOneByAppAndStream(event.getApp(), event.getStream());
            if (streamProxy != null) {
                ErrorCallback<StreamInfo> callback = CALLBACK_MAP.remove(streamProxy.getId());
                StreamInfo streamInfo = STREAM_INFO_MAP.remove(streamProxy.getId());
                if (callback != null && streamInfo != null) {
                    callback.run(InviteErrorCode.SUCCESS.getCode(), InviteErrorCode.SUCCESS.getMsg(), streamInfo);
                }
            }
        }
    }
    @Override
    public void start(Long id, ErrorCallback<StreamInfo> callback) {
        StreamProxyVo streamProxyVo = streamProxyMapper.select(id);

        AssertUtils.isNotNull(streamProxyVo == null,"代理信息未找到");
        StreamProxy streamProxy = MapstructUtils.convert(streamProxyVo, StreamProxy.class);
        StreamInfo streamInfo = startProxy(streamProxy);
        if (streamInfo == null) {
            callback.run(Response.BUSY_HERE, "busy here", null);
            return;
        }
        CALLBACK_MAP.put(id, callback);
        STREAM_INFO_MAP.put(id, streamInfo);
        MediaServer mediaServer = mediaServerService.getOne(streamProxy.getMediaServerId());
        if (mediaServer != null) {
            MediaInfo mediaInfo = mediaServerService.getMediaInfo(mediaServer, streamProxy.getApp(), streamProxy.getStream());
            if (mediaInfo != null) {
                CALLBACK_MAP.remove(id);
                STREAM_INFO_MAP.remove(id);
                callback.run(InviteErrorCode.SUCCESS.getCode(), InviteErrorCode.SUCCESS.getMsg(), streamInfo);
            }
        }
    }

    @Override
    public StreamInfo start(Long id) {
        StreamProxyVo streamProxyVo = streamProxyMapper.select(id);
        StreamProxy streamProxy = MapstructUtils.convert(streamProxyVo, StreamProxy.class);
        if (streamProxy == null) {
            throw new ServiceException("代理信息未找到");
        }
        return startProxy(streamProxy);
    }

    @Override
    public StreamInfo startProxy(StreamProxy streamProxy){
        if (!streamProxy.isEnable()) {
            return null;
        }
        MediaServer mediaServer;
        String mediaServerId = streamProxy.getRelatesMediaServerId();
        if (mediaServerId == null) {
            mediaServer = mediaServerService.getMediaServerForMinimumLoad(null);
        }else {
            mediaServer = mediaServerService.getOne(mediaServerId);
        }
        if (mediaServer == null) {
            throw new ServiceException(mediaServerId == null?"未找到可用的媒体节点":"未找到节点" + mediaServerId);
        }
        StreamInfo streamInfo = mediaServerService.startProxy(mediaServer, streamProxy);
        if (mediaServerId == null || !mediaServerId.equals(mediaServer.getId())) {
            streamProxy.setMediaServerId(mediaServer.getId());
            streamProxyMapper.insertOrUpdate(streamProxy);
        }
        return streamInfo;
    }

    @Override
    public void stop(Long id) {
        StreamProxyVo streamProxyVo = streamProxyMapper.select(id);
        StreamProxy streamProxy = MapstructUtils.convert(streamProxyVo, StreamProxy.class);
        if (streamProxy == null) {
            throw new ServiceException("代理信息未找到");
        }
        stopProxy(streamProxy);
    }

    @Override
    public void stopProxy(StreamProxy streamProxy){

        String mediaServerId = streamProxy.getMediaServerId();
        Assert.notNull(mediaServerId, "代理节点不存在");

        MediaServer mediaServer = mediaServerService.getOne(mediaServerId);
        if (mediaServer == null) {
            throw new ServiceException("媒体节点不存在");
        }
        if (ObjectUtils.isEmpty(streamProxy.getStreamKey())) {
            mediaServerService.closeStreams(mediaServer, streamProxy.getApp(), streamProxy.getStream());
        }else {
            mediaServerService.stopProxy(mediaServer, streamProxy.getStreamKey());
        }
        streamProxyMapper.deleteById(streamProxy.getId());
    }

}
