package com.cdzeroly.wvp.service;

import com.cdzeroly.wvp.gb28181.bean.OpenRTPServerResult;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.domain.bean.ErrorCallback;
import com.cdzeroly.wvp.domain.bean.RTPServerParam;
import com.cdzeroly.wvp.domain.bean.SSRCInfo;

/**
 * @author MAGRY
 */
public interface IReceiveRtpServerService {
    /**
     * 打开 RTP 服务器
     * @param rtpServerParam
     * @param callback
     * @return
     */
    SSRCInfo openRTPServer(RTPServerParam rtpServerParam, ErrorCallback<OpenRTPServerResult> callback);

    /**
     * 关闭 RTP 服务器
     * @param mediaServer
     * @param ssrcInfo
     */
    void closeRTPServer(MediaServer mediaServer, SSRCInfo ssrcInfo);
}
