package com.cdzeroly.wvp.gb28181.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.gb28181.domian.Region;
import com.cdzeroly.wvp.gb28181.domian.bean.RegionTree;
import com.cdzeroly.wvp.gb28181.domian.vo.RegionVo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * @author MAGRY
 */
@Mapper
public interface RegionMapper extends BaseMapperPlus<Region, RegionVo> {


    List<Region> query(Page<Region> page, @Param("query") String query, @Param("parentId") String parentId);

    List<Region> getChildren(@Param("parentId") Integer parentId);


    List<String> getUninitializedCivilCode();

    List<String> queryInList(Set<String> codes);




    List<RegionTree> queryForTree(@Param("query") String query, @Param("parentId") Integer parentId);


    List<Region> queryInRegionListByDeviceId(List<Region> regionList);

    List<CommonGBChannel> queryByPlatform(@Param("platformId") Integer platformId);



    void updateParentId(List<Region> regionListForAdd);

    void updateChild(@Param("parentId") int parentId, @Param("parentDeviceId") String parentDeviceId);

    Region queryByDeviceId(@Param("deviceId") String deviceId);

    Set<Region> queryParentInChannelList(Set<Region> regionSet);

    Set<Region> queryByChannelList(List<CommonGBChannel> channelList);

    Set<Region> queryNotShareRegionForPlatformByChannelList(List<CommonGBChannel> channelList, @Param("platformId") Integer platformId);

    Set<Region> queryNotShareRegionForPlatformByRegionList(Set<Region> allRegion, @Param("platformId") Integer platformId);

}
