package com.cdzeroly.wvp.domain.bean;

/**
 * @author MGARY
 */
public interface InviteTimeOutCallback {

    /**
     *
     * @param code  0 sip超时, 1 收流超时
     * @param msg 消息
     */
    void run(int code, String msg);
}
