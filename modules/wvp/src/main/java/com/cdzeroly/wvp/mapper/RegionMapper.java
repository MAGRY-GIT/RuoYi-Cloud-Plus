package com.cdzeroly.wvp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.domain.vo.RegionVo;
import com.cdzeroly.wvp.domain.CommonGbChannel;
import com.cdzeroly.wvp.domain.Region;
import com.cdzeroly.wvp.gb28181.bean.RegionTree;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * @author MAGRY
 */
@Mapper
public interface RegionMapper extends BaseMapperPlus<Region, RegionVo> {


    List<Region> query(Page<Region> page, @Param("query") String query, @Param("parentId") String parentId);

    List<Region> getChildren(@Param("parentId") Long parentId);


    List<String> getUninitializedCivilCode();

    List<String> queryInList(Set<String> codes);




    List<RegionTree> queryForTree(@Param("query") String query, @Param("parentId") Integer parentId);


    List<Region> queryInRegionListByDeviceId(List<Region> regionList);

    List<CommonGbChannel> queryByPlatform(@Param("platformId") Long platformId);



    void updateParentId(List<Region> regionListForAdd);

    void updateChild(@Param("parentId") Long parentId, @Param("parentDeviceId") String parentDeviceId);

    Region queryByDeviceId(@Param("deviceId") String deviceId);

    Set<Region> queryParentInChannelList(Set<Region> regionSet);

    Set<Region> queryByChannelList(List<CommonGbChannel> channelList);

    Set<Region> queryNotShareRegionForPlatformByChannelList(List<CommonGbChannel> channelList, @Param("platformId") Long platformId);

    Set<Region> queryNotShareRegionForPlatformByRegionList(Set<Region> allRegion, @Param("platformId") Long platformId);

}
