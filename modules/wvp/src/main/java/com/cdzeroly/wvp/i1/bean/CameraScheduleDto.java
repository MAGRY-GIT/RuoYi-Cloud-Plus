package com.cdzeroly.wvp.i1.bean;


import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * 摄像机定时工作时间表设置 数据包
 *
 * @author MAGRY
 */
@Setter
@Getter

public class CameraScheduleDto {
    // 工作起始时间数组
    private List<Integer> startTimes;
    // 工作结束时间数组
    private List<Integer> endTimes;

    public CameraScheduleDto() {

    }

}
