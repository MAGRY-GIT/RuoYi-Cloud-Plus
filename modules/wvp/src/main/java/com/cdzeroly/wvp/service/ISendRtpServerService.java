package com.cdzeroly.wvp.service;

import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.domain.bean.SendRtpInfo;

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
                                  String deviceId, Long channelId, Boolean isTcp, Boolean rtcp);

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
                                  String app, String stream, Long channelId, Boolean tcp, Boolean rtcp);

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
    SendRtpInfo queryByChannelId(Long channelId, String targetId);

    SendRtpInfo queryByCallId(String callId);

    List<SendRtpInfo> queryByStream(String stream);

    SendRtpInfo queryByStream(String stream, String targetId);

    void delete(SendRtpInfo sendRtpInfo);

    void deleteByCallId(String callId);

    void deleteByStream(String stream, String targetId);

    void deleteByChannel(Long channelId, String targetId);

    List<SendRtpInfo> queryAll();

    boolean isChannelSendingRTP(Integer channelId);

    List<SendRtpInfo> queryForPlatform(String platformId);

    List<SendRtpInfo> queryByChannelId(int id);

    void deleteByStream(String stream);

    int getNextPort(MediaServer mediaServer);
}
