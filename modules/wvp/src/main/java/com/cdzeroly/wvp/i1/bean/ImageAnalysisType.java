package com.cdzeroly.wvp.i1.bean;

import java.util.List;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/17 16:14
 */
public class ImageAnalysisType {

    //通道号
    public byte channelNumber;

    //支持识别类型数量
    public byte identifyQuantity;

    //识别类型
    public List<IdentifyType> identifyType;

}
