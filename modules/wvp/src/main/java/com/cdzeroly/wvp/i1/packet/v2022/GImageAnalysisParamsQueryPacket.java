package com.cdzeroly.wvp.i1.packet.v2022;

import com.cdzeroly.wvp.i1.bean.IdentifyType;
import com.cdzeroly.wvp.i1.bean.ImageAnalysisParams;
import com.cdzeroly.wvp.i1.bean.ImageAnalysisParamsQuery;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * 图像分析参数查询报 数据包
 * <p>
 * 该类用于构建图像分析参数查询报，包含通道号和预置位号。
 * 该数据包用于查询监拍装置的图像分析参数。
 *
 * @author MAGRY
 */
@Getter
@Setter
public class GImageAnalysisParamsQueryPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_IMAGE_ANALYSIS_PARAMS_QUERY = (byte) 0xF6;


    // 数据域参数组数量
    private List<ImageAnalysisParamsQuery> imageAnalysisParamsQueryList;

    // 数据域参数组数量
    private ImageAnalysisParams imageAnalysisParams;


    public GImageAnalysisParamsQueryPacket() {
    }


    public GImageAnalysisParamsQueryPacket(List<ImageAnalysisParamsQuery> imageAnalysisParamsQueryList) {
        this.imageAnalysisParamsQueryList = imageAnalysisParamsQueryList;
    }

    public GImageAnalysisParamsQueryPacket(ByteBuf data) {
        ImageAnalysisParams imageAnalysisParams = new ImageAnalysisParams();
        imageAnalysisParams.setChannelNo(data.readByte());
        imageAnalysisParams.setPresettingNo(data.readByte());
        imageAnalysisParams.setAnalysisEnableFlag(data.readByte());
        // 告警类型和阈值
        byte alarmTypeCount = data.readByte();
        ByteBuf alarmTypeBuffer = Unpooled.buffer(alarmTypeCount * 2);
        data.readBytes(alarmTypeBuffer);
        imageAnalysisParams.setAlarmTypeInfo(alarmTypeBuffer,alarmTypeCount);
        // 告警区域
        byte alarmRegionCount = data.readByte();
        ByteBuf alarmRegionBuffer = Unpooled.buffer(alarmTypeCount * 2);
        data.readBytes(alarmRegionBuffer);
        imageAnalysisParams.setAlarmRegion(alarmRegionBuffer,alarmRegionCount);
        this.imageAnalysisParams = imageAnalysisParams;
    }


    /**
     * 构建数据包字节数组。
     * <p>
     * 该方法将当前对象的字段值序列化为字节数组，用于网络传输。
     *
     * @return 构建好的数据包字节数组
     */
    @Override
    public byte[] getFrameBytes() {


        ByteBuffer buffer = ByteBuffer.allocate(12 + imageAnalysisParamsQueryList.size() * 2)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put((byte) imageAnalysisParamsQueryList.size());
        imageAnalysisParamsQueryList.forEach(imageAnalysisParamsQuery -> {
            buffer.put(imageAnalysisParamsQuery.toBytes());
        });
        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

    /**
     * 获取帧类型。
     * <p>
     * 该方法返回当前数据包的帧类型。
     *
     * @return 帧类型
     */
    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    /**
     * 获取报文类型。
     * <p>
     * 该方法返回当前数据包的报文类型。
     *
     * @return 报文类型
     */
    @Override
    public byte getMessageType() {
        return FRAME_TYPE_IMAGE_ANALYSIS_PARAMS_QUERY;
    }
}
