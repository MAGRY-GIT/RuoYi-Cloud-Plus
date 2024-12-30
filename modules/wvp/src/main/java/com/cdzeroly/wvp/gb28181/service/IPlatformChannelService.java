package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.domain.Group;
import com.cdzeroly.wvp.gb28181.domian.CommonGbChannel;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.domain.Region;
import com.cdzeroly.wvp.gb28181.domian.bean.*;


import java.util.List;

/**
 * 平台关联通道管理
 * @author lin
 */
public interface IPlatformChannelService {

    TableDataInfo<PlatformChannel> queryChannelList(PageQuery pageQuery, String query, Integer channelType, Boolean online, Long platformId, Boolean hasShare);

    int addAllChannel(Long platformId);

    int removeAllChannel(Long platformId);

    int addChannels(Long platformId, List<Long> channelIds);

    int removeChannels(Long platformId, List<Long> channelIds);

    void removeChannels(List<Long> ids);

    void removeChannel(Long gbId);

    List<CommonGbChannel> queryByPlatform(Platform platform);

    void pushChannel(Long platformId);

    void addChannelByDevice(Long platformId, List<Long> deviceIds);

    void removeChannelByDevice(Long platformId, List<Long> deviceIds);

    void updateCustomChannel(PlatformChannel channel);

    void checkGroupRemove(List<CommonGbChannel> channelList, List<Group> groups);

    void checkGroupAdd(List<CommonGbChannel> channelList);

    List<Platform> queryPlatFormListByChannelDeviceId(Long channelId, List<String> platforms);

    CommonGbChannel queryChannelByPlatformIdAndChannelId(Long platformId, Long channelId);

    void checkRegionAdd(List<CommonGbChannel> channelList);

    void checkRegionRemove(List<CommonGbChannel> channelList, List<Region> regionList);
}
