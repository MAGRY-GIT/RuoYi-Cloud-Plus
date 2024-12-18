package com.cdzeroly.wvp.vmanager.bean.vo;

import com.cdzeroly.wvp.vmanager.bean.ResourceBaseInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class ResourceInfoVo {

    private ResourceBaseInfo device;
    private ResourceBaseInfo channel;
    private ResourceBaseInfo push;
    private ResourceBaseInfo proxy;

}
