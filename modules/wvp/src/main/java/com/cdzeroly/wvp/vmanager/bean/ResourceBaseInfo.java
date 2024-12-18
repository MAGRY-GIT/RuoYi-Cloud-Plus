package com.cdzeroly.wvp.vmanager.bean;

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
    private int total;
    /**
     * 在线的设备
     */
    private int online;

    public ResourceBaseInfo() {
    }

    public ResourceBaseInfo(int total, int online) {
        this.total = total;
        this.online = online;
    }

}
