package com.cdzeroly.wvp.service.bean;

import lombok.Data;

@Data
public class SSRCInfo {

    private int port;
    private String ssrc;
    private String Stream;
    private String timeOutTaskKey;

    public SSRCInfo(int port, String ssrc, String stream, String timeOutTaskKey) {
        this.port = port;
        this.ssrc = ssrc;
        this.Stream = stream;
        this.timeOutTaskKey = timeOutTaskKey;
    }
}
