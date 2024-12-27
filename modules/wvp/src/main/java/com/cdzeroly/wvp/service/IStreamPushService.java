package com.cdzeroly.wvp.service;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.domain.bean.GPSMsgInfo;
import com.cdzeroly.wvp.domain.bean.StreamPushItemFromRedis;
import com.cdzeroly.wvp.domain.vo.StreamPushVo;
import com.cdzeroly.wvp.domain.ResourceBaseInfo;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lin
 */
public interface IStreamPushService {

    /**
     * 获取
     */
    TableDataInfo<StreamPushVo> getPushList(PageQuery pageQuery, String query, Boolean pushing, String mediaServerId);

    List<StreamPushVo> getPushList(String mediaSererId);

    StreamPushVo getPush(String app, String streamId);

    boolean stop(StreamPushVo streamPushVo);

    /**
     * 停止一路推流
     * @param app 应用名
     * @param stream 流ID
     */
    boolean stopByAppAndStream(String app, String stream);

    /**
     * 新的节点加入
     */
    void zlmServerOnline(MediaServer mediaServer);

    /**
     * 节点离线
     */
    void zlmServerOffline(MediaServer mediaServer);

    /**
     * 批量添加
     */
    void batchAdd(List<StreamPushVo> streamPushVoExcelDtoList);


    /**
     * 全部离线
     */
    void allOffline();

    /**
     * 推流离线
     */
    void offline(List<StreamPushItemFromRedis> offlineStreams);

    /**
     * 推流上线
     */
    void online(List<StreamPushItemFromRedis> onlineStreams);

    /**
     * 增加推流
     */
    boolean add(StreamPushVo stream);

    boolean update(StreamPushVo stream);

    /**
     * 获取全部的app+Streanm 用于判断推流列表是新增还是修改
     * @return
     */
    List<String> getAllAppAndStream();

    /**
     * 获取统计信息
     * @return
     */
    ResourceBaseInfo getOverview();

    Map<String, StreamPushVo> getAllAppAndStreamMap();

    Map<String, StreamPushVo> getAllGBId();

    void updateStatus(StreamPushVo push);

    void deleteByAppAndStream(String app, String stream);

    void updatePushStatus(StreamPushVo streamPushVo, boolean pushIng);

    void batchUpdate(List<StreamPushVo> streamPushVoItemForUpdate);

    int delete(Long id);

    void batchRemove(Set<Long> ids);

    void updateGPSFromGPSMsgInfo(List<GPSMsgInfo> gpsMsgInfoList);
}
