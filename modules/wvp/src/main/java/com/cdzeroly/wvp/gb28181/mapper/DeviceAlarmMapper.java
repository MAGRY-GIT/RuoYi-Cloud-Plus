package com.cdzeroly.wvp.gb28181.mapper;

import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.gb28181.domian.DeviceAlarm;
import com.cdzeroly.wvp.gb28181.domian.vo.DeviceAlarmVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用于存储设备的报警信息
 *
 * @author MGARY
 */
@Mapper
public interface DeviceAlarmMapper extends BaseMapperPlus<DeviceAlarm, DeviceAlarmVo> {
}
