package com.cdzeroly.wvp.gb28181.transmit.bean;

import lombok.Data;

/**
 * 从INVITE消息中解析需要的信息
 * @author MAGRY
 */
@Data
public class InviteInfo {

    /**
     * 请求ID
     */
    private String requesterId;
    private String targetChannelId;
    private String sourceChannelId;
    private String sessionName;
    private String ssrc;
    private boolean tcp;
    private boolean tcpActive;
    private String callId;
    private Long startTime;
    private Long stopTime;
    private String downloadSpeed;
    private String ip;
    private Integer port;

}
