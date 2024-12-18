package com.cdzeroly.common.core.utils;

import cn.hutool.core.util.ObjUtil;
import com.cdzeroly.common.core.exception.ServiceException;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 断言工具类
 * @author MAGRY
 * @version 1.0.0
 */
public class AssertUtils {

    /**
     * equal 断言
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @param e  异常
     */
    public static void equal(Object obj1, Object obj2, RuntimeException e) {
        isTrue(ObjUtil.equal(obj1, obj2), e);
    }

    /**
     * equal 断言
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @param message  返回提示
     */
    public static void equal(Object obj1, Object obj2, String message) {
        isTrue(ObjUtil.equal(obj1, obj2), message);
    }

    /**
     * equal 断言
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @param e  异常
     */
    public static void notEqual(Object obj1, Object obj2, RuntimeException e) {
        isFalse(ObjUtil.equal(obj1, obj2), e);
    }

    /**
     * equal 断言
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @param message  返回提示
     */
    public static void notEqual(Object obj1, Object obj2, String message) {
        isFalse(ObjUtil.equal(obj1, obj2), message);
    }

    /**
     * 字符有值 断言
     *
     * @param text  字符
     * @param e  异常
     */
    public static void hasLength(String text, RuntimeException e) {
        isTrue(text != null && !text.isEmpty(), e);
    }

    /**
     * boolean 断言
     *
     * @param expression 表达式
     * @param e  异常
     */
    public static void isTrue(boolean expression, RuntimeException e) {
        if (!expression) {
            throw e;
        }
    }

    /**
     * boolean 断言
     *
     * @param expression 表达式
     * @param message  返回提示
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw ServiceException.build(message);
        }
    }

    /**
     * boolean 断言
     *
     * @param expression 表达式
     * @param e  异常
     */
    public static void isFalse(boolean expression, RuntimeException e) {
        isTrue(!expression, e);
    }

    /**
     * boolean 断言
     *
     * @param expression 表达式
     * @param message  返回提示
     */
    public static void isFalse(boolean expression, String message) {
        isTrue(!expression, message);
    }

    /**
     * 字符有值且非空值 断言
     *
     * @param text 字符
     * @param e  异常
     */
    public static void hasText(String text, RuntimeException e) {
        isTrue(StringUtils.isNotBlank(text), e);
    }

    /**
     * 空Map 断言
     *
     * @param map 值
     * @param e  异常
     */
    public static void isEmpty(Map<Object,Object> map, RuntimeException e) {
        isTrue(map == null || map.isEmpty(), e);
    }

    /**
     * 空对象 断言
     *
     * @param obj 对象
     * @param e  异常
     */
    public static void isEmpty(Object obj, RuntimeException e) {
        isTrue(obj == null || obj == "", e);
    }


    /**
     * 空数组 断言
     *
     * @param array 数组对象
     * @param e  异常
     */
    public static void isEmpty(Object[] array, RuntimeException e) {
        isTrue(array == null || array.length == 0, e);
    }

    /**
     * 空集合 断言
     *
     * @param collection  集合
     * @param e  异常
     */
    public static void isEmpty(Collection<Object> collection, RuntimeException e) {
        isTrue(collection == null || collection.isEmpty(), e);
    }

    /**
     * 空集合 断言
     *
     * @param collection  集合
     * @param message  返回提示
     */
    public static void isEmpty(Collection<Object> collection, String message) {
        isTrue(collection == null || collection.isEmpty(), message);
    }

    /**
     * 空集合 断言
     *
     * @param collection  集合
     * @param message  返回提示
     */
    public static void isNotEmpty(Collection<Object> collection, String message) {
        isFalse(collection == null || collection.isEmpty(), message);
    }

    /**
     * 空集合 断言
     *
     * @param collection  集合
     * @param e  异常
     */
    public static void isNotEmpty(Collection<Object> collection,  RuntimeException e) {
        isFalse(collection == null || collection.isEmpty(), e);
    }

    /**
     * null 断言
     *
     * @param object 对象
     * @param e  异常
     */
    public static void isNull(Object object, RuntimeException e) {
        isTrue(object == null, e);
    }

    /**
     * null 断言
     *
     * @param object 对象
     * @param e  异常
     */
    public static void isNotNull(Object object, RuntimeException e) {
        isFalse(object == null, e);
    }


    /**
     * null 断言
     *
     * @param object 对象
     * @param message  异常
     */
    public static void isNotNull(Object object, String message) {
        isFalse(object == null, message);
    }

