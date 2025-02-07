package com.cdzeroly.common.core.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
/**
 * Spi工具类
 * @author : MGARY
 * @description :
 */
public class SpiUtils<S> {

    private final Class<S> spiClass;

    private SpiUtils(Class<S> spiClass) {
        this.spiClass = spiClass;
    }

    /**
     * 构建spi class
     *
     * @param spiClass spiClass
     * @return SpiUtils<S>
     */
    public static <S> SpiUtils<S> build(Class<S> spiClass) {
        return new SpiUtils<>(spiClass);
    }

    /**
     * 获取Spi服务
     * @return  List<S>
     */
    public List<S> getSpiServiceList() {
        List<S> list = new ArrayList<>();
        ServiceLoader<S> load = ServiceLoader.load(spiClass);
        for (S next : load) {
            list.add(next);
        }
        return list;
    }
}
