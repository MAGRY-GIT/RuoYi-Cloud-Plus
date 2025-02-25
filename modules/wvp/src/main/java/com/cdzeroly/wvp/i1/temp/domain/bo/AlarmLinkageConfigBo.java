package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.common.json.utils.JsonUtils;
import com.cdzeroly.wvp.i1.bean.AlarmLinkage;
import com.cdzeroly.wvp.i1.temp.domain.AlarmLinkageConfig;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.List;

import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;

/**
 * 监拍装置告警联动参数配置报业务对象 alarm_linkage_config
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AlarmLinkageConfig.class, reverseConvertGenerate = false)
public class AlarmLinkageConfigBo extends BaseEntity {

    String monitoringDeviceId;

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 通道号
     */
    @NotNull(message = "通道号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long channelNo;

    /**
     * 预置位号
     */
    @NotNull(message = "预置位号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long presettingNo;

    // 联动配置列表

    /**
     * 联动配置
     */
    private String linkageConfigs;
    /**
     * 联动配置
     */
    private List<AlarmLinkage> alarmLinkageList;



    // 新增方法：将 List<AlarmLinkage> 转换为 String
    public   List<AlarmLinkage> getAlarmLinkageList() {
        return JsonUtils.parseObject(linkageConfigs, new TypeReference<>() {
        });
    }

}
