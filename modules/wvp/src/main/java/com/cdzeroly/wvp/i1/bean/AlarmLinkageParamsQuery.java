package com.cdzeroly.wvp.i1.bean;

import lombok.Data;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/18 19:02
 */
@Data
public class AlarmLinkageParamsQuery {

    // 通道号
    private byte channelNo;
    // 预置位号，无云台固定为255 (FFH)
    private byte presettingNo;

    public byte[] toBytes() {
        return new byte[]{channelNo,channelNo
        };
    }
}
