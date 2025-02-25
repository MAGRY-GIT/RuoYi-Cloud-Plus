package com.cdzeroly.wvp.i1.temp.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.cdzeroly.common.mybatis.core.domain.BaseDateEntity;
import com.cdzeroly.wvp.i1.bean.AlarmLinkage;
import com.cdzeroly.wvp.i1.temp.domain.vo.AlarmLinkageConfigVo;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.List;

import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import lombok.NoArgsConstructor;

/**
 * 监拍装置告警联动参数配置报对象 alarm_linkage_config
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("alarm_linkage_config")
@NoArgsConstructor
public class AlarmLinkageConfig extends BaseDateEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 通道号
     */
    private Long channelNo;

    /**
     * 预置位号
     */
    private Long presettingNo;


    private String linkageConfigs;

}
