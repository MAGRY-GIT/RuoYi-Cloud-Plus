package com.cdzeroly.wvp.service;

import com.cdzeroly.wvp.gb28181.domian.bean.OpenRTPServerResult;
import com.cdzeroly.wvp.media.domian.MediaServer;
import com.cdzeroly.wvp.service.bean.ErrorCallback;
import com.cdzeroly.wvp.service.bean.RTPServerParam;
import com.cdzeroly.wvp.service.bean.SSRCInfo;

public interface IReceiveRtpServerService {
    SSRCInfo openRTPServer(RTPServerParam rtpServerParam, ErrorCallback<OpenRTPServerResult> callback);

    void closeRTPServer(MediaServer mediaServer, SSRCInfo ssrcInfo);
}
