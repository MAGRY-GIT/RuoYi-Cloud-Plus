package com.cdzeroly.wvp.conf.redis.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class RedisRpcMessage {

    private RedisRpcRequest request;

    private RedisRpcResponse response;

}
