package com.cdzeroly.wvp.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author MGARY
 */
@Setter
@Getter
public class SystemAllInfo {

    private List<Object> cpu;
    private List<Object> mem;
    private List<Object> net;

    private long netTotal;

    private Object disk;

}
