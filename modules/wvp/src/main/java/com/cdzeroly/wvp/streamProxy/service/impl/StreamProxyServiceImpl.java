package com.cdzeroly.wvp.streamProxy.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.common.enums.ChannelDataType;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.gb28181.service.IGbChannelService;
import com.cdzeroly.wvp.media.domian.MediaServer;
import com.cdzeroly.wvp.media.event.media.MediaArrivalEvent;
import com.cdzeroly.wvp.media.event.media.MediaDepartureEvent;
import com.cdzeroly.wvp.media.event.media.MediaNotFoundEvent;
import com.cdzeroly.wvp.media.event.mediaServer.MediaServerOfflineEvent;
import com.cdzeroly.wvp.media.event.mediaServer.MediaServerOnlineEvent;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.media.zlm.dto.hook.OriginType;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import com.cdzeroly.wvp.streamProxy.domain.StreamProxy;
import com.cdzeroly.wvp.streamProxy.domain.bean.StreamProxyParam;
import com.cdzeroly.wvp.streamProxy.domain.bo.StreamProxyBo;
import com.cdzeroly.wvp.streamProxy.domain.vo.StreamProxyVo;
import com.cdzeroly.wvp.streamProxy.mapper.StreamProxyMapper;
import com.cdzeroly.wvp.streamProxy.service.IStreamProxyPlayService;
import com.cdzeroly.wvp.streamProxy.service.IStreamProxyService;
import com.cdzeroly.wvp.vmanager.bean.ResourceBaseInfo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频代理业务
 * @author MGARY
 */
@Slf4j
@Service
@AllArgsConstructor
public class StreamProxyServiceImpl implements IStreamProxyService {

    private final   StreamProxyMapper streamProxyMapper;

    private final   IRedisCatchStorage redisCatchStorage;

    private final   UserSetting userSetting;

    private final   IStreamProxyPlayService playService;

    private final   IMediaServerService mediaServerService;

    private final   IGbChannelService gbChannelService;

    /**
     * 流到来的处理
     */
    @Async("taskExecutor")
    @Transactional
    @EventListener
    public void onApplicationEvent(MediaArrivalEvent event) {
        if ("rtsp".equals(event.getSchema())) {
            streamChangeHandler(event.getApp(), event.getStream(), event.getMediaServer().getId(), true);
        }
    }

    /**
     * 流离开的处理
     */
    @Async("taskExecutor")
    @EventListener
    @Transactional
    public void onApplicationEvent(MediaDepartureEvent event) {
        if ("rtsp".equals(event.getSchema())) {
            streamChangeHandler(event.getApp(), event.getStream(), event.getMediaServer().getId(), false);
        }
    }

