package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.gb28181.domian.DeviceAlarm;
import com.cdzeroly.wvp.gb28181.domian.bo.AlarmBo;
import com.cdzeroly.wvp.gb28181.domian.bo.DeviceAlarmBo;
import com.cdzeroly.wvp.gb28181.domian.vo.DeviceAlarmVo;

/**
 * 报警相关业务处理
 */
public interface IDeviceAlarmService {

    /**
     * 根据多个添加获取报警列表

     * @return 报警列表
     */
    TableDataInfo<DeviceAlarmVo> getAllAlarm(PageQuery pageQuery, DeviceAlarmBo bo);

    /**
     * 添加一个报警
     * @param deviceAlarm 添加报警
     */
    void add(DeviceAlarm deviceAlarm);


    int clearAlarmBeforeTime(AlarmBo bo);


}
