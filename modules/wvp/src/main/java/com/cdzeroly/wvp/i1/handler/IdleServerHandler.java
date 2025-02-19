package com.cdzeroly.wvp.i1.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 连接空闲Handler
 * @author MGARY
 */
@Component
@Slf4j
public class IdleServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent event) {
            String type = "";
			if (event.state() == IdleState.READER_IDLE) {
				type = "read idle";
			} else if (event.state() == IdleState.WRITER_IDLE) {
				type = "write idle";
			} else if (event.state() == IdleState.ALL_IDLE) {
				type = "all idle";
				log.info("读写全部空闲40秒,关闭连接");
				ctx.channel().close();
			}
            log.debug("{}超时类型：{}", ctx.channel().remoteAddress(), type);
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}
}
