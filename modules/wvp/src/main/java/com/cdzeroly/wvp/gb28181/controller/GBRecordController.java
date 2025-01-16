package com.cdzeroly.wvp.gb28181.controller;

import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.web.core.BaseController;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.conf.exception.SsrcTransactionNotFoundException;

import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.DeviceChannel;
import com.cdzeroly.wvp.gb28181.bean.RecordInfo;
import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import com.cdzeroly.wvp.gb28181.service.IDeviceService;
import com.cdzeroly.wvp.gb28181.service.IPlayService;
import com.cdzeroly.wvp.gb28181.transmit.callback.DeferredResultHolder;
import com.cdzeroly.wvp.gb28181.transmit.callback.RequestMessage;
import com.cdzeroly.wvp.gb28181.transmit.cmd.impl.SIPCommander;
import com.cdzeroly.wvp.domain.bean.InviteErrorCode;
import com.cdzeroly.wvp.domain.ErrorCode;
import com.cdzeroly.wvp.domain.vo.StreamContentVo;
import com.cdzeroly.wvp.domain.WVPResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import java.text.ParseException;
import java.util.UUID;

/**
 * @author MAGRY
 */
@Tag(name  = "国标录像")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/gb_record")
public class GBRecordController extends BaseController {

	private final SIPCommander cmder;

	private final DeferredResultHolder resultHolder;

	private final IPlayService playService;

	private final IDeviceChannelService channelService;

	private final IDeviceService deviceService;

	private final UserSetting userSetting;

	@Operation(summary = "录像查询")
	@Parameter(name = "deviceId", description = "设备国标编号", required = true)
	@Parameter(name = "channelId", description = "通道国标编号", required = true)
	@Parameter(name = "startTime", description = "开始时间", required = true)
	@Parameter(name = "endTime", description = "结束时间", required = true)
	@GetMapping("/query/{deviceId}/{channelId}")
	public DeferredResult<R<RecordInfo>> recordInfo(@PathVariable String deviceId, @PathVariable String channelId, String startTime, String endTime){

		if (log.isDebugEnabled()) {
			log.debug("录像信息查询 API调用，deviceId：{} ，startTime：{}， endTime：{}", deviceId, startTime, endTime);
		}
		DeferredResult<R<RecordInfo>> result = new DeferredResult<>();


		Device device = deviceService.getDeviceByDeviceId(deviceId);
		// 指定超时时间 1分钟30秒
		String uuid = UUID.randomUUID().toString();
		int sn  =  (int)((Math.random()*9+1)*100000);
		String key = DeferredResultHolder.CALLBACK_CMD_RECORDINFO + deviceId + sn;
		RequestMessage msg = new RequestMessage();
		msg.setId(uuid);
		msg.setKey(key);
		try {
			cmder.recordInfoQuery(device, channelId, startTime, endTime, sn, null, null, null, (eventResult -> {

				msg.setData(R.fail("查询录像失败, status: " +  eventResult.statusCode + ", message: " + eventResult.msg));
				resultHolder.invokeResult(msg);
			}));
		} catch (InvalidArgumentException | SipException | ParseException e) {
			log.error("[命令发送失败] 查询录像: {}", e.getMessage());
			throw new ServiceException( "命令发送失败: " +  e.getMessage());
		}
		// 录像查询以channelId作为deviceId查询
		resultHolder.put(key, uuid, result);
		result.onTimeout(()->{
            R<Object> wvpResult = R.fail("timeout");
			msg.setData(wvpResult);
			resultHolder.invokeResult(msg);
		});
        return result;
	}


