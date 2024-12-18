package com.cdzeroly.wvp.media.event.media;

import com.cdzeroly.wvp.media.domian.MediaServer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 流到来事件
 * @author MGARY
 */
@Setter
@Getter
public class MediaEvent extends ApplicationEvent {

    public MediaEvent(Object source) {
        super(source);
    }

    private String app;

    private String stream;

    private MediaServer mediaServer;

    private String schema;


}
