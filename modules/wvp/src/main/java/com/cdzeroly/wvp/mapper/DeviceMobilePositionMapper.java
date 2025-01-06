package com.cdzeroly.wvp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cdzeroly.wvp.domain.MobilePosition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author MGARY
 */
@Mapper
public interface DeviceMobilePositionMapper  extends BaseMapper<MobilePosition> {


    /**
     * 按设备 ID 和时间查询位置
     * @param deviceId
     * @param channelId
     * @param startTime
     * @param endTime
     * @return
     */
    List<MobilePosition> queryPositionByDeviceIdAndTime(@Param("deviceId") String deviceId, @Param("channelId") String channelId, @Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 按设备查询最新位置
     * @param deviceId
     * @return
     */
    MobilePosition queryLatestPositionByDevice(String deviceId);

    /**
     * 按设备 ID 清除移动设备位置
     * @param deviceId
     * @return
     */
    int clearMobilePositionsByDeviceId(String deviceId);


}
