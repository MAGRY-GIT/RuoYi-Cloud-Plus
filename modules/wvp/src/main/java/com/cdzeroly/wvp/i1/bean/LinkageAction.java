package com.cdzeroly.wvp.i1.bean;

import lombok.Getter;

/**
 * @author MAGREY
 */

@Getter
public enum LinkageAction {

    NO_LINKAGE(0, "无联动，即取消联动", "无参数, 参数1, 2固定为FFFFH"),
    UPLOAD_VIDEO(1, "联动上传录像", "参数1, 录像时长, 单位: 秒; 参数2, 固定为FFFFH"),
    UPLOAD_PHOTO(2, "联动上传拍照", "参数1， 拍照次数； 参数2， 拍照间隔单位： 秒"),
    IO_OUTPUT(3, "联动I/O输出", "参数1， 1/0编号， 如为2表示执行第2个1/0输出动作");

    private final int value;
    private final String description;
    private final String parameters;

    LinkageAction(int value, String description, String parameters) {
        this.value = value;
        this.description = description;
        this.parameters = parameters;
    }

    public static LinkageAction fromValue(int value) {
        for (LinkageAction action : LinkageAction.values()) {
            if (action.getValue() == value) {
                return action;
            }
        }
        throw new IllegalArgumentException("Unknown linkage action value: " + value);
    }
}
