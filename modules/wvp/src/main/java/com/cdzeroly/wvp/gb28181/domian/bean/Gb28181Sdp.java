package com.cdzeroly.wvp.gb28181.domian.bean;

import lombok.Getter;
import lombok.Setter;

import javax.sdp.SessionDescription;

/**
 * 28181 的SDP解析器
 * @author MGARY
 */
@Setter
@Getter
public class Gb28181Sdp  {
    private SessionDescription baseSdb;
    private String ssrc;

    private String mediaDescription;

    public static Gb28181Sdp getInstance(SessionDescription baseSdb, String ssrc, String mediaDescription) {
        Gb28181Sdp gb28181Sdp = new Gb28181Sdp();
        gb28181Sdp.setBaseSdb(baseSdb);
        gb28181Sdp.setSsrc(ssrc);
        gb28181Sdp.setMediaDescription(mediaDescription);
        return gb28181Sdp;
    }


}
