package com.cdzeroly.weather.domain.bo;

import com.cdzeroly.common.core.domain.GeoPoint;
import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
import com.cdzeroly.weather.domain.GroundStation;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 中国地面气象站点业务对象 w_ground_station
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = GroundStation.class, reverseConvertGenerate = false)
public class GroundStationBo extends BaseEntity {

    /**
     * 区站号
     */
    @NotNull(message = "区站号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long id;

    /**
     * 省份
     */
    @NotBlank(message = "省份不能为空", groups = { AddGroup.class, EditGroup.class })
    private String province;

    /**
     * 站名
     */
    @NotBlank(message = "站名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String stationName;

    /**
     * 站类
     */
    @NotBlank(message = "站类不能为空", groups = { AddGroup.class, EditGroup.class })
    private String stationType;

    /**
     * 经度
     */
    @NotNull(message = "经度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long longitude;

    /**
     * 纬度
     */
    @NotNull(message = "纬度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long latitude;

    /**
     * 地理坐标
     */
    @NotNull(message = "地理坐标不能为空", groups = { AddGroup.class, EditGroup.class })
    private GeoPoint geoPoint;

    /**
     * 坐标类型
     */
    @NotBlank(message = "坐标类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String geoType;

    /**
     * 观测场拔海高度（米）
     */
    @NotNull(message = "观测场拔海高度（米）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long obsSeaLevel;

    /**
     * 气压传感器拔海高度（米）
     */
    @NotNull(message = "气压传感器拔海高度（米）不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long apSensorSeaLevel;


}
