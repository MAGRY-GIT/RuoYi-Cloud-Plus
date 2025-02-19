package com.cdzeroly.wvp.i1.bean;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/12 18:01
 */

import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

/**
 * 拍照时间表设置 数据包
 *
 * @author MAGRY
 */

public class PhotoTimeTable {

    /**
     * 参数配置类型标识：
     * ①00H查询配置信息
     * ②01H 设置配置信息。
     */
    private byte requestSetFlag;
    // 通道号
    private byte channelNo;
    // 组数
    private byte group;
    // 时间表，每组包含时、分、预置位号
    private List<TimeTable> timeTables;


}
