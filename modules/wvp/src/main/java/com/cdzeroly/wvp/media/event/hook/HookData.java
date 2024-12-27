package com.cdzeroly.wvp.media.event.hook;

import com.cdzeroly.wvp.domain.bean.MediaInfo;
import com.cdzeroly.wvp.domain.bean.RecordInfo;
import com.cdzeroly.wvp.media.event.media.MediaArrivalEvent;
import com.cdzeroly.wvp.media.event.media.MediaEvent;
import com.cdzeroly.wvp.media.event.media.MediaPublishEvent;
import com.cdzeroly.wvp.media.event.media.MediaRecordMp4Event;
import com.cdzeroly.wvp.domain.MediaServer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Hook返回的内容
 * @author MGARY
 */
@Data
public class HookData {
    /**
     * 应用名
     */
    private String app;
    /**
     * 流ID
     */
    private String stream;
    /**
     * 流媒体节点
     */
    private MediaServer mediaServer;
    /**
     * 协议
     */
    private String schema;

    /**
     * 流信息
     */
    private MediaInfo mediaInfo;

    /**
     * 录像信息
     */
    private RecordInfo recordInfo;

    @Schema(description = "推流的额外参数")
    private String params;
    public static HookData getInstance(MediaEvent mediaEvent) {
        HookData hookData = new HookData();
        hookData.setApp(mediaEvent.getApp());
        hookData.setStream(mediaEvent.getStream());
        hookData.setSchema(mediaEvent.getSchema());
        hookData.setMediaServer(mediaEvent.getMediaServer());

        if (mediaEvent instanceof MediaPublishEvent event) {
            hookData.setParams(event.getParams());
        }else if (mediaEvent instanceof MediaArrivalEvent event) {
            hookData.setMediaInfo(event.getMediaInfo());
        }else if (mediaEvent instanceof MediaRecordMp4Event event) {
            hookData.setRecordInfo(event.getRecordInfo());
        }
        return hookData;
    }
}
