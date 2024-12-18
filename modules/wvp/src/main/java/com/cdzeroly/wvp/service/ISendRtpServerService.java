package com.cdzeroly.wvp.service;

import com.cdzeroly.wvp.gb28181.domian.bean.SendRtpInfo;
import com.cdzeroly.wvp.media.domian.MediaServer;

import java.util.List;

/**
 * 发送 rtp 服务器服务
 * @author MGARY
 */
public interface ISendRtpServerService {

    /**
     *  创建发送rtp信息
     * @param mediaServer
     * @param ip
     * @param port
     * @param ssrc
     * @param requesterId
     * @param deviceId
     * @param channelId
     * @param isTcp
     * @param rtcp
     * @return
     */
    SendRtpInfo createSendRtpInfo(MediaServer mediaServer, String ip, Integer port, String ssrc, String requesterId,
                                  String deviceId, Integer channelId, Boolean isTcp, Boolean rtcp);

    /**
     *  创建发送rtp信息
     * @param mediaServer
     * @param ip
     * @param port
     * @param ssrc
     * @param platformId
     * @param app
     * @param stream
     * @param channelId
     * @param tcp
     * @param rtcp
     * @return
     */
    SendRtpInfo createSendRtpInfo(MediaServer mediaServer, String ip, Integer port, String ssrc, String platformId,
                                  String app, String stream, Integer channelId, Boolean tcp, Boolean rtcp);

    /**
     * 更新
     * @param sendRtpItem
     */
    void update(SendRtpInfo sendRtpItem);

    /**
     *
     * @param channelId
     * @param targetId
     * @return
     */
    SendRtpInfo queryByChannelId(Integer channelId, String targetId);

    SendRtpInfo queryByCallId(String callId);

    List<SendRtpInfo> queryByStream(String stream);

    SendRtpInfo queryByStream(String stream, String targetId);

    void delete(SendRtpInfo sendRtpInfo);

    void deleteByCallId(String callId);

    void deleteByStream(String Stream, String targetId);

    void deleteByChannel(Integer channelId, String targetId);

    List<SendRtpInfo> queryAll();

    boolean isChannelSendingRTP(Integer channelId);

    List<SendRtpInfo> queryForPlatform(String platformId);

    List<SendRtpInfo> queryByChannelId(int id);

    void deleteByStream(String stream);

    int getNextPort(MediaServer mediaServer);
}
