package com.cdzeroly.wvp.gb28181.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class RemoteAddressInfo {
    private String ip;
    private Integer port;

    public RemoteAddressInfo(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

}
