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

    /**
     * 无人观看关闭流
     * @param mediaServerId
     * @param app
     * @param stream
     * @param schema
     * @return
     */
    boolean closeStreamOnNoneReader(String mediaServerId, String app, String stream, String schema);
}
