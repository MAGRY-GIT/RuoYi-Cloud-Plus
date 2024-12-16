package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.gb28181.domian.Region;
import com.cdzeroly.wvp.gb28181.domian.bean.*;


import java.util.List;

/**
 * 平台关联通道管理
 * @author lin
 */
public interface IPlatformChannelService {

    TableDataInfo<PlatformChannel> queryChannelList(PageQuery pageQuery, String query, Integer channelType, Boolean online, Integer platformId, Boolean hasShare);

    int addAllChannel(Integer platformId);

    int removeAllChannel(Integer platformId);

    int addChannels(Integer platformId, List<Integer> channelIds);

    int removeChannels(Integer platformId, List<Integer> channelIds);

    void removeChannels(List<Integer> ids);

    void removeChannel(int gbId);

    List<CommonGBChannel> queryByPlatform(Platform platform);

    void pushChannel(Integer platformId);

    void addChannelByDevice(Integer platformId, List<Integer> deviceIds);

    void removeChannelByDevice(Integer platformId, List<Integer> deviceIds);

    void updateCustomChannel(PlatformChannel channel);

    void checkGroupRemove(List<CommonGBChannel> channelList, List<Group> groups);

    void checkGroupAdd(List<CommonGBChannel> channelList);

    List<Platform> queryPlatFormListByChannelDeviceId(Integer channelId, List<String> platforms);

    CommonGBChannel queryChannelByPlatformIdAndChannelId(Integer platformId, Integer channelId);

    void checkRegionAdd(List<CommonGBChannel> channelList);

    void checkRegionRemove(List<CommonGBChannel> channelList, List<Region> regionList);
}
