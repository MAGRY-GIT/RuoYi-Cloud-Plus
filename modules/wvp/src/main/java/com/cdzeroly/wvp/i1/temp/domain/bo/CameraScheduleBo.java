package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.common.json.utils.JsonUtils;
import com.cdzeroly.wvp.i1.bean.AlarmLinkage;
import com.cdzeroly.wvp.i1.temp.domain.CameraSchedule;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;

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

    // 工作起始时间数组
    private List<Integer> startTimeList;
    // 工作结束时间数组
    private List<Integer> endTimeList;

    public   List<Integer> getStartTimeList() {
        return JsonUtils.parseObject(startTimes, new TypeReference<>() {
        });
    }

    public   List<Integer> getEndTimeList() {
        return JsonUtils.parseObject(endTimes, new TypeReference<>() {
        });
    }


}
