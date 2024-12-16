package com.cdzeroly.wvp.vmanager.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class OtherRtpSendInfo {

    /**
     * 发流IP
     */
    private String sendLocalIp;

    /**
     * 音频发流端口
     */
    private int sendLocalPortForAudio;

    /**
     * 视频发流端口
     */
    private int sendLocalPortForVideo;

    /**
     * 收流IP
     */
    private String receiveIp;

    /**
     * 音频收流端口
     */
    private int receivePortForAudio;

    /**
     * 视频收流端口
     */
    private int receivePortForVideo;

    /**
     * 会话ID
     */
    private String callId;

    /**
     * 流ID
     */
    private String stream;

    /**
     * 推流应用名
     */
    private String pushApp;

    /**
     * 推流流ID
     */
    private String pushStream;

    /**
     * 推流SSRC
     */
    private String pushSSRC;


    @Override
    public String toString() {
        return "OtherRtpSendInfo{" +
                "sendLocalIp='" + sendLocalIp + '\'' +
                ", sendLocalPortForAudio=" + sendLocalPortForAudio +
                ", sendLocalPortForVideo=" + sendLocalPortForVideo +
                ", receiveIp='" + receiveIp + '\'' +
                ", receivePortForAudio=" + receivePortForAudio +
                ", receivePortForVideo=" + receivePortForVideo +
                ", callId='" + callId + '\'' +
                ", stream='" + stream + '\'' +
                ", pushApp='" + pushApp + '\'' +
                ", pushStream='" + pushStream + '\'' +
                ", pushSSRC='" + pushSSRC + '\'' +
                '}';
    }
}
