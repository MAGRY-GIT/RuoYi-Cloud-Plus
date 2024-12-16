package com.cdzeroly.wvp.streamPush.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.conf.exception.ControllerException;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.gb28181.service.IGbChannelService;
import com.cdzeroly.wvp.media.bean.MediaInfo;
import com.cdzeroly.wvp.media.domian.MediaServer;
import com.cdzeroly.wvp.media.event.media.MediaArrivalEvent;
import com.cdzeroly.wvp.media.event.media.MediaDepartureEvent;
import com.cdzeroly.wvp.media.event.mediaServer.MediaServerOfflineEvent;
import com.cdzeroly.wvp.media.event.mediaServer.MediaServerOnlineEvent;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.media.zlm.dto.StreamAuthorityInfo;
import com.cdzeroly.wvp.media.zlm.dto.hook.OriginType;
import com.cdzeroly.wvp.service.ISendRtpServerService;
import com.cdzeroly.wvp.service.bean.GPSMsgInfo;
import com.cdzeroly.wvp.service.bean.StreamPushItemFromRedis;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import com.cdzeroly.wvp.streamPush.domian.StreamPush;
import com.cdzeroly.wvp.streamPush.domian.vo.StreamPushVo;
import com.cdzeroly.wvp.streamPush.mapper.StreamPushMapper;
import com.cdzeroly.wvp.streamPush.service.IStreamPushService;
import com.cdzeroly.wvp.utils.DateUtil;
import com.cdzeroly.wvp.vmanager.bean.ErrorCode;
import com.cdzeroly.wvp.vmanager.bean.ResourceBaseInfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
@Slf4j
@DS("master")
public class StreamPushServiceImpl implements IStreamPushService {

    @Autowired
    private StreamPushMapper streamPushMapper;

    @Autowired
    private IRedisCatchStorage redisCatchStorage;

    @Autowired
    private UserSetting userSetting;

    @Autowired
    private IMediaServerService mediaServerService;

    @Autowired
    private ISendRtpServerService sendRtpServerService;

    @Autowired
    private IGbChannelService gbChannelService;

    /**
     * 流到来的处理
     */
    @Async("taskExecutor")
    @EventListener
    @Transactional
    public void onApplicationEvent(MediaArrivalEvent event) {
        MediaInfo mediaInfo = event.getMediaInfo();
        if (mediaInfo == null) {
            return;
        }
        if (mediaInfo.getOriginType() != OriginType.RTMP_PUSH.ordinal()
                && mediaInfo.getOriginType() != OriginType.RTSP_PUSH.ordinal()
                && mediaInfo.getOriginType() != OriginType.RTC_PUSH.ordinal()) {
            return;
        }

        StreamAuthorityInfo streamAuthorityInfo = redisCatchStorage.getStreamAuthorityInfo(event.getApp(), event.getStream());
        if (streamAuthorityInfo == null) {
            streamAuthorityInfo = StreamAuthorityInfo.getInstanceByHook(event);
        } else {
            streamAuthorityInfo.setOriginType(mediaInfo.getOriginType());
        }
        redisCatchStorage.updateStreamAuthorityInfo(event.getApp(), event.getStream(), streamAuthorityInfo);

        StreamPushVo streamPushVoInDb = getPush(event.getApp(), event.getStream());
        if (streamPushVoInDb == null) {
            StreamPushVo streamPushVo = StreamPushVo.getInstance(event, userSetting.getServerId());
            streamPushVo.setPushing(true);
            streamPushVo.setPushTime(DateUtil.getNow());
            add(streamPushVo);
        }else {
            updatePushStatus(streamPushVoInDb, true);
        }
        // 冗余数据，自己系统中自用
        if (!"broadcast".equals(event.getApp()) && !"talk".equals(event.getApp())) {
            redisCatchStorage.addPushListItem(event.getApp(), event.getStream(), event.getMediaInfo());
        }

        // 发送流变化redis消息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverId", userSetting.getServerId());
        jsonObject.put("app", event.getApp());
        jsonObject.put("stream", event.getStream());
        jsonObject.put("register", true);
        jsonObject.put("mediaServerId", event.getMediaServer().getId());
        redisCatchStorage.sendStreamChangeMsg(OriginType.values()[event.getMediaInfo().getOriginType()].getType(), jsonObject);
    }

