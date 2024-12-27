package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.wvp.domain.Group;
import com.cdzeroly.wvp.domain.bean.GroupTree;

import java.util.List;


public interface IGroupService {

    void add(Group group);

    void update(Group group);

    Group queryGroupByDeviceId(String regionDeviceId);

    List<GroupTree> queryForTree(String query, Long parent, Boolean hasChannel);

    void syncFromChannel();

    boolean delete(Long id);

    boolean batchAdd(List<Group> groupList);

    List<Group> getPath(String deviceId, String businessGroup);
}
