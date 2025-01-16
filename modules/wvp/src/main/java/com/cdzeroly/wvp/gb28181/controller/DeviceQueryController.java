package com.cdzeroly.wvp.gb28181.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.http.HttpStatus;
import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.core.utils.AssertUtils;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
import com.cdzeroly.common.log.annotation.Log;
import com.cdzeroly.common.log.enums.BusinessType;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.web.core.BaseController;
import com.cdzeroly.wvp.conf.task.DynamicTask;

import com.cdzeroly.wvp.domain.bo.DeviceBo;
import com.cdzeroly.wvp.domain.vo.DeviceVo;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.DeviceChannel;
import com.cdzeroly.wvp.domain.bean.SyncStatus;

import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import com.cdzeroly.wvp.gb28181.service.IDeviceService;
import com.cdzeroly.wvp.gb28181.service.IInviteStreamService;
import com.cdzeroly.wvp.gb28181.task.ISubscribeTask;
import com.cdzeroly.wvp.gb28181.task.impl.CatalogSubscribeTask;
import com.cdzeroly.wvp.gb28181.task.impl.MobilePositionSubscribeTask;
import com.cdzeroly.wvp.gb28181.transmit.callback.DeferredResultHolder;
import com.cdzeroly.wvp.gb28181.transmit.callback.RequestMessage;
import com.cdzeroly.wvp.gb28181.transmit.cmd.impl.SIPCommander;
import com.cdzeroly.wvp.domain.WVPResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.*;

/**
 * @author MGARY
 */
@Tag(name = "国标设备查询", description = "国标设备查询")
@Slf4j
@RestController
@RequestMapping("/device")
@AllArgsConstructor
public class DeviceQueryController extends BaseController {

    private final IDeviceChannelService deviceChannelService;

    private final IInviteStreamService inviteStreamService;

    private final SIPCommander cmder;

    private final DeferredResultHolder resultHolder;

    private final IDeviceService deviceService;

    private final DynamicTask dynamicTask;

    /**
     * 使用ID查询国标设备
     *
     * @param deviceId 国标ID
     * @return 国标设备
     */
    @Operation(summary = "查询国标设备")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @GetMapping("/{deviceId}")
    public R<DeviceVo> getDevice(@PathVariable String deviceId) {
        Device deviceByDeviceId = deviceService.getDeviceByDeviceId(deviceId);
        DeviceVo device = MapstructUtils.convert(deviceByDeviceId, DeviceVo.class);
        return R.ok(device);
    }


    /**
     * 分页查询国标设备
     *
     * @return 分页国标列表
     */
    @Operation(summary = "分页查询国标设备")
    @Parameter(name = "query", description = "搜索")
    @Parameter(name = "status", description = "状态")
    @GetMapping("/list")
    @SaCheckPermission("wvp:device:list")
    public TableDataInfo<Device> devices(PageQuery pageQuery, String query, Boolean status) {
        return deviceService.getAll(pageQuery, query, status);
    }



    /**
     * 同步设备通道
     */
    @Operation(summary = "同步设备通道")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @GetMapping("/{deviceId}/sync")
    public R<SyncStatus> devicesSync(@PathVariable String deviceId) {
        if (log.isDebugEnabled()) {
            log.debug("设备通道信息同步API调用，deviceId：" + deviceId);
        }
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        deviceService.isSyncRunning(deviceId);
        // 已存在则返回进度
        if (deviceService.isSyncRunning(deviceId)) {
            SyncStatus channelSyncStatus = deviceService.getChannelSyncStatus(deviceId);
            if (channelSyncStatus.getErrorMsg() != null) {
                return R.fail(HttpStatus.HTTP_CONTINUE, channelSyncStatus.getErrorMsg());
            } else if (channelSyncStatus.getTotal() == null || channelSyncStatus.getTotal() == 0) {
                return R.ok("等待通道信息...");
            } else {
                return R.ok(channelSyncStatus);
            }
        }
        deviceService.sync(device);
        return R.ok("开始同步");
    }

    @Operation(summary = "移除设备")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @DeleteMapping("/{deviceIds}")
    @SaCheckPermission("wvp:device:remove")
    @Log(title = "国标设备/平台", businessType = BusinessType.DELETE)
    public R<Void> delete(@PathVariable String deviceIds) {
        List<String> list = Arrays.stream(deviceIds.split(StringUtils.SEPARATOR)).toList();
        list.forEach(deviceId -> {
            // 清除redis记录
            boolean isSuccess = deviceService.delete(deviceId);
            if (isSuccess) {
                inviteStreamService.clearInviteInfo(deviceId);
                // 停止此设备的订阅更新
                Set<String> allKeys = dynamicTask.getAllKeys();
                for (String key : allKeys) {
                    if (key.startsWith(deviceId)) {
                        Runnable runnable = dynamicTask.get(key);
                        if (runnable instanceof ISubscribeTask subscribeTask) {
                            subscribeTask.stop(null);
                        }
                        dynamicTask.stop(key);
                    }
                }

            } else {
                log.warn("设备信息删除API调用失败！");
                throw new ServiceException("设备信息删除API调用失败！");
            }
        });
        return R.ok();
    }





