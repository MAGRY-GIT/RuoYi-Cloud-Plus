package com.cdzeroly.wvp.service.impl;

import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.wvp.api.RemoteScreenshotService;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.domain.CommonGbChannel;
import com.cdzeroly.wvp.domain.bean.InviteErrorCode;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import com.google.common.base.Joiner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.cdzeroly.wvp.domain.bo.WvpScreenshotPlanBo;
import com.cdzeroly.wvp.domain.vo.WvpScreenshotPlanVo;
import com.cdzeroly.wvp.domain.WvpScreenshotPlan;
import com.cdzeroly.wvp.mapper.WvpScreenshotPlanMapper;
import com.cdzeroly.wvp.service.IWvpScreenshotPlanService;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 截图计划Service业务层处理
 *
 * @author MGARY
 * @date 2025-02-28
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class WvpScreenshotPlanServiceImpl implements IWvpScreenshotPlanService {

    private final WvpScreenshotPlanMapper baseMapper;
    private final RemoteScreenshotService  remoteScreenshotService;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
    private final Map<Long, ScheduledFuture<?>> scheduledTasks = new HashMap<>();

    /**
     * 查询截图计划
     *
     * @param id 主键
     * @return 截图计划
     */
    @Override
    public WvpScreenshotPlanVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询截图计划列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 截图计划分页列表
     */
    @Override
    public TableDataInfo<WvpScreenshotPlanVo> queryPageList(WvpScreenshotPlanBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WvpScreenshotPlan> lqw = buildQueryWrapper(bo);
        Page<WvpScreenshotPlanVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的截图计划列表
     *
     * @param bo 查询条件
     * @return 截图计划列表
     */
    @Override
    public List<WvpScreenshotPlanVo> queryList(WvpScreenshotPlanBo bo) {
        LambdaQueryWrapper<WvpScreenshotPlan> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WvpScreenshotPlan> buildQueryWrapper(WvpScreenshotPlanBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WvpScreenshotPlan> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCron() != null, WvpScreenshotPlan::getCron, bo.getCron());
        lqw.like(StringUtils.isNotBlank(bo.getName()), WvpScreenshotPlan::getName, bo.getName());
        return lqw;
    }

    /**
     * 新增截图计划
     *
     * @param bo 截图计划
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(WvpScreenshotPlanBo bo) {
        WvpScreenshotPlan add = MapstructUtils.convert(bo, WvpScreenshotPlan.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
            scheduleTask(add.getId(), add.getCron());
        }
        return flag;
    }

    /**
     * 修改截图计划
     *
     * @param bo 截图计划
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(WvpScreenshotPlanBo bo) {
        WvpScreenshotPlan update = MapstructUtils.convert(bo, WvpScreenshotPlan.class);
        validEntityBeforeSave(update);
        boolean flag = baseMapper.updateById(update) > 0;
        if (flag) {
            cancelScheduledTask(update.getId());
            scheduleTask(update.getId(), update.getCron());
        }
        return flag;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WvpScreenshotPlan entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除截图计划信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        boolean flag = baseMapper.deleteByIds(ids) > 0;
        if (flag) {
            ids.forEach(this::cancelScheduledTask);
        }
        return flag;
    }

    private void scheduleTask(Long id, String cron) {
        Runnable task = () -> {
            remoteScreenshotService.screenshotStorageProxy(null,null);
        };
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(task, 0, getCronPeriod(cron), TimeUnit.SECONDS);
        scheduledTasks.put(id, future);
    }



    private void cancelScheduledTask(Long id) {
        ScheduledFuture<?> future = scheduledTasks.remove(id);
        if (future != null) {
            future.cancel(false);
        }

    }

    private long getCronPeriod(String cron) {
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);
        CronParser parser = new CronParser(cronDefinition);
        ExecutionTime executionTime = ExecutionTime.forCron(parser.parse(cron));
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime nextExecution = executionTime.nextExecution(now).orElseThrow(() -> new RuntimeException("无法解析 Cron 表达式"));
        return ChronoUnit.SECONDS.between(now, nextExecution);
    }

}
