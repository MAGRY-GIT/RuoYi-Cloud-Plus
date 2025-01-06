package com.cdzeroly.wvp.gb28181.controller;

import com.alibaba.fastjson2.JSONObject;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.common.NetProtocol;
import com.cdzeroly.wvp.conf.SipConfig;



import com.cdzeroly.wvp.domain.bean.SubscribeHolder;
import com.cdzeroly.wvp.domain.bo.UpdateChannelParam;
import com.cdzeroly.wvp.domain.Platform;
import com.cdzeroly.wvp.gb28181.bean.PlatformChannel;

import com.cdzeroly.wvp.gb28181.service.IPlatformChannelService;
import com.cdzeroly.wvp.gb28181.service.IPlatformService;
import com.cdzeroly.wvp.domain.WVPResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 级联平台管理
 * @author MGARY
 */
@Tag(name  = "级联平台管理")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/platform")
public class PlatformController {

    private final IPlatformChannelService platformChannelService;

    private final SubscribeHolder subscribeHolder;

    private final SipConfig sipConfig;

	private final IPlatformService platformService;


    @Operation(summary = "获取国标服务的配置")
    @GetMapping("/server_config")
    public JSONObject serverConfig() {
        JSONObject result = new JSONObject();
        result.put("deviceIp", sipConfig.getShowIp());
        result.put("devicePort", sipConfig.getPort());
        result.put("username", sipConfig.getId());
        result.put("password", sipConfig.getPassword());
        return result;
    }

    @Operation(summary = "获取级联服务器信息")
    @Parameter(name = "id", description = "平台国标编号", required = true)
    @GetMapping("/info/{id}")
    public Platform getPlatform(@PathVariable String id) {
        Platform parentPlatform = platformService.queryPlatformByServerGBId(id);
        if (parentPlatform != null) {
            return  parentPlatform;
        } else {
            throw new ServiceException("未查询到此平台") ;
        }
    }

    @GetMapping("/query")
    @Operation(summary = "分页查询级联平台")
    @Parameter(name = "page", description = "当前页")
    @Parameter(name = "count", description = "每页查询数量")
    @Parameter(name = "query", description = "查询内容")
    public TableDataInfo<Platform> platforms(PageQuery pageQuery,
                                             @RequestParam(required = false) String query) {

        TableDataInfo<Platform> parentPlatformPageInfo = platformService.queryPlatformList( pageQuery, query);
        return   parentPlatformPageInfo.map(platform -> {
            platform.setMobilePositionSubscribe(subscribeHolder.getMobilePositionSubscribe(platform.getServerGbId()) != null);
            platform.setCatalogSubscribe(subscribeHolder.getCatalogSubscribe(platform.getServerGbId()) != null);
            return platform;
        });

    }

    @Operation(summary = "添加上级平台信息")
    @PostMapping("/add")
    @ResponseBody
    public void add(@RequestBody Platform platform) {

        if (log.isDebugEnabled()) {
            log.debug("保存上级平台信息API调用");
        }
        Assert.notNull(platform.getName(), "平台名称不可为空");
        Assert.notNull(platform.getServerGbId(), "上级平台国标编号不可为空");
        Assert.notNull(platform.getServerIp(), "上级平台IP不可为空");
        Assert.isTrue(platform.getServerPort() > 0 && platform.getServerPort() < 65535, "上级平台端口异常");
        Assert.notNull(platform.getServerGbId(), "本平台国标编号不可为空");

        if (ObjectUtils.isEmpty(platform.getServerGbDomain())) {
            platform.setServerGbDomain(platform.getServerGbId().substring(0, 6));
        }

        if (platform.getExpires() <= 0) {
            platform.setExpires(3600);
        }

        if (platform.getKeepTimeout() <= 0) {
            platform.setKeepTimeout(60);
        }

        if (ObjectUtils.isEmpty(platform.getTransport())) {
            platform.setTransport(NetProtocol.UDP.name());
        }

        if (ObjectUtils.isEmpty(platform.getCharacterSet())) {
            platform.setCharacterSet("GB2312");
        }

        Platform parentPlatformOld = platformService.queryPlatformByServerGBId(platform.getServerGbId());
        if (parentPlatformOld != null) {
            throw new ServiceException( "平台 " + platform.getServerGbId() + " 已存在");
        }
        boolean updateResult = platformService.add(platform);

        if (!updateResult) {
            throw new ServiceException("失败");
        }
    }

    @Operation(summary = "更新上级平台信息")
    @PostMapping("/update")
    @ResponseBody
    public void updatePlatform(@RequestBody Platform parentPlatform) {

        if (log.isDebugEnabled()) {
            log.debug("保存上级平台信息API调用");
        }
        if (ObjectUtils.isEmpty(parentPlatform.getName())
                || ObjectUtils.isEmpty(parentPlatform.getServerGbId())
                || ObjectUtils.isEmpty(parentPlatform.getServerGbDomain())
                || ObjectUtils.isEmpty(parentPlatform.getServerIp())
                || ObjectUtils.isEmpty(parentPlatform.getServerPort())
                || ObjectUtils.isEmpty(parentPlatform.getServerGbId())
                || ObjectUtils.isEmpty(parentPlatform.getExpires())
                || ObjectUtils.isEmpty(parentPlatform.getKeepTimeout())
                || ObjectUtils.isEmpty(parentPlatform.getTransport())
                || ObjectUtils.isEmpty(parentPlatform.getCharacterSet())
        ) {
            // throw new ServiceException(ErrorCode.ERROR400);TODO
        }
        platformService.update(parentPlatform);
    }

