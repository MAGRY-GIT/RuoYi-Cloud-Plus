package com.cdzeroly.wvp.gb28181.domian;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.cdzeroly.common.tenant.core.TenantEntity;
import com.cdzeroly.wvp.common.CivilCodePo;
import com.cdzeroly.wvp.utils.CivilCodeUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * 区域
 * @author MGARY
 */
@Data
@Schema(description = "区域")
@TableName("wvp_common_region")
public class Region  extends BaseEntity implements Comparable<Region>{
    /**
     * 数据库自增ID
     */
    @Schema(description = "数据库自增ID")
    @TableId
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



    public static Region getInstance(String commonRegionDeviceId, String commonRegionName, String commonRegionParentId) {
        Region region = new Region();
        region.setDeviceId(commonRegionDeviceId);
        region.setName(commonRegionName);
        region.setParentDeviceId(commonRegionParentId);
        return region;
    }

    public static Region getInstance(CivilCodePo civilCodePo) {
        Region region = new Region();
        region.setName(civilCodePo.getName());
        region.setDeviceId(civilCodePo.getCode());
        if (civilCodePo.getCode().length() > 2) {
            region.setParentDeviceId(civilCodePo.getParentCode());
        }
        return region;
    }

    public static Region getInstance(DeviceChannel channel) {
        Region region = new Region();
        region.setName(channel.getName());
        region.setDeviceId(channel.getDeviceId());
        CivilCodePo parentCode = CivilCodeUtil.INSTANCE.getParentCode(channel.getDeviceId());
        if (parentCode != null) {
            region.setParentDeviceId(parentCode.getCode());
        }
        return region;
    }

    @Override
    public int compareTo(@NotNull Region region) {
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
        if (obj instanceof Region) {
            Region region = (Region) obj;

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
