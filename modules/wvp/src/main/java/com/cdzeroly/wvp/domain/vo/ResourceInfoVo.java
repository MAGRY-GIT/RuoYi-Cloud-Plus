package com.cdzeroly.wvp.domain.vo;

import com.cdzeroly.wvp.domain.ResourceBaseInfo;
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
