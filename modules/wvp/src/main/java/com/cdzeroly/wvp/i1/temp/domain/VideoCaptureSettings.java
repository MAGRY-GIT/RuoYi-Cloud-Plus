package com.cdzeroly.wvp.i1.temp.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;

/**
 * 短视频采集参数设置报对象 video_capture_settings
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("video_capture_settings")
public class VideoCaptureSettings extends BaseEntity {

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
    private String channelNo;

    /**
     * 视频格式
     */
    private String videoFormat;

    /**
     * 视频录制时间
     */
    private String videoTime;



}
