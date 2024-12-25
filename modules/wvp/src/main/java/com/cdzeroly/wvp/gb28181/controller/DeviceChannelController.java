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
import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.domian.DeviceChannel;
import com.cdzeroly.wvp.gb28181.domian.bean.SyncStatus;
import com.cdzeroly.wvp.gb28181.domian.bo.DeviceBo;
import com.cdzeroly.wvp.gb28181.domian.vo.DeviceVo;
import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import com.cdzeroly.wvp.gb28181.service.IDeviceService;
import com.cdzeroly.wvp.gb28181.service.IInviteStreamService;
import com.cdzeroly.wvp.gb28181.task.ISubscribeTask;
import com.cdzeroly.wvp.gb28181.task.impl.CatalogSubscribeTask;
import com.cdzeroly.wvp.gb28181.task.impl.MobilePositionSubscribeTask;
import com.cdzeroly.wvp.gb28181.transmit.callback.DeferredResultHolder;
import com.cdzeroly.wvp.gb28181.transmit.callback.RequestMessage;
import com.cdzeroly.wvp.gb28181.transmit.cmd.impl.SIPCommander;
import com.cdzeroly.wvp.vmanager.bean.WVPResult;
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
import org.springframework.util.Assert;
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
@RequestMapping("/deviceChannel")
@AllArgsConstructor
public class DeviceChannelController extends BaseController {

    private final IDeviceChannelService deviceChannelService;




    /**
     * 分页查询通道数
     */
    @GetMapping("/list/{deviceId}")
    @Operation(summary = "分页查询通道")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @Parameter(name = "query", description = "查询内容")
    @Parameter(name = "online", description = "是否在线")
    @Parameter(name = "channelType", description = "设备/子目录-> false/true")
    public TableDataInfo<DeviceChannel> channels(@PathVariable String deviceId, PageQuery pageQuery, @RequestParam(required = false) String query, @RequestParam(required = false) Boolean online, @RequestParam(required = false) Boolean channelType) {
        return deviceChannelService.queryChannelsByDeviceId(deviceId, query, channelType, online, pageQuery);
    }



    /**
     * 分页查询子目录通道
     *
     * @param deviceId    通道id
     * @param channelId   通道id
     * @param query       查询内容
     * @param online      是否在线
     * @param channelType 通道类型
     * @return 子通道列表
     */
    @Operation(summary = "分页查询子目录通道")
    @Parameter(name = "deviceId", description = "设备国标编号", required = true)
    @Parameter(name = "channelId", description = "通道国标编号", required = true)
    @Parameter(name = "page", description = "当前页", required = true)
    @Parameter(name = "count", description = "每页查询数量", required = true)
    @Parameter(name = "query", description = "查询内容")
    @Parameter(name = "online", description = "是否在线")
    @Parameter(name = "channelType", description = "设备/子目录-> false/true")
    @GetMapping("/sub_channels/{deviceId}/{channelId}/channels")
    public TableDataInfo<DeviceChannel> subChannels(PageQuery pageQuery, @PathVariable String deviceId, @PathVariable String channelId, @RequestParam(required = false) String query, @RequestParam(required = false) Boolean online, @RequestParam(required = false) Boolean channelType) {

        DeviceChannel deviceChannel = deviceChannelService.getOne(deviceId, channelId);
        if (deviceChannel == null) {
            return new TableDataInfo<>();
        }

        return deviceChannelService.getSubChannels(deviceChannel.getDataDeviceId(), channelId, query, channelType, online, pageQuery);
    }

    @Operation(summary = "开启/关闭通道的音频")
    @Parameter(name = "channelId", description = "通道的数据库ID", required = true)
    @Parameter(name = "audio", description = "开启/关闭音频", required = true)
    @PostMapping("/channel/audio")
    public void changeAudio(Integer channelId, Boolean audio) {
        Assert.notNull(channelId, "通道的数据库ID不可为NULL");
        Assert.notNull(audio, "开启/关闭音频不可为NULL");
        deviceChannelService.changeAudio(channelId, audio);
    }

    @Operation(summary = "修改通道的码流类型")
    @PostMapping("/stream/identification/update/")
    public void updateChannelStreamIdentification(DeviceChannel channel) {
        deviceChannelService.updateChannelStreamIdentification(channel);
    }


}
