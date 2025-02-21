package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.wvp.i1.temp.domain.ImageOsd;
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
 * 图像OSD查询/设置报业务对象 image_osd
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ImageOsd.class, reverseConvertGenerate = false)
public class ImageOsdBo extends BaseEntity {


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
     * 是否显示时间
     */
    @NotNull(message = "是否显示时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long showTime;

    /**
     * 文本显示
     */
    @NotNull(message = "文本显示不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long showText;

    /**
     * 文本内容
     */
    @NotBlank(message = "文本内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String textContent;




}
