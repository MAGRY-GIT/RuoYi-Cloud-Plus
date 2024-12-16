package com.cdzeroly.wvp.vmanager.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class ResourceBaseInfo {
    private int total;
    private int online;

    public ResourceBaseInfo() {
    }

    public ResourceBaseInfo(int total, int online) {
        this.total = total;
        this.online = online;
    }

}
