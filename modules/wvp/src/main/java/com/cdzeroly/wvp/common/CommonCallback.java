package com.cdzeroly.wvp.common;

/**
 * @author MGARY
 */
public interface CommonCallback<T>{
    /**
     * 公共允许类
     * @param t 请求参数
     */
    public void run(T t);
}
