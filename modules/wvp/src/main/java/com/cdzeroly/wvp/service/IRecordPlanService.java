package com.cdzeroly.wvp.service;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.service.bean.RecordPlan;
import com.cdzeroly.wvp.service.bo.RecordPlanBo;
import com.cdzeroly.wvp.service.vo.RecordPlanVo;


import java.util.List;

/**
 * @author MGARY
 */
public interface IRecordPlanService {


    RecordPlanVo get(Integer planId);

    void update(RecordPlanBo plan);

    void delete(Integer planId);

    TableDataInfo<RecordPlan> query(PageQuery pageQuery, String query);

    void add(RecordPlanBo plan);

    void link(List<Integer> channelIds, Integer planId);

    TableDataInfo<CommonGBChannel> queryChannelList(PageQuery pageQuery, String query, Integer channelType, Boolean online, Integer planId, Boolean hasLink);

    void linkAll(Integer planId);

    void cleanAll(Integer planId);

    Integer recording(String app, String stream);
}
