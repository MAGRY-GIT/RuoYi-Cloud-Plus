package com.cdzeroly.wvp.enums;

import lombok.Getter;

/**
 * 图像分辨率
 * @author MAGRY
 */
@Getter
public enum ImageResolutionEnum {

    RESOLUTION_1(320, 240, (byte) 1),
    RESOLUTION_2(640, 480, (byte) 2),
    RESOLUTION_3(704, 576, (byte) 3),
    RESOLUTION_4(800, 600, (byte) 4),
    RESOLUTION_5(1024, 768, (byte) 5),
    RESOLUTION_6(1280, 1024, (byte) 6),
    RESOLUTION_7(1280, 720, (byte) 7),
    RESOLUTION_8(1920, 1080, (byte) 8),
    RESOLUTION_9(2560, 1440, (byte) 9),
    RESOLUTION_10(3840, 2160, (byte) 10),
    RESOLUTION_11(2560, 1920, (byte) 11),
    RESOLUTION_12(3264, 2448, (byte) 11),
    RESOLUTION_13(4224, 3136, (byte) 11),
    RESOLUTION_OTHER(Integer.MAX_VALUE, Integer.MAX_VALUE, (byte) 255);

    private final int width;
    private final int height;
    private final byte code;

    ImageResolutionEnum(int width, int height, byte code) {
        this.width = width;
        this.height = height;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Resolution{" +
            "width=" + width +
            ", height=" + height +
            ", code=" + code +
            '}';
    }

    // 根据编号获取对应的分辨率
    public static ImageResolutionEnum getResolutionByCode(int code) {
        for (ImageResolutionEnum resolution : ImageResolutionEnum.values()) {
            if (resolution.getCode() == code) {
                return resolution;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
