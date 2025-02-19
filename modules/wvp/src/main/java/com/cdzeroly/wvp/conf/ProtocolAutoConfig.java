package com.cdzeroly.wvp.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ace
 * @date 2017/9/15
 */
@Getter
@Setter
@Configuration
public class ProtocolAutoConfig {
    @Value("${netty.port:9530}")
    private Integer port;
    @Value("${netty.soBacklog:1024}")
    private Integer soBacklog;
    @Value("${netty.soKeepAlive:true}")
    private Boolean soKeepAlive;
    @Value("${netty.threadCount.boss:2}")
    private Integer boss;
    @Value("${netty.threadCount.worker:2}")
    private Integer worker;
}
