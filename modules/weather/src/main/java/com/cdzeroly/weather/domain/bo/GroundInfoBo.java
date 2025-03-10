package com.cdzeroly.weather.domain.bo;

import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
import com.cdzeroly.weather.domain.GroundInfo;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 气象数据业务对象 w_ground_info
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = GroundInfo.class, reverseConvertGenerate = false)
public class GroundInfoBo extends BaseEntity {

    /**
     * 乐观锁
     */
    @NotNull(message = "乐观锁不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long revision;

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 天气情况
     */
    @NotBlank(message = "天气情况不能为空", groups = { AddGroup.class, EditGroup.class })
    private String wea;

    /**
     * 天气对应图标
     */
    @NotBlank(message = "天气对应图标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String weaImg;

    /**
     * 实时温度
     */
    @NotBlank(message = "实时温度不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tem;

    /**
     * 高温
     */
    @NotBlank(message = "高温不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tem1;

    /**
     * 低温
     */
    @NotBlank(message = "低温不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tem2;

    /**
     * 风向
     */
    @NotBlank(message = "风向不能为空", groups = { AddGroup.class, EditGroup.class })
    private String win;

    /**
     * 风力等级
     */
    @NotBlank(message = "风力等级不能为空", groups = { AddGroup.class, EditGroup.class })
    private String winSpeed;

    /**
     * 风速
     */
    @NotBlank(message = "风速不能为空", groups = { AddGroup.class, EditGroup.class })
    private String winMeter;

    /**
     * 湿度
     */
    @NotBlank(message = "湿度不能为空", groups = { AddGroup.class, EditGroup.class })
    private String humidity;

    /**
     * 能见度
     */
    @NotBlank(message = "能见度不能为空", groups = { AddGroup.class, EditGroup.class })
    private String visibility;

    /**
     * 气压hPa
     */
    @NotBlank(message = "气压hPa不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pressure;

    /**
     * 今日降雨量数值
     */
    @NotBlank(message = "今日降雨量数值不能为空", groups = { AddGroup.class, EditGroup.class })
    private String rainPcpn;

    /**
     * 空气质量
     */
    @NotBlank(message = "空气质量不能为空", groups = { AddGroup.class, EditGroup.class })
    private String air;

    /**
     * 空气质量等级
     */
    @NotBlank(message = "空气质量等级不能为空", groups = { AddGroup.class, EditGroup.class })
    private String airLevel;

    /**
     * 空气质量描述
     */
    @NotBlank(message = "空气质量描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String airTips;


}
