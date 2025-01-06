package com.cdzeroly.wvp.domain.bean;

import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.Platform;
import lombok.Getter;
import lombok.Setter;
import org.dom4j.Element;

import javax.sip.RequestEvent;

/**
 * @author MGARY
 */
@Setter
@Getter
public class SipMsgInfo {
    private RequestEvent evt;
    private Device device;
    private Platform platform;
    private Element rootElement;

    public SipMsgInfo(RequestEvent evt, Device device, Element rootElement) {
        this.evt = evt;
        this.device = device;
        this.rootElement = rootElement;
    }

    public SipMsgInfo(RequestEvent evt, Platform platform, Element rootElement) {
        this.evt = evt;
        this.platform = platform;
        this.rootElement = rootElement;
    }

}
