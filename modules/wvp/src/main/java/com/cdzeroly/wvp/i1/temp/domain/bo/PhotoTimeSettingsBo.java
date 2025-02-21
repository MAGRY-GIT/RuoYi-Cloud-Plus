package com.cdzeroly.wvp.i1.temp.domain.bo;

import com.cdzeroly.wvp.i1.temp.domain.PhotoTimeSettings;
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
 * 拍照时间设置报业务对象 photo_time_settings
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = PhotoTimeSettings.class, reverseConvertGenerate = false)
public class PhotoTimeSettingsBo extends BaseEntity {


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
     * 时1
     */
    @NotNull(message = "时1不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long hour1;

    /**
     * 分1
     */
    @NotNull(message = "分1不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long minute1;

    /**
     * 时2
     */
    @NotNull(message = "时2不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long hour2;

    /**
     * 分2
     */
    @NotNull(message = "分2不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long minute2;

    /**
     * 时3
     */
    @NotNull(message = "时3不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long Hour3;

    /**
     * 分3
     */
    @NotNull(message = "分3不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long minute3;




}
