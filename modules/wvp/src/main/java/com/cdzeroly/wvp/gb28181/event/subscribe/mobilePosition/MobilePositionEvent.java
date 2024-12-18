package com.cdzeroly.wvp.gb28181.event.subscribe.mobilePosition;

import com.cdzeroly.wvp.gb28181.domian.MobilePosition;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


/**
 * @author MGARY
 */
@Setter
@Getter
public class MobilePositionEvent extends ApplicationEvent {
    public MobilePositionEvent(Object source) {
        super(source);
    }

    private MobilePosition mobilePosition;
}
