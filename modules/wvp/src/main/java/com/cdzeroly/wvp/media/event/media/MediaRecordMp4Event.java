package com.cdzeroly.wvp.media.event.media;

import com.cdzeroly.wvp.media.domian.bean.RecordInfo;
import com.cdzeroly.wvp.media.domian.MediaServer;
import com.cdzeroly.wvp.media.zlm.dto.hook.OnRecordMp4HookParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 录像文件生成事件
 * @author MGARY
 */
@Setter
@Getter
public class MediaRecordMp4Event extends MediaEvent {
    public MediaRecordMp4Event(Object source) {
        super(source);
    }

    private RecordInfo recordInfo;

    public static MediaRecordMp4Event getInstance(Object source, OnRecordMp4HookParam hookParam, MediaServer mediaServer){
        MediaRecordMp4Event mediaRecordMp4Event = new MediaRecordMp4Event(source);
        mediaRecordMp4Event.setApp(hookParam.getApp());
        mediaRecordMp4Event.setStream(hookParam.getStream());
        RecordInfo recordInfo = RecordInfo.getInstance(hookParam);
        mediaRecordMp4Event.setRecordInfo(recordInfo);
        mediaRecordMp4Event.setMediaServer(mediaServer);
        return mediaRecordMp4Event;
    }

}
