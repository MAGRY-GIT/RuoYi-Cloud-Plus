package com.cdzeroly.wvp.i1.temp.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.cdzeroly.common.mybatis.core.domain.BaseDateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;

/**
 * 图像OSD查询/设置报对象 image_osd
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("image_osd")
public class ImageOsd extends BaseDateEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 通道号
     */
    private Long channelNo;

    /**
     * 是否显示时间
     */
    private Long showTime;

    /**
     * 文本显示
     */
    private Long showText;

    /**
     * 文本内容
     */
    private String textContent;



}
