package com.cdzeroly.wvp.domain;

/**
 * 延迟结果筛选器
 * @author MGARY
 */
public interface DeferredResultFilter {

    /**
     *  处理程序
     * @param o  待处理对象和
     * @return  Object
     */
    Object handler(Object o);
}
