package com.cdzeroly.wvp.domain.bean;

import lombok.Data;

/**
 * @author MGARY
 */
@Data
public class SSRCInfo {

    private int port;
    private String ssrc;
    private String string;
    private String timeOutTaskKey;

    public SSRCInfo(int port, String ssrc, String string, String timeOutTaskKey) {
        this.port = port;
        this.ssrc = ssrc;
        this.string = string;
        this.timeOutTaskKey = timeOutTaskKey;
    }
}
