package com.cdzeroly.wvp.media.zlm.event;

import com.cdzeroly.wvp.media.domian.MediaServer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * zlm 心跳事件
 * @author MGARY
 */
@Setter
@Getter
public class HookZlmServerKeepaliveEvent extends ApplicationEvent {

    public HookZlmServerKeepaliveEvent(Object source) {
        super(source);
    }

    private MediaServer mediaServer;

}
