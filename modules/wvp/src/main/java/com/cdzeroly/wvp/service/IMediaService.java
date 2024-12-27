package com.cdzeroly.wvp.service;

import com.cdzeroly.wvp.media.zlm.dto.ResultForOnPublish;
import com.cdzeroly.wvp.domain.MediaServer;

/**
 * 媒体信息业务
 */
public interface IMediaService {

    /**
     * 播放鉴权
     */
    boolean authenticatePlay(String app, String stream, String callId);

    ResultForOnPublish authenticatePublish(MediaServer mediaServer, String app, String stream, String params);

    boolean closeStreamOnNoneReader(String mediaServerId, String app, String stream, String schema);
}
