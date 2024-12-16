package com.cdzeroly.wvp.streamPush.bean;

import com.cdzeroly.wvp.streamPush.domian.vo.StreamPushVo;
import lombok.Data;

@Data
public class RedisPushStreamMessage {

    private String gbId;
    private String app;
    private String stream;
    private String name;
    private boolean status;

    public StreamPushVo buildstreamPush() {
        StreamPushVo push = new StreamPushVo();
        push.setApp(app);
        push.setStream(stream);
        push.setGbName(name);
        push.setGbDeviceId(gbId);
        push.setStartOfflinePush(true);
        push.setGbStatus(status?"ON":"OFF");
        return push;
    }
}
