package com.cdzeroly.weather.domain.bo;

import com.cdzeroly.common.core.domain.GeoPoint;
import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
import com.cdzeroly.weather.domain.RegionalInfo;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 区域信息业务对象 w_regional_info
 *
 * @author MGARY
 * @date 2025-03-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = RegionalInfo.class, reverseConvertGenerate = false)
public class RegionalInfoBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String addr;

    /**
     * 中心坐标
     */
    @NotNull(message = "中心坐标不能为空", groups = { AddGroup.class, EditGroup.class })
    private GeoPoint centerCoordinates;

    /**
     * 面积
     */
    @NotNull(message = "面积不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long area;

    /**
     * 颜色
     */
    @NotBlank(message = "颜色不能为空", groups = { AddGroup.class, EditGroup.class })
    private String colour;

    /**
     * 透明度
     */
    @NotNull(message = "透明度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long transparency;

    /**
     * 轮廓线
     */
    @NotNull(message = "轮廓线不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long contourLine;

    /**
     * 高温
     */
    private Long highTem;

    /**
     * 低温
     */
    private Long lowTem;

    /**
     * 1H变温
     */
    private Long variableTem1h;

    /**
     * 1H降水
     */
    private Long rainfall1h;

    /**
     * 大风
     */
    private Long gale;

    /**
     * 内容(JSON)
     */
    @NotBlank(message = "内容(JSON)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String content;


}
