package com.cdzeroly.wvp.gb28181.domian.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.cdzeroly.wvp.common.CivilCodePo;
import com.cdzeroly.wvp.gb28181.domian.DeviceChannel;
import com.cdzeroly.wvp.utils.CivilCodeUtil;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * 区域
 */
@Data
@Schema(description = "区域")
@ExcelIgnoreUnannotated
@AutoMapper(target = DeviceChannel.class)
public class RegionVo implements Comparable<RegionVo> , Serializable {
    /**
     * 数据库自增ID
     */
    @Schema(description = "数据库自增ID")
    private Integer id;

    /**
     * 区域国标编号
     */
    @Schema(description = "区域国标编号")
    private String deviceId;

    /**
     * 区域名称
     */
    @Schema(description = "区域名称")
    private String name;

    /**
     * 父区域国标ID
     */
    @Schema(description = "父区域ID")
    private Integer parentId;

    /**
     * 父区域国标ID
     */
    @Schema(description = "父区域国标ID")
    private String parentDeviceId;



    public static RegionVo getInstance(String commonRegionDeviceId, String commonRegionName, String commonRegionParentId) {
        RegionVo region = new RegionVo();
        region.setDeviceId(commonRegionDeviceId);
        region.setName(commonRegionName);
        region.setParentDeviceId(commonRegionParentId);
        return region;
    }

    public static RegionVo getInstance(CivilCodePo civilCodePo) {
        RegionVo region = new RegionVo();
        region.setName(civilCodePo.getName());
        region.setDeviceId(civilCodePo.getCode());
        if (civilCodePo.getCode().length() > 2) {
            region.setParentDeviceId(civilCodePo.getParentCode());
        }
        return region;
    }

    public static RegionVo getInstance(DeviceChannel channel) {
        RegionVo region = new RegionVo();
        region.setName(channel.getName());
        region.setDeviceId(channel.getDeviceId());
        CivilCodePo parentCode = CivilCodeUtil.INSTANCE.getParentCode(channel.getDeviceId());
        if (parentCode != null) {
            region.setParentDeviceId(parentCode.getCode());
        }
        return region;
    }

    @Override
    public int compareTo(@NotNull RegionVo region) {
        return Integer.compare(Integer.parseInt(this.deviceId), Integer.parseInt(region.getDeviceId()));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegionVo) {
            RegionVo region = (RegionVo) obj;

            // 比较每个属性的值一致时才返回true
            if (region.getId() == this.id) {
                return true;
            }
        }
        return false;
    }

    /**
     * 重写hashcode方法，返回的hashCode一样才再去比较每个属性的值
     */
    @Override
    public int hashCode() {
        return id;
    }
}
