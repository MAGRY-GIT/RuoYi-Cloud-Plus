package com.cdzeroly.wvp.utils;

/**
 * 作用：确认码生成器，客户端使用，生成不重复的确认码
 * 作者：GcsSloop
 */
public class CodeUtils {
    private static int code = 1;

    public static synchronized int getCode() {
        if (code == Integer.MAX_VALUE) {
            code = 0;
        }
        return code++;
    }
}
