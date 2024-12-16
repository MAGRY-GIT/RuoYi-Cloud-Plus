package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.domian.DeviceChannel;
import com.cdzeroly.wvp.gb28181.domian.MobilePosition;
import com.cdzeroly.wvp.gb28181.domian.bo.ChannelReduce;
import com.cdzeroly.wvp.vmanager.bean.ResourceBaseInfo;
import com.cdzeroly.wvp.gb28181.domian.dto.DeviceChannelExtend;


import java.util.List;

/**
 * 国标通道业务类
 * @author lin
 */
public interface IDeviceChannelService {

    /**
     * 批量添加设备通道
     */
    int updateChannels(Device device, List<DeviceChannel> channels);

    /**
     * 获取统计信息
     * @return
     */
    ResourceBaseInfo getOverview();

    /**
     * 查询所有未分配的通道
     * @param platformId
     * @return
     */
    List<ChannelReduce> queryAllChannelList(String platformId);

    TableDataInfo<ChannelReduce> queryAllChannelList(PageQuery pageQuery, String query, Boolean online, Boolean channelType, String platformId, String catalogId);

    /**
     * 查询通道所属的设备
     */
    List<Device> getDeviceByChannelId(String channelId);

    /**
     * 批量删除通道
     * @param deleteChannelList 待删除的通道列表
     */
    int deleteChannelsForNotify(List<DeviceChannel> deleteChannelList);

    int updateChannelsStatus(List<DeviceChannel> channels);

    /**
     *  获取一个通道
     */
    DeviceChannel getOne(String deviceId, String channelId);

    DeviceChannel getOneForSource(String deviceId, String channelId);

    /**
     * 直接批量更新通道
     */
    void batchUpdateChannelForNotify(List<DeviceChannel> channels);

    /**
     * 直接批量添加
     */
    void batchAddChannel(List<DeviceChannel> deviceChannels);

    /**
     * 修改通道的码流类型
     */
    void updateChannelStreamIdentification(DeviceChannel channel);

    List<DeviceChannel> queryChaneListByDeviceId(String deviceId);

    void updateChannelGPS(Device device, DeviceChannel deviceChannel, MobilePosition mobilePosition);

    void startPlay(Integer channelId, String stream);

    void stopPlay(Integer channelId);

    void batchUpdateChannelGPS(List<DeviceChannel> channelList);

    void batchAddMobilePosition(List<MobilePosition> addMobilePositionList);

    void online(DeviceChannel channel);

    void offline(DeviceChannel channel);

    void delete(DeviceChannel channel);

    void cleanChannelsForDevice(int deviceId);

    boolean resetChannels(int deviceDbId, List<DeviceChannel> deviceChannels);

    TableDataInfo<DeviceChannel> getSubChannels(int deviceDbId, String channelId, String query, Boolean channelType, Boolean online, PageQuery pageQuery);

    List<DeviceChannelExtend> queryChannelExtendsByDeviceId(String deviceId, List<String> channelIds, Boolean online);

    TableDataInfo<DeviceChannel> queryChannelsByDeviceId(String deviceId, String query, Boolean channelType, Boolean online, PageQuery pageQuery);


    List<Device> queryDeviceWithAsMessageChannel();

    DeviceChannel getRawChannel(int id);

    DeviceChannel getOneById(Integer channelId);

    DeviceChannel getOneForSourceById(Integer channelId);

    DeviceChannel getBroadcastChannel(int deviceDbId);

    void changeAudio(Integer channelId, Boolean audio);

    void updateChannelStatus(DeviceChannel channel);

    void addChannel(DeviceChannel channel);

    void updateChannelForNotify(DeviceChannel channel);

    DeviceChannel getOneForSource(int deviceDbId, String channelId);

    DeviceChannel getOneBySourceId(int deviceDbId, String channelId);

    List<DeviceChannel> queryChaneListByDeviceDbId(Integer deviceDbId);

    List<Integer> queryChaneIdListByDeviceDbIds(List<Integer> deviceDbId);
}
