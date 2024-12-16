package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.gb28181.domian.Region;
import com.cdzeroly.wvp.gb28181.domian.bean.RegionTree;


import java.util.List;


public interface IRegionService {

    void add(Region region);

    boolean deleteByDeviceId(Integer regionDeviceId);

    /**
     * 查询区划列表
     */
    TableDataInfo<Region> query(String query, PageQuery page);

    /**
     * 更新区域
     */
    void update(Region region);

    List<Region> getAllChild(String parent);

    Region queryRegionByDeviceId(String regionDeviceId);

    List<RegionTree> queryForTree(String query, Integer parent, Boolean hasChannel);

    void syncFromChannel();

    boolean delete(int id);

    boolean batchAdd(List<Region> regionList);

    List<Region> getPath(String deviceId);
}
