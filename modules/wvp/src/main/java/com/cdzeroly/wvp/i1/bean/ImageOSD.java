package com.cdzeroly.wvp.i1.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/17 15:53
 */
@Getter
@Setter
public class ImageOSD {

    // 通道号
    private byte channelNo;
    // 是否显示时间标识：0 不显示，1 显示
    private byte showTime;
    // 文本显示标识：0 不显示，1 显示
    private byte showText;
    // 文本内容，UTF-8编码格式，以'\0'结尾
    private String textContent;

}
