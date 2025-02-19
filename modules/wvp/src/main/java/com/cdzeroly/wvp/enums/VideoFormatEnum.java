package com.cdzeroly.wvp.enums;

import lombok.Getter;

/**
 * 短视频格式枚举
 *
 * @author MAGRY
 */
@Getter
public enum VideoFormatEnum {
    /**
     * QVGA (320x240)
     */
    QVGA(1, "320x240"),
    /**
     * VGA (640x480)
     */
    VGA(2, "640x480"),
    /**
     * 480p (720x480)
     */
    _480P(3, "720x480"),
    /**
     * 720p (1280x720)
     */
    _720P(4, "1280x720"),
    /**
     * 1080p (1920x1080)
     */
    _1080P(5, "1920x1080"),
    /**
     * 其他格式
     */
    OTHER(6, "其他");

    private final int code;
    private final String description;

    VideoFormatEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据代码获取对应的枚举
     *
     * @param code 视频格式代码
     * @return 对应的枚举
     */
    public static VideoFormatEnum fromCode(int code) {
        for (VideoFormatEnum format : VideoFormatEnum.values()) {
            if (format.getCode() == code) {
                return format;
            }
        }
        throw new IllegalArgumentException("Invalid video format code: " + code);
    }
}
