package com.cdzeroly.wvp.i1.temp.domain.vo;

import java.util.Date;

import com.cdzeroly.common.json.utils.JsonUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisType;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 图像分析类型查询报视图对象 image_analysis_type
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ImageAnalysisType.class)
public class ImageAnalysisTypeVo implements Serializable {

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
     * 数据源
     */
    @ExcelProperty(value = "数据源")
    private String dataSources;

    /**
     */
    private String imageAnalysisType;



    private  ImageAnalysisType imageAnalysisTypeData;

    public  ImageAnalysisType getImageAnalysisTypeData() {
        return JsonUtils.parseObject(imageAnalysisType, new TypeReference<>() {
        });
    }

}