    @Operation(summary = "删除上级平台")
    @Parameter(name = "id", description = "上级平台ID")
    @DeleteMapping("/delete")
    @ResponseBody
    public DeferredResult<Object> deletePlatform(Integer id) {

        if (log.isDebugEnabled()) {
            log.debug("删除上级平台API调用");
        }
        DeferredResult<Object> deferredResult = new DeferredResult<>();

        platformService.delete(id, (object)->{
            deferredResult.setResult(WVPResult.success());
        });
        return deferredResult;
    }

    @Operation(summary = "查询上级平台是否存在")
    @Parameter(name = "serverGBId", description = "上级平台的国标编号")
    @GetMapping("/exit/{serverGBId}")
    @ResponseBody
    public Boolean exitPlatform(@PathVariable String serverGBId) {
        Platform platform = platformService.queryPlatformByServerGBId(serverGBId);
        return platform != null;
    }

    @Operation(summary = "分页查询级联平台的所有所有通道")
    @Parameter(name = "page", description = "当前页", required = true)
    @Parameter(name = "count", description = "每页条数", required = true)
    @Parameter(name = "platformId", description = "上级平台的数据ID")
    @Parameter(name = "channelType", description = "通道类型， 0：国标设备，1：推流设备，2：拉流代理")
    @Parameter(name = "query", description = "查询内容")
    @Parameter(name = "online", description = "是否在线")
    @Parameter(name = "hasShare", description = "是否已经共享")
    @GetMapping("/channel/list")
    @ResponseBody
    public TableDataInfo<PlatformChannel> queryChannelList(PageQuery pageQuery,
                                                           @RequestParam(required = false) Long platformId,
                                                      @RequestParam(required = false) String query,
                                                      @RequestParam(required = false) Integer channelType,
                                                      @RequestParam(required = false) Boolean online,
                                                      @RequestParam(required = false) Boolean hasShare) {

        Assert.notNull(platformId, "上级平台的数据ID不可为NULL");
        if (ObjectUtils.isEmpty(query)) {
            query = null;
        }

        return platformChannelService.queryChannelList(pageQuery, query, channelType,  online, platformId, hasShare);
    }

    @Operation(summary = "向上级平台添加国标通道")
    @PostMapping("/channel/add")
    @ResponseBody
    public void addChannel(@RequestBody UpdateChannelParam param) {

        if (log.isDebugEnabled()) {
            log.debug("给上级平台添加国标通道API调用");
        }
        int result = 0;
        if (param.getChannelIds() == null || param.getChannelIds().isEmpty()) {
            if (param.isAll()) {
                log.info("[国标级联]添加所有通道到上级平台， {}", param.getPlatformId());
                result = platformChannelService.addAllChannel(param.getPlatformId());
            }
        }else {
            result = platformChannelService.addChannels(param.getPlatformId(), param.getChannelIds());
        }
        if (result <= 0) {
            throw new ServiceException("失败");
        }
    }

    @Operation(summary = "从上级平台移除国标通道")
    @DeleteMapping("/channel/remove")
    @ResponseBody
    public void delChannelForGB(@RequestBody UpdateChannelParam param) {

        if (log.isDebugEnabled()) {
            log.debug("给上级平台删除国标通道API调用");
        }
        int result = 0;
        if (param.getChannelIds() == null || param.getChannelIds().isEmpty()) {
            if (param.isAll()) {
                log.info("[国标级联]移除所有通道，上级平台， {}", param.getPlatformId());
                result = platformChannelService.removeAllChannel(param.getPlatformId());
            }
        }else {
            result = platformChannelService.removeChannels(param.getPlatformId(), param.getChannelIds());
        }
        if (result <= 0) {
            throw new ServiceException("失败");
        }
    }

    @Operation(summary = "推送通道")
    @Parameter(name = "id", description = "平台ID", required = true)
    @GetMapping("/channel/push")
    @ResponseBody
    public void pushChannel(Long id) {
        Assert.notNull(id, "平台ID不可为空");
        platformChannelService.pushChannel(id);
    }

    @Operation(summary = "添加通道-通过设备")
    @PostMapping("/channel/device/add")
    @ResponseBody
    public void addChannelByDevice(@RequestBody UpdateChannelParam param) {
        Assert.notNull(param.getPlatformId(), "平台ID不可为空");
        Assert.notNull(param.getDeviceIds(), "设备ID不可为空");
        Assert.notEmpty(param.getDeviceIds(), "设备ID不可为空");
        platformChannelService.addChannelByDevice(param.getPlatformId(), param.getDeviceIds());
    }

    @Operation(summary = "移除通道-通过设备")
    @PostMapping("/channel/device/remove")
    @ResponseBody
    public void removeChannelByDevice(@RequestBody UpdateChannelParam param) {
        Assert.notNull(param.getPlatformId(), "平台ID不可为空");
        Assert.notNull(param.getDeviceIds(), "设备ID不可为空");
        Assert.notEmpty(param.getDeviceIds(), "设备ID不可为空");
        platformChannelService.removeChannelByDevice(param.getPlatformId(), param.getDeviceIds());
    }

    @Operation(summary = "自定义共享通道信息")
    @PostMapping("/channel/custom/update")
    @ResponseBody
    public void updateCustomChannel(@RequestBody PlatformChannel channel) {
        Assert.isTrue(channel.getId() > 0, "共享通道ID必须存在");
        platformChannelService.updateCustomChannel(channel);
    }
}
