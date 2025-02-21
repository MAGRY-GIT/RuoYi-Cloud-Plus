package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisType;
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

    /**
     * 创建人
     */
    @NotBlank(message = "创建人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String createdBy;

    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date createdTime;

    /**
     * 更新人
     */
    @NotBlank(message = "更新人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String updatedBy;

    /**
     * 更新时间
     */
    @NotNull(message = "更新时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date updatedTime;


}
