package com.cdzeroly.wvp.streamProxy.service;

import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.streamProxy.bean.StreamProxy;

public interface IStreamProxyPlayService {

    StreamInfo start(int id);

    StreamInfo startProxy(StreamProxy streamProxy);

    void stop(int id);

    void stopProxy(StreamProxy streamProxy);
}
