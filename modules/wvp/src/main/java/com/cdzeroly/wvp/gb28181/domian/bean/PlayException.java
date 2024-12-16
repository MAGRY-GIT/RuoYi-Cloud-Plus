package com.cdzeroly.wvp.gb28181.domian.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author MGARY
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PlayException extends RuntimeException{
    private int code;
    private String msg;

    public PlayException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
