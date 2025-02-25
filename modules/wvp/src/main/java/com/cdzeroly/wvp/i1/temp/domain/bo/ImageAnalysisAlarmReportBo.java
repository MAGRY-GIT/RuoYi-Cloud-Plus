package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.common.json.utils.JsonUtils;
import com.cdzeroly.wvp.i1.bean.AlarmTarget;
import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisAlarmReport;
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
 * 图像分析告警上报报业务对象 image_analysis_alarm_report
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImageAnalysisAlarmReport.class, reverseConvertGenerate = false)
public class ImageAnalysisAlarmReportBo extends BaseEntity {


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
     * 预置位
     */
    @NotNull(message = "预置位不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long presettingNo;

    /**
     * 告警时间;
     */
    @NotNull(message = "告警时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long alarmTime;

    /**
     * 告警目标数量
     */
    @NotNull(message = "告警目标数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long alarmTargetCount;

    /**
     * 告警目标信息
     */
    @NotBlank(message = "告警目标信息不能为空", groups = { AddGroup.class, EditGroup.class })
    private String alarmTargetsInfo;

    private List<AlarmTarget> alarmTargetsInfoList;

    public   List<Integer> getAlarmTargetsInfoList() {
        return JsonUtils.parseObject(alarmTargetsInfo, new TypeReference<>() {
        });
    }

}
