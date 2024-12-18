package com.cdzeroly.wvp.service.domian.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class MediaServerLoadVo {

    private String id;
    private int push;
    private int proxy;
    private int gbReceive;
    private int gbSend;

}
