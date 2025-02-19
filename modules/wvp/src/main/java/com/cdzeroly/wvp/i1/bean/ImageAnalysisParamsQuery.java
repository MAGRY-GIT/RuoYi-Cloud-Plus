package com.cdzeroly.wvp.i1.bean;

import lombok.Data;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/17 16:36
 */
@Data
public class ImageAnalysisParamsQuery {

    private byte channelNo;
    // 预置位号，从1开始，无预置位传FFH
    private byte presettingNo;

    public byte[] toBytes() {
        return  new byte[]{channelNo,presettingNo};
    }
}
