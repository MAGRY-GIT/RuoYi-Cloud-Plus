package com.cdzeroly.common.core.utils;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import org.springframework.util.CollectionUtils;


/**
 * 集合操作工具类
 *
 * @author guoqing.gaogq
 */
public class CollectionUtil {

    /**
     * 当满足条件时才把item加入collection
     */
    public static <T> void addWithCondition(Collection<T> collection, T item, Predicate<T> predicate) {
        Objects.requireNonNull(predicate, "predicate is null");
        if (collection != null && predicate.test(item)) {
            collection.add(item);
        }
    }

    /**
     * 集合是否包含元素
     *
     * @param collection 集合
     * @param obj 对象
     * @return  boolean
     */
    public static boolean contain(Collection<Object> collection, Object obj) {
        return collection != null && obj != null && collection.contains(obj);
    }

    /**
     * 遍历Map中非null元素
     *
     * @param map  值
     * @param consumer  非空
     */
    public static <T, U> void forEachNotNull(Map<T, U> map, BiConsumer<T, U> consumer) {
        if (map != null && consumer != null) {
            map.forEach(consumer);
        }
    }

    /**
     * 遍历集合中非null元素
     *
     * @param collection 集合
     * @param consumer   非null元素
     */
    public static <T> void forEachNotNull(Collection<T> collection, Consumer<T> consumer) {
        if (collection != null && consumer != null) {
            for (T t : collection) {
                if (t != null) {
                    consumer.accept(t);
                }
            }
        }
    }

