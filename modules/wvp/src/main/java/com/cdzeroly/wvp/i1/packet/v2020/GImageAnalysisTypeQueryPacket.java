package com.cdzeroly.wvp.i1.packet.v2020;

import com.cdzeroly.common.json.utils.JsonUtils;
import com.cdzeroly.wvp.i1.bean.IdentifyType;
import com.cdzeroly.wvp.i1.bean.ImageAnalysisType;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.compress.utils.Lists;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

/**
 * 图像分析类型查询报 数据包
 * <p>
 * 该类用于构建图像分析类型查询报，包含通道号和数据源。
 * 该数据包用于查询监拍装置支持的图像分析类型。
 *
 * @author MAGRY
 */
@Getter
@Setter
public class GImageAnalysisTypeQueryPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_IMAGE_ANALYSIS_TYPE_QUERY = (byte) 0xF4;

    // 通道号
    private byte channelNo;
    // 数据源，每字节表示一个通道号，从1开始计数
    private byte[] dataSources;

    ImageAnalysisType imageAnalysisType;

    public GImageAnalysisTypeQueryPacket() {
    }

    public GImageAnalysisTypeQueryPacket(ByteBuf data) {
        imageAnalysisType = new ImageAnalysisType();
        imageAnalysisType.channelNumber = data.readByte();
        imageAnalysisType.identifyQuantity = data.readByte();
        ArrayList<IdentifyType> objects = Lists.newArrayList();
        for (int i = 0; i < data.readableBytes(); i++) {
            IdentifyType.getById(data.readByte()).ifPresent(objects::add);
        }
    }


    public  String imageAnalysisTypeToJson(){
        return JsonUtils.toJsonString(imageAnalysisType);
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
        // 计算总长度
        int totalLength = 12 + dataSources.length;
        ByteBuffer buffer = ByteBuffer.allocate(totalLength)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(channelNo)
            .put(dataSources);

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
        return FRAME_TYPE_IMAGE_ANALYSIS_TYPE_QUERY;
    }
}