    /**
     * null 断言
     *
     * @param object 对象
     * @param message    返回提示
     */
    public static void isNull(Object object, String message) {
        isTrue(object == null, message);
    }

    /**
     * notBlack 断言
     *
     * @param str  字符串
     * @param message  返回提示
     */
    public static void notBlack(String str, String message) {
        isTrue(StringUtils.isNotBlank(str), message);
    }

    /**
     * notBlack 断言
     *
     * @param str  字符串
     * @param e  异常
     */
    public static void notBlack(String str, RuntimeException e) {
        isTrue(StringUtils.isNotBlank(str), e);
    }

    /**
     * black 断言
     *
     * @param str  字符串
     * @param message  返回提示
     */
    public static void black(String str, String message) {
        isFalse(StringUtils.isNotBlank(str), message);
    }

    /**
     * black 断言
     *
     * @param str  字符串
     * @param e  异常
     */
    public static void black(String str, RuntimeException e) {
        isFalse(StringUtils.isNotBlank(str), e);
    }

    /**
     * notBlack 断言
     *
     * @param str  字符串
     * @param exceptionSupplier   异常
     */
    public static <X extends RuntimeException> void notBlack(String str, Supplier<? extends X> exceptionSupplier) {
        notBlack(str, exceptionSupplier.get());
    }

    /**
     * 集合包含元素 断言
     *
     * @param o          元素
     * @param collection 集合
     * @param e  异常
     */
    public static void contains(Object o, Collection<Object> collection, RuntimeException e) {
        isTrue(o != null && collection != null && collection.contains(o), e);
    }

    /**
     * 集合包含元素 断言
     *
     * @param o          元素
     * @param collection 集合
     * @param message  返回提示
     */
    public static  void contains(Object o, Collection<Object> collection, String message) {
        isTrue(o != null && collection != null && collection.contains(o), message);
    }

    /**
     * 集合不包含元素 断言
     *
     * @param o          元素
     * @param collection 集合
     * @param e  异常
     */
    public static void notContains(Object o, Collection<Object> collection, RuntimeException e) {
        isTrue(o == null || collection == null || !collection.contains(o), e);
    }

    /**
     * 集合不包含元素 断言
     *
     * @param o          元素
     * @param collection 集合
     * @param message  返回提示
     */
    public static void notContains(Object o, Collection<Object> collection, String message) {
        isTrue(o == null || collection == null || !collection.contains(o), message);
    }

    /**
     * 非空Map 断言
     *
     * @param map 值
     * @param message  返回提示
     */
    public static void notEmpty(Map<Object,Object> map, String message) {
        isTrue(map != null && !map.isEmpty(), message);
    }

    /**
     * 非空Map 断言
     *
     * @param map 值
     * @param e  异常
     */
    public static void notEmpty(Map<Object,Object> map, RuntimeException e) {
        isTrue(map != null && !map.isEmpty(), e);
    }

    /**
     * 非空集合 断言
     *
     * @param collection  集合
     * @param e  异常
     */
    public static void notEmpty(Collection<Object> collection, RuntimeException e) {
        isTrue(collection != null && !collection.isEmpty(), e);
    }

    /**
     * 非空集合 断言
     *
     * @param collection  集合
     * @param message  返回提示
     */
    public static void notEmpty(Collection<Object> collection, String message) {
        isTrue(collection != null && !collection.isEmpty(), message);
    }

    /**
     * 空数组 断言
     *
     * @param array 数组对象
     * @param e  异常
     */
    public static void notEmpty(Object[] array, RuntimeException e) {
        isTrue(array != null && array.length > 0, e);
    }

    /**
     * 空数组 断言
     *
     * @param array 数组对象
     * @param message  返回提示
     */
    public static void notEmpty(Object[] array, String message) {
        isTrue(array != null && array.length > 0, message);
    }

    /**
     * 空字节 断言
     *
     * @param array 数组对象
     * @param message  返回提示
     */
    public static void notEmpty(byte[] array, String message) {
        isTrue(array != null && array.length > 0, message);
    }

    /**
     * 非null 断言
     *
     * @param object 对象
     * @param e  异常
     */
    public static void notNull(Object object, RuntimeException e) {
        isTrue(object != null, e);
    }

    /**
     * 非null 断言
     *
     * @param object 对象
     * @param message  返回提示
     */
    public static void notNull(Object object, String message) {
        isTrue(object != null, message);
    }


}