    /**
     * 修改数据流传输模式
     *
     * @param deviceId   设备id
     * @param streamMode 数据流传输模式
     * @return
     */
    @Operation(summary = "修改数据流传输模式")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @Parameter(name = "streamMode", description = "数据流传输模式, 取值：" + "UDP（udp传输），TCP-ACTIVE（tcp主动模式），TCP-PASSIVE（tcp被动模式）", required = true)
    @PostMapping("/transport/{deviceId}/{streamMode}")
    public void updateTransport(@PathVariable String deviceId, @PathVariable String streamMode) {
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        device.setStreamMode(streamMode);
        deviceService.updateCustomDevice(device);
    }

    @Operation(summary = "添加设备信息")
    @Parameter(name = "deviceBo", description = "设备", required = true)
    @PostMapping
    @SaCheckPermission("wvp:device:add")
    @Log(title = "国标设备/平台", businessType = BusinessType.INSERT)
    public void addDevice(@Validated(AddGroup.class) @RequestBody DeviceBo deviceBo) {
        boolean exist = deviceService.isExist(deviceBo.getDeviceId());
        AssertUtils.isFalse(exist, "设备编号已存在");
        Device device = MapstructUtils.convert(deviceBo, Device.class);
        deviceService.addDevice(device);
    }

    /**
     * 更新设备信息
     *
     * @param deviceBo 设备信息
     */
    @Operation(summary = "更新设备信息")
    @Parameter(name = "device", description = "设备", required = true)
    @PutMapping()
    @SaCheckPermission("wvp:device:edit")
    @Log(title = "国标设备/平台", businessType = BusinessType.UPDATE)
    public void updateDevice(@Validated(EditGroup.class) @RequestBody DeviceBo deviceBo) {
        Device device = MapstructUtils.convert(deviceBo, Device.class);
        deviceService.updateCustomDevice(device);
    }

    /**
     * 设备状态查询请求API接口
     *
     * @param deviceId 设备id
     */
    @Operation(summary = "设备状态查询")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @GetMapping("/devices/{deviceId}/status")
    public DeferredResult<ResponseEntity<String>> deviceStatusApi(@PathVariable String deviceId) {
        if (log.isDebugEnabled()) {
            log.debug("设备状态查询API调用");
        }
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        String uuid = UUID.randomUUID().toString();
        String key = DeferredResultHolder.CALLBACK_CMD_DEVICESTATUS + deviceId;
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<ResponseEntity<String>>(2 * 1000L);
        if (device == null) {
            result.setResult(new ResponseEntity(String.format("设备%s不存在", deviceId), org.springframework.http.HttpStatus.OK));
            return result;
        }
        try {
            cmder.deviceStatusQuery(device, event -> {
                RequestMessage msg = new RequestMessage();
                msg.setId(uuid);
                msg.setKey(key);
                msg.setData(String.format("获取设备状态失败，错误码： %s, %s", event.statusCode, event.msg));
                resultHolder.invokeResult(msg);
            });
        } catch (InvalidArgumentException | SipException | ParseException e) {
            log.error("[命令发送失败] 获取设备状态: {}", e.getMessage());
            throw new ServiceException("命令发送失败: " + e.getMessage());
        }
        result.onTimeout(() -> {
            log.warn(String.format("获取设备状态超时"));
            // 释放rtpserver
            RequestMessage msg = new RequestMessage();
            msg.setId(uuid);
            msg.setKey(key);
            msg.setData("Timeout. Device did not response to this command.");
            resultHolder.invokeResult(msg);
        });
        resultHolder.put(DeferredResultHolder.CALLBACK_CMD_DEVICESTATUS + deviceId, uuid, result);
        return result;
    }

