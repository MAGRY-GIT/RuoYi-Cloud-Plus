package com.cdzeroly.wvp.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author ace
 * @date 2017/9/15
 */
@Getter
@Setter
public class ProtocolAutoConfig {
    @Value("${netty.port}")
    private Integer port;
    @Value("${netty.soBacklog}")
    private Integer soBacklog;
    @Value("${netty.soKeepAlive}")
    private Boolean soKeepAlive;
    @Value("${netty.threadCount.boss}")
    private Integer boss;
    @Value("${netty.threadCount.worker}")
    private Integer worker;

}
