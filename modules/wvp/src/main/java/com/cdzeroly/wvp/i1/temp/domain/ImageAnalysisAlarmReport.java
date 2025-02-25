package com.cdzeroly.wvp.i1.temp.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.cdzeroly.common.mybatis.core.domain.BaseDateEntity;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 图像分析告警上报报对象 image_analysis_alarm_report
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("image_analysis_alarm_report")
public class ImageAnalysisAlarmReport extends BaseDateEntity {

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
     * 预置位
     */
    private Long presettingNo;

    /**
     * 告警时间;
     */
    private Long alarmTime;

    /**
     * 告警目标数量
     */
    private Long alarmTargetCount;

    /**
     * 告警目标信息
     */
    private String alarmTargetsInfo;




}
