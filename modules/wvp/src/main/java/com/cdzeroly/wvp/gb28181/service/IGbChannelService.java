package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.domain.Group;
import com.cdzeroly.wvp.domain.CommonGbChannel;
import com.cdzeroly.wvp.domain.Region;
import com.cdzeroly.wvp.domain.vo.StreamPushVo;
import com.cdzeroly.wvp.domain.bean.*;


import java.util.Collection;
import java.util.List;

/**
 * @author MGARY
 */
public interface IGbChannelService {

    /**
     * 查询国标设备
     * @param gbDeviceId 获取国标设备ID
     * @return
     */
    CommonGbChannel queryByDeviceId(String gbDeviceId);

    /**
     * 添加国标离线
     * @param commonGbChannel
     * @return
     */
    int add(CommonGbChannel commonGbChannel);

    /**
     * 删除国标设备
     * @param gbId
     * @return
     */
    int delete(Long gbId);

    void delete(Collection<Long> ids);

    int update(CommonGbChannel commonGBChannel);

    int offline(CommonGbChannel commonGBChannel);

    int offline(List<CommonGbChannel> commonGBChannelList);

    int online(CommonGbChannel commonGBChannel);

    int online(List<CommonGbChannel> commonGBChannelList);

    void batchAdd(List<CommonGbChannel> commonGBChannels);

    void updateStatus(List<CommonGbChannel> channelList);

    CommonGbChannel getOne(Long id);

    List<IndustryCodeType> getIndustryCodeList();

    List<DeviceType> getDeviceTypeList();

    List<NetworkIdentificationType> getNetworkIdentificationTypeList();

    void reset(Long id);

    TableDataInfo<CommonGbChannel> queryListByCivilCode(PageQuery pageQuery, String query, Boolean online, Integer channelType, String civilCode);

    TableDataInfo<CommonGbChannel> queryListByParentId(PageQuery pageQuery, String query, Boolean online, Integer channelType, String groupDeviceId);

    void removeCivilCode(List<Region> allChildren);

    void addChannelToRegion(String civilCode, List<Long> channelIds);

    void deleteChannelToRegion(String civilCode, List<Long> channelIds);

    void deleteChannelToRegionByCivilCode(String civilCode);

    void deleteChannelToRegionByChannelIds(List<Long> channelIds);

    void addChannelToRegionByGbDevice(String civilCode, List<Long> deviceIds);

    void deleteChannelToRegionByGbDevice(List<Long> deviceIds);

    void removeParentIdByBusinessGroup(String businessGroup);

    void removeParentIdByGroupList(List<Group> groupList);

    void updateBusinessGroup(String oldBusinessGroup, String newBusinessGroup);

    void updateParentIdGroup(String oldParentId, String newParentId);

    void addChannelToGroup(String parentId, String businessGroup, List<Long> channelIds);

    void deleteChannelToGroup(String parentId, String businessGroup, List<Long> channelIds);

    void addChannelToGroupByGbDevice(String parentId, String businessGroup, List<Long> deviceIds);

    void deleteChannelToGroupByGbDevice(List<Long> deviceIds);

    void batchUpdate(List<CommonGbChannel> commonGBChannels);

    CommonGbChannel queryOneWithPlatform(Long platformId, String channelDeviceId);

    void updateCivilCode(String oldCivilCode, String newCivilCode);

    List<CommonGbChannel> queryListByStreamPushList(List<StreamPushVo> streamPushVoList);

    void updateGpsByDeviceIdForStreamPush(List<CommonGbChannel> channels);

    TableDataInfo<CommonGbChannel> queryList(PageQuery pageQuery, String query, Boolean online, Boolean hasRecordPlan, Integer channelType);

}
