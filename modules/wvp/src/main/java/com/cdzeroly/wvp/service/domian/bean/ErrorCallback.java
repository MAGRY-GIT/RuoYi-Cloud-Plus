package com.cdzeroly.wvp.service.domian.bean;

/**
 * @author MGARY
 */
public interface ErrorCallback<T> {

    void run(int code, String msg, T data);
}
