package com.cdzeroly.wvp.gb28181.controller;

import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.web.core.BaseController;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.conf.UserSetting;

import com.cdzeroly.wvp.domain.bo.ChannelToGroupByGbDeviceParam;
import com.cdzeroly.wvp.domain.bo.ChannelToGroupParam;
import com.cdzeroly.wvp.domain.bo.ChannelToRegionByGbDeviceParam;
import com.cdzeroly.wvp.domain.bean.*;



import com.cdzeroly.wvp.domain.bo.ChannelToRegionParam;
import com.cdzeroly.wvp.domain.CommonGbChannel;
import com.cdzeroly.wvp.gb28181.service.IGbChannelPlayService;
import com.cdzeroly.wvp.gb28181.service.IGbChannelService;
import com.cdzeroly.wvp.domain.vo.StreamContentVo;
import com.cdzeroly.wvp.domain.WVPResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/**
 * @author MGARY
 */
@Tag(name  = "全局通道管理")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/commonChannel")
public class CommonChannelController extends BaseController {


    private final IGbChannelService channelService;


    private final IGbChannelPlayService channelPlayService;

    private final UserSetting userSetting;


    @Operation(summary = "查询通道信息")
    @Parameter(name = "id", description = "通道的数据库自增Id", required = true)
    @GetMapping(value = "/one")
    public R<CommonGbChannel> getOne(Long id){
        return R.ok(channelService.getOne(id));
    }

    @Operation(summary = "获取行业编码列表")
    @GetMapping("/industry/list")
    public R<List<IndustryCodeType>> getIndustryCodeList(){
        return R.ok(channelService.getIndustryCodeList());
    }

    @Operation(summary = "获取编码列表")
    @GetMapping("/type/list")
    public R<List<DeviceType>> getDeviceTypeList(){
        return R.ok(channelService.getDeviceTypeList());
    }

    @Operation(summary = "获取编码列表")
    @GetMapping("/network/identification/list")
    public R<List<NetworkIdentificationType>> getNetworkIdentificationTypeList(){
        return R.ok(channelService.getNetworkIdentificationTypeList());
    }

    @Operation(summary = "更新通道")
    @PostMapping("/update")
    public R<Void> update(@RequestBody CommonGbChannel channel){
        channelService.update(channel);
        return R.ok();
    }

    @Operation(summary = "重置国标通道")
    @PostMapping("/reset")
    public  R<Void> reset(Long id){
        channelService.reset(id);
        return R.ok();
    }

    @Operation(summary = "增加国标通道")
    @PostMapping("/add")
    public CommonGbChannel add(@RequestBody CommonGbChannel channel){
        channelService.add(channel);
        return channel;
    }

    @Operation(summary = "获取通道列表")
    @Parameter(name = "page", description = "当前页", required = true)
    @Parameter(name = "count", description = "每页查询数量", required = true)
    @Parameter(name = "query", description = "查询内容")
    @Parameter(name = "online", description = "是否在线")
    @Parameter(name = "hasRecordPlan", description = "是否已设置录制计划")
    @Parameter(name = "channelType", description = "通道类型， 0：国标设备，1：推流设备，2：拉流代理")
    @GetMapping("/list")
    public TableDataInfo<CommonGbChannel> queryList(PageQuery pageQuery,
                                                    @RequestParam(required = false) String query,
                                                    @RequestParam(required = false) Boolean online,
                                                    @RequestParam(required = false) Boolean hasRecordPlan,
                                                    @RequestParam(required = false) Integer channelType){

        return channelService.queryList(pageQuery, query, online, hasRecordPlan, channelType);
    }

    @Operation(summary = "获取关联行政区划通道列表")
    @Parameter(name = "page", description = "当前页", required = true)
    @Parameter(name = "count", description = "每页查询数量", required = true)
    @Parameter(name = "query", description = "查询内容")
    @Parameter(name = "online", description = "是否在线")
    @Parameter(name = "channelType", description = "通道类型， 0：国标设备，1：推流设备，2：拉流代理")
    @Parameter(name = "civilCode", description = "行政区划")
    @GetMapping("/civilcode/list")
    public TableDataInfo<CommonGbChannel> queryListByCivilCode(PageQuery pageQuery,
                                                               @RequestParam(required = false) String query,
                                                               @RequestParam(required = false) Boolean online,
                                                               @RequestParam(required = false) Integer channelType,
                                                               @RequestParam(required = false) String civilCode){
        return channelService.queryListByCivilCode(pageQuery, query, online, channelType, civilCode);
    }

    @Operation(summary = "获取关联业务分组通道列表")
    @Parameter(name = "page", description = "当前页", required = true)
    @Parameter(name = "count", description = "每页查询数量", required = true)
    @Parameter(name = "query", description = "查询内容")
    @Parameter(name = "online", description = "是否在线")
    @Parameter(name = "channelType", description = "通道类型， 0：国标设备，1：推流设备，2：拉流代理")
    @Parameter(name = "groupDeviceId", description = "业务分组下的父节点ID")
    @GetMapping("/parent/list")
    public TableDataInfo<CommonGbChannel> queryListByParentId(PageQuery pageQuery,
                                                              @RequestParam(required = false) String query,
                                                              @RequestParam(required = false) Boolean online,
                                                              @RequestParam(required = false) Integer channelType,
                                                              @RequestParam(required = false) String groupDeviceId){
        if (ObjectUtils.isEmpty(query)){
            query = null;
        }
        return channelService.queryListByParentId(pageQuery, query, online, channelType, groupDeviceId);
    }

