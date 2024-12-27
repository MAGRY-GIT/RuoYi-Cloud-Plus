package com.cdzeroly.wvp.domain.bean;

import com.cdzeroly.wvp.domain.vo.StreamPushVo;
import lombok.Data;

/**
 * @author MGARY
 */
@Data
public class RedisPushStreamMessage {

    private String gbId;
    private String app;
    private String stream;
    private String name;
    private boolean status;

    public StreamPushVo buildStreamPush() {
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
