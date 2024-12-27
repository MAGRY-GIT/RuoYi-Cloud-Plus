package com.cdzeroly.wvp.controller;

import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.gb28181.domian.CommonGBChannel;
import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import com.cdzeroly.wvp.service.IRecordPlanService;
import com.cdzeroly.wvp.domain.RecordPlan;
import com.cdzeroly.wvp.domain.bo.RecordPlanBo;
import com.cdzeroly.wvp.domain.vo.RecordPlanVo;
import com.cdzeroly.wvp.domain.bo.RecordPlanDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MGARY
 */
@Tag(name = "录制计划")
@RestController
@AllArgsConstructor
@RequestMapping("/recordPlan")
public class RecordPlanController {

    private IRecordPlanService recordPlanService;

    private IDeviceChannelService deviceChannelService;



    @PostMapping("")
    @Operation(summary = "添加录制计划")
    @Parameter(name = "plan", description = "计划", required = true)
    public R<Void> add(@RequestBody RecordPlanBo plan) {
        if (plan.getPlanItemList() == null || plan.getPlanItemList().isEmpty()) {
            throw new ServiceException("添加录制计划时，录制计划不可为空");
        }
        recordPlanService.add(plan);
        return R.ok();
    }


    @PostMapping("/link")
    @Operation(summary = "通道关联录制计划")
    @Parameter(name = "param", description = "通道关联录制计划", required = true)
    public R<Void> link(@RequestBody RecordPlanDto param) {
        if (param.getAllLink() != null) {
            if (param.getAllLink()) {
                recordPlanService.linkAll(param.getPlanId());
            }else {
                recordPlanService.cleanAll(param.getPlanId());
            }
            return R.ok();
        }

        if (param.getChannelIds() == null && param.getDeviceDbIds() == null) {
            throw new ServiceException("通道ID和国标设备ID不可都为NULL");
        }

        List<Long> channelIds = new ArrayList<>();
        if (param.getChannelIds() != null) {
            channelIds.addAll(param.getChannelIds());
        }else {
            List<Long> chanelIdList = deviceChannelService.queryChaneIdListByDeviceDbIds(param.getDeviceDbIds());
            if (chanelIdList != null && !chanelIdList.isEmpty()) {
                channelIds = chanelIdList;
            }
        }
        recordPlanService.link(channelIds, param.getPlanId());
        return R.ok();
    }


    @GetMapping("")
    @Operation(summary = "查询录制计划")
    @Parameter(name = "planId", description = "计划ID", required = true)
    public R<RecordPlanVo> get(Integer planId) {
        if (planId == null) {
            throw new ServiceException("计划ID不可为NULL");
        }
        return R.ok(recordPlanService.get(planId));
    }


    @GetMapping("/list")
    @Operation(summary = "查询录制计划列表")
    public TableDataInfo<RecordPlan> query(@RequestParam(required = false) String query, PageQuery pageQuery) {
        if (query != null && ObjectUtils.isEmpty(query.trim())) {
            query = null;
        }
        return recordPlanService.query(pageQuery, query);
    }



    @Operation(summary = "分页查询级联平台的所有所有通道")
    @Parameter(name = "page", description = "当前页", required = true)
    @Parameter(name = "count", description = "每页条数", required = true)
    @Parameter(name = "planId", description = "录制计划ID")
    @Parameter(name = "channelType", description = "通道类型， 0：国标设备，1：推流设备，2：拉流代理")
    @Parameter(name = "query", description = "查询内容")
    @Parameter(name = "online", description = "是否在线")
    @Parameter(name = "hasLink", description = "是否已经关联")
    @GetMapping("/channel/list")
    public TableDataInfo<CommonGBChannel> queryChannelList( PageQuery pageQuery,
                                                           @RequestParam(required = false) Integer planId,
                                                           @RequestParam(required = false) String query,
                                                           @RequestParam(required = false) Integer channelType,
                                                           @RequestParam(required = false) Boolean online,
                                                           @RequestParam(required = false) Boolean hasLink) {

        Assert.notNull(planId, "录制计划ID不可为NULL");
        if (ObjectUtils.isEmpty(query)) {
            query = null;
        }
        return recordPlanService.queryChannelList(pageQuery, query, channelType,  online, planId, hasLink);
    }


    @PutMapping("")
    @Operation(summary = "更新录制计划")
    @Parameter(name = "plan", description = "计划", required = true)
    public R<Void> update(@RequestBody RecordPlanBo plan) {
        if (plan == null || plan.getId() == 0) {
            throw new ServiceException("参数或方法错误");
        }
        recordPlanService.update(plan);
        return R.ok();
    }


    @DeleteMapping()
    @Operation(summary = "删除录制计划")
    @Parameter(name = "planId", description = "计划ID", required = true)
    public R<Void> delete(Integer planId) {
        if (planId == null) {
            throw new ServiceException("计划IDID不可为NULL");
        }
        recordPlanService.delete(planId);
        return R.ok();
    }

}
