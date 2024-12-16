package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.gb28181.domian.bean.InviteInfo;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.service.domian.bean.ErrorCallback;

public interface IGbChannelPlayService {

    void start(CommonGBChannel channel, InviteInfo inviteInfo, Platform platform, ErrorCallback<StreamInfo> callback);

    void play(CommonGBChannel channel, Platform platform,  ErrorCallback<StreamInfo> callback);

    void playGbDeviceChannel(CommonGBChannel channel, ErrorCallback<StreamInfo> callback);

    void playProxy(CommonGBChannel channel, ErrorCallback<StreamInfo> callback);

    void playPush(CommonGBChannel channel, String platformDeviceId, String platformName, ErrorCallback<StreamInfo> callback);
}
