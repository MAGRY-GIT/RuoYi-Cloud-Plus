package com.cdzeroly.wvp.conf.exception;

import com.cdzeroly.wvp.vmanager.bean.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常，controller出现错误时直接抛出异常由全局异常捕获并返回结果
 * @author MGARY
 */
@Setter
@Getter
public class ControllerException extends RuntimeException{

    private int code;
    private String msg;

    public ControllerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ControllerException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

}
