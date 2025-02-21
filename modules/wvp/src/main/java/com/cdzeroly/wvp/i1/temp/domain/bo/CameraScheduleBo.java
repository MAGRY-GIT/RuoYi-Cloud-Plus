package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.wvp.i1.temp.domain.CameraSchedule;
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
 * 摄像机定时工作时间设置业务对象 camera_schedule
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = CameraSchedule.class, reverseConvertGenerate = false)
public class CameraScheduleBo extends BaseEntity {


    String monitoringDeviceId;


    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 工作起始时间数组
     */
    @NotBlank(message = "工作起始时间数组不能为空", groups = { AddGroup.class, EditGroup.class })
    private String startTimes;

    /**
     * 工作结束时间数组
     */
    @NotBlank(message = "工作结束时间数组不能为空", groups = { AddGroup.class, EditGroup.class })
    private String endTimes;




}
