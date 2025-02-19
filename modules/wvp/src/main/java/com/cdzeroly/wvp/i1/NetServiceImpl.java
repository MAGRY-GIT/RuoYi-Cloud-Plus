package com.cdzeroly.wvp.i1;

import com.cdzeroly.wvp.i1.iinterface.Filter;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * @author MAGRY
 */
@Component
public class NetServiceImpl implements NetService {
    // 信息读取等待超时的时间, 超过该之间自动返回
    private long mReadTimeout = 120000;

    /**
     * 发送数据队列
     */
    public LinkedBlockingDeque<AbstractPacket> sendQueue;
    /**
     * 接收数据队列
     */
    public LinkedBlockingDeque<AbstractPacket> recvQueue;

    public NetServiceImpl() {
        sendQueue = new LinkedBlockingDeque<>();
        recvQueue = new LinkedBlockingDeque<>();
    }

    @Override
    public void sendPacket(AbstractPacket packet) {

        try {
            sendQueue.add(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public <T extends AbstractPacket> Flowable<T> sendPacket(AbstractPacket packet, Filter filter) {
        return Flowable.create((FlowableOnSubscribe<T>) emitter -> {
            sendQueue.add(packet);
            Optional<T> temp = getFilterPacket(filter);
            if (temp.isPresent()) {
                emitter.onNext(temp.get());
                emitter.onComplete();
            } else {
                emitter.onError(new TimeoutException("timeout!"));
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io());
    }


    //--- 数据获取 ------------------------------------------------------------------------------------------------------

    @Override
    public <T extends AbstractPacket> Optional<T> getFilterPacket(Filter filter) {
        return getFilterPacket(filter, mReadTimeout);
    }

    @Override
    public <T extends AbstractPacket> Optional<T> getFilterPacket(Filter filter, long timeout) {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        AbstractPacket ret = null;
        // 在未超时的情况下不断尝试获取新数据
        do {

            // 计算新的 timeout
            long newtimeout = timeout - (System.currentTimeMillis() - startTime);
            if (newtimeout < 100) {
                newtimeout = 100;
            }

            try {
                AbstractPacket packet = recvQueue.pollLast(newtimeout, TimeUnit.MILLISECONDS);
                if (filter.accept(packet)) {
                    // 获取到数据，直接跳出循环
                    ret = packet;
                    break;
                } else {
                    // 将不需要的数据放回队列
                    if (null != packet) {
                        recvQueue.putFirst(packet);
                    }
                }
                // 默认休眠， 防止不断重复的取垃圾数据
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        } while (System.currentTimeMillis() - startTime < timeout);
        if (null == ret) {
            return Optional.empty();
        }
        return Optional.of((T) ret);
    }

    @Override
    public LinkedBlockingDeque<AbstractPacket> getRecvQueue() {
        return recvQueue;
    }

    @Override
    public LinkedBlockingDeque<AbstractPacket> getSendQueue() {
        return sendQueue;
    }
}
