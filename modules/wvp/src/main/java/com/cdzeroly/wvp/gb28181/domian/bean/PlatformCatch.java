package com.cdzeroly.wvp.gb28181.domian.bean;

import com.cdzeroly.wvp.gb28181.domian.Platform;
import lombok.Data;

/**
 * @author MGARY
 */
@Data
public class PlatformCatch {

    private String id;

    /**
     * 心跳未回复次数
     */
    private int keepAliveReply;

    // 注册未回复次数
    private int registerAliveReply;

    private String callId;

    private Platform platform;

    private SipTransactionInfo sipTransactionInfo;

}
