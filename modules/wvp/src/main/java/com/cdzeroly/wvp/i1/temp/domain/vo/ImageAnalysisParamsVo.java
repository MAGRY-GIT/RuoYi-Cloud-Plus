package com.cdzeroly.wvp.i1.temp.domain.vo;

import com.cdzeroly.common.json.utils.JsonUtils;
import com.cdzeroly.wvp.i1.bean.ImageAnalysisParamsDto;
import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisParams;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 图像分析参数设置报视图对象 image_analysis_params
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImageAnalysisParams.class)
public class ImageAnalysisParamsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 通道号
     */
    @ExcelProperty(value = "通道号")
    private Long channelNo;

    /**
     * 预置位号
     */
    @ExcelProperty(value = "预置位号")
    private Long presettingNo;

    /**
     * 智能分析启用
     */
    @ExcelProperty(value = "智能分析启用")
    private Long analysisEnableFlag;

    /**
     * 告警类型
     */
    @ExcelProperty(value = "告警类型")
    private String alarmTypeInfo;

    /**
     * 告警区域信息
     */
    @ExcelProperty(value = "告警区域信息")
    private String alarmRegion;



}
