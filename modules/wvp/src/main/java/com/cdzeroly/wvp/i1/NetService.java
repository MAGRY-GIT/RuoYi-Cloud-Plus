package com.cdzeroly.wvp.i1;

import com.cdzeroly.wvp.i1.iinterface.Filter;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import io.reactivex.rxjava3.core.Flowable;

import java.util.Optional;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author MAGRY
 */
public interface NetService {
    /**
     * 发送数据包, 不关心结果
     *
     * @param packet 数据包
     */
    void sendPacket(AbstractPacket packet);

    /**
     * 发送数据并监听返回数据
     *
     * @param packet 数据包
     * @param filter 数据过滤器
     * @param <T>    Packet
     * @return Packet
     */
    <T extends AbstractPacket> Flowable<T> sendPacket(AbstractPacket packet, Filter filter);

    public LinkedBlockingDeque<AbstractPacket> getRecvQueue();

    public LinkedBlockingDeque<AbstractPacket> getSendQueue();


    public  <T extends AbstractPacket> Optional<T> getFilterPacket(Filter filter, long timeout);


    public  <T extends AbstractPacket> Optional<T> getFilterPacket(Filter filter);
}
