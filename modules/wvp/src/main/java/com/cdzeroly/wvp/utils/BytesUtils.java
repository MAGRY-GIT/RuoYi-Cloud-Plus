package com.cdzeroly.wvp.utils;


import org.springframework.lang.Nullable;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BytesUtils {

    //byte 数组与 int 的相互转换
    public static int byte2Int(byte[] b) {
        int targets = (b[0] & 0xFF) | ((b[1] << 8) & 0xFF00);
        return targets;
    }


    //--- {byte[] <-> int} ---------------------------------------------------------------------------------------------

    /**
     * 将 byte 数组转换为 int, 取前 4 位.
     *
     * @param b byte 数据
     * @return int 值
     */
    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }


    /**
     * 将 int 数据转换为 byte 数组
     *
     * @param a int 数据
     * @return byte 数组, 4 位
     */
    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    //--- {byte[] <-> short} -------------------------------------------------------------------------------------------

    /**
     * 将 byte 数组转换为 short, 取前 2 位.
     *
     * @param b byte 数据
     * @return short 值
     */
    public static short byteArrayToShort(byte[] b) {
        return (short) (b[1] & 0xFF |
                (b[0] & 0xFF) << 8);
    }


    /**
     * 通过byte数组取到short
     *
     * @param b
     * @param index 第几位开始取
     * @return
     */
    public static short getShort(byte[] b, int index) {
        return (short) (((b[index + 1] << 8) | b[index + 0] & 0xff));
    }

    /**
     * 将 short 数据转换为 byte 数组
     *
     * @param a short 数据
     * @return short 数组, 2 位
     */
    public static byte[] shortToByteArray(short a) {
        return new byte[]{
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    //--- {byte[] <-> hex string} --------------------------------------------------------------------------------------

    /**
     * 实现字节数组转向十六进制的转换的方法, 默认使用 空格 作为分隔符
     */
    @Nullable
    public static String b2h(byte... src) {
        return b2h(" ", src);
    }

    /**
     * 实现字节数组转向十六进制的转换的方法
     *
     * @param split 分割符
     * @param src   字节数组
     * @return Hex String
     */
    @Nullable
    public static String b2h(String split, byte... src) {
        if (src == null || src.length <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0XFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
            if (split != null && i != src.length - 1) {
                stringBuilder.append(split);
            }
        }
        return stringBuilder.toString().toUpperCase();
    }

    /**
     * 将字节数组转换为十进制字符串
     * @param split 分隔符
     * @param src 字节数组
     * @return 十进制字符串
     */
    public static String b2d(String split, byte... src) {
        if (src == null || src.length <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0XFF;
            stringBuilder.append(v);
            if (split != null && i != src.length - 1) {
                stringBuilder.append(split);
            }
        }
        return stringBuilder.toString();
    }
    private static String hex_regex = "^[A-Fa-f0-9]+$";

    /**
     * 实现十六进制String转向字节数组转换的方法.
     * 在进行数据转换时进行了严格的数据校验,一旦发现数据格式不正常,则直接返回 null
     *
     * @param hex   Hex String
     * @param split 分割符
     * @return 解析后的 byte[] 或者 null
     */
    @Nullable
    public static byte[] h2b(String hex, String split) {
        if (hex == null || hex.length() <= 0) {
            return null;
        }
        // 没有分割符号
        if (null == split || split.length() == 0) {
            hex = hex.replace(" ", "");
            if (!hex.matches(hex_regex)) {
                return null;
            }
            // 长度不符合要求
            if (hex.length() % 2 != 0) {
                return null;
            }
            byte[] result = new byte[hex.length() / 2];
            int j = 0;
            for (int i = 0; i < result.length; i++) {
                char c0 = hex.charAt(j++);
                char c1 = hex.charAt(j++);
                result[i] = (byte) ((parse(c0) << 4) | parse(c1));
            }
            return result;
        }
        // 有分割符号
        else {
            String[] hexs = hex.split(split);
            byte[] result = new byte[hexs.length];
            for (int i = 0; i < hexs.length; i++) {
                int len = hexs[i].length();
                if (len == 0 || len > 2) {
                    return null;
                }
                String tem = hexs[i].replace(" ", "");
                if (!tem.matches(hex_regex)) {
                    return null;
                }
                try {
                    result[i] = (byte) Integer.parseInt(tem, 16);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return result;
        }
    }

    /**
     * 数值字符串转字节
     * <p>
     *      字符限定为10进制数值 范围为0~9 每个字符转一个字节
     * </p>
     * @param str
     * @return
     */
    public static byte[] s2b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        byte[] result = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException("字符串中的字符必须是0到9之间的数字");
            }
            result[i] = (byte) (c - '0');
        }
        return result;
    }
    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        // 这里的1代表正数
        return new BigInteger(1, bytes).toString(radix);
    }

    /**
     * 拆分字符串
     *
     * @param bytes byte[]
     * @param radix 基数可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return
     */
    public static List<Integer> bytesToBinary(byte[] bytes, int radix) {
        // 这里的1代表正数
        return Arrays.stream(binary(bytes, radix).split("")).map(Integer::parseInt).collect(Collectors.toList());
    }


    /**
     * 拆分字符串
     *
     * @param bytes byte[]
     * @return
     */
    public static List<Integer> bytesToBinary(byte[] bytes) {
        // 这里的1代表正数
        return Arrays.stream(conver2HexStr(bytes).split("")).map(Integer::parseInt).collect(Collectors.toList());
    }

    /**
     * byte数组转换为二进制字符串,每个字节以","隔开
     **/
    public static String conver2HexStr(byte[] b) {

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            StringBuffer str = new StringBuffer();
            String string = Long.toString(b[i] & 0xff, 2);
            str.append(string);
            for (int j = 0; j < 8 - string.split("").length; j++) {
                str.insert(0, "0");
            }
            result.append(str.reverse());
        }
        return result.toString();
    }

    private static int parse(char c) {
        if (c >= 'a') {
            return (c - 'a' + 10) & 0x0f;
        }
        if (c >= 'A') {
            return (c - 'A' + 10) & 0x0f;
        }
        return (c - '0') & 0x0f;
    }
}
