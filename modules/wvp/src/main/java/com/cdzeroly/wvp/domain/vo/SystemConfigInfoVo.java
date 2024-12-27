package com.cdzeroly.wvp.domain.vo;

import com.cdzeroly.wvp.conf.SipConfig;
import com.cdzeroly.wvp.conf.UserSetting;
import lombok.Data;

/**
 * @author MGARY
 */
@Data
public class SystemConfigInfoVo {
    /**
     * 服务器端口
     */
    private int serverPort;
    /**
     * sip 信息
     */
    private SipConfig sip;
    /**
     * 用户信息
     */
    private UserSetting addOn;

}