    @Operation(summary = "通道设置行政区划")
    @PostMapping("/region/add")
    public  R<Void> addChannelToRegion(@RequestBody ChannelToRegionParam param){
        Assert.notEmpty(param.getChannelIds(),"通道ID不可为空");
        Assert.hasLength(param.getCivilCode(),"未添加行政区划");
        channelService.addChannelToRegion(param.getCivilCode(), param.getChannelIds());
        return R.ok();
    }

    @Operation(summary = "通道删除行政区划")
    @PostMapping("/region/delete")
    public  R<Void> deleteChannelToRegion(@RequestBody ChannelToRegionParam param){
        Assert.isTrue(!param.getChannelIds().isEmpty() || !ObjectUtils.isEmpty(param.getCivilCode()),"参数异常");
        channelService.deleteChannelToRegion(param.getCivilCode(), param.getChannelIds());
        return R.ok();
    }

    @Operation(summary = "通道设置行政区划-根据国标设备")
    @PostMapping("/region/device/add")
    public  R<Void> addChannelToRegionByGbDevice(@RequestBody ChannelToRegionByGbDeviceParam param){
        Assert.notEmpty(param.getDeviceIds(),"参数异常");
        Assert.hasLength(param.getCivilCode(),"未添加行政区划");
        channelService.addChannelToRegionByGbDevice(param.getCivilCode(), param.getDeviceIds());
        return R.ok();
    }

    @Operation(summary = "通道删除行政区划-根据国标设备")
    @PostMapping("/region/device/delete")
    public  R<Void> deleteChannelToRegionByGbDevice(@RequestBody ChannelToRegionByGbDeviceParam param){
        Assert.notEmpty(param.getDeviceIds(),"参数异常");
        channelService.deleteChannelToRegionByGbDevice(param.getDeviceIds());
        return R.ok();
    }

    @Operation(summary = "通道设置业务分组")
    @PostMapping("/group/add")
    public  R<Void> addChannelToGroup(@RequestBody ChannelToGroupParam param){
        Assert.notEmpty(param.getChannelIds(),"通道ID不可为空");
        Assert.hasLength(param.getParentId(),"未添加上级分组编号");
        Assert.hasLength(param.getBusinessGroup(),"未添加业务分组");
        channelService.addChannelToGroup(param.getParentId(), param.getBusinessGroup(), param.getChannelIds());
        return R.ok();
    }

    @Operation(summary = "通道删除业务分组")
    @PostMapping("/group/delete")
    public  R<Void> deleteChannelToGroup(@RequestBody ChannelToGroupParam param){
        Assert.isTrue(!param.getChannelIds().isEmpty()
                || (!ObjectUtils.isEmpty(param.getParentId()) && !ObjectUtils.isEmpty(param.getBusinessGroup())),
                "参数异常");
        channelService.deleteChannelToGroup(param.getParentId(), param.getBusinessGroup(), param.getChannelIds());
        return R.ok();
    }

    @Operation(summary = "通道设置业务分组-根据国标设备")
    @PostMapping("/group/device/add")
    public  R<Void> addChannelToGroupByGbDevice(@RequestBody ChannelToGroupByGbDeviceParam param){
        Assert.notEmpty(param.getDeviceIds(),"参数异常");
        Assert.hasLength(param.getParentId(),"未添加上级分组编号");
        Assert.hasLength(param.getBusinessGroup(),"未添加业务分组");
        channelService.addChannelToGroupByGbDevice(param.getParentId(), param.getBusinessGroup(), param.getDeviceIds());
        return R.ok();
    }

    @Operation(summary = "通道删除业务分组-根据国标设备")
    @PostMapping("/group/device/delete")
    public  R<Void> deleteChannelToGroupByGbDevice(@RequestBody ChannelToGroupByGbDeviceParam param){
        Assert.notEmpty(param.getDeviceIds(),"参数异常");
        channelService.deleteChannelToGroupByGbDevice(param.getDeviceIds());
        return R.ok();
    }

    @Operation(summary = "播放通道")
    @GetMapping("/play")
    public DeferredResult<WVPResult<StreamContentVo>> deleteChannelToGroupByGbDevice(Long channelId){
        Assert.notNull(channelId,"参数异常");
        CommonGbChannel channel = channelService.getOne(channelId);
        Assert.notNull(channel, "通道不存在");

        DeferredResult<WVPResult<StreamContentVo>> result = new DeferredResult<>(userSetting.getPlayTimeout().longValue());

        ErrorCallback<StreamInfo> callback = (code, msg, streamInfo) -> {
            if (code == InviteErrorCode.SUCCESS.getCode()) {
                WVPResult<StreamContentVo> wvpResult = WVPResult.success();
                if (streamInfo != null) {
                    if (userSetting.getUseSourceIpAsStreamIp()) {
                        streamInfo=streamInfo.clone();//深拷贝
                        String host;
                        try {
                            URL url=new URL(request.getRequestURL().toString());
                            host=url.getHost();
                        } catch (MalformedURLException e) {
                            host=request.getLocalAddr();
                        }
                        streamInfo.channgeStreamIp(host);
                    }
                    if (!ObjectUtils.isEmpty(streamInfo.getMediaServer().getTranscodeSuffix())
                            && !"null".equalsIgnoreCase(streamInfo.getMediaServer().getTranscodeSuffix())) {
                        streamInfo.setStream(streamInfo.getStream() + "_" + streamInfo.getMediaServer().getTranscodeSuffix());
                    }
                    wvpResult.setData(new StreamContentVo(streamInfo));
                }else {
                    wvpResult.setCode(code);
                    wvpResult.setMsg(msg);
                }

                result.setResult(wvpResult);
            }else {
                result.setResult(WVPResult.fail(code, msg));
            }
        };
        channelPlayService.play(channel, null, callback);
        return result;
    }
}
