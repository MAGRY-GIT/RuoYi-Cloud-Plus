package com.cdzeroly.wvp.gb28181;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.gb28181.bean.Preset;
import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import com.cdzeroly.wvp.gb28181.service.IDeviceService;
import com.cdzeroly.wvp.gb28181.transmit.callback.DeferredResultHolder;
import com.cdzeroly.wvp.gb28181.transmit.callback.RequestMessage;
import com.cdzeroly.wvp.gb28181.transmit.cmd.impl.SIPCommander;
import com.cdzeroly.wvp.domain.DeferredResultEx;
import com.cdzeroly.wvp.domain.vo.DeviceChannelExtendVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import java.text.ParseException;
import java.util.*;

/**
 * API兼容：设备信息
 * @author MAGRY
 */
@SuppressWarnings("unchecked")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/device")
public class ApiDeviceController {


    private final   SIPCommander cmder;

    private final   IDeviceChannelService channelService;


    private final   DeferredResultHolder resultHolder;


    private final   IDeviceService deviceService;


    /**
     * 分页获取设备列表 现在直接返回，尚未实现分页
     * @param start
     * @param limit
     * @param q
     * @param online
     * @return
     */
    @GetMapping(value = "/list")
    public JSONObject list( @RequestParam(required = false)Integer start,
                            @RequestParam(required = false)Integer limit,
                            @RequestParam(required = false)String q,
                            @RequestParam(required = false)Boolean online ){

//        if (logger.isDebugEnabled()) {
//            logger.debug("查询所有视频设备API调用");
//        }

        JSONObject result = new JSONObject();
        List<Device> devices;
        if (start == null || limit ==null) {
            devices = deviceService.getAllByStatus(online);
            result.put("DeviceCount", devices.size());
        }else {

            PageQuery pageQuery = new PageQuery();
            pageQuery.setPageNum(start/limit);
            pageQuery.setPageSize(limit);
            TableDataInfo<Device> deviceList = deviceService.getAll(pageQuery, null, online);
            result.put("DeviceCount", deviceList.getTotal());
            devices = deviceList.getRows();
        }

        JSONArray deviceJsonList = new JSONArray();
        devices.forEach(device -> {
            JSONObject deviceJsonObject = new JSONObject();
            deviceJsonObject.put("ID", device.getDeviceId());
            deviceJsonObject.put("Name", device.getName());
            deviceJsonObject.put("Type", "GB");
            deviceJsonObject.put("ChannelCount", device.getChannelCount());
            deviceJsonObject.put("RecvStreamIP", "");
            deviceJsonObject.put("CatalogInterval", 3600); // 通道目录抓取周期
            deviceJsonObject.put("SubscribeInterval", device.getSubscribeCycleForCatalog()); // 订阅周期(秒), 0 表示后台不周期订阅
            deviceJsonObject.put("Online", device.isOnLine());
            deviceJsonObject.put("Password", "");
            deviceJsonObject.put("MediaTransport", device.getTransport());
            deviceJsonObject.put("RemoteIP", device.getIp());
            deviceJsonObject.put("RemotePort", device.getPort());
            deviceJsonObject.put("LastRegisterAt", "");
            deviceJsonObject.put("LastKeepaliveAt", "");
            deviceJsonObject.put("UpdatedAt", "");
            deviceJsonObject.put("CreatedAt", "");
            deviceJsonList.add(deviceJsonObject);
        });
        result.put("DeviceList",deviceJsonList);
        return result;
    }

