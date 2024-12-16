package com.cdzeroly.wvp.media.zlm.event;

import com.cdzeroly.wvp.media.domian.MediaServer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * zlm server_start事件
 * @author MGARY
 */
@Setter
@Getter
public class HookZlmServerStartEvent extends ApplicationEvent {

    public HookZlmServerStartEvent(Object source) {
        super(source);
    }

    private MediaServer mediaServerItem;

}
