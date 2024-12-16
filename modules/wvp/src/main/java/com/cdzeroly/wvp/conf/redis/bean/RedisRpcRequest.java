package com.cdzeroly.wvp.conf.redis.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 通过redis发送请求
 * @author MGARY
 */
@Setter
@Getter
public class RedisRpcRequest {

    /**
     * 来自的WVP ID
     */
    private String fromId;


    /**
     * 目标的WVP ID
     */
    private String toId;

    /**
     * 序列号
     */
    private long sn;

    /**
     * 访问的路径
     */
    private String uri;

    /**
     * 参数
     */
    private Object param;

    @Override
    public String toString() {
        return "RedisRpcRequest{" +
                "uri='" + uri + '\'' +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", sn=" + sn +
                ", param=" + param +
                '}';
    }

    public RedisRpcResponse getResponse() {
        RedisRpcResponse response = new RedisRpcResponse();
        response.setFromId(fromId);
        response.setToId(toId);
        response.setSn(sn);
        response.setUri(uri);
        return response;
    }
}
