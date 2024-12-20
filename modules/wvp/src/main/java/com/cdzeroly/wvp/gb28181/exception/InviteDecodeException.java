package com.cdzeroly.wvp.gb28181.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author MGARY
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InviteDecodeException extends RuntimeException{
    private Integer code;
    private String msg;

    public InviteDecodeException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