    @GetMapping(value = "/channellist")
    public JSONObject channellist( String serial,
                                   @RequestParam(required = false)String channel_type,
                                   @RequestParam(required = false)String code ,
                                   @RequestParam(required = false)String dir_serial ,
                                   @RequestParam(required = false)Integer start,
                                   @RequestParam(required = false)Integer limit,
                                   @RequestParam(required = false)String q,
                                   @RequestParam(required = false)Boolean online ){

        JSONObject result = new JSONObject();
        List<DeviceChannelExtendVo> deviceChannels;
        List<String> channelIds = null;
        if (!ObjectUtils.isEmpty(code)) {
            String[] split = code.trim().split(",");
            channelIds = Arrays.asList(split);
        }
        List<DeviceChannelExtendVo> allDeviceChannelList = channelService.queryChannelExtendsByDeviceId(serial,channelIds,online);
        if (start == null || limit ==null) {
            deviceChannels = allDeviceChannelList;
            result.put("ChannelCount", deviceChannels.size());
        }else {
            if (start > allDeviceChannelList.size()) {
                deviceChannels = new ArrayList<>();
            }else {
                if (start + limit < allDeviceChannelList.size()) {
                    deviceChannels = allDeviceChannelList.subList(start, start + limit);
                }else {
                    deviceChannels = allDeviceChannelList.subList(start, allDeviceChannelList.size());
                }
            }
            result.put("ChannelCount", allDeviceChannelList.size());
        }
        JSONArray channelJsonList = new JSONArray();
        deviceChannels.forEach(deviceChannelExtendVo -> {
            JSONObject deviceJsonChannel = new JSONObject();
            deviceJsonChannel.put("ID", deviceChannelExtendVo.getChannelId());
            deviceJsonChannel.put("DeviceID", deviceChannelExtendVo.getDeviceId());
            deviceJsonChannel.put("DeviceName", deviceChannelExtendVo.getDeviceName());
            deviceJsonChannel.put("DeviceOnline", deviceChannelExtendVo.isDeviceOnline());
            deviceJsonChannel.put("Channel", 0); // TODO 自定义序号
            deviceJsonChannel.put("Name", deviceChannelExtendVo.getName());
            deviceJsonChannel.put("Custom", false);
            deviceJsonChannel.put("CustomName", "");
            deviceJsonChannel.put("SubCount", deviceChannelExtendVo.getSubCount()); // TODO ? 子节点数, SubCount > 0 表示该通道为子目录
            deviceJsonChannel.put("SnapURL", "");
            deviceJsonChannel.put("Manufacturer ", deviceChannelExtendVo.getManufacture());
            deviceJsonChannel.put("Model", deviceChannelExtendVo.getModel());
            deviceJsonChannel.put("Owner", deviceChannelExtendVo.getOwner());
            deviceJsonChannel.put("CivilCode", deviceChannelExtendVo.getCivilCode());
            deviceJsonChannel.put("Address", deviceChannelExtendVo.getAddress());
            deviceJsonChannel.put("Parental", deviceChannelExtendVo.getParental()); // 当为通道设备时, 是否有通道子设备, 1-有,0-没有
            deviceJsonChannel.put("ParentID", deviceChannelExtendVo.getParentId()); // 直接上级编号
            deviceJsonChannel.put("Secrecy", deviceChannelExtendVo.getSecrecy());
            deviceJsonChannel.put("RegisterWay", 1); // 注册方式, 缺省为1, 允许值: 1, 2, 3
            // 1-IETF RFC3261,
            // 2-基于口令的双向认证,
            // 3-基于数字证书的双向认证
            deviceJsonChannel.put("Status", deviceChannelExtendVo.getStatus());
            deviceJsonChannel.put("Longitude", deviceChannelExtendVo.getLongitude());
            deviceJsonChannel.put("Latitude", deviceChannelExtendVo.getLatitude());
            deviceJsonChannel.put("PTZType ", deviceChannelExtendVo.getPTZType()); // 云台类型, 0 - 未知, 1 - 球机, 2 - 半球,
            //   3 - 固定枪机, 4 - 遥控枪机
            deviceJsonChannel.put("CustomPTZType", "");
            deviceJsonChannel.put("StreamID", deviceChannelExtendVo.getStreamId()); // StreamID 直播流ID, 有值表示正在直播
            deviceJsonChannel.put("NumOutputs ", -1); // 直播在线人数
            channelJsonList.add(deviceJsonChannel);
        });
        result.put("ChannelList", channelJsonList);
        return result;
    }

    /**
     * 设备信息 - 获取下级通道预置位
     * @param serial 设备编号
     * @param code 通道编号,通过 /api/v1/device/channellist 获取的 ChannelList.ID, 该参数和 channel 二选一传递即可
     * @param channel 通道序号, 默认值: 1
     * @param fill 是否填充空置预置位，当下级返回预置位，但不够255个时，自动填充空置预置位到255个， 默认值: true， 允许值: true, false
     * @param timeout 超时时间(秒) 默认值: 15
     * @return
     */
    @GetMapping(value = "/fetchpreset")
    private DeferredResult<R<String>>  list(String serial,
                      @RequestParam(required = false)Integer channel,
                      @RequestParam(required = false)String code,
                      @RequestParam(required = false)Boolean fill,
                      @RequestParam(required = false)Integer timeout){

        if (log.isDebugEnabled()) {
            log.debug("<模拟接口> 获取下级通道预置位 API调用，deviceId：{} ，channel：{} ，code：{} ，fill：{} ，timeout：{} ",
                    serial, channel, code, fill, timeout);
        }

        Device device = deviceService.getDeviceByDeviceId(serial);
        String uuid =  UUID.randomUUID().toString();
        String key =  DeferredResultHolder.CALLBACK_CMD_PRESET_QUERY + (ObjectUtils.isEmpty(code) ? serial : code);
        DeferredResult<R<String>> result = new DeferredResult<> (timeout * 1000L);
        DeferredResultEx<R<String>> deferredResultEx = new DeferredResultEx<>(result);
        result.onTimeout(()->{
            log.warn("<模拟接口> 获取设备预置位超时");
            // 释放rtpserver
            RequestMessage msg = new RequestMessage();
            msg.setId(uuid);
            msg.setKey(key);
            msg.setData(R.fail("wait for presetquery timeout["+timeout+"s]"));
            resultHolder.invokeResult(msg);
        });
        if (resultHolder.exist(key, null)) {
            return result;
        }

        deferredResultEx.setFilter(filterResult->{
            List<Preset> presetQuerySipReqList = (List<Preset>)filterResult;
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("DeviceID", code);
            resultMap.put("Result", "OK");
            resultMap.put("SumNum", presetQuerySipReqList.size());
            List<Map<String, Object>> presetItemList = new ArrayList<>(presetQuerySipReqList.size());
            for (Preset presetQuerySipReq : presetQuerySipReqList) {
                Map<String, Object> item = new HashMap<>();
                item.put("PresetID", presetQuerySipReq.getPresetId());
                item.put("PresetName", presetQuerySipReq.getPresetName());
                item.put("PresetEnable", true);
                presetItemList.add(item);
            }
            resultMap.put("PresetItemList",presetItemList );
            return resultMap;
        });

        resultHolder.put(key, uuid, deferredResultEx);

        try {
            cmder.presetQuery(device, code, event -> {
                RequestMessage msg = new RequestMessage();
                msg.setId(uuid);
                msg.setKey(key);
                msg.setData(R.fail(String.format("获取设备预置位失败，错误码： %s, %s", event.statusCode, event.msg)));
                resultHolder.invokeResult(msg);
            });
        } catch (InvalidArgumentException | SipException | ParseException e) {
            log.error("[命令发送失败] 获取设备预置位: {}", e.getMessage());
            throw new ServiceException("命令发送失败: " + e.getMessage());
        }
        return result;
    }
}
