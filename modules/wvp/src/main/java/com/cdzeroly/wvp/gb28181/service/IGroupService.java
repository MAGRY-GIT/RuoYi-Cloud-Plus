package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.wvp.gb28181.domian.bean.Group;
import com.cdzeroly.wvp.gb28181.domian.bean.GroupTree;

import java.util.List;


public interface IGroupService {

    void add(Group group);

    void update(Group group);

    Group queryGroupByDeviceId(String regionDeviceId);

    List<GroupTree> queryForTree(String query, Integer parent, Boolean hasChannel);

    void syncFromChannel();

    boolean delete(int id);

    boolean batchAdd(List<Group> groupList);

    List<Group> getPath(String deviceId, String businessGroup);
}