    /**
     * 遍历列表，带索引
     *
     * @param elements   元素
     * @param action 动作
     */
    public static <T> void forEachWithIndex(Iterable<? extends T> elements, BiConsumer<Integer, ? super T> action) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);
        int index = 0;
        for (T element : elements) {
            action.accept(index++, element);
        }
    }

    /**
     * 两个集合并集
     *
     * @param collection1 集合1
     * @param collection2 集合2
     * @return  Collection<T>
     */
    public static <T> Collection<T> joinAll(Collection<T> collection1, Collection<T> collection2) {
        if (org.springframework.util.CollectionUtils.isEmpty(collection1)
            && org.springframework.util.CollectionUtils.isEmpty(collection2)) {
            return new ArrayList<>();
        }
        if (org.springframework.util.CollectionUtils.isEmpty(collection1)) {
            return collection2;
        }
        if (org.springframework.util.CollectionUtils.isEmpty(collection2)) {
            return collection1;
        }
        List<T> returnObject = new ArrayList<>(collection1);
        returnObject.removeAll(collection2);
        returnObject.addAll(collection2);
        return returnObject;
    }

    /**
     * 将集合组成字符串
     *
     * @param strList 集合
     * @param separation 分隔符
     * @return String
     */
    public static String parseList2String(List<String> strList, String separation) {
        if (!org.springframework.util.CollectionUtils.isEmpty(strList) && StringUtils.isNotBlank(separation)) {
            return String.join(separation, strList);
        }
        return null;
    }

    /**
     * 将集合组成字符串并分隔符包围 例如 [1,2,3] -> ,1,2,3,
     *
     * @param strList  集合
     * @param separation 分隔符包围
     * @return String
     */
    public static String parseList2StringAndSurround(List<String> strList, String separation) {
        if (!org.springframework.util.CollectionUtils.isEmpty(strList) && StringUtils.isNotBlank(separation)) {
            return separation + String.join(separation, strList) + separation;
        }
        return separation;
    }

    /**
     * 将字符串切割成集合
     *
     * @param data 字符串
     * @param separation 分隔符
     * @return List<String>
     */
    public static List<String> parseString2List(String data, String separation) {
        if (StringUtils.isNotBlank(data) && StringUtils.isNotBlank(separation)) {
            String[] splitArr = data.split(separation.trim());
            return Arrays.stream(splitArr).filter(s -> !StringUtils.equals(separation, s))
                .filter(s -> !StringUtils.equals(CharSequenceUtil.EMPTY, s)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 两个集合交集
     *
     * @param collection1 集合1
     * @param collection2 集合2
     * @return Collection<T>
     */
    public static <T> Collection<T> retainAll(Collection<T> collection1, Collection<T> collection2) {
        if (CollectionUtils.isEmpty(collection1) || CollectionUtils.isEmpty(collection2)) {
            return new ArrayList<>();
        }
        List<T> returnObject = new ArrayList<>(collection1);
        returnObject.retainAll(collection2);
        return returnObject;
    }

    /**
     * 两个集合交集
     *
     * @param collection1 集合1
     * @param collection2 集合2
     * @return List<T>
     */
    public static <T> List<T> retainAllList(Collection<T> collection1, Collection<T> collection2) {
        if (CollectionUtils.isEmpty(collection1)
            || CollectionUtils.isEmpty(collection2)) {
            return new ArrayList<>();
        }
        List<T> returnObject = new ArrayList<>(collection1);
        returnObject.retainAll(collection2);
        return returnObject;
    }

    /**
     * 求差集,存在 collection1 且不存在 collection2 (collection1 比 collection2 多的元素)
     *
     * @param collection1 集合1
     * @param collection2 集合2
     * @return  Collection<T>
     */
    public static <T> Collection<T> retain(Collection<T> collection1, Collection<T> collection2) {
        if (org.springframework.util.CollectionUtils.isEmpty(collection1)) {
            return new ArrayList<>();
        } else if (org.springframework.util.CollectionUtils.isEmpty(collection2)) {
            return collection1;
        }
        List<T> returnObject = new ArrayList<>(collection1);
        returnObject.removeAll(collection2);
        return returnObject;
    }

    /**
     * 获取重复的数据
     *
     * @param list 集合
     * @return  Collection<T>
     */
    public static <T> Collection<T> getRepeatDataList(Collection<T> list) {
        Collection<T> repeat = new ArrayList<>();
        // 获取数据与出现的次数
        Map<T, Long> map = list.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        // 利用lambda遍历map
        map.keySet().forEach(key -> {
            if (map.get(key) > 1) {
                repeat.add(key);
            }
        });
        return repeat;
    }

    /**
     * 遍历集合
     *
     * @param collection  集合
     * @param consumer 动作
     */
    public static <T> void forEach(Collection<T> collection, Consumer<T> consumer) {
        for (T t : collection) {
            consumer.accept(t);
        }

    }

    /**
     * 合并集合
     *
     * @param collections 集合
     * @return  List<T>
     */
    @SafeVarargs
    public static <T> List<T> mergeList(Collection<T>... collections) {
        return Stream.of(collections).flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * 合并集合
     *
     * @param collections 集合
     * @param <T> Set<T>
     */
    @SafeVarargs
    public static <T> Set<T> mergeSet(Collection<T>... collections) {
        return Stream.of(collections).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    /**
     * 安全获取列表流
     *
     * @param list 集合
     * @return Stream<T>
     */
    public static <T> Stream<T> safeStream(Collection<T> list) {
        return Optional.ofNullable(list).orElse(CollUtil.newArrayList()).stream();
    }

    /**
     * 安全获取列表流
     *
     * @param list 集合
     * @return Stream<T>
     */
    public static <T> Stream<T> safeParallelStream(Collection<T> list) {
        return Optional.ofNullable(list).orElse(CollUtil.newArrayList()).parallelStream();
    }

    /**
     * 安全获取集合流
     *
     * @param set 集合
     * @return Stream<T>
     */
    public static <T> Stream<T> safeStream(Set<T> set) {
        return Optional.ofNullable(set).orElse(CollUtil.newHashSet()).stream();
    }

    /**
     * 获取集合size
     *
     * @param collection 集合
     * @return Integer
     */
    public static Integer getCollectionSize(Collection<Object> collection) {
        return Optional.ofNullable(collection).map(Collection::size).orElse(0);
    }

}
