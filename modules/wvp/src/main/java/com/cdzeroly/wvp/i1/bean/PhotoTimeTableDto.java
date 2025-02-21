package com.cdzeroly.wvp.i1.bean;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/12 18:01
 */

import lombok.Data;

import java.util.List;

/**
 * 拍照时间表设置 数据包
 *
 * @author MAGRY
 */
@Data
public class PhotoTimeTableDto {

    // 通道号
    private byte channelNo;
    // 时间表，每组包含时、分、预置位号
    private List<TimeTable> timeTables;


}
