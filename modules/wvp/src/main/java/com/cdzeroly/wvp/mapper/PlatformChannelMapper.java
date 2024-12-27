package com.cdzeroly.wvp.mapper;

import com.cdzeroly.wvp.domain.Group;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.domian.Platform;
import com.cdzeroly.wvp.domain.Region;
import com.cdzeroly.wvp.gb28181.domian.bean.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author MAGRY
 */
@Mapper
@Repository
public interface PlatformChannelMapper   {



    int addChannels(@Param("platformId") Long platformId, @Param("channelList") List<CommonGBChannel> channelList);


    int delChannelForDeviceId(String deviceId);


    List<Device> queryDeviceByPlatformIdAndChannelId(@Param("platformId") String platformId, @Param("channelId") String channelId);


    List<Platform> queryPlatFormListForGBWithGBId(@Param("channelId") Long channelId, List<String> platforms);

    List<Device> queryDeviceInfoByPlatformIdAndChannelId(@Param("platformId") String platformId, @Param("channelId") String channelId);

    List<Long> queryParentPlatformByChannelId(@Param("channelId") String channelId);


    List<PlatformChannel> queryForPlatformForWebList(@Param("platformId") Long platformId, @Param("query") String query,
                                                     @Param("dataType") Integer dataType, @Param("online") Boolean online,
                                                     @Param("hasShare") Boolean hasShare);



    List<CommonGBChannel> queryOneWithPlatform(@Param("platformId") Long platformId, @Param("channelDeviceId") String channelDeviceId);



    List<CommonGBChannel> queryNotShare(@Param("platformId") Long platformId, List<Long> channelIds);


    List<CommonGBChannel> queryShare(@Param("platformId") Long platformId, List<Long> channelIds);


    int removeChannelsWithPlatform(@Param("platformId") Long platformId, List<CommonGBChannel> channelList);


    int removeChannels(List<CommonGBChannel> channelList);


    int addPlatformGroup(Collection<Group> groupListNotShare, @Param("platformId") Long platformId);


    int addPlatformRegion(List<Region> regionListNotShare, @Param("platformId") Long platformId);


    int removePlatformGroup(List<Group> groupList, @Param("platformId") Long platformId);


    void removePlatformGroupById(@Param("id") Long id, @Param("platformId") Long platformId);


    void removePlatformRegionById(@Param("id") Long id, @Param("platformId") Long platformId);


    Set<Group> queryShareChildrenGroup(@Param("parentId") Long parentId, @Param("platformId") Long platformId);


    Set<Region> queryShareChildrenRegion(@Param("parentId") String parentId, @Param("platformId") Long platformId);


    Set<Group> queryShareParentGroupByGroupSet(Set<Group> groupSet, @Param("platformId") Long platformId);


    Set<Region> queryShareParentRegionByRegionSet(Set<Region> regionSet, @Param("platformId") Long platformId);


    List<Platform> queryPlatFormListByChannelList(Collection<Long> ids);


    List<Platform> queryPlatFormListByChannelId(@Param("channelId") Long channelId);


    void removeChannelsByPlatformId(@Param("platformId") Long platformId);


    void removePlatformGroupsByPlatformId(@Param("platformId") Long platformId);


    void removePlatformRegionByPlatformId(@Param("platformId") Long platformId);


    void updateCustomChannel(PlatformChannel channel);



    CommonGBChannel queryShareChannel(@Param("platformId") Long platformId, @Param("gbId") Long gbId);



    Set<Group> queryShareGroup(@Param("platformId") Long platformId);


    Set<Region> queryShareRegion(Long id);
}
