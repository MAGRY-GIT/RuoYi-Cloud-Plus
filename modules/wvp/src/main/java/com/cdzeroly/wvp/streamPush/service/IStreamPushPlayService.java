package com.cdzeroly.wvp.streamPush.service;

import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.service.domian.bean.ErrorCallback;

/**
 * 流媒体推送播放服务
 * @author MGARY
 */
public interface IStreamPushPlayService {
    /**
     * 启动
     * @param id ID
     * @param callback 错误回调
     * @param platformDeviceId 平台设备ID
     * @param platformName  平台名称
     */
    void start(Integer id, ErrorCallback<StreamInfo> callback, String platformDeviceId, String platformName );
}
