package com.cdzeroly.wvp.i1.temp.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.cdzeroly.common.mybatis.core.domain.BaseDateEntity;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;

/**
 * 拍照时间设置报对象 photo_time_settings
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("photo_time_settings")
public class PhotoTimeSettings extends BaseDateEntity {

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
     * 时1
     */
    private Long hour1;

    /**
     * 分1
     */
    private Long minute1;

    /**
     * 时2
     */
    private Long hour2;

    /**
     * 分2
     */
    private Long minute2;

    /**
     * 时3
     */
    private Long hour3;

    /**
     * 分3
     */
    private Long minute3;



}