    /**
     * 设备报警查询请求API接口
     *
     * @param deviceId      设备id
     * @param startPriority 报警起始级别（可选）
     * @param endPriority   报警终止级别（可选）
     * @param alarmMethod   报警方式条件（可选）
     * @param alarmType     报警类型
     * @param startTime     报警发生起始时间（可选）
     * @param endTime       报警发生终止时间（可选）
     * @return true = 命令发送成功
     */
    @Operation(summary = "设备报警查询")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @Parameter(name = "startPriority", description = "报警起始级别")
    @Parameter(name = "endPriority", description = "报警终止级别")
    @Parameter(name = "alarmMethod", description = "报警方式条件")
    @Parameter(name = "alarmType", description = "报警类型")
    @Parameter(name = "startTime", description = "报警发生起始时间")
    @Parameter(name = "endTime", description = "报警发生终止时间")
    @GetMapping("/alarm/{deviceId}")
    public DeferredResult<ResponseEntity<String>> alarmApi(@PathVariable String deviceId, @RequestParam(required = false) String startPriority, @RequestParam(required = false) String endPriority, @RequestParam(required = false) String alarmMethod, @RequestParam(required = false) String alarmType, @RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime) {
        if (log.isDebugEnabled()) {
            log.debug("设备报警查询API调用");
        }
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        String key = DeferredResultHolder.CALLBACK_CMD_ALARM + deviceId;
        String uuid = UUID.randomUUID().toString();
        try {
            cmder.alarmInfoQuery(device, startPriority, endPriority, alarmMethod, alarmType, startTime, endTime, event -> {
                RequestMessage msg = new RequestMessage();
                msg.setId(uuid);
                msg.setKey(key);
                msg.setData(String.format("设备报警查询失败，错误码： %s, %s", event.statusCode, event.msg));
                resultHolder.invokeResult(msg);
            });
        } catch (InvalidArgumentException | SipException | ParseException e) {
            log.error("[命令发送失败] 设备报警查询: {}", e.getMessage());
            throw new ServiceException("命令发送失败: " + e.getMessage());
        }
        DeferredResult<ResponseEntity<String>> result = new DeferredResult<ResponseEntity<String>>(3 * 1000L);
        result.onTimeout(() -> {
            log.warn(String.format("设备报警查询超时"));
            // 释放rtpserver
            RequestMessage msg = new RequestMessage();
            msg.setId(uuid);
            msg.setKey(key);
            msg.setData("设备报警查询超时");
            resultHolder.invokeResult(msg);
        });
        resultHolder.put(DeferredResultHolder.CALLBACK_CMD_ALARM + deviceId, uuid, result);
        return result;
    }


    @GetMapping("/{deviceId}/sync_status")
    @Operation(summary = "获取通道同步进度")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    public R<SyncStatus> getSyncStatus(@PathVariable String deviceId) {
        SyncStatus channelSyncStatus = deviceService.getChannelSyncStatus(deviceId);
        if (channelSyncStatus == null) {
            log.error("同步不存在");
            return  R.fail(HttpStatus.HTTP_ACCEPTED,"同步不存在");
        } else if (channelSyncStatus.getErrorMsg() != null) {
            log.error(channelSyncStatus.getErrorMsg());
            return R.fail(HttpStatus.HTTP_ACCEPTED,channelSyncStatus.getErrorMsg());
        } else if (channelSyncStatus.getTotal() == null || channelSyncStatus.getTotal() == 0) {
            return R.ok("等待通道信息...");
        } else {
           return R.ok(channelSyncStatus);
        }
    }

    @GetMapping("/{deviceId}/subscribe_info")
    @Operation(summary = "获取设备的订阅状态")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    public WVPResult<Map<String, Integer>> getSubscribeInfo(@PathVariable String deviceId) {
        Set<String> allKeys = dynamicTask.getAllKeys();
        Map<String, Integer> dialogStateMap = new HashMap<>();
        for (String key : allKeys) {
            if (key.startsWith(deviceId)) {
                ISubscribeTask subscribeTask = (ISubscribeTask) dynamicTask.get(key);
                if (subscribeTask instanceof CatalogSubscribeTask) {
                    dialogStateMap.put("catalog", 1);
                } else if (subscribeTask instanceof MobilePositionSubscribeTask) {
                    dialogStateMap.put("mobilePosition", 1);
                }
            }
        }
        WVPResult<Map<String, Integer>> wvpResult = new WVPResult<>();
        wvpResult.setCode(0);
        wvpResult.setData(dialogStateMap);
        return wvpResult;
    }

    @GetMapping("/snap/{deviceId}/{channelId}")
    @Operation(summary = "请求截图")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @Parameter(name = "channelId", description = "通道国标编号", required = true)
    @Parameter(name = "mark", description = "标识", required = false)
    public void getSnap(@PathVariable String deviceId, @PathVariable String channelId, @RequestParam(required = false) String mark) {

        try {
            final InputStream in = Files.newInputStream(new File("snap" + File.separator + deviceId + "_" + channelId + (mark == null ? ".jpg" : ("_" + mark + ".jpg"))).toPath());
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copy(in, response.getOutputStream());
            in.close();
            outputStream.close();
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    @GetMapping("/channel/raw")
    @Operation(summary = "国标通道编辑时的数据回显")
    @Parameter(name = "id", description = "通道的Id", required = true)
    public DeviceChannel getRawChannel(Long id) {
        return deviceChannelService.getRawChannel(id);
    }

    @GetMapping("/subscribe/catalog")
    @Operation(summary = "开启/关闭目录订阅")
    @Parameter(name = "id", description = "通道的Id", required = true)
    @Parameter(name = "cycle", description = "订阅周期", required = true)
    public void subscribeCatalog(Long id, int cycle) {
        deviceService.subscribeCatalog(id, cycle);
    }

    @GetMapping("/subscribe/mobile-position")
    @Operation(summary = "开启/关闭移动位置订阅")
    @Parameter(name = "id", description = "通道的Id", required = true)
    @Parameter(name = "cycle", description = "订阅周期", required = true)
    @Parameter(name = "interval", description = "报送间隔", required = true)
    public void subscribeMobilePosition(Long id, int cycle, int interval) {
        deviceService.subscribeMobilePosition(id, cycle, interval);
    }
}
