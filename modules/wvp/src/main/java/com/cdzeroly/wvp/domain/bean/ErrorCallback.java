package com.cdzeroly.wvp.domain.bean;

/**
 * @author MGARY
 */
public interface ErrorCallback<T> {

    void run(int code, String msg, T data);
}
