package com.cdzeroly.wvp.conf.exception;

import lombok.Getter;

/**
 * @author lin
 */
@Getter
public class SsrcTransactionNotFoundException extends Exception{
    private final String deviceId;
    private final String channelId;
    private final String callId;
    private final String stream;



    public SsrcTransactionNotFoundException(String deviceId, String channelId, String callId, String stream) {
        this.deviceId = deviceId;
        this.channelId = channelId;
        this.callId = callId;
        this.stream = stream;
    }

    @Override
    public String getMessage() {
        StringBuilder msg = new StringBuilder();
        msg.append(String.format("缓存事务信息未找到，device：%s channel: %s ",  deviceId, channelId));
        if (callId != null) {
            msg.append(",callId: ").append(callId);
        }
        if (stream != null) {
            msg.append(",stream: ").append(stream);
        }
        return msg.toString();
    }
}
