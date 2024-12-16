package com.cdzeroly.wvp.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.core.utils.CollectionUtil;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.conf.exception.ControllerException;
import com.cdzeroly.wvp.gb28181.service.ICloudRecordService;
import com.cdzeroly.wvp.media.domian.MediaServer;
import com.cdzeroly.wvp.media.event.media.MediaRecordMp4Event;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.media.zlm.AssistRESTfulUtils;
import com.cdzeroly.wvp.media.zlm.dto.StreamAuthorityInfo;
import com.cdzeroly.wvp.service.domian.bean.CloudRecordItem;
import com.cdzeroly.wvp.service.domian.bean.DownloadFileInfo;
import com.cdzeroly.wvp.service.domian.bo.CloudRecordItemBo;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import com.cdzeroly.wvp.storager.mapper.CloudRecordServiceMapper;
import com.cdzeroly.wvp.utils.CloudRecordUtils;
import com.cdzeroly.wvp.vmanager.bean.ErrorCode;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MGARY
 */
@Slf4j
@Service
public class CloudRecordServiceImpl implements ICloudRecordService {

    @Autowired
    private CloudRecordServiceMapper cloudRecordServiceMapper;

    @Autowired
    private IMediaServerService mediaServerService;

    @Autowired
    private IRedisCatchStorage redisCatchStorage;

    @Autowired
    private AssistRESTfulUtils assistRESTfulUtils;

    @Override
    public TableDataInfo<CloudRecordItem> getList(CloudRecordItemBo bo, PageQuery pageQuery) {
        Wrapper<CloudRecordItem> cloudRecordItemWrapper = buildQueryWrapper(bo);
        Page<CloudRecordItem> page = cloudRecordServiceMapper.selectPage(pageQuery.build(), cloudRecordItemWrapper);
        return TableDataInfo.build(page);
    }


    @Override
    public List<String> getDateList(String app, String stream, int year, int month, List<MediaServer> mediaServerItems) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate;
        if (month == 12) {
            endDate = LocalDate.of(year + 1, 1, 1);
        } else {
            endDate = LocalDate.of(year, month + 1, 1);
        }
        long startTimeStamp = startDate.atStartOfDay().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        long endTimeStamp = endDate.atStartOfDay().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        CloudRecordItemBo cloudRecordItemBo = new CloudRecordItemBo();
        cloudRecordItemBo.setApp(app);
        cloudRecordItemBo.setStream(stream);
        cloudRecordItemBo.setStartTime(DateUtil.date(startTimeStamp));
        cloudRecordItemBo.setEndTime(DateUtil.date(endTimeStamp));
        cloudRecordItemBo.setMediaServerIds(CollectionUtil.safeStream(mediaServerItems).map(MediaServer::getId).toList());

