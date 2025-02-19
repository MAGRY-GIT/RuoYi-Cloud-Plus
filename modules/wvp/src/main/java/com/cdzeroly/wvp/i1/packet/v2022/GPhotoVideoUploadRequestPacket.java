package com.cdzeroly.wvp.i1.packet.v2022;

import com.cdzeroly.wvp.i1.bean.constant.FrameTypeConstant;
import com.cdzeroly.wvp.i1.packet.AbstractPacket;
import com.cdzeroly.wvp.utils.BytesUtils;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 监拍装置请求上送照片/短视频报
 * <p>
 *     监拍装置上送图像数据前发送该指令，可视化系统收到后立即返回响应报文给装置，该命令最多循环发送5次，每次间隔3秒，收到可视化系统应答后立即开始传输图像数据。发送5次，监拍装置仍然没有收到可视化系统返回的指令，则停止发送。
 *     监拍装置请求上送照片/短视频的数据报文格式见表B.14，响应方式的数据报文格式见表B.15。
 * </p>
 * @author Administrator
 */
@Getter
@Setter
public class GPhotoVideoUploadRequestPacket extends AbstractPacket {
    public static final byte FRAME_TYPE_UPLOAD_REQUEST = (byte) 0xEF;

    // 通道号
    private byte channelNo;
    // 包数
    private short packet;
    // 内容ID，唯一标识所上传的文件
    private int contentId;

    // 是否允许
    private byte  uploadStatus = (byte) 0xFF;

    public GPhotoVideoUploadRequestPacket() {
    }




    public GPhotoVideoUploadRequestPacket(ByteBuf data) {
        this.channelNo = data.readByte();
        this.packet = data.readShort();
        this.contentId = data.readInt();

    }


    @Override
    public byte[] getFrameBytes() {

        ByteBuffer buffer = ByteBuffer.allocate(13)
            .order(ByteOrder.LITTLE_ENDIAN)
            .put(uploadStatus);

        return packet(monitoringDeviceId, serialNumber, buffer.array());
    }

    @Override
    public byte getFrameType() {
        return FrameTypeConstant.WORK_STATUS_RESPONSE_REPORT;
    }

    @Override
    public byte getMessageType() {
        return FRAME_TYPE_UPLOAD_REQUEST;
    }
}
