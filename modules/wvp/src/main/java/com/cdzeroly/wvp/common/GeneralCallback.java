package com.cdzeroly.wvp.common;

/**
 * @author MGARY
 * @param <T>
 */
public interface GeneralCallback<T>{
    /**
     *  常规回调
     * @param code 编码
     * @param msg 消息
     * @param data 数据
     */
    void run(int code, String msg, T data);
}
