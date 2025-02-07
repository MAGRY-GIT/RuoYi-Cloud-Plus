package com.cdzeroly.wvp.service;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.domain.bean.StreamProxyParam;
import com.cdzeroly.wvp.domain.bo.StreamProxyBo;
import com.cdzeroly.wvp.domain.vo.StreamProxyVo;
import com.cdzeroly.wvp.domain.ResourceBaseInfo;


import java.util.Map;

public interface IStreamProxyService {

    /**
     * 保存视频代理
     * @param param
     */
    StreamInfo save(StreamProxyParam param);

    /**
     * 分页查询
     *
     * @return
     */
    TableDataInfo<StreamProxyVo> getAll(PageQuery pageQuery, String query, Boolean pulling, String mediaServerId);

    /**
     * 删除视频代理
     * @param app
     * @param stream
     */
    void delteByAppAndStream(String app, String stream);

    /**
     * 启用视频代理
     * @param app
     * @param stream
     * @return
     */
    boolean startByAppAndStream(String app, String stream);

    /**
     * 停用用视频代理
     * @param app
     * @param stream
     */
    void stopByAppAndStream(String app, String stream);

    /**
     * 获取ffmpeg.cmd模板
     *
     */
    Map<String, String> getFFmpegCMDs(MediaServer mediaServerItem);

    /**
     * 根据app与stream获取streamProxy
     */
    StreamProxyVo getStreamProxyByAppAndStream(String app, String streamId);


    /**
     * 新的节点加入
     * @param mediaServer
     * @return
     */
    void zlmServerOnline(MediaServer mediaServer);

    /**
     * 节点离线
     * @param mediaServer 
     * @return
     */
    void zlmServerOffline(MediaServer mediaServer);

    /**
     * 更新代理流
     */
    boolean update(StreamProxyBo streamProxyItem);

    /**
     * 获取统计信息
     * @return
     */
    ResourceBaseInfo getOverview();

    void add(StreamProxyBo streamProxy);

    StreamProxyVo getStreamProxy(Long id);

    void delete(Long id);
}