	@Operation(summary = "开始历史媒体下载")
	@Parameter(name = "deviceId", description = "设备国标编号", required = true)
	@Parameter(name = "channelId", description = "通道国标编号", required = true)
	@Parameter(name = "startTime", description = "开始时间", required = true)
	@Parameter(name = "endTime", description = "结束时间", required = true)
	@Parameter(name = "downloadSpeed", description = "下载倍速", required = true)
	@GetMapping("/download/start/{deviceId}/{channelId}")
	public DeferredResult<R<StreamContentVo>> download(@PathVariable String deviceId, @PathVariable String channelId,
                                                               String startTime, String endTime, String downloadSpeed) {

		if (log.isDebugEnabled()) {
			log.debug(String.format("历史媒体下载 API调用，deviceId：%s，channelId：%s，downloadSpeed：%s", deviceId, channelId, downloadSpeed));
		}

		String uuid = UUID.randomUUID().toString();
		String key = DeferredResultHolder.CALLBACK_CMD_DOWNLOAD + deviceId + channelId;
		DeferredResult<R<StreamContentVo>> result = new DeferredResult<>(30000L);
		resultHolder.put(key, uuid, result);
		RequestMessage requestMessage = new RequestMessage();
		requestMessage.setId(uuid);
		requestMessage.setKey(key);

		Device device = deviceService.getDeviceByDeviceId(deviceId);
		if (device == null) {
			log.warn("[开始历史媒体下载] 未找到设备 deviceId: {},channelId:{}", deviceId, channelId);
			throw new ServiceException( "未找到设备：" + deviceId);
		}

		DeviceChannel channel = channelService.getOne(deviceId, channelId);
		if (channel == null) {
			log.warn("[开始历史媒体下载] 未找到通道 deviceId: {},channelId:{}", deviceId, channelId);
			throw new ServiceException( "未找到通道：" + channelId);
		}
		playService.download(device, channel, startTime, endTime, Integer.parseInt(downloadSpeed),
		(code, msg, streamInfo)->{

			R<StreamContentVo> wvpResult = null;
			if (code == InviteErrorCode.SUCCESS.getCode()) {
				if (streamInfo != null) {
					if (userSetting.getUseSourceIpAsStreamIp()) {
						streamInfo.channgeStreamIp(request.getLocalAddr());
					}
                    wvpResult = R.ok(new StreamContentVo(streamInfo));
				}
			}else {
                wvpResult = R.fail(code,msg);
			}
			requestMessage.setData(wvpResult);
			resultHolder.invokeResult(requestMessage);
		});

		return result;
	}

	@Operation(summary = "停止历史媒体下载")
	@Parameter(name = "deviceId", description = "设备国标编号", required = true)
	@Parameter(name = "channelId", description = "通道国标编号", required = true)
	@Parameter(name = "stream", description = "流ID", required = true)
	@GetMapping("/download/stop/{deviceId}/{channelId}/{stream}")
	public void playStop(@PathVariable String deviceId, @PathVariable String channelId, @PathVariable String stream) {

		if (log.isDebugEnabled()) {
			log.debug(String.format("设备历史媒体下载停止 API调用，deviceId/channelId：%s_%s", deviceId, channelId));
		}

		if (deviceId == null || channelId == null) {
			throw new ServiceException("参数或方法错误");
		}

		Device device = deviceService.getDeviceByDeviceId(deviceId);
		if (device == null) {
			throw new ServiceException( "设备：" + deviceId + "未找到");
		}

		try {
			cmder.streamByeCmd(device, channelId, stream, null);
		} catch (InvalidArgumentException | ParseException | SipException | SsrcTransactionNotFoundException e) {
			log.warn("[停止历史媒体下载]停止历史媒体下载，发送BYE失败 {}", e.getMessage());
		}
	}

	@Operation(summary = "获取历史媒体下载进度")
	@Parameter(name = "deviceId", description = "设备国标编号", required = true)
	@Parameter(name = "channelId", description = "通道国标编号", required = true)
	@Parameter(name = "stream", description = "流ID", required = true)
	@GetMapping("/download/progress/{deviceId}/{channelId}/{stream}")
	public StreamContentVo getProgress(@PathVariable String deviceId, @PathVariable String channelId, @PathVariable String stream) {
		Device device = deviceService.getDeviceByDeviceId(deviceId);
		if (device == null) {
			log.warn("[获取历史媒体下载进度] 未找到设备 deviceId: {},channelId:{}", deviceId, channelId);
			throw new ServiceException( "未找到设备：" + deviceId);
		}

		DeviceChannel channel = channelService.getOne(deviceId, channelId);
		if (channel == null) {
			log.warn("[获取历史媒体下载进度] 未找到通道 deviceId: {},channelId:{}", deviceId, channelId);
			throw new ServiceException( "未找到通道：" + channelId);
		}
		StreamInfo downLoadInfo = playService.getDownLoadInfo(device, channel, stream);
		if (downLoadInfo == null) {
			// throw new ServiceException(ErrorCode.ERROR404); TODO
		}
		return new StreamContentVo(downLoadInfo);
	}
}
