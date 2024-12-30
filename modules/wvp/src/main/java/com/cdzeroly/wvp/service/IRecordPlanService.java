package com.cdzeroly.wvp.service;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.gb28181.domian.CommonGbChannel;
import com.cdzeroly.wvp.domain.RecordPlan;
import com.cdzeroly.wvp.domain.bo.RecordPlanBo;
import com.cdzeroly.wvp.domain.vo.RecordPlanVo;


import java.util.List;

/**
 * @author MGARY
 */
public interface IRecordPlanService {


    RecordPlanVo get(Long planId);

    void update(RecordPlanBo plan);

    void delete(Long planId);

    TableDataInfo<RecordPlan> query(PageQuery pageQuery, String query);

    void add(RecordPlanBo plan);

    void link(List<Long> channelIds, Long planId);

    TableDataInfo<CommonGbChannel> queryChannelList(PageQuery pageQuery, String query, Integer channelType, Boolean online, Long planId, Boolean hasLink);

    void linkAll(Long planId);

    void cleanAll(Long planId);

    Long recording(String app, String stream);
}
