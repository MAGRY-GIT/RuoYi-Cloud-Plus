package com.cdzeroly.wvp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.wvp.domain.Group;
import com.cdzeroly.wvp.domain.bean.GroupTree;
import com.cdzeroly.wvp.gb28181.bean.RegionTree;
import com.cdzeroly.wvp.domain.vo.CommonGBChannelVo;
import com.cdzeroly.wvp.domain.CommonGbChannel;
import com.cdzeroly.wvp.domain.Region;
import com.cdzeroly.wvp.mapper.provider.ChannelProvider;
import com.cdzeroly.wvp.domain.vo.StreamPushVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author MAGRY
 */
@Mapper
@Repository
public interface CommonGbChannelMapper extends BaseMapperPlus<CommonGbChannel, CommonGBChannelVo> {


    @SelectProvider(type = ChannelProvider.class, method = "queryByDeviceId")
    CommonGbChannel queryByDeviceId(@Param("gbDeviceId") String gbDeviceId);



    @SelectProvider(type = ChannelProvider.class, method = "queryById")
    CommonGbChannel queryById(@Param("gbId") Long gbId);


    int updateStatusById(@Param("gbId") Long gbId, @Param("status") String status);


    int updateStatusForListById(List<CommonGbChannel> commonGbChannels, @Param("status") String status);

    @SelectProvider(type = ChannelProvider.class, method = "queryInListByStatus")
    List<CommonGbChannel> queryInListByStatus(List<CommonGbChannel> commonGbChannels, @Param("status") String status);




    int updateStatus(List<CommonGbChannel> commonGbChannels);


    void reset(@Param("id") Long id, @Param("dataType") Integer dataType, @Param("dataDeviceId") Long dataDeviceId, @Param("updateTime") String updateTime);


    @SelectProvider(type = ChannelProvider.class, method = "queryByIds")
    List<CommonGbChannel> queryByIds(Collection<Long> ids);


    @SelectProvider(type = ChannelProvider.class, method = "queryListByCivilCode")
    List<CommonGbChannel> queryListByCivilCode(PageQuery pageQuery, @Param("query") String query, @Param("online") Boolean online,
                                               @Param("dataType") Integer dataType, @Param("civilCode") String civilCode);




    @SelectProvider(type = ChannelProvider.class, method = "queryListByParentId")
    List<CommonGbChannel> queryListByParentId(PageQuery pageQuery, @Param("query") String query, @Param("online") Boolean online,
                                              @Param("dataType") Integer dataType, @Param("groupDeviceId") String groupDeviceId);




    List<RegionTree> queryForRegionTreeByCivilCode(@Param("query") String query, @Param("parentDeviceId") String parentDeviceId);


    int removeCivilCode(List<Region> allChildren);



    int updateRegion(@Param("civilCode") String civilCode, @Param("channelList") List<CommonGbChannel> channelList);



    int removeCivilCodeByChannels(List<CommonGbChannel> channelList);

    @SelectProvider(type = ChannelProvider.class, method = "queryByCivilCode")
    List<CommonGbChannel> queryByCivilCode(@Param("civilCode") String civilCode);

    @SelectProvider(type = ChannelProvider.class, method = "queryByGbDeviceIds")
    List<CommonGbChannel> queryByGbDeviceIds(@Param("dataType") Integer dataType, List<Integer> deviceIds);

    List<Long> queryByGbDeviceIdsForIds(@Param("dataType") Integer dataType, List<Long> deviceIds);

    @SelectProvider(type = ChannelProvider.class, method = "queryByGroupList")
    List<CommonGbChannel> queryByGroupList(List<Group> groupList);


    int removeParentIdByChannels(List<CommonGbChannel> channelList);

    @SelectProvider(type = ChannelProvider.class, method = "queryByBusinessGroup")
    List<CommonGbChannel> queryByBusinessGroup(@Param("businessGroup") String businessGroup);

    @SelectProvider(type = ChannelProvider.class, method = "queryByParentId")
    List<CommonGbChannel> queryByParentId(@Param("parentId") String parentId);


    int updateBusinessGroupByChannelList(@Param("businessGroup") String businessGroup, List<CommonGbChannel> channelList);


    int updateParentIdByChannelList(@Param("parentId") String parentId, List<CommonGbChannel> channelList);


    List<GroupTree> queryForGroupTreeByParentId(@Param("query") String query, @Param("parent") String parent);


    int updateGroup(@Param("parentId") String parentId, @Param("businessGroup") String businessGroup,
                    List<CommonGbChannel> channelList);


    int batchUpdate(List<CommonGbChannel> commonGbChannels);

    @SelectProvider(type = ChannelProvider.class, method = "queryWithPlatform")
    List<CommonGbChannel> queryWithPlatform(@Param("platformId") Long platformId);

    @SelectProvider(type = ChannelProvider.class, method = "queryShareChannelByParentId")
    List<CommonGbChannel> queryShareChannelByParentId(@Param("parentId") String parentId, @Param("platformId") Long platformId);

    @SelectProvider(type = ChannelProvider.class, method = "queryShareChannelByCivilCode")
    List<CommonGbChannel> queryShareChannelByCivilCode(@Param("civilCode") String civilCode, @Param("platformId") Long platformId);


    int updateCivilCodeByChannelList(@Param("civilCode") String civilCode, List<CommonGbChannel> channelList);

    @SelectProvider(type = ChannelProvider.class, method = "queryListByStreamPushList")
    List<CommonGbChannel> queryListByStreamPushList(@Param("dataType") Integer dataType, List<StreamPushVo> streamPushVoList);



    void updateGpsByDeviceIdForStreamPush(@Param("dataType") Integer dataType,  List<CommonGbChannel> channels);

    @SelectProvider(type = ChannelProvider.class, method = "queryList")
    List<CommonGbChannel> queryList(@Param("page") Page<CommonGbChannel> page, @Param("query") String query, @Param("online") Boolean online,
                                    @Param("hasRecordPlan") Boolean hasRecordPlan, @Param("dataType") Integer dataType);


    void removeRecordPlan(List<Long> channelIds);


    void addRecordPlan(List<Long> channelIds, @Param("planId") Long planId);


    void addRecordPlanForAll(@Param("planId") Long planId);


    void removeRecordPlanByPlanId( @Param("planId") Long planId);



    List<CommonGbChannel> queryForRecordPlanForWebList(PageQuery pageQuery, @Param("planId") Long planId, @Param("query") String query,
                                                       @Param("dataType") Integer dataType, @Param("online") Boolean online,
                                                       @Param("hasLink") Boolean hasLink);


    @SelectProvider(type = ChannelProvider.class, method = "queryByDataId")
    CommonGbChannel queryByDataId(@Param("dataType") Integer dataType, @Param("dataDeviceId") Long dataDeviceId);


}
