package com.cdzeroly.wvp.media.zlm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MGARY
 */
public class ServerKeepaliveData {
    @JsonProperty("Buffer")
    private  Integer buffer;
    @JsonProperty("BufferLikeString")
    private  Integer bufferLikeString;
    @JsonProperty("BufferList")
    private  Integer bufferList;
    @JsonProperty("BufferRaw")
    private  Integer bufferRaw;
    @JsonProperty("Frame")
    private  Integer frame;
    @JsonProperty("FrameImp")
    private  Integer frameImp;
    @JsonProperty("MediaSource")
    private  Integer mediaSource;
    @JsonProperty("MultiMediaSourceMuxer")
    private  Integer multiMediaSourceMuxer;
    @JsonProperty("RtmpPacket")
    private  Integer rtmpPacket;
    @JsonProperty("RtpPacket")
    private  Integer rtpPacket;
    @JsonProperty("Socket")
    private  Integer socket;
    @JsonProperty("TcpClient")
    private  Integer tcpClient;
    @JsonProperty("TcpServer")
    private  Integer tcpServer;
    @JsonProperty("TcpSession")
    private  Integer tcpSession;
    @JsonProperty("UdpServer")
    private  Integer udpServer;
    @JsonProperty("UdpSession")
    private  Integer udpSession;
         }
