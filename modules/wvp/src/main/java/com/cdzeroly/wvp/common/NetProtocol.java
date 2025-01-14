package com.cdzeroly.wvp.common;

import lombok.Getter;

/**
 * @author MGARY
 * @program wvp-GB28181-pro
 * @date 2024/12/11 09:36
 */
@Getter
public enum NetProtocol {

    TCP("TCP"), TCP_PASSIVE("TCP-PASSIVE"), UDP("UDP");
    /**
     * 协议类型
     */
    final String protocol;

    NetProtocol(String protocol) {
        this.protocol = protocol;
    }

}
