package com.cdzeroly.wvp.service.redisMsg;

import com.cdzeroly.wvp.common.CommonCallback;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.domain.bean.SendRtpInfo;
import com.cdzeroly.wvp.domain.WVPResult;

public interface IRedisRpcService {

    SendRtpInfo getSendRtpItem(String callId);

    WVPResult startSendRtp(String callId, SendRtpInfo sendRtpItem);

    WVPResult stopSendRtp(String callId);

    long waitePushStreamOnline(SendRtpInfo sendRtpItem, CommonCallback<Long> callback);

    void stopWaitePushStreamOnline(SendRtpInfo sendRtpItem);

    void rtpSendStopped(String callId);

    void removeCallback(long key);

    long onStreamOnlineEvent(String app, String stream, CommonCallback<StreamInfo> callback);
    void unPushStreamOnlineEvent(String app, String stream);
}
