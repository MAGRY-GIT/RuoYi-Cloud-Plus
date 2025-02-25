package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.common.json.utils.JsonUtils;
import com.cdzeroly.wvp.i1.bean.ImageAnalysisParamsDto;
import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisType;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
/**
 * 图像分析类型查询报业务对象 image_analysis_type
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImageAnalysisType.class, reverseConvertGenerate = false)
public class ImageAnalysisTypeBo extends BaseEntity {

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
     * 数据源
     */
    @NotBlank(message = "数据源不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dataSources;

    /**
     * （TODO）
     */
    @NotBlank(message = "（TODO）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String imageAnalysisType;

    private  ImageAnalysisType imageAnalysisTypeData;

    public  ImageAnalysisType getImageAnalysisTypeData() {
        return JsonUtils.parseObject(imageAnalysisType, new TypeReference<>() {
        });
    }
}
