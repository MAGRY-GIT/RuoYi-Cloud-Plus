package com.cdzeroly.wvp.gb28181.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 收录行业编码
 *
 * @author MGARY
 */
@Getter
public enum NetworkIdentificationTypeEnum {
    PUBLIC_SECURITY_VIDEO_TRANSMISSION_NETWORK("0", "公安视频传输网"),
    PUBLIC_SECURITY_VIDEO_TRANSMISSION_NETWORK2("1", "公安视频传输网"),
    INDUSTRY_SPECIFIC_NETWORK("2", "行业专网"),
    POLITICAL_AND_LEGAL_INFORMATION_NETWORK("3", "政法信息网"),
    PUBLIC_SECURITY_MOBILE_INFORMATION_NETWORK("4", "公安移动信息网"),
    PUBLIC_SECURITY_INFORMATION_NETWORK("5", "公安信息网"),
    ELECTRONIC_GOVERNMENT_EXTRANET("6", "电子政务外网"),
    PUBLIC_NETWORKS_SUCH_AS_THE_INTERNET("7", "互联网等公共网络"),
    Dedicated_Line("8", "专线"),
    RESERVE("9", "预留"),
    ;

    /**
     * 接入类型码
     */
    private final String name;

    /**
     * 名称
     */
    private final String code;


    NetworkIdentificationTypeEnum(String code, String name) {
        this.name = name;
        this.code = code;
    }

}
