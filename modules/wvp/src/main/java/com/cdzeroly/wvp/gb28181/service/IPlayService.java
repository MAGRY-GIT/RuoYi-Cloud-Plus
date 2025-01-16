package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.wvp.common.InviteInfo;
import com.cdzeroly.wvp.common.InviteSessionType;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.domain.bean.SendRtpInfo;
import com.cdzeroly.wvp.domain.CommonGbChannel;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.DeviceChannel;
import com.cdzeroly.wvp.domain.Platform;
import com.cdzeroly.wvp.domain.bo.AudioBroadcastEvent;
import com.cdzeroly.wvp.domain.bean.MediaInfo;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.domain.bean.ErrorCallback;
import com.cdzeroly.wvp.domain.bean.SSRCInfo;
import com.cdzeroly.wvp.domain.vo.AudioBroadcastVo;
import gov.nist.javax.sip.message.SIPResponse;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import javax.sip.header.CallIdHeader;
import java.text.ParseException;

/**
 * 点播处理
 * @author MAGRY
 */
public interface IPlayService {

    SSRCInfo play(MediaServer mediaServerItem, String deviceId, String channelId, String ssrc, ErrorCallback<StreamInfo> callback);

    StreamInfo onPublishHandlerForPlay(MediaServer mediaServerItem, MediaInfo mediaInfo, Device device, DeviceChannel channel);

    MediaServer getNewMediaServerItem(Device device);

    void playBack(Device device, DeviceChannel channel, String startTime, String endTime, ErrorCallback<StreamInfo> callback);
    void zlmServerOffline(MediaServer mediaServer);

    void download(Device device, DeviceChannel channel, String startTime, String endTime, int downloadSpeed, ErrorCallback<StreamInfo> callback);

    StreamInfo getDownLoadInfo(Device device, DeviceChannel channel, String stream);

    void zlmServerOnline(MediaServer mediaServer);

    AudioBroadcastVo audioBroadcast(Device device, DeviceChannel deviceChannel, Boolean broadcastMode);

    boolean audioBroadcastCmd(Device device, DeviceChannel channel, MediaServer mediaServerItem, String app, String stream, int timeout, boolean isFromPlatform, AudioBroadcastEvent event) throws InvalidArgumentException, ParseException, SipException;

    boolean audioBroadcastInUse(Device device, DeviceChannel channel);

    void stopAudioBroadcast(Device device, DeviceChannel channel);

    void pauseRtp(String streamId) throws ServiceException, InvalidArgumentException, ParseException, SipException;

    void resumeRtp(String streamId) throws ServiceException, InvalidArgumentException, ParseException, SipException;

    void startPushStream(SendRtpInfo sendRtpItem, DeviceChannel channel, SIPResponse sipResponse, Platform platform, CallIdHeader callIdHeader);

    void startSendRtpStreamFailHand(SendRtpInfo sendRtpItem, Platform platform, CallIdHeader callIdHeader);

    void talkCmd(Device device, DeviceChannel channel, MediaServer mediaServerItem, String stream, AudioBroadcastEvent event);

    void stopTalk(Device device, DeviceChannel channel, Boolean streamIsReady);

    void getSnap(String deviceId, String channelId, String fileName, ErrorCallback errorCallback);

    void stop(InviteSessionType type, Device device, DeviceChannel channel, String stream);

    void stop(InviteInfo inviteInfo);

    void play(CommonGbChannel channel, ErrorCallback<StreamInfo> callback);

    void playBack(CommonGbChannel channel, Long startTime, Long stopTime, ErrorCallback<StreamInfo> callback);

    void download(CommonGbChannel channel, Long startTime, Long stopTime, Integer downloadSpeed, ErrorCallback<StreamInfo> callback);

}
