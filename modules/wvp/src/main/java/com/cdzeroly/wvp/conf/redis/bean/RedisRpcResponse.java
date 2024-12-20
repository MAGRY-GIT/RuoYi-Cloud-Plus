package com.cdzeroly.wvp.conf.redis.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 通过redis发送回复
 * @author MGARY
 */
@Setter
@Getter
public class RedisRpcResponse {

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
     * 状态码
     */
    private Integer statusCode;

    /**
     * 访问的路径
     */
    private String uri;

    /**
     * 参数
     */
    private Object body;

    @Override
    public String toString() {
        return "RedisRpcResponse{" +
                "uri='" + uri + '\'' +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", sn=" + sn +
                ", statusCode=" + statusCode +
                ", body=" + body +
                '}';
    }
}
