package com.cdzeroly.wvp.gb28181.domian.bean;

import com.cdzeroly.wvp.gb28181.domian.Device;
import lombok.Getter;
import lombok.Setter;
import org.dom4j.Element;

import javax.sip.RequestEvent;

/**
 * @author lin
 */
@Setter
@Getter
public class HandlerCatchData {
    private RequestEvent evt;
    private Device device;
    private Element rootElement;

    public HandlerCatchData(RequestEvent evt, Device device, Element rootElement) {
        this.evt = evt;
        this.device = device;
        this.rootElement = rootElement;
    }

}
