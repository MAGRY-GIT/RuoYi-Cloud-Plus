package com.cdzeroly.wvp.i1.temp.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;

/**
 * 图像分析参数设置报对象 image_analysis_params
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("image_analysis_params")
public class ImageAnalysisParams extends BaseEntity {

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

    /**
     * 智能分析启用
     */
    private Long analysisEnableFlag;

    /**
     * 告警类型
     */
    private String alarmTypeInfo;

    /**
     * 告警区域信息
     */
    private String alarmRegion;



}
