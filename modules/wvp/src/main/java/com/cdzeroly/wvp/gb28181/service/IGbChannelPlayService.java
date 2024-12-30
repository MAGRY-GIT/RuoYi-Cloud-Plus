package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.gb28181.domian.CommonGbChannel;
import com.cdzeroly.wvp.gb28181.transmit.bean.InviteInfo;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.domain.bean.ErrorCallback;

public interface IGbChannelPlayService {

    void start(CommonGbChannel channel, InviteInfo inviteInfo, Platform platform, ErrorCallback<StreamInfo> callback);

    void play(CommonGbChannel channel, Platform platform, ErrorCallback<StreamInfo> callback);

    void playGbDeviceChannel(CommonGbChannel channel, ErrorCallback<StreamInfo> callback);

    void playProxy(CommonGbChannel channel, ErrorCallback<StreamInfo> callback);

    void playPush(CommonGbChannel channel, String platformDeviceId, String platformName, ErrorCallback<StreamInfo> callback);
}
