package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.common.json.utils.JsonUtils;
import com.cdzeroly.wvp.i1.bean.AlarmTarget;
import com.cdzeroly.wvp.i1.bean.IdentifyType;
import com.cdzeroly.wvp.i1.bean.ImageAnalysisParamsDto;
import com.cdzeroly.wvp.i1.bean.TimeTable;
import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisParams;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
/**
 * 图像分析参数设置报业务对象 image_analysis_params
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImageAnalysisParams.class, reverseConvertGenerate = false)
public class ImageAnalysisParamsBo extends BaseEntity {

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
     * 预置位号
     */
    @NotNull(message = "预置位号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long presettingNo;

    /**
     * 智能分析启用
     */
    @NotNull(message = "智能分析启用不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long analysisEnableFlag;


    // 告警类型
    private String alarmTypeInfo;

    // 告警区域信息
    private String alarmRegion;

    // 告警类型编码列表
    private List<IdentifyType> alarmTypes;

    // 告警阈值列表，取值范围[1-100]
    private List<Byte> alarmThresholds;


    private List<ImageAnalysisParamsDto.AlarmRegion.RegionalInfo> regionalInfos;


    public String getAlarmTypeInfo() {
        ImageAnalysisParamsDto.AlarmTypeInfo alarmTypeInfo1 = new ImageAnalysisParamsDto.AlarmTypeInfo(alarmThresholds, alarmTypes);
        return JsonUtils.toJsonString(alarmTypeInfo1);
    }

    public String getAlarmRegion() {

        ImageAnalysisParamsDto.AlarmRegion alarmRegion1 = new ImageAnalysisParamsDto.AlarmRegion(regionalInfos);
        return JsonUtils.toJsonString(alarmRegion1);
    }




}
