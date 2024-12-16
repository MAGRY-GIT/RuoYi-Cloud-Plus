package com.cdzeroly.wvp.gb28181.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.gb28181.domian.DeviceAlarm;
import com.cdzeroly.wvp.gb28181.domian.bo.AlarmBo;
import com.cdzeroly.wvp.gb28181.domian.bo.DeviceAlarmBo;
import com.cdzeroly.wvp.gb28181.domian.vo.DeviceAlarmVo;
import com.cdzeroly.wvp.gb28181.service.IDeviceAlarmService;
import com.cdzeroly.wvp.gb28181.mapper.DeviceAlarmMapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MGARY
 */
@Service
@AllArgsConstructor
public class DeviceAlarmServiceImpl implements IDeviceAlarmService {

    private final DeviceAlarmMapper deviceAlarmMapper;

    @Override
    public TableDataInfo<DeviceAlarmVo> getAllAlarm(PageQuery pageQuery, DeviceAlarmBo bo) {
        List<DeviceAlarmVo> all = deviceAlarmMapper.selectVoPage(pageQuery.build(),this.buildQueryWrapper(bo));
           return  TableDataInfo.build(all);
    }

    @Override
    public void add(DeviceAlarm deviceAlarm) {
        deviceAlarmMapper.insert(deviceAlarm);
    }

    @Override
    public int clearAlarmBeforeTime(AlarmBo bo) {
        LambdaQueryWrapper<DeviceAlarm> wrapper =   Wrappers.lambdaQuery();
        wrapper.eq(CollUtil.isNotEmpty(bo.getDeviceIds())&& ObjUtil.isEmpty(bo.getId()),DeviceAlarm::getDeviceId,bo.getDeviceIds());
        wrapper.le(ObjUtil.isNotEmpty(bo.getTime())&& ObjUtil.isEmpty(bo.getId()),DeviceAlarm::getAlarmTime,bo.getTime());
        wrapper.eq(ObjUtil.isNotEmpty(bo.getId()),DeviceAlarm::getId,bo.getId());

        return deviceAlarmMapper.delete(wrapper);
    }


    private Wrapper<DeviceAlarm> buildQueryWrapper(DeviceAlarmBo bo) {
        LambdaQueryWrapper<DeviceAlarm> wrapper = Wrappers.lambdaQuery(DeviceAlarm.class);
        // 设备ID不为空时添加条件
        wrapper.eq(StrUtil.isNotBlank(bo.getDeviceId()), DeviceAlarm::getDeviceId, bo.getDeviceId());
        // 报警优先级不为空时添加条件
        wrapper.eq(StrUtil.isNotBlank(bo.getAlarmPriority()), DeviceAlarm::getAlarmPriority, bo.getAlarmPriority());
        // 报警方法不为空时添加条件
        wrapper.eq(StrUtil.isNotBlank(bo.getAlarmMethod()), DeviceAlarm::getAlarmMethod, bo.getAlarmMethod());
        // 报警类型不为空时添加条件
        wrapper.eq(StrUtil.isNotBlank(bo.getAlarmType()), DeviceAlarm::getAlarmType, bo.getAlarmType());
        // 开始时间不为空时添加条件
        wrapper.ge(ObjUtil.isNotNull(bo.getStartTime()), DeviceAlarm::getAlarmTime, bo.getStartTime());
        // 结束时间不为空时添加条件
        wrapper.le(ObjUtil.isNotNull(bo.getEndTime()), DeviceAlarm::getAlarmTime, bo.getEndTime());
        // 如果有额外的参数，例如(startTime 和 endTime 同时不为空)
        if (ObjUtil.isNotNull(bo.getStartTime()) && ObjUtil.isNotNull(bo.getEndTime())) {
            wrapper.between(DeviceAlarm::getAlarmTime, bo.getStartTime(), bo.getEndTime());
        }
        // 排序
        wrapper.orderByAsc(DeviceAlarm::getAlarmTime);
        return wrapper;
    }
}
