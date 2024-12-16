package com.cdzeroly.wvp.streamProxy.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.conf.exception.ControllerException;
import com.cdzeroly.wvp.media.domian.MediaServer;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.streamProxy.bean.StreamProxy;
import com.cdzeroly.wvp.streamProxy.mapper.StreamProxyMapper;
import com.cdzeroly.wvp.streamProxy.service.IStreamProxyPlayService;
import com.cdzeroly.wvp.vmanager.bean.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * 视频代理业务
 */
@Slf4j
@Service
@DS("master")
public class StreamProxyPlayServiceImpl implements IStreamProxyPlayService {

    @Autowired
    private StreamProxyMapper streamProxyMapper;

    @Autowired
    private IMediaServerService mediaServerService;

    @Override
    public StreamInfo start(int id) {
        StreamProxy streamProxy = streamProxyMapper.select(id);
        if (streamProxy == null) {
            throw new ControllerException(ErrorCode.ERROR404.getCode(), "代理信息未找到");
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
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "未找到可用的媒体节点");
        }
        StreamInfo streamInfo = mediaServerService.startProxy(mediaServer, streamProxy);
        if (mediaServerId == null || !mediaServerId.equals(mediaServer.getId())) {
            streamProxy.setMediaServerId(mediaServer.getId());
            streamProxyMapper.insertOrUpdate(streamProxy);
        }
        return streamInfo;
    }

    @Override
    public void stop(int id) {
        StreamProxy streamProxy = streamProxyMapper.select(id);
        if (streamProxy == null) {
            throw new ControllerException(ErrorCode.ERROR404.getCode(), "代理信息未找到");
        }
        stopProxy(streamProxy);
    }

    @Override
    public void stopProxy(StreamProxy streamProxy){

        String mediaServerId = streamProxy.getMediaServerId();
        Assert.notNull(mediaServerId, "代理节点不存在");

        MediaServer mediaServer = mediaServerService.getOne(mediaServerId);
        if (mediaServer == null) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "媒体节点不存在");
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
