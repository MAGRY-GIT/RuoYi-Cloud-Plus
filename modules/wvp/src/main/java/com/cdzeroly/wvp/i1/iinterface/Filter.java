package com.cdzeroly.wvp.i1.iinterface;


import com.cdzeroly.wvp.i1.packet.AbstractPacket;

/**
 * 数据过滤器 用于过滤所需数据
 * @author MAGRY
 */
public interface Filter {
    boolean accept(AbstractPacket packet);
}
