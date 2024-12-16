package com.cdzeroly.wvp.gb28181.domian.bean;

import com.cdzeroly.wvp.gb28181.enums.NetworkIdentificationTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
/**
 * @author MGARY
 */
@Setter
@Getter
public class NetworkIdentificationType implements Comparable<NetworkIdentificationType>{

    /**
     * 接入类型码
     */
    private String name;

    /**
     * 名称
     */
    private String code;

    public static NetworkIdentificationType getInstance(NetworkIdentificationTypeEnum typeEnum) {
        NetworkIdentificationType industryCodeType = new NetworkIdentificationType();
        industryCodeType.setName(typeEnum.getName());
        industryCodeType.setCode(typeEnum.getCode());
        return industryCodeType;
    }


    @Override
    public int compareTo(@NotNull NetworkIdentificationType networkIdentificationType) {
        return Integer.compare(Integer.parseInt(this.code), Integer.parseInt(networkIdentificationType.getCode()));
    }
}
