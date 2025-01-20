package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.domain.CommonGbChannel;
import com.cdzeroly.wvp.gb28181.transmit.bean.InviteInfo;
import com.cdzeroly.wvp.domain.Platform;
import com.cdzeroly.wvp.domain.bean.ErrorCallback;

/**
 * GB频道播放服务
 * @author MGARY
 */
public interface IGbChannelPlayService {

    /**
     * 开始
     * @param channel
     * @param inviteInfo
     * @param platform
     * @param callback
     */
    void start(CommonGbChannel channel, InviteInfo inviteInfo, Platform platform, ErrorCallback<StreamInfo> callback);

    /**
     * 播放
     * @param channel
     * @param platform
     * @param callback
     */
    void play(CommonGbChannel channel, Platform platform, ErrorCallback<StreamInfo> callback);

    /**
     *  播放GB设备频道
     * @param channel
     * @param callback
     */
    void playGbDeviceChannel(CommonGbChannel channel, ErrorCallback<StreamInfo> callback);


    /**
     *  播放代理
     * @param channel
     * @param callback
     */
    void playProxy(CommonGbChannel channel, ErrorCallback<StreamInfo> callback);


    /**
     *  播放推流
     * @param channel
     * @param platformDeviceId
     * @param platformName
     * @param callback
     */
    void playPush(CommonGbChannel channel, String platformDeviceId, String platformName, ErrorCallback<StreamInfo> callback);
}
