package com.cdzeroly.wvp.media.zlm.dto;

import com.cdzeroly.wvp.gb28181.domian.bean.SendRtpInfo;

import java.text.ParseException;

/**
 * @author lin
 */
public interface ChannelOnlineEvent {

    void run(SendRtpInfo sendRtpItem) throws ParseException;
}
