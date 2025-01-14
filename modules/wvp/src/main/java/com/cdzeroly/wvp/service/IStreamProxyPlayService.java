package com.cdzeroly.wvp.service;

import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.domain.StreamProxy;
import com.cdzeroly.wvp.domain.bean.ErrorCallback;

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
    StreamInfo start(Long id);

    void start(Long id, ErrorCallback<StreamInfo> callback);

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
    void stop(Long id);


    /**
     * 停止代理
     * @param streamProxy
     */
    void stopProxy(StreamProxy streamProxy);
}
