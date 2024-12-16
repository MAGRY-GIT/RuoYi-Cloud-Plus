package com.cdzeroly.wvp.service.domian.bean;

public interface ErrorCallback<T> {

    void run(int code, String msg, T data);
}
