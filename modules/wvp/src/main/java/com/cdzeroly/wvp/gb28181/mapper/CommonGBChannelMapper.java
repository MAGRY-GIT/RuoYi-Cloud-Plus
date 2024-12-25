package com.cdzeroly.wvp.gb28181.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.gb28181.domian.Region;
import com.cdzeroly.wvp.gb28181.domian.vo.CommonGBChannelVo;
import com.cdzeroly.wvp.gb28181.mapper.provider.ChannelProvider;
import com.cdzeroly.wvp.gb28181.domian.bean.*;
import com.cdzeroly.wvp.streamPush.domian.StreamPush;
import com.cdzeroly.wvp.streamPush.domian.vo.StreamPushVo;
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
public interface CommonGBChannelMapper extends BaseMapperPlus<CommonGBChannel, CommonGBChannelVo> {


    @SelectProvider(type = ChannelProvider.class, method = "queryByDeviceId")
    CommonGBChannel queryByDeviceId(@Param("gbDeviceId") String gbDeviceId);



    @SelectProvider(type = ChannelProvider.class, method = "queryById")
    CommonGBChannel queryById(@Param("gbId") int gbId);


    int updateStatusById(@Param("gbId") int gbId, @Param("status") int status);


    int updateStatusForListById(List<CommonGBChannel> commonGBChannels, @Param("status") String status);

    @SelectProvider(type = ChannelProvider.class, method = "queryInListByStatus")
    List<CommonGBChannel> queryInListByStatus(List<CommonGBChannel> commonGBChannelList, @Param("status") String status);




    int updateStatus(List<CommonGBChannel> commonGBChannels);


    void reset(@Param("id") int id, @Param("dataType") Integer dataType, @Param("dataDeviceId") int dataDeviceId, @Param("updateTime") String updateTime);


    @SelectProvider(type = ChannelProvider.class, method = "queryByIds")
    List<CommonGBChannel> queryByIds(Collection<Integer> ids);


    @SelectProvider(type = ChannelProvider.class, method = "queryListByCivilCode")
    List<CommonGBChannel> queryListByCivilCode(PageQuery pageQuery,@Param("query") String query, @Param("online") Boolean online,
                                               @Param("dataType") Integer dataType, @Param("civilCode") String civilCode);




    @SelectProvider(type = ChannelProvider.class, method = "queryListByParentId")
    List<CommonGBChannel> queryListByParentId(PageQuery pageQuery,@Param("query") String query, @Param("online") Boolean online,
                                              @Param("dataType") Integer dataType, @Param("groupDeviceId") String groupDeviceId);




    List<RegionTree> queryForRegionTreeByCivilCode(@Param("query") String query, @Param("parentDeviceId") String parentDeviceId);


    int removeCivilCode(List<Region> allChildren);



    int updateRegion(@Param("civilCode") String civilCode, @Param("channelList") List<CommonGBChannel> channelList);



    int removeCivilCodeByChannels(List<CommonGBChannel> channelList);

    @SelectProvider(type = ChannelProvider.class, method = "queryByCivilCode")
    List<CommonGBChannel> queryByCivilCode(@Param("civilCode") String civilCode);

    @SelectProvider(type = ChannelProvider.class, method = "queryByGbDeviceIds")
    List<CommonGBChannel> queryByGbDeviceIds(@Param("dataType") Integer dataType, List<Integer> deviceIds);

    List<Integer> queryByGbDeviceIdsForIds(@Param("dataType") Integer dataType, List<Integer> deviceIds);

    @SelectProvider(type = ChannelProvider.class, method = "queryByGroupList")
    List<CommonGBChannel> queryByGroupList(List<Group> groupList);


    int removeParentIdByChannels(List<CommonGBChannel> channelList);

    @SelectProvider(type = ChannelProvider.class, method = "queryByBusinessGroup")
    List<CommonGBChannel> queryByBusinessGroup(@Param("businessGroup") String businessGroup);

    @SelectProvider(type = ChannelProvider.class, method = "queryByParentId")
    List<CommonGBChannel> queryByParentId(@Param("parentId") String parentId);


    int updateBusinessGroupByChannelList(@Param("businessGroup") String businessGroup, List<CommonGBChannel> channelList);


    int updateParentIdByChannelList(@Param("parentId") String parentId, List<CommonGBChannel> channelList);


    List<GroupTree> queryForGroupTreeByParentId(@Param("query") String query, @Param("parent") String parent);


    int updateGroup(@Param("parentId") String parentId, @Param("businessGroup") String businessGroup,
                    List<CommonGBChannel> channelList);


    int batchUpdate(List<CommonGBChannel> commonGBChannels);

    @SelectProvider(type = ChannelProvider.class, method = "queryWithPlatform")
    List<CommonGBChannel> queryWithPlatform(@Param("platformId") Integer platformId);

    @SelectProvider(type = ChannelProvider.class, method = "queryShareChannelByParentId")
    List<CommonGBChannel> queryShareChannelByParentId(@Param("parentId") String parentId, @Param("platformId") Integer platformId);

    @SelectProvider(type = ChannelProvider.class, method = "queryShareChannelByCivilCode")
    List<CommonGBChannel> queryShareChannelByCivilCode(@Param("civilCode") String civilCode, @Param("platformId") Integer platformId);


    int updateCivilCodeByChannelList(@Param("civilCode") String civilCode, List<CommonGBChannel> channelList);

    @SelectProvider(type = ChannelProvider.class, method = "queryListByStreamPushList")
    List<CommonGBChannel> queryListByStreamPushList(@Param("dataType") Integer dataType, List<StreamPushVo> streamPushVoList);



    void updateGpsByDeviceIdForStreamPush(@Param("dataType") Integer dataType,  List<CommonGBChannel> channels);

    @SelectProvider(type = ChannelProvider.class, method = "queryList")
    List<CommonGBChannel> queryList(@Param("page") Page<CommonGBChannel> page, @Param("query") String query, @Param("online") Boolean online,
                                    @Param("hasRecordPlan") Boolean hasRecordPlan, @Param("dataType") Integer dataType);


    void removeRecordPlan(List<Integer> channelIds);


    void addRecordPlan(List<Integer> channelIds, @Param("planId") Integer planId);


    void addRecordPlanForAll(@Param("planId") Integer planId);


    void removeRecordPlanByPlanId( @Param("planId") Integer planId);



    List<CommonGBChannel> queryForRecordPlanForWebList(PageQuery pageQuery, @Param("planId") Integer planId, @Param("query") String query,
                                                       @Param("dataType") Integer dataType, @Param("online") Boolean online,
                                                       @Param("hasLink") Boolean hasLink);


    @SelectProvider(type = ChannelProvider.class, method = "queryByDataId")
    CommonGBChannel queryByDataId(@Param("dataType") Integer dataType, @Param("dataDeviceId") Integer dataDeviceId);


}
