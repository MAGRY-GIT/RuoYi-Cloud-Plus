package com.cdzeroly.wvp.streamPush.service;

import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.service.domian.bean.ErrorCallback;

public interface IStreamPushPlayService {
    void start(Integer id, ErrorCallback<StreamInfo> callback, String platformDeviceId, String platformName );
}
