package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.wvp.i1.temp.domain.VideoCaptureSettings;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
/**
 * 短视频采集参数设置报业务对象 video_capture_settings
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = VideoCaptureSettings.class, reverseConvertGenerate = false)
public class VideoCaptureSettingsBo extends BaseEntity {


    String monitoringDeviceId;

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 通道号
     */
    @NotBlank(message = "通道号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String channelNo;

    /**
     * 视频格式
     */
    @NotBlank(message = "视频格式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String videoFormat;

    /**
     * 视频录制时间
     */
    @NotBlank(message = "视频录制时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private String videoTime;




}