    /**
     * 流未找到的处理
     */
    @Async("taskExecutor")
    @EventListener
    public void onApplicationEvent(MediaNotFoundEvent event) {
        if ("rtp".equals(event.getApp())) {
            return;
        }
        // 拉流代理
        StreamProxyVo streamProxyByAppAndStream = getStreamProxyByAppAndStream(event.getApp(), event.getStream());
        if (streamProxyByAppAndStream != null && streamProxyByAppAndStream.isEnableDisableNoneReader()) {
            startByAppAndStream(event.getApp(), event.getStream());
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
    @Transactional
    public StreamInfo save(StreamProxyParam param) {
        // 兼容旧接口
        StreamProxyVo streamProxyVo = getStreamProxyByAppAndStream(param.getApp(), param.getStream());
        StreamProxy streamProxy = MapstructUtils.convert(streamProxyVo, StreamProxy.class);
        if (streamProxyVo != null && streamProxyVo.getPulling() != null && streamProxyVo.getPulling()) {
            playService.stopProxy(streamProxy);
        }
        if ("auto".equals(param.getMediaServerId())) {
            param.setMediaServerId(null);
        }
        StreamProxyBo streamProxyBo = param.buildStreamProxy();

        if (streamProxyVo == null) {
            add(streamProxyBo);
        } else {
            try {
                playService.stopProxy(streamProxy);
            } catch (ServiceException ignored) {
            }
            streamProxyMapper.deleteById(streamProxyVo.getId());
            add(streamProxyBo);
        }

        if (param.isEnable()) {
            return playService.startProxy(MapstructUtils.convert(streamProxyBo, StreamProxy.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void add(StreamProxyBo proxyBo) {
        StreamProxyVo streamProxyInDb = streamProxyMapper.selectOneByAppAndStream(proxyBo.getApp(), proxyBo.getStream());
        if (streamProxyInDb != null) {
            throw new ServiceException( "APP+STREAM已经存在");
        }
        if (proxyBo.getGbDeviceId() != null) {
            gbChannelService.add(proxyBo.buildCommonGBChannel());
        }
        StreamProxy streamProxy = MapstructUtils.convert(proxyBo, StreamProxy.class);
        streamProxyMapper.insert(streamProxy);
    }

    @Override
    public void delete(int id) {
        StreamProxyVo streamProxy = getStreamProxy(id);
        if (streamProxy == null) {
            throw new ServiceException( "代理不存在");
        }
        delete(streamProxy);
    }

    private void delete(StreamProxyVo streamProxyVo) {
        Assert.notNull(streamProxyVo, "代理不可为NULL");
        if (streamProxyVo.getPulling() != null && streamProxyVo.getPulling()) {
            StreamProxy streamProxy = MapstructUtils.convert(streamProxyVo, StreamProxy.class);
            playService.stopProxy(streamProxy);
        }
        if (streamProxyVo.getGbId() > 0) {
            gbChannelService.delete(streamProxyVo.getGbId());
        }
        streamProxyMapper.deleteById(streamProxyVo.getId());
    }

    @Override
    @Transactional
    public void delteByAppAndStream(String app, String stream) {
        StreamProxyVo streamProxyVo = streamProxyMapper.selectOneByAppAndStream(app, stream);
        if (streamProxyVo == null) {
            throw new ServiceException( "代理不存在");
        }
        delete(streamProxyVo);
    }

    /**
     * 更新代理流
     */
    @Override
    public boolean update(StreamProxyBo streamProxyBo) {
        StreamProxyVo streamProxyVo = streamProxyMapper.select(streamProxyBo.getId());
        if (streamProxyVo == null) {
            throw new ServiceException( "代理不存在");
        }
        StreamProxy streamProxy = MapstructUtils.convert(streamProxyBo, StreamProxy.class);
        boolean updateResult = streamProxyMapper.insertOrUpdate(streamProxy);

        if (updateResult && !ObjectUtils.isEmpty(streamProxyBo.getGbDeviceId())) {
            if (streamProxyBo.getGbId() > 0) {
                gbChannelService.update(streamProxyBo.buildCommonGBChannel());
            } else {
                gbChannelService.add(streamProxyBo.buildCommonGBChannel());
            }
        }
        return true;
    }

    @Override
    public TableDataInfo<StreamProxyVo> getAll(PageQuery pageQuery, String query, Boolean pulling, String mediaServerId) {
        if (query != null) {
            query = query.replaceAll("/", "//")
                    .replaceAll("%", "/%")
                    .replaceAll("_", "/_");
        }
        Page<StreamProxy> build = pageQuery.build();
        List<StreamProxyVo> all = streamProxyMapper.selectAll(build,query, pulling, mediaServerId);
           return  TableDataInfo.build(all);
    }


    @Override
    public boolean startByAppAndStream(String app, String stream) {
        StreamProxyVo streamProxyVo = streamProxyMapper.selectOneByAppAndStream(app, stream);
        if (streamProxyVo == null) {
            throw new ServiceException("代理信息未找到");
        }
        StreamProxy streamProxy = MapstructUtils.convert(streamProxyVo, StreamProxy.class);
        StreamInfo streamInfo = playService.startProxy(streamProxy);
        return streamInfo != null;
    }

    @Override
    public void stopByAppAndStream(String app, String stream) {
        StreamProxyVo streamProxyVo = streamProxyMapper.selectOneByAppAndStream(app, stream);
        if (streamProxyVo == null) {
            throw new ServiceException("代理信息未找到");
        }
        StreamProxy streamProxy = MapstructUtils.convert(streamProxyVo, StreamProxy.class);
        playService.stopProxy(streamProxy);
    }


    @Override
    public Map<String, String> getFFmpegCMDs(MediaServer mediaServer) {
        return mediaServerService.getFFmpegCMDs(mediaServer);
    }


    @Override
    public StreamProxyVo getStreamProxyByAppAndStream(String app, String stream) {

        return streamProxyMapper.selectOneByAppAndStream(app, stream);
    }

    @Override
    @Transactional
    public void zlmServerOnline(MediaServer mediaServer) {
        if (mediaServer == null) {
            return;
        }
        // 这里主要是控制数据库/redis缓存/以及zlm中存在的代理流 三者状态一致。以数据库中数据为根本
        redisCatchStorage.removeStream(mediaServer.getId(), "PULL");

        List<StreamProxyVo> streamProxyVos = streamProxyMapper.selectForPushingInMediaServer(mediaServer.getId(), true);
        if (streamProxyVos.isEmpty()) {
            return;
        }
        Map<String, StreamProxyVo> streamProxyVoMap = new HashMap<>();
        for (StreamProxyVo streamProxyVo : streamProxyVos) {
            streamProxyVoMap.put(streamProxyVo.getApp() + "_" + streamProxyVo.getStream(), streamProxyVo);
        }

        List<StreamInfo> streamInfoList = mediaServerService.getMediaList(mediaServer, null, null, null);

        List<CommonGBChannel> channelListForOnline = new ArrayList<>();
        for (StreamInfo streamInfo : streamInfoList) {
            String key = streamInfo.getApp() + streamInfo.getStream();
            StreamProxyVo streamProxy = streamProxyVoMap.get(key);
            if (streamProxy == null) {
                // 流媒体存在，数据库中不存在
                continue;
            }
            if (streamInfo.getOriginType() == OriginType.PULL.ordinal()
                    || streamInfo.getOriginType() == OriginType.FFMPEG_PULL.ordinal()) {
                if (streamProxyVoMap.get(key) != null) {
                    redisCatchStorage.addStream(mediaServer, "pull", streamInfo.getApp(), streamInfo.getStream(), streamInfo.getMediaInfo());
                    if ("OFF".equalsIgnoreCase(streamProxy.getGbStatus()) && streamProxy.getGbId() > 0) {
                        streamProxy.setGbStatus("ON");
                        channelListForOnline.add(streamProxy.buildCommonGBChannel());
                    }
                    streamProxyVoMap.remove(key);
                }
            }
        }

        if (!channelListForOnline.isEmpty()) {
            gbChannelService.online(channelListForOnline);
        }
        List<CommonGBChannel> channelListForOffline = new ArrayList<>();
        List<StreamProxyVo> streamProxiesForRemove = new ArrayList<>();
        if (!streamProxyVoMap.isEmpty()) {
            for (StreamProxyVo streamProxy : streamProxyVoMap.values()) {
                if ("ON".equalsIgnoreCase(streamProxy.getGbStatus()) && streamProxy.getGbId() > 0) {
                    streamProxy.setGbStatus("OFF");
                    channelListForOffline.add(streamProxy.buildCommonGBChannel());
                }
                // 移除开启了无人观看自动移除的流
                if (streamProxy.getGbDeviceId() == null && streamProxy.isEnableRemoveNoneReader()) {
                    streamProxiesForRemove.add(streamProxy);
                    streamProxyVoMap.remove(streamProxy.getApp() + streamProxy.getStream());
                }
            }
        }
        if (!channelListForOffline.isEmpty()) {
            gbChannelService.offline(channelListForOffline);
        }
        if (!streamProxiesForRemove.isEmpty()) {
            List<Integer> ids = streamProxiesForRemove.stream().map(StreamProxyVo::getId).toList();
            streamProxyMapper.deleteByIds(ids);
        }

        if (!streamProxyVoMap.isEmpty()) {
            for (StreamProxyVo streamProxy : streamProxyVoMap.values()) {
                streamProxyMapper.offline(streamProxy.getId());
            }
        }
    }

    @Override
    public void zlmServerOffline(MediaServer mediaServer) {
        List<StreamProxyVo> streamProxyVos = streamProxyMapper.selectForPushingInMediaServer(mediaServer.getId(), true);

        // 清理redis相关的缓存
        redisCatchStorage.removeStream(mediaServer.getId(), "PULL");

        if (streamProxyVos.isEmpty()) {
            return;
        }
        List<StreamProxyVo> streamProxiesForRemove = new ArrayList<>();
        List<StreamProxyVo> streamProxiesForSendMessage = new ArrayList<>();
        List<CommonGBChannel> channelListForOffline = new ArrayList<>();

        for (StreamProxyVo streamProxy : streamProxyVos) {
            if (streamProxy.getGbId() > 0 && "ON".equalsIgnoreCase(streamProxy.getGbStatus())) {
                channelListForOffline.add(streamProxy.buildCommonGBChannel());
            }
            if (streamProxy.getGbId() == 0 && streamProxy.isEnableRemoveNoneReader()) {
                streamProxiesForRemove.add(streamProxy);
            }
            if ("ON".equalsIgnoreCase(streamProxy.getGbStatus())) {
                streamProxiesForSendMessage.add(streamProxy);
            }
        }
        if (!streamProxiesForRemove.isEmpty()) {
            // 移除开启了无人观看自动移除的流
            List<Integer> ids = streamProxiesForRemove.stream().map(StreamProxyVo::getId).toList();
            streamProxyMapper.deleteByIds(ids);
        }
        if (!streamProxiesForRemove.isEmpty()) {
            // 修改国标关联的国标通道的状态
            gbChannelService.offline(channelListForOffline);
        }
        if (!streamProxiesForSendMessage.isEmpty()) {
            for (StreamProxyVo streamProxy : streamProxiesForSendMessage) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("serverId", userSetting.getServerId());
                jsonObject.put("app", streamProxy.getApp());
                jsonObject.put("stream", streamProxy.getStream());
                jsonObject.put("register", false);
                jsonObject.put("mediaServerId", mediaServer);
                redisCatchStorage.sendStreamChangeMsg("pull", jsonObject);
            }
        }
    }

    @Transactional
    public void streamChangeHandler(String app, String stream, String mediaServerId, boolean status) {
        // 状态变化时推送到国标上级
        StreamProxyVo streamProxyVo = streamProxyMapper.selectOneByAppAndStream(app, stream);
        if (streamProxyVo == null) {
            return;
        }
        streamProxyVo.setPulling(status);
        if (!mediaServerId.equals(streamProxyVo.getMediaServerId())) {
            streamProxyVo.setMediaServerId(mediaServerId);
        }
        StreamProxy streamProxy = MapstructUtils.convert(streamProxyVo, StreamProxy.class);
        streamProxyMapper.insertOrUpdate(streamProxy);
        streamProxyVo.setGbStatus(status ? "ON" : "OFF");
        if (streamProxyVo.getGbId() > 0) {
            if (status) {
                gbChannelService.online(streamProxyVo.buildCommonGBChannel());
            } else {
                gbChannelService.offline(streamProxyVo.buildCommonGBChannel());
            }
        }
    }

    @Override
    public ResourceBaseInfo getOverview() {

        int total = streamProxyMapper.count().intValue();
        int online = streamProxyMapper.getOnline();

        return new ResourceBaseInfo(total, online);
    }

    @Override
    public StreamProxyVo getStreamProxy(int id) {
        return streamProxyMapper.select(id);
    }

}
