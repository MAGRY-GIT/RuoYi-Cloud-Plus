package com.cdzeroly.wvp.conf.redis.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class RedisRpcMessage {

    /**
     * 请求参数
     */
    private RedisRpcRequest request;


    /**
     * 返回参数
     */
    private RedisRpcResponse response;

}
