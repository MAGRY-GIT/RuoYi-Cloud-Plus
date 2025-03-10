package com.cdzeroly.weather.domain.bo;

import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
import com.cdzeroly.weather.domain.StationData;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 地面站点数据业务对象 station_data
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = StationData.class, reverseConvertGenerate = false)
public class StationDataBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 区站号
     */
    @NotNull(message = "区站号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long stationId;

    /**
     * 气压
     */
    @NotNull(message = "气压不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long prs;

    /**
     * 海平面气压
     */
    @NotNull(message = "海平面气压不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long prsSea;

    /**
     * 最高气压
     */
    @NotNull(message = "最高气压不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long prsMax;

    /**
     * 最低气压
     */
    @NotNull(message = "最低气压不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long prsMin;

    /**
     * 最大风速
     */
    @NotNull(message = "最大风速不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long winSMax;

    /**
     * 极大风速
     */
    @NotNull(message = "极大风速不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long winSInstMax;

    /**
     * 极大风速的风向
     */
    @NotNull(message = "极大风速的风向不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long winDInstMax;

    /**
     * 2分钟平均风向
     */
    @NotNull(message = "2分钟平均风向不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long winDAvg2mi;

    /**
     * 2分钟平均风速
     */
    @NotNull(message = "2分钟平均风速不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long winSAvg2mi;

    /**
     * 最大风速的风向
     */
    @NotNull(message = "最大风速的风向不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long winDSMax;

    /**
     * 温度
     */
    @NotNull(message = "温度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long tem;

    /**
     * 最高气温
     */
    @NotNull(message = "最高气温不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long temMax;

    /**
     * 最低气温
     */
    @NotNull(message = "最低气温不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long temMin;

    /**
     * 相对湿度
     */
    @NotNull(message = "相对湿度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long rhu;

    /**
     * 最小相对湿度
     */
    @NotNull(message = "最小相对湿度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long rhuMin;

    /**
     * 过去3小时降水量
     */
    @NotNull(message = "过去3小时降水量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long pre3h;


}
