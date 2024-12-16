package com.cdzeroly.wvp.gb28181.event.device;

import lombok.Getter;
import lombok.Setter;

import javax.sip.Dialog;
import java.util.EventObject;

/**
 * @author MGARY
 */
@Setter
@Getter
public class DeviceNotFoundEvent extends EventObject {

    private String callId;

    /**
     * Constructs a prototypical Event.
     *
     * @param dialog
     * @throws IllegalArgumentException if source is null.
     */
    public DeviceNotFoundEvent(Dialog dialog) {
        super(dialog);
    }

}
