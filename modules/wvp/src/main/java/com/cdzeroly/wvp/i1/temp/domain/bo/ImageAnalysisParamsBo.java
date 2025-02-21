package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisParams;
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

    /**
     * 告警类型
     */
    @NotBlank(message = "告警类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String alarmTypeInfo;

    /**
     * 告警区域信息
     */
    @NotBlank(message = "告警区域信息不能为空", groups = { AddGroup.class, EditGroup.class })
    private String alarmRegion;




}