    /**
     * 流离开的处理
     */
    @Async("taskExecutor")
    @EventListener
    @Transactional
    public void onApplicationEvent(MediaDepartureEvent event) {

        // 兼容流注销时类型从redis记录获取
        MediaInfo mediaInfo = redisCatchStorage.getStreamInfo(
                event.getApp(), event.getStream(), event.getMediaServer().getId());
        if (mediaInfo != null) {
            String type = OriginType.values()[mediaInfo.getOriginType()].getType();
            redisCatchStorage.removeStream(event.getMediaServer().getId(), type, event.getApp(), event.getStream());
            if ("PUSH".equalsIgnoreCase(type)) {
                // 冗余数据，自己系统中自用
                redisCatchStorage.removePushListItem(event.getApp(), event.getStream(), event.getMediaServer().getId());
            }
            if (type != null) {
                // 发送流变化redis消息
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("serverId", userSetting.getServerId());
                jsonObject.put("app", event.getApp());
                jsonObject.put("stream", event.getStream());
                jsonObject.put("register", false);
                jsonObject.put("mediaServerId", event.getMediaServer().getId());
                redisCatchStorage.sendStreamChangeMsg(type, jsonObject);
            }
        }
        StreamPushVo streamPushVo = getPush(event.getApp(), event.getStream());
        if (streamPushVo == null) {
            return;
        }
        if (streamPushVo.getGbDeviceId() != null) {
            updatePushStatus(streamPushVo, false);
        }else {
            deleteByAppAndStream(event.getApp(), event.getStream());
        }
    }

    /**
     * 流媒体节点上线
     */
    @Async("taskExecutor")
    @EventListener
    @Transactional
    public void onApplicationEvent(MediaServerOnlineEvent event) {
        zlmServerOnline(event.getMediaServer());
    }

    /**
     * 流媒体节点离线
     */
    @Async("taskExecutor")
    @EventListener
    @Transactional
    public void onApplicationEvent(MediaServerOfflineEvent event) {
        zlmServerOffline(event.getMediaServer());
    }

    @Override
    public TableDataInfo<StreamPushVo> getPushList(PageQuery pageQuery, String query, Boolean pushing, String mediaServerId) {
        if (query != null) {
            query = query.replaceAll("/", "//")
                    .replaceAll("%", "/%")
                    .replaceAll("_", "/_");
        }
        Page<StreamPushVo> page = pageQuery.build();
        List<StreamPushVo> all = streamPushMapper.selectPage(page,query, pushing, mediaServerId);
           return  TableDataInfo.build(all);
    }

    @Override
    public List<StreamPushVo> getPushList(String mediaServerId) {
        return streamPushMapper.selectAllByMediaServerIdWithOutGbID(mediaServerId);
    }


    @Override
    public StreamPushVo getPush(String app, String stream) {
        return streamPushMapper.selectByAppAndStream(app, stream);
    }

