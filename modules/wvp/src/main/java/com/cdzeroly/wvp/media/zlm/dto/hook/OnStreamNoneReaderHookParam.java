package com.cdzeroly.wvp.media.zlm.dto.hook;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class OnStreamNoneReaderHookParam extends HookParam{

    private String schema;
    private String app;
    private String stream;
    private String vhost;

    @Override
    public String toString() {
        return "OnStreamNoneReaderHookParam{" +
                "schema='" + schema + '\'' +
                ", app='" + app + '\'' +
                ", stream='" + stream + '\'' +
                ", vhost='" + vhost + '\'' +
                '}';
    }
}
