package com.cdzeroly.weather.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.cdzeroly.common.core.domain.GeoPoint;
import com.cdzeroly.common.tenant.core.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 区域信息对象 w_regional_info
 *
 * @author MGARY
 * @date 2025-03-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("w_regional_info")
public class RegionalInfo extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String addr;

    /**
     * 中心坐标
     */
    private GeoPoint centerCoordinates;

    /**
     * 面积
     */
    private Long area;

    /**
     * 颜色
     */
    private String colour;

    /**
     * 透明度
     */
    private Long transparency;

    /**
     * 轮廓线
     */
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
    private String content;


}
