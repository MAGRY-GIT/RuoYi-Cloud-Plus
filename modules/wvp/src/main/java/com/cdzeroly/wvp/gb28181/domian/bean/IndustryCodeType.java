package com.cdzeroly.wvp.gb28181.domian.bean;

import com.cdzeroly.wvp.gb28181.enums.IndustryCodeTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

/**
 * @author MGARY
 */
@Setter
@Getter
public class IndustryCodeType implements Comparable<IndustryCodeType>{

    /**
     * 接入类型码
     */
    private String name;

    /**
     * 名称
     */
    private String code;

    /**
     * 备注
     */
    private String notes;

    public static IndustryCodeType getInstance(IndustryCodeTypeEnum typeEnum) {
        IndustryCodeType industryCodeType = new IndustryCodeType();
        industryCodeType.setName(typeEnum.getName());
        industryCodeType.setCode(typeEnum.getCode());
        industryCodeType.setNotes(typeEnum.getNotes());
        return industryCodeType;
    }


    @Override
    public int compareTo(@NotNull IndustryCodeType industryCodeType) {
        return Integer.compare(Integer.parseInt(this.code), Integer.parseInt(industryCodeType.getCode()));
    }
}
