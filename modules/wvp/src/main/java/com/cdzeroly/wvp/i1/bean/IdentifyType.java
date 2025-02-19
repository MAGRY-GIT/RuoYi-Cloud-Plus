package com.cdzeroly.wvp.i1.bean;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 告警类型编码
 * @author MAGRY
 */
public enum IdentifyType {

     // 施工机械
    CONSTRUCTION_MACHINERY( 1,"施工机械"),

     // 火险
    FIRE_RISK( 2,"火险"),

     // 烟雾
    SMOKE( 3,"烟雾"),

     // 导地线异物
    FOREIGN_OBJECT( 4,"导地线异物"),

     // 其他
    OTHER( 5,"其他");


    private final int code;
    @Getter
    private final String name;

    IdentifyType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Optional<IdentifyType> getById(byte b) {
       return Arrays.stream(IdentifyType.values()).filter(e -> e.getCode() == b).findFirst();
    }

    public byte getCode() {
        return (byte) code;
    }

}