    @Override
    @Transactional
    public boolean add(StreamPushVo stream) {
        log.info("[添加推流] app: {}, stream: {}, 国标编号: {}", stream.getApp(), stream.getStream(), stream.getGbDeviceId());
        StreamPushVo streamPushVoInDb = streamPushMapper.selectByAppAndStream(stream.getApp(), stream.getStream());
        if (streamPushVoInDb != null) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "应用名+流ID已存在");
        }
        StreamPush streamPush = MapstructUtils.convert(stream, StreamPush.class);
        int addResult = streamPushMapper.insert(streamPush);
        if (addResult <= 0) {
            return false;
        }
        if (ObjectUtils.isEmpty(stream.getGbDeviceId())) {
            return true;
        }
        CommonGBChannel channel = gbChannelService.queryByDeviceId(stream.getGbDeviceId());
        if (channel != null) {
            log.info("[添加推流]失败，国标编号已存在: {} app: {}, stream: {}, ", stream.getGbDeviceId(), stream.getApp(), stream.getStream());
        }
        int addChannelResult = gbChannelService.add(stream.buildCommonGBChannel());
        return addChannelResult > 0;
    }

    @Override
    @Transactional
    public void deleteByAppAndStream(String app, String stream) {
        log.info("[删除推流] app: {}, stream: {}, ", app, stream);
        StreamPushVo streamPushVo = streamPushMapper.selectByAppAndStream(app, stream);
        if (streamPushVo == null) {
            log.info("[删除推流]失败， 不存在 app: {}, stream: {}, ", app, stream);
            return;
        }
        if (streamPushVo.isPushing()) {
            stop(streamPushVo);
        }
        if (streamPushVo.getGbId() > 0) {
            gbChannelService.delete(streamPushVo.getGbId());
        }
        streamPushMapper.deleteById(streamPushVo.getId());
    }
    @Override
    @Transactional
    public boolean update(StreamPushVo streamPushVo) {
        Assert.notNull(streamPushVo, "推流信息不可为NULL");
        Assert.isTrue(streamPushVo.getId() > 0, "推流信息ID必须存在");
        log.info("[更新推流]：id: {}, app: {}, stream: {}, ", streamPushVo.getId(), streamPushVo.getApp(), streamPushVo.getStream());
        StreamPushVo streamPushVoInDb = streamPushMapper.queryOne(streamPushVo.getId());
        if (!streamPushVoInDb.getApp().equals(streamPushVo.getApp()) || !streamPushVoInDb.getStream().equals(streamPushVo.getStream())) {
            // app或者stream变化
            StreamPushVo streamPushInDbForAppAndStreamBoVo = streamPushMapper.selectByAppAndStream(streamPushVo.getApp(), streamPushVo.getStream());
            if (streamPushInDbForAppAndStreamBoVo != null && !streamPushInDbForAppAndStreamBoVo.getId().equals(streamPushVo.getId())) {
                throw new ControllerException(ErrorCode.ERROR100.getCode(), "应用名+流ID已存在");
            }
        }
        StreamPush streamPush = MapstructUtils.convert(streamPushVo, StreamPush.class);
        streamPushMapper.insertOrUpdate(streamPush);
        if (streamPushVo.getGbId() > 0) {
            gbChannelService.update(streamPushVo.buildCommonGBChannel());
        }
        return true;
    }


    @Override
    @Transactional
    public boolean stop(StreamPushVo streamPushVo) {
        log.info("[主动停止推流] id: {}, app: {}, stream: {}, ", streamPushVo.getId(), streamPushVo.getApp(), streamPushVo.getStream());
        MediaServer mediaServer = null;
        if (streamPushVo.getMediaServerId() == null) {
            log.info("[主动停止推流]未找到使用MediaServer，开始自动检索 id: {}, app: {}, stream: {}, ", streamPushVo.getId(), streamPushVo.getApp(), streamPushVo.getStream());
            mediaServer = mediaServerService.getMediaServerByAppAndStream(streamPushVo.getApp(), streamPushVo.getStream());
            if (mediaServer != null) {
                log.info("[主动停止推流] 检索到MediaServer为{}， id: {}, app: {}, stream: {}, ", mediaServer.getId(), streamPushVo.getId(), streamPushVo.getApp(), streamPushVo.getStream());
            }else {
                log.info("[主动停止推流]未找到使用MediaServer id: {}, app: {}, stream: {}, ", streamPushVo.getId(), streamPushVo.getApp(), streamPushVo.getStream());
            }
        }else {
            mediaServer = mediaServerService.getOne(streamPushVo.getMediaServerId());
            if (mediaServer == null) {
                log.info("[主动停止推流]未找到使用的MediaServer： {}，开始自动检索 id: {}, app: {}, stream: {}, ", streamPushVo.getMediaServerId(),  streamPushVo.getId(), streamPushVo.getApp(), streamPushVo.getStream());
                mediaServer = mediaServerService.getMediaServerByAppAndStream(streamPushVo.getApp(), streamPushVo.getStream());
                if (mediaServer != null) {
                    log.info("[主动停止推流] 检索到MediaServer为{}， id: {}, app: {}, stream: {}, ", mediaServer.getId(), streamPushVo.getId(), streamPushVo.getApp(), streamPushVo.getStream());
                }else {
                    log.info("[主动停止推流]未找到使用MediaServer id: {}, app: {}, stream: {}, ", streamPushVo.getId(), streamPushVo.getApp(), streamPushVo.getStream());
                }
            }
        }
        if (mediaServer != null) {
            mediaServerService.closeStreams(mediaServer, streamPushVo.getApp(), streamPushVo.getStream());
        }
        streamPushVo.setPushing(false);
        if (userSetting.getUsePushingAsStatus()) {
            CommonGBChannel commonGBChannel = streamPushVo.buildCommonGBChannel();
            if (commonGBChannel != null) {
                gbChannelService.offline(commonGBChannel);
            }
        }
        sendRtpServerService.deleteByStream(streamPushVo.getStream());
        mediaServerService.stopSendRtp(mediaServer, streamPushVo.getApp(), streamPushVo.getStream(), null);
        StreamPush streamPush = MapstructUtils.convert(streamPushVo, StreamPush.class);
        streamPushMapper.insertOrUpdate(streamPush);
        return true;
    }

    @Override
    @Transactional
    public boolean stopByAppAndStream(String app, String stream) {
        log.info("[主动停止推流] ： app: {}, stream: {}, ", app, stream);
        StreamPushVo streamPushVoItem = streamPushMapper.selectByAppAndStream(app, stream);
        if (streamPushVoItem != null) {
            stop(streamPushVoItem);
        }
        return true;
    }

    @Override
    @Transactional
    public void zlmServerOnline(MediaServer mediaServer) {
        // 同步zlm推流信息
        if (mediaServer == null) {
            return;
        }
        // 数据库记录
        List<StreamPushVo> pushList = getPushList(mediaServer.getId());
        Map<String, StreamPushVo> pushItemMap = new HashMap<>();
        // redis记录
        List<MediaInfo> mediaInfoList = redisCatchStorage.getStreams(mediaServer.getId(), "PUSH");
        Map<String, MediaInfo> streamInfoPushItemMap = new HashMap<>();
        if (!pushList.isEmpty()) {
            for (StreamPushVo streamPushVoItem : pushList) {
                if (ObjectUtils.isEmpty(streamPushVoItem.getGbId())) {
                    pushItemMap.put(streamPushVoItem.getApp() + streamPushVoItem.getStream(), streamPushVoItem);
                }
            }
        }
        if (!mediaInfoList.isEmpty()) {
            for (MediaInfo mediaInfo : mediaInfoList) {
                streamInfoPushItemMap.put(mediaInfo.getApp() + mediaInfo.getStream(), mediaInfo);
            }
        }
        // 获取所有推流鉴权信息，清理过期的
        List<StreamAuthorityInfo> allStreamAuthorityInfo = redisCatchStorage.getAllStreamAuthorityInfo();
        Map<String, StreamAuthorityInfo> streamAuthorityInfoInfoMap = new HashMap<>();
        for (StreamAuthorityInfo streamAuthorityInfo : allStreamAuthorityInfo) {
            streamAuthorityInfoInfoMap.put(streamAuthorityInfo.getApp() + streamAuthorityInfo.getStream(), streamAuthorityInfo);
        }
        List<StreamInfo> mediaList = mediaServerService.getMediaList(mediaServer, null, null, null);
        if (mediaList == null) {
            return;
        }
        List<StreamPushVo> streamPushVoItems = handleJSON(mediaList);
        if (streamPushVoItems != null) {
            for (StreamPushVo streamPushVoItem : streamPushVoItems) {
                pushItemMap.remove(streamPushVoItem.getApp() + streamPushVoItem.getStream());
                streamInfoPushItemMap.remove(streamPushVoItem.getApp() + streamPushVoItem.getStream());
                streamAuthorityInfoInfoMap.remove(streamPushVoItem.getApp() + streamPushVoItem.getStream());
            }
        }
        List<StreamPushVo> changedStreamPushVoList = new ArrayList<>(pushItemMap.values());
        if (!changedStreamPushVoList.isEmpty()) {
            for (StreamPushVo streamPushVo : changedStreamPushVoList) {
                stop(streamPushVo);
            }
        }

        Collection<MediaInfo> mediaInfos = streamInfoPushItemMap.values();
        if (!mediaInfos.isEmpty()) {
            String type = "PUSH";
            for (MediaInfo mediaInfo : mediaInfos) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("serverId", userSetting.getServerId());
                jsonObject.put("app", mediaInfo.getApp());
                jsonObject.put("stream", mediaInfo.getStream());
                jsonObject.put("register", false);
                jsonObject.put("mediaServerId", mediaServer.getId());
                redisCatchStorage.sendStreamChangeMsg(type, jsonObject);
                // 移除redis内流的信息
                redisCatchStorage.removeStream(mediaServer.getId(), "PUSH", mediaInfo.getApp(), mediaInfo.getStream());
                // 冗余数据，自己系统中自用
                redisCatchStorage.removePushListItem(mediaInfo.getApp(), mediaInfo.getStream(), mediaServer.getId());
            }
        }

        Collection<StreamAuthorityInfo> streamAuthorityInfos = streamAuthorityInfoInfoMap.values();
        if (!streamAuthorityInfos.isEmpty()) {
            for (StreamAuthorityInfo streamAuthorityInfo : streamAuthorityInfos) {
                // 移除redis内流的信息
                redisCatchStorage.removeStreamAuthorityInfo(streamAuthorityInfo.getApp(), streamAuthorityInfo.getStream());
            }
        }
    }

    @Override
    @Transactional
    public void zlmServerOffline(MediaServer mediaServer) {
        List<StreamPushVo> streamPushVoItems = streamPushMapper.selectAllByMediaServerId(mediaServer.getId());
        if (!streamPushVoItems.isEmpty()) {
            for (StreamPushVo streamPushVoItem : streamPushVoItems) {
                stop(streamPushVoItem);
            }
        }
//        // 移除没有GBId的推流
//        streamPushMapper.deleteWithoutGBId(mediaServerId);
//        // 其他的流设置未启用
//        streamPushMapper.updateStatusByMediaServerId(mediaServerId, false);
//        streamProxyMapper.updateStatusByMediaServerId(mediaServerId, false);
        // 发送流停止消息
        String type = "PUSH";
        // 发送redis消息
        List<MediaInfo> mediaInfoList = redisCatchStorage.getStreams(mediaServer.getId(), type);
        if (!mediaInfoList.isEmpty()) {
            for (MediaInfo mediaInfo : mediaInfoList) {
                // 移除redis内流的信息
                redisCatchStorage.removeStream(mediaServer.getId(), type, mediaInfo.getApp(), mediaInfo.getStream());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("serverId", userSetting.getServerId());
                jsonObject.put("app", mediaInfo.getApp());
                jsonObject.put("stream", mediaInfo.getStream());
                jsonObject.put("register", false);
                jsonObject.put("mediaServerId", mediaServer.getId());
                redisCatchStorage.sendStreamChangeMsg(type, jsonObject);

                // 冗余数据，自己系统中自用
                redisCatchStorage.removePushListItem(mediaInfo.getApp(), mediaInfo.getStream(), mediaServer.getId());
            }
        }
    }

    @Override
    @Transactional
    public void batchAdd(List<StreamPushVo> streamPushVoItems) {
        List<StreamPush> streamPushes = streamPushVoItems.stream().map(streamPushVo -> {
            return MapstructUtils.convert(streamPushVo, StreamPush.class);
        }).toList();
        streamPushMapper.insert(streamPushes);
        List<CommonGBChannel> commonGBChannels = new ArrayList<>();
        for (StreamPushVo streamPushVo : streamPushVoItems) {
            if (!ObjectUtils.isEmpty(streamPushVo.getGbDeviceId())) {
                commonGBChannels.add(streamPushVo.buildCommonGBChannel());
            }
        }
        gbChannelService.batchAdd(commonGBChannels);
    }

    @Override
    public void allOffline() {
        List<StreamPushVo> streamPushVoList = streamPushMapper.selectVoList();
        if (streamPushVoList.isEmpty()) {
            return;
        }
        List<CommonGBChannel> commonGBChannelList = new ArrayList<>();
        for (StreamPushVo streamPushVo : streamPushVoList) {
            CommonGBChannel commonGBChannel = streamPushVo.buildCommonGBChannel();
            if (commonGBChannel != null) {
                commonGBChannelList.add(streamPushVo.buildCommonGBChannel());
            }
        }
        gbChannelService.offline(commonGBChannelList);
    }

    @Override
    public void offline(List<StreamPushItemFromRedis> offlineStreams) {
        // 更新部分设备离线
        List<StreamPushVo> streamPushVoList = streamPushMapper.getListFromRedis(offlineStreams);
        List<CommonGBChannel> commonGBChannelList = gbChannelService.queryListByStreamPushList(streamPushVoList);
        gbChannelService.offline(commonGBChannelList);
    }

    @Override
    public void online(List<StreamPushItemFromRedis> onlineStreams) {
        // 更新部分设备上线streamPushService
        List<StreamPushVo> streamPushVoList = streamPushMapper.getListFromRedis(onlineStreams);
        List<CommonGBChannel> commonGBChannelList = gbChannelService.queryListByStreamPushList(streamPushVoList);
        gbChannelService.online(commonGBChannelList);
    }

    @Override
    public List<String> getAllAppAndStream() {
        return streamPushMapper.getAllAppAndStream();
    }

    @Override
    public ResourceBaseInfo getOverview() {
        int total = streamPushMapper.count().intValue();
        int online = streamPushMapper.getAllPushing(userSetting.getUsePushingAsStatus());

        return new ResourceBaseInfo(total, online);
    }

    @Override
    public Map<String, StreamPushVo> getAllAppAndStreamMap() {
        return streamPushMapper.getAllAppAndStreamMap();
    }

    @Override
    public Map<String, StreamPushVo> getAllGBId() {
        return streamPushMapper.getAllGBId();
    }

    @Override
    public void updateStatus(StreamPushVo push) {

    }



    @Override
    @Transactional
    public void updatePushStatus(StreamPushVo streamPushVo, boolean pushIng) {
        streamPushVo.setPushing(pushIng);
        if (userSetting.getUsePushingAsStatus()) {
            streamPushVo.setGbStatus(pushIng?"ON":"OFF");
        }
        streamPushVo.setPushTime(DateUtil.getNow());
        streamPushMapper.updatePushStatus(streamPushVo.getId(), pushIng);
        if (ObjectUtils.isEmpty(streamPushVo.getGbDeviceId())) {
            return;
        }
        if (userSetting.getUsePushingAsStatus()) {
            if ("ON".equalsIgnoreCase(streamPushVo.getGbStatus()) ) {
                gbChannelService.online(streamPushVo.buildCommonGBChannel());
            }else {
                gbChannelService.offline(streamPushVo.buildCommonGBChannel());
            }
        }
    }

    private List<StreamPushVo> handleJSON(List<StreamInfo> streamInfoList) {
        if (streamInfoList == null || streamInfoList.isEmpty()) {
            return null;
        }
        Map<String, StreamPushVo> result = new HashMap<>();
        for (StreamInfo streamInfo : streamInfoList) {
            // 不保存国标推理以及拉流代理的流
            if (streamInfo.getOriginType() == OriginType.RTSP_PUSH.ordinal()
                    || streamInfo.getOriginType() == OriginType.RTMP_PUSH.ordinal()
                    || streamInfo.getOriginType() == OriginType.RTC_PUSH.ordinal() ) {
                String key = streamInfo.getApp() + "_" + streamInfo.getStream();
                StreamPushVo streamPushVoItem = result.get(key);
                if (streamPushVoItem == null) {
                    streamPushVoItem = StreamPushVo.getInstance(streamInfo);
                    result.put(key, streamPushVoItem);
                }
            }
        }
        return new ArrayList<>(result.values());
    }

    @Override
    public void batchUpdate(List<StreamPushVo> streamPushVoItemForUpdate) {
        streamPushMapper.batchUpdate(streamPushVoItemForUpdate);
        List<CommonGBChannel> commonGBChannels = new ArrayList<>();
        for (StreamPushVo streamPushVo : streamPushVoItemForUpdate) {
            if (!ObjectUtils.isEmpty(streamPushVo.getGbDeviceId())) {
                commonGBChannels.add(streamPushVo.buildCommonGBChannel());
            }
        }
        gbChannelService.batchUpdate(commonGBChannels);
    }

    @Override
    @Transactional
    public int delete(int id) {
        StreamPushVo streamPushVo = streamPushMapper.queryOne(id);
        if (streamPushVo == null) {
            return 0;
        }
        if(streamPushVo.isPushing()) {
            MediaServer mediaServer = mediaServerService.getOne(streamPushVo.getMediaServerId());
            mediaServerService.closeStreams(mediaServer, streamPushVo.getApp(), streamPushVo.getStream());
        }
        if (streamPushVo.getGbDeviceId() != null) {
            gbChannelService.delete(streamPushVo.getGbId());
        }
        return streamPushMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void batchRemove(Set<Integer> ids) {
        List<StreamPushVo> streamPushVoList = streamPushMapper.selectInSet(ids);
        if (streamPushVoList.isEmpty()) {
            return;
        }
        List<CommonGBChannel> commonGBChannelList = new ArrayList<>();
        streamPushVoList.stream().forEach(streamPush -> {
            if (streamPush.getGbDeviceId() != null) {
                commonGBChannelList.add(streamPush.buildCommonGBChannel());
            }
        });
        streamPushMapper.deleteByIds(streamPushVoList);
        gbChannelService.delete(ids);
    }

    @Override
    public void updateGPSFromGPSMsgInfo(List<GPSMsgInfo> gpsMsgInfoList) {
        List<CommonGBChannel> channels = new ArrayList<>();
        for (GPSMsgInfo gpsMsgInfo : gpsMsgInfoList) {
            CommonGBChannel channel = new CommonGBChannel();
            channel.setGbDeviceId(gpsMsgInfo.getId());
            channel.setGbLongitude(gpsMsgInfo.getLng());
            channel.setGbLatitude(gpsMsgInfo.getLat());
            channels.add(channel);
        }
        gbChannelService.updateGpsByDeviceIdForStreamPush(channels);
    }
}
