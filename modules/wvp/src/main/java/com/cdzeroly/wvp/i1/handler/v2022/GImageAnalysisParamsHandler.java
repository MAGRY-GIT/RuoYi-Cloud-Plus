package com.cdzeroly.wvp.i1.handler.v2022;

import com.cdzeroly.wvp.i1.handler.BaseAckHandler;
import com.cdzeroly.wvp.i1.packet.v2020.GImageAnalysisParamsPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 图像分析参数设置报 数据包处理程序
 */
@Slf4j
@ChannelHandler.Sharable
public class GImageAnalysisParamsHandler extends BaseAckHandler<GImageAnalysisParamsPacket> {

    @Override
    public void channelRead(ChannelHandlerContext ctx, GImageAnalysisParamsPacket msg) {
        // 处理图像分析参数设置数据包
        log.info("Received image analysis params packet: {}", msg);
        // 这里可以添加具体的处理逻辑
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("Exception caught in GImageAnalysisParamsHandler", cause);
        ctx.close();
    }
}
