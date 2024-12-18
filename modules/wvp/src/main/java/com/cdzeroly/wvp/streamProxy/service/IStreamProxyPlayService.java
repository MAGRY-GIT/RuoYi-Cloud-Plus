package com.cdzeroly.wvp.streamProxy.service;

import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.streamProxy.domain.StreamProxy;

/**
 * 流代理播放服务
 * @author MGARY
 */
public interface IStreamProxyPlayService {

    /**
     * 播放
     * @param id
     * @return
     */
    StreamInfo start(int id);

    /**
     * 开启代理
     * @param streamProxy
     * @return
     */
    StreamInfo startProxy(StreamProxy streamProxy);

    /**
     * 停止
     * @param id
     */
    void stop(int id);


    /**
     * 停止代理
     * @param streamProxy
     */
    void stopProxy(StreamProxy streamProxy);
}
