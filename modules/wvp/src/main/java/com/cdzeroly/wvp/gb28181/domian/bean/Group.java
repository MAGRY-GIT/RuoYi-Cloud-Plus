package com.cdzeroly.wvp.gb28181.domian.bean;

import com.cdzeroly.wvp.gb28181.domian.DeviceChannel;
import com.cdzeroly.wvp.utils.DateUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * 业务分组
 * @author MGARY
 */
@Data
@Schema(description = "业务分组")
public class Group implements Comparable<Group>{
    /**
     * 数据库自增ID
     */
    @Schema(description = "数据库自增ID")
    private int id;

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
     * 父分组ID
     */
    @Schema(description = "父分组ID")
    private Integer parentId;

    /**
     * 父区域国标ID
     */
    @Schema(description = "父区域国标ID")
    private String parentDeviceId;

    /**
     * 所属的业务分组国标编号
     */
    @Schema(description = "所属的业务分组国标编号")
    private String businessGroup;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private String createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private String updateTime;
    /**
     * 行政区划
     */
    @Schema(description = "行政区划")
    private String civilCode;

    public static Group getInstance(DeviceChannel channel) {
        GbCode gbCode = GbCode.decode(channel.getDeviceId());
        if (gbCode == null || (!"215".equals(gbCode.getTypeCode()) && !"216".equals(gbCode.getTypeCode()))) {
            return null;
        }
        Group group = new Group();
        group.setName(channel.getName());
        group.setDeviceId(channel.getDeviceId());
        group.setCreateTime(DateUtil.getNow());
        group.setUpdateTime(DateUtil.getNow());
        if ("215".equals(gbCode.getTypeCode())) {
            group.setBusinessGroup(channel.getDeviceId());
        }else if ("216".equals(gbCode.getTypeCode())) {
            group.setBusinessGroup(channel.getBusinessGroupId());
            group.setParentDeviceId(channel.getParentId());
        }
        if (group.getBusinessGroup() == null) {
            return null;
        }
        return group;
    }

    @Override
    public int compareTo(@NotNull Group region) {
        return Integer.compare(Integer.parseInt(this.deviceId), Integer.parseInt(region.getDeviceId()));
    }
}
