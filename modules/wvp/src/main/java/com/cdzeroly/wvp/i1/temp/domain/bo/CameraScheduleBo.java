package com.cdzeroly.wvp.i1.temp.domain.bo;

import cn.hutool.core.date.DateUtil;
import com.cdzeroly.common.json.utils.JsonUtils;
import com.cdzeroly.wvp.i1.bean.AlarmLinkage;
import com.cdzeroly.wvp.i1.temp.domain.CameraSchedule;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    private List<String> startTimes;

    /**
     * 工作结束时间数组
     */
    private List<String> endTimes;

    public String getEndTimes() {
        return JsonUtils.toJsonString(startTimes);
    }


    public String getStartTimes() {
        return JsonUtils.toJsonString(endTimes);
    }


    public   List<Integer> getStartTimeList() {

       return Optional.ofNullable(startTimes)
           .map(startTimes-> startTimes.stream().map(startTime-> Math.toIntExact((DateUtil.parse(startTime).getTime() / 1000))).toList()).orElse(Lists.newArrayList());

    }

    public   List<Integer> getEndTimeList() {
        return Optional.ofNullable(endTimes)
            .map(endTimes-> endTimes.stream().map(endTime-> Math.toIntExact((DateUtil.parse(endTime).getTime() / 1000))).toList()).orElse(Lists.newArrayList());
    }


}
