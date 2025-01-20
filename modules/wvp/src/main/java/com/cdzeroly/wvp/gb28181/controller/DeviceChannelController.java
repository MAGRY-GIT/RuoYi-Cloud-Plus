package com.cdzeroly.wvp.gb28181.controller;

import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.web.core.BaseController;
import com.cdzeroly.wvp.domain.DeviceChannel;
import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/audio")
    public R<Void> changeAudio(Long channelId, Boolean audio) {
        Assert.notNull(channelId, "通道的数据库ID不可为NULL");
        Assert.notNull(audio, "开启/关闭音频不可为NULL");
        deviceChannelService.changeAudio(channelId, audio);
        return R.ok();
    }

    @Operation(summary = "修改通道的码流类型")
    @PostMapping("/stream/identification/update/")
    public  R<Void>  updateChannelStreamIdentification(DeviceChannel channel) {
        deviceChannelService.updateChannelStreamIdentification(channel);
        return R.ok();
    }


}
