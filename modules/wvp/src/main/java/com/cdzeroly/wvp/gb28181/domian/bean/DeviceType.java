package com.cdzeroly.wvp.gb28181.domian.bean;

import com.cdzeroly.wvp.gb28181.enums.DeviceTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

/**
 * @author MGARY
 */
@Setter
@Getter
public class DeviceType implements Comparable<DeviceType>{

    /**
     * 编号
     */
    private String name;

    /**
     * 名称
     */
    private String code;

    /**
     * 归属名称
     */
    private String ownerName;
    public static DeviceType getInstance(DeviceTypeEnum typeEnum) {
        DeviceType deviceType = new DeviceType();
        deviceType.setName(typeEnum.getName());
        deviceType.setCode(typeEnum.getCode());
        deviceType.setOwnerName(typeEnum.getOwnerName());
        return deviceType;
    }


    @Override
    public int compareTo(@NotNull DeviceType deviceType) {
        return Integer.compare(Integer.parseInt(this.code), Integer.parseInt(deviceType.getCode()));
    }
}
