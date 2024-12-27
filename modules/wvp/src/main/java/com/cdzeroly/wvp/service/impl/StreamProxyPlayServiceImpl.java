package com.cdzeroly.wvp.service.impl;

import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.domain.StreamProxy;
import com.cdzeroly.wvp.domain.vo.StreamProxyVo;
import com.cdzeroly.wvp.mapper.StreamProxyMapper;
import com.cdzeroly.wvp.service.IStreamProxyPlayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

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
        String mediaServerId = streamProxy.getMediaServerId();
        if (mediaServerId == null) {
            mediaServer = mediaServerService.getMediaServerForMinimumLoad(null);
        }else {
            mediaServer = mediaServerService.getOne(mediaServerId);
            if (mediaServer == null) {
                mediaServer = mediaServerService.getMediaServerForMinimumLoad(null);
            }
        }
        if (mediaServer == null) {
            throw new ServiceException("未找到可用的媒体节点");
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
        streamProxy.setMediaServerId(mediaServer.getId());
        streamProxy.setStreamKey(null);
        streamProxy.setPulling(false);
        streamProxyMapper.deleteById(streamProxy.getId());
    }

}
