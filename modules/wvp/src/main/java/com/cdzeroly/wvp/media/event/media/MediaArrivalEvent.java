package com.cdzeroly.wvp.media.event.media;

import com.cdzeroly.wvp.domain.bean.MediaInfo;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.zlm.dto.hook.OnStreamChangedHookParam;
import com.cdzeroly.wvp.domain.vo.StreamContentVo;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 流到来事件
 * @author MGARY
 */

@Setter
@Getter
public class MediaArrivalEvent extends MediaEvent {
    public MediaArrivalEvent(Object source) {
        super(source);
    }

    public static MediaArrivalEvent getInstance(Object source, OnStreamChangedHookParam hookParam, MediaServer mediaServer, String serverId){
        MediaArrivalEvent mediaArrivalEvent = new MediaArrivalEvent(source);
        mediaArrivalEvent.setMediaInfo(MediaInfo.getInstance(hookParam, mediaServer, serverId));
        mediaArrivalEvent.setApp(hookParam.getApp());
        mediaArrivalEvent.setStream(hookParam.getStream());
        mediaArrivalEvent.setMediaServer(mediaServer);
        mediaArrivalEvent.setSchema(hookParam.getSchema());
        mediaArrivalEvent.setSchema(hookParam.getSchema());
        mediaArrivalEvent.setParamMap(hookParam.getParamMap());
        return mediaArrivalEvent;
    }

    private MediaInfo mediaInfo;

    private String callId;

    private StreamContentVo streamInfo;

    private Map<String, String> paramMap;

    private String serverId;


}
