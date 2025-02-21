package com.cdzeroly.wvp.i1.temp.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;

/**
 * 图像采集参数设置对象 image_acquisition
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("image_acquisition")
public class ImageAcquisition extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 色彩选择
     */
    private String colorSelect;

    /**
     * 分辨率
     */
    private String resolution;

    /**
     * 亮度
     */
    private Long luminance;

    /**
     * 对比度
     */
    private Long contrast;

    /**
     * 饱和度
     */
    private Long saturation;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Long updatedTime;


}
