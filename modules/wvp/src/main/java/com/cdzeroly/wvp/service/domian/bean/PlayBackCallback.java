package com.cdzeroly.wvp.service.domian.bean;

public interface PlayBackCallback<T> {

    void call(PlayBackResult<T> msg);

}
