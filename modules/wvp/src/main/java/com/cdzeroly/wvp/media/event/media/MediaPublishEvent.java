package com.cdzeroly.wvp.media.event.media;

import com.cdzeroly.wvp.media.domian.MediaServer;
import com.cdzeroly.wvp.media.zlm.dto.hook.OnPublishHookParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 推流鉴权事件
 * @author MGARY
 */
@Setter
@Getter
public class MediaPublishEvent extends MediaEvent {
    public MediaPublishEvent(Object source) {
        super(source);
    }

    public static MediaPublishEvent getInstance(Object source, OnPublishHookParam hookParam, MediaServer mediaServer){
        MediaPublishEvent mediaPublishEvent = new MediaPublishEvent(source);
        mediaPublishEvent.setApp(hookParam.getApp());
        mediaPublishEvent.setStream(hookParam.getStream());
        mediaPublishEvent.setMediaServer(mediaServer);
        mediaPublishEvent.setSchema(hookParam.getSchema());
        mediaPublishEvent.setParams(hookParam.getParams());
        return mediaPublishEvent;
    }

    private String params;

}
