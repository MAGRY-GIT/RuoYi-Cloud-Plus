package com.cdzeroly.wvp.i1.packet.v2020;

import com.cdzeroly.wvp.i1.bean.ImageAnalysisParamsDto;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;

/**
 * 图像分析参数设置报 数据包
 * <p>
 * 该类用于构建图像分析参数设置报，包含通道号、预置位号、智能分析启用标志、告警类型及其阈值和区域设置。
 * 该数据包用于设置监拍装置的图像分析参数。
 *
 * @author MAGRY
 */
@Getter
@Setter
public class GImageAnalysisParamsPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_IMAGE_ANALYSIS_PARAMS = (byte) 0xF5;



    ImageAnalysisParamsDto imageAnalysisParams;

    public GImageAnalysisParamsPacket() {
    }



    public GImageAnalysisParamsPacket( ImageAnalysisParamsDto imageAnalysisParams) {
    }


    public GImageAnalysisParamsPacket(ByteBuf data) {
        ImageAnalysisParamsDto imageAnalysisParams = new ImageAnalysisParamsDto();
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


        return packet(monitoringDeviceId, serialNumber, imageAnalysisParams.toBytes());
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
        return FRAME_TYPE_IMAGE_ANALYSIS_PARAMS;
    }
}
