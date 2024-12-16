package com.cdzeroly.wvp.vmanager.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.async.DeferredResult;

@Setter
@Getter
public class DeferredResultEx<T> {

    private DeferredResult<T> deferredResult;

    private DeferredResultFilter filter;

    public DeferredResultEx(DeferredResult<T> result) {
        this.deferredResult = result;
    }


}
