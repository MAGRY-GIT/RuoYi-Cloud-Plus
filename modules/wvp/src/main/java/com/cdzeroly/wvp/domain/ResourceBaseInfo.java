package com.cdzeroly.wvp.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class ResourceBaseInfo {
    /**
     * 全部设备
     */
    private Integer total;
    /**
     * 在线的设备
     */
    private Integer online;

    public ResourceBaseInfo() {
    }

    public ResourceBaseInfo(int total, int online) {
        this.total = total;
        this.online = online;
    }

}
