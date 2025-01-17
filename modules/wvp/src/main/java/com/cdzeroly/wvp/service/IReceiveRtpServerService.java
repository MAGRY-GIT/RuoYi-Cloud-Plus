package com.cdzeroly.wvp.service;

import com.cdzeroly.wvp.gb28181.bean.OpenRTPServerResult;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.domain.bean.ErrorCallback;
import com.cdzeroly.wvp.domain.bean.RTPServerParam;
import com.cdzeroly.wvp.domain.bean.SSRCInfo;

/**
 * 收到rtp服务器服务
 * @author MAGRY
 */
public interface IReceiveRtpServerService {
    /**
     * 打开 RTP 服务器
     * @param rtpServerParam  rtpServerParam
     * @param callback  错误回调
     * @return SSRCInfo
     */
    SSRCInfo openRtpServer(RTPServerParam rtpServerParam, ErrorCallback<OpenRTPServerResult> callback);

    /**
     * 关闭 RTP 服务器
     * @param mediaServer  流媒体服务器
     * @param ssrcInfo   校验信息
     */
    void closeRtpServer(MediaServer mediaServer, SSRCInfo ssrcInfo);
}
