package com.cdzeroly.wvp.vmanager.bean;

import com.cdzeroly.wvp.common.VersionPo;
import com.cdzeroly.wvp.conf.SipConfig;
import com.cdzeroly.wvp.conf.UserSetting;
import lombok.Data;

/**
 * @author lombok
 */
@Data
public class SystemConfigInfo {

    private int serverPort;
    private SipConfig sip;
    private UserSetting addOn;
    private VersionPo version;

}