        List<CloudRecordItem> cloudRecordItemList = this.getAllList(cloudRecordItemBo);
        ;
        return CollectionUtil.safeStream(cloudRecordItemList).map(cloudRecordItem -> DateUtil.formatDateTime(cloudRecordItem.getStartTime())).toList();

    }

    @Async("taskExecutor")
    @EventListener
    public void onApplicationEvent(MediaRecordMp4Event event) {
        CloudRecordItem cloudRecordItem = CloudRecordItem.getInstance(event);
        if (ObjectUtils.isEmpty(cloudRecordItem.getCallId())) {
            StreamAuthorityInfo streamAuthorityInfo = redisCatchStorage.getStreamAuthorityInfo(event.getApp(), event.getStream());
            if (streamAuthorityInfo != null) {
                cloudRecordItem.setCallId(streamAuthorityInfo.getCallId());
            }
        }
        log.info("[添加录像记录] {}/{}, callId: {}, 内容：{}", event.getApp(), event.getStream(), cloudRecordItem.getCallId(), event.getRecordInfo());
        cloudRecordServiceMapper.insert(cloudRecordItem);
    }

    @Override
    public String addTask(String app, String stream, MediaServer mediaServerItem, String startTime, String endTime, String callId, String remoteHost, boolean filterMediaServer) {
        // 参数校验
        Assert.notNull(app, "应用名为NULL");
        Assert.notNull(stream, "流ID为NULL");
        if (mediaServerItem.getRecordAssistPort() == 0) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "为配置Assist服务");
        }


        List<MediaServer> mediaServers = new ArrayList<>();
        mediaServers.add(mediaServerItem);

        LambdaQueryWrapper<CloudRecordItem> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StrUtil.isNotEmpty(app), CloudRecordItem::getApp, app);
        wrapper.eq(StrUtil.isNotEmpty(stream), CloudRecordItem::getStream, stream);
        wrapper.eq(StrUtil.isNotEmpty(callId), CloudRecordItem::getCallId, callId);
        wrapper.ge(ObjectUtil.isNotNull(startTime), CloudRecordItem::getStartTime, startTime);
        wrapper.le(ObjectUtil.isNotNull(endTime), CloudRecordItem::getEndTime, endTime);

        List<String> mediaServerIds = filterMediaServer ? mediaServers.stream().map(MediaServer::getId).toList() : Lists.newArrayList();
        wrapper.in(CollUtil.isNotEmpty(mediaServerIds), CloudRecordItem::getMediaServerId, mediaServerIds);

        // 检索相关的录像文件
        List<String> filePathList = cloudRecordServiceMapper.selectList(wrapper).stream().map(CloudRecordItem::getFilePath).collect(Collectors.toList());
        if (CollUtil.isEmpty(mediaServerIds)) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "未检索到视频文件");
        }
        JSONObject result = assistRESTfulUtils.addTask(mediaServerItem, app, stream, startTime, endTime, callId, filePathList, remoteHost);
        if (result.getInteger("code") != 0) {
            throw new ControllerException(result.getInteger("code"), result.getString("msg"));
        }
        return result.getString("data");
    }

    @Override
    public JSONArray queryTask(String app, String stream, String callId, String taskId, String mediaServerId, Boolean isEnd, String scheme) {
        MediaServer mediaServerItem = null;
        if (mediaServerId == null) {
            mediaServerItem = mediaServerService.getDefaultMediaServer();
        } else {
            mediaServerItem = mediaServerService.getOne(mediaServerId);
        }
        if (mediaServerItem == null) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "未找到可用的流媒体");
        }

        JSONObject result = assistRESTfulUtils.queryTaskList(mediaServerItem, app, stream, callId, taskId, isEnd, scheme);
        if (result == null || result.getInteger("code") != 0) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), result == null ? "查询任务列表失败" : result.getString("msg"));
        }
        return result.getJSONArray("data");
    }

    @Override
    public int changeCollect(boolean result, String app, String stream, String mediaServerId, String startTime, String endTime, String callId) {


        List<MediaServer> mediaServerItems;
        if (!ObjectUtils.isEmpty(mediaServerId)) {
            mediaServerItems = new ArrayList<>();
            MediaServer mediaServerItem = mediaServerService.getOne(mediaServerId);
            if (mediaServerItem == null) {
                throw new ControllerException(ErrorCode.ERROR100.getCode(), "未找到流媒体: " + mediaServerId);
            }
            mediaServerItems.add(mediaServerItem);
        } else {
            mediaServerItems = null;
        }
        CloudRecordItemBo cloudRecordItemBo = new CloudRecordItemBo();
        cloudRecordItemBo.setApp(app);
        cloudRecordItemBo.setStream(stream);
        cloudRecordItemBo.setStartTime(DateUtil.parseDateTime(startTime));
        cloudRecordItemBo.setEndTime(DateUtil.parseDateTime(endTime));
        cloudRecordItemBo.setCallId(callId);
        cloudRecordItemBo.setMediaServerIds(CollectionUtil.safeStream(mediaServerItems).map(MediaServer::getId).toList());

        List<CloudRecordItem> all = this.getAllList(cloudRecordItemBo);
        if (all.isEmpty()) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "未找到待收藏的视频");
        }
        int limitCount = 50;
        int resultCount = 0;
        if (all.size() > limitCount) {
            for (int i = 0; i < all.size(); i += limitCount) {
                int toIndex = i + limitCount;
                if (i + limitCount > all.size()) {
                    toIndex = all.size();
                }
                resultCount += this.updateCollectList(result, all.subList(i, toIndex));

            }
        } else {
            resultCount = this.updateCollectList(result, all);
        }
        return resultCount;
    }

    /**
     * 批量插入
     *
     * @param collect             条件
     * @param cloudRecordItemList 插入值
     * @return 插入条数
     */
    private int updateCollectList(boolean collect, List<CloudRecordItem> cloudRecordItemList) {
        LambdaUpdateWrapper<CloudRecordItem> wrapper = Wrappers.lambdaUpdate();
        List<String> filePaths = cloudRecordItemList.stream().map(CloudRecordItem::getFilePath).toList();
        wrapper.set(CloudRecordItem::getCollect, collect).in(CloudRecordItem::getFileName, filePaths);
        return cloudRecordServiceMapper.update(wrapper);
    }

    @Override
    public int changeCollectById(Integer recordId, boolean result) {
        QueryWrapper<CloudRecordItem> wrapper = Wrappers.query();
        wrapper.eq("collect", result);
        wrapper.eq("id", recordId);
        return cloudRecordServiceMapper.update(wrapper);
    }

    @Override
    public DownloadFileInfo getPlayUrlPath(Integer recordId) {
        CloudRecordItem recordItem = cloudRecordServiceMapper.selectById(recordId);
        if (recordItem == null) {
            throw new ControllerException(ErrorCode.ERROR400.getCode(), "资源不存在");
        }
        String filePath = recordItem.getFilePath();
        MediaServer mediaServerItem = mediaServerService.getOne(recordItem.getMediaServerId());
        return CloudRecordUtils.getDownloadFilePath(mediaServerItem, filePath);
    }

    @Override
    public List<CloudRecordItem> getAllList(CloudRecordItemBo bo) {
        Wrapper<CloudRecordItem> cloudRecordItemWrapper = buildQueryWrapper(bo);
        return cloudRecordServiceMapper.selectList(cloudRecordItemWrapper);
    }


    private Wrapper<CloudRecordItem> buildQueryWrapper(CloudRecordItemBo bo) {
        Map<String, Object> params = bo.getParams();
        QueryWrapper<CloudRecordItem> wrapper = Wrappers.query();
        wrapper.eq(StrUtil.isNotBlank(bo.getApp()), "app", bo.getApp()).eq(StrUtil.isNotBlank(bo.getStream()), "stream", bo.getStream()).ge(ObjectUtil.isNotNull(bo.getStartTime()), "end_time", bo.getStartTime()).le(ObjectUtil.isNotNull(bo.getEndTime()), "start_time", bo.getEndTime()).eq(StrUtil.isNotBlank(bo.getCallId()), "call_id", bo.getCallId()).in(CollUtil.isNotEmpty(bo.getMediaServerIds()), "media_server_id", bo.getMediaServerIds()).in(CollUtil.isNotEmpty(bo.getIds()), "id", bo.getIds()).eq(ObjectUtil.isNotNull(bo.getId()), "id", bo.getId()).and(StrUtil.isNotBlank(bo.getQuery()), w -> {
            w.like("app", bo.getQuery()).or().like("stream", bo.getQuery());
        }).orderByDesc("start_time");

        return wrapper;
    }
}
