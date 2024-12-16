package com.cdzeroly.wvp.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

/**
 * @author MGARY
 */
@Setter
@Getter
public class CivilCodePo {

    private String code;

    private String name;

    private String parentCode;

    public static CivilCodePo getInstance(String[] infoArray) {
        CivilCodePo civilCodePo = new CivilCodePo();
        civilCodePo.setCode(infoArray[0]);
        civilCodePo.setName(infoArray[1]);
        if (!ObjectUtils.isEmpty(infoArray[2])) {
            civilCodePo.setParentCode(infoArray[2]);
        }
        return civilCodePo;
    }

}
