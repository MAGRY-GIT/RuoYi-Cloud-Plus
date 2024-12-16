package com.cdzeroly.wvp.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * @author MGARY
 */

@Schema(description = "流地址信息")
public class StreamURL implements Serializable,Cloneable {
    @Setter
    @Getter
    @Schema(description = "协议")
    private String protocol;
    @Setter
    @Getter
    @Schema(description = "主机地址")
    private String host;
    @Setter
    @Getter
    @Schema(description = "端口")
    private int port = -1;
    @Setter
    @Getter
    @Schema(description = "定位位置")
    private String file;

    @Schema(description = "拼接后的地址")
    private String url;

    public StreamURL() {
    }

    public StreamURL(String protocol, String host, int port, String file) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.file = file;
    }


    public String getUrl() {
        return this.toString();
    }

    @Override
    public String toString() {
        if (protocol != null && host != null && port != -1 ) {
            return String.format("%s://%s:%s/%s", protocol, host, port, file);
        }else {
            return null;
        }
    }
    @Override
    public StreamURL clone() throws CloneNotSupportedException {
        return (StreamURL) super.clone();
    }
}
