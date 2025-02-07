package com.cdzeroly.wvp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.domain.vo.DeviceChannelVo;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.DeviceChannel;
import com.cdzeroly.wvp.domain.bo.ChannelReduce;
import com.cdzeroly.wvp.mapper.provider.DeviceChannelProvider;
import com.cdzeroly.wvp.domain.bean.GPSMsgInfo;
import com.cdzeroly.wvp.domain.vo.DeviceChannelExtendVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用于存储设备通道信息
 * @author MGARY
 */
@Mapper
@Repository
public interface DeviceChannelMapper extends BaseMapperPlus<DeviceChannel, DeviceChannelVo> {



    @SelectProvider(type = DeviceChannelProvider.class, method = "queryChannels")
    List<DeviceChannel> queryChannels(@Param("page") Page<DeviceChannel> page,@Param("dataDeviceId") Long dataDeviceId, @Param("civilCode") String civilCode,
                                      @Param("businessGroupId") String businessGroupId, @Param("parentChannelId") String parentChannelId,
                                      @Param("query") String query, @Param("hasSubChannel") Boolean hasSubChannel,
                                      @Param("online") Boolean online, @Param("channelIds") List<String> channelIds);

    List<DeviceChannel> queryChannelsByDeviceDbId(@Param("dataDeviceId") Long dataDeviceId,@Param("dataDeviceId") Integer dataType);



    List<Long> queryChaneIdListByDeviceDbIds(List<Integer> deviceDbIds);



    int cleanChannelsByDeviceId(@Param("dataDeviceId") Long dataDeviceId);


    int del(@Param("id") Long id);


    List<DeviceChannelExtendVo> queryChannelsWithDeviceInfo( @Param("deviceId") String deviceId, @Param("parentChannelId") String parentChannelId, @Param("query") String query, @Param("hasSubChannel") Boolean hasSubChannel, @Param("online") Boolean online, @Param("channelIds") List<String> channelIds);




    void startPlay(@Param("channelId") Long channelId, @Param("streamId") String streamId);



    void updateAllChannelStreamIdentification(@Param("streamIdentification") String streamIdentification);


    Page<ChannelReduce> queryChannelListInAll( @Param("page") Page<ChannelReduce> pageQuery , @Param("query") String query, @Param("online") Boolean online, @Param("hasSubChannel") Boolean hasSubChannel, @Param("platformId") String platformId, @Param("catalogId") String catalogId);


    void offline(@Param("id") Long id);


    int batchAdd(@Param("addChannels") List<DeviceChannel> addChannels);


    void online(@Param("id") Long id);


    int batchUpdate(List<DeviceChannel> updateChannels);



    int batchUpdateForNotify(List<DeviceChannel> updateChannels);

    /**
     * 更新频道子计数
     * @param dataDeviceId
     * @param channelId
     * @return
     */
    int updateChannelSubCount(@Param("dataDeviceId") Long dataDeviceId, @Param("channelId") String channelId);


    /**
     * 更新位置
     * @param deviceChannel
     * @return
     */
    int updatePosition(DeviceChannel deviceChannel);

    /**
     * 查询所有频道以进行刷新
     * @param dataDeviceId
     * @return
     */
    List<DeviceChannel> queryAllChannelsForRefresh(@Param("dataDeviceId") Long dataDeviceId);

    /**
     *  按通道设备 ID 获取设备
     * @param channelId
     * @return
     */
    List<Device> getDeviceByChannelDeviceId(@Param("channelId") String channelId);



    /**
     * 批删除
     * @param deleteChannelList
     * @return
     */
    int batchDel(List<DeviceChannel> deleteChannelList);

    /**
     * 批量更新状态
     * @param channels
     * @return
     */
    int batchUpdateStatus(List<DeviceChannel> channels);

    /**
     * 获取在线计数
     * @return
     */
    int getOnlineCount();



    /**
     * 更新频道流标识
     * @param channel
     */
    void updateChannelStreamIdentification(DeviceChannel channel);

    /**
     * 批量更新位置
     * @param channelList
     */
    void batchUpdatePosition(List<DeviceChannel> channelList);

    @SelectProvider(type = DeviceChannelProvider.class, method = "getOne")
    DeviceChannel getOne(@Param("id") Long id);

    /**
     * 为 Source 获取一个
     * @param id
     * @return
     */
    DeviceChannel getOneForSource(@Param("id") Long id);



    DeviceChannel getOneByDeviceId(@Param("dataDeviceId") Integer dataType,@Param("dataDeviceId") Long dataDeviceId, @Param("channelId") String channelId);


    /**
     * 按源的设备 ID 获取一个
     * @param dataDeviceId
     * @param channelId
     * @return
     */
    DeviceChannel getOneByDeviceIdForSource(@Param("dataDeviceId") Long dataDeviceId, @Param("channelId") String channelId);

    /**
     * 按 ID 停止播放
     * @param channelId
     */
    void stopPlayById(@Param("channelId") Long channelId);

    /**
     * 更改音频
     * @param channelId
     * @param audio
     */

    void changeAudio(@Param("channelId") Long channelId, @Param("audio") boolean audio);

    /**
     * 更新流 GPS
     * @param gpsMsgInfoList
     */
    void updateStreamGPS(List<GPSMsgInfo> gpsMsgInfoList);

    /**
     * 更新状态
     * @param channel
     */
    void updateStatus(DeviceChannel channel);


    /**
     * 更新通知频道
     * @param channel
     */
    void updateChannelForNotify(DeviceChannel channel);


    /**
     * 按源通道 ID 获取一个
     * @param dataDeviceId
     * @param channelId
     * @return
     */
    DeviceChannel getOneBySourceChannelId(@Param("dataDeviceId") Long dataDeviceId, @Param("channelId") String channelId);


}
