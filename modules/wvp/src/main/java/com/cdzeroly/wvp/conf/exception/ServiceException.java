package com.cdzeroly.wvp.conf.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lin
 */
@Setter
@Getter
public class ServiceException extends Exception{
    private String msg;



    public ServiceException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
