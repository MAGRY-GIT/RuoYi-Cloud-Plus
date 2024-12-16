package com.cdzeroly.wvp.service.bean;

public interface PlayBackCallback<T> {

    void call(PlayBackResult<T> msg);

}
