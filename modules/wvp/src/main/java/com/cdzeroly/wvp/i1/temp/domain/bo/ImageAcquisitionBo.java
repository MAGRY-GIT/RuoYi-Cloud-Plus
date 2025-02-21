package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.wvp.i1.temp.domain.ImageAcquisition;
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
 * 图像采集参数设置业务对象 image_acquisition
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImageAcquisition.class, reverseConvertGenerate = false)
public class ImageAcquisitionBo extends BaseEntity {


    String monitoringDeviceId;
    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 色彩选择
     */
    @NotBlank(message = "色彩选择不能为空", groups = { AddGroup.class, EditGroup.class })
    private String colorSelect;

    /**
     * 分辨率
     */
    @NotBlank(message = "分辨率不能为空", groups = { AddGroup.class, EditGroup.class })
    private String resolution;

    /**
     * 亮度
     */
    @NotNull(message = "亮度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long luminance;

    /**
     * 对比度
     */
    @NotNull(message = "对比度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long contrast;

    /**
     * 饱和度
     */
    @NotNull(message = "饱和度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long saturation;

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
    private Long updatedTime;


}
