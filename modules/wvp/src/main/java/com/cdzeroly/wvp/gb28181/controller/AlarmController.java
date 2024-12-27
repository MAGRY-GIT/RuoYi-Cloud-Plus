package com.cdzeroly.wvp.gb28181.controller;

import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.domain.bo.AlarmBo;
import com.cdzeroly.wvp.domain.bo.DeviceAlarmBo;
import com.cdzeroly.wvp.domain.vo.DeviceAlarmVo;



import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.domian.DeviceAlarm;
import com.cdzeroly.wvp.gb28181.domian.Platform;

import com.cdzeroly.wvp.gb28181.service.IDeviceAlarmService;
import com.cdzeroly.wvp.gb28181.service.IDeviceService;
import com.cdzeroly.wvp.gb28181.service.IPlatformService;
import com.cdzeroly.wvp.gb28181.transmit.cmd.ISIPCommander;
import com.cdzeroly.wvp.gb28181.transmit.cmd.ISIPCommanderForPlatform;
import com.cdzeroly.wvp.utils.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import java.text.ParseException;

/**
 * @author MGARY
 */
@Tag(name = "报警信息管理")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {

    private final IDeviceAlarmService deviceAlarmService;

    private final ISIPCommander commander;

    private final ISIPCommanderForPlatform commanderForPlatform;

    private final IPlatformService platformService;

    private final IDeviceService deviceService;


    @DeleteMapping("/delete")
    @Operation(summary = "删除报警")
    public R<Integer> delete(AlarmBo bo) {
        return R.ok(deviceAlarmService.clearAlarmBeforeTime(bo));
    }


    @GetMapping("/test/notify/alarm")
    @Operation(summary = "测试向上级/设备发送模拟报警通知")
    @Parameter(name = "deviceId", description = "设备国标编号")
    public R<Void> delete(@RequestParam String deviceId) {
        Device device = deviceService.getDeviceByDeviceId(deviceId);
        Platform platform = platformService.queryPlatformByServerGBId(deviceId);
        DeviceAlarm deviceAlarm = new DeviceAlarm();
        deviceAlarm.setChannelId(deviceId);
        deviceAlarm.setAlarmDescription("test");
        deviceAlarm.setAlarmMethod("1");
        deviceAlarm.setAlarmPriority("1");
        deviceAlarm.setAlarmTime(DateUtil.getNow());
        deviceAlarm.setAlarmType("1");
        deviceAlarm.setLongitude(115.33333);
        deviceAlarm.setLatitude(39.33333);

        if (device != null && platform == null) {

            try {
                commander.sendAlarmMessage(device, deviceAlarm);
            } catch (InvalidArgumentException | SipException | ParseException ignored) {

            }
        } else if (device == null && platform != null) {
            try {
                commanderForPlatform.sendAlarmMessage(platform, deviceAlarm);
            } catch (SipException | InvalidArgumentException | ParseException e) {
                log.error("[命令发送失败] 国标级联 发送BYE: {}", e.getMessage());
                return  R.fail("命令发送失败: " + e.getMessage());
            }
        } else {
           return R.fail( "无法确定" + deviceId + "是平台还是设备");

        }
        return R.ok();
    }


    @Operation(summary = "分页查询报警")
    @GetMapping("/all")
    public TableDataInfo<DeviceAlarmVo> getAll(PageQuery pageQuery, DeviceAlarmBo bo) {
        return deviceAlarmService.getAllAlarm(pageQuery, bo);
    }
}
