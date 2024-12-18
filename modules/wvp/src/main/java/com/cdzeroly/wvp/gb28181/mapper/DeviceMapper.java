package com.cdzeroly.wvp.gb28181.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.domian.vo.DeviceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用于存储设备信息
 *
 * @author MGARY
 */
public interface DeviceMapper extends BaseMapperPlus<Device, DeviceVo> {

    /**
     * 根据设备ID查询数据
     * @param deviceId 设备ID
     * @return Device
     */
    Device getDeviceByDeviceId(@Param("deviceId") String deviceId);

    /**
     * 查询设备
     * @param onLine 是否在线
     * @return  List<Device>
     */
    List<Device> getDevices(Boolean onLine);

    /**
     * 根据设备ID删除数据
     * @param deviceId 设备ID
     * @return  List<Device>
     */
    int deleteDeviceById(String deviceId);

    /**
     * 查询在线设备
     * @return  List<Device>
     */
    List<Device> selectOnlineDevices();

    /**
     * 根据ID和端口查询数据
     * @param host IP
     * @param port  端口
     * @return 设备
     */
    Device getDeviceByHostAndPort(@Param("host") String host, @Param("port") int port);


    void updateCustom(Device device);




    Page<Device> getDeviceList(@Param("page")Page<Device> page,@Param("query") String query, @Param("status") Boolean status);

    Device queryByChannelId(@Param("channelId") Integer channelId);

    Device getDeviceBySourceChannelDeviceId(@Param("channelDeviceId") String channelDeviceId);

}
