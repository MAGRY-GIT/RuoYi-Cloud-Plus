package com.cdzeroly.wvp.i1.temp.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.cdzeroly.common.mybatis.core.domain.BaseDateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;

/**
 * 摄像机定时工作时间设置对象 camera_schedule
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("camera_schedule")
public class CameraSchedule extends BaseDateEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 工作起始时间数组
     */
    private String startTimes;

    /**
     * 工作结束时间数组
     */
    private String endTimes;



}
