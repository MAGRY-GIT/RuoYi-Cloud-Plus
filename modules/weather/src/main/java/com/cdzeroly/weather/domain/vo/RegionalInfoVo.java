package com.cdzeroly.weather.domain.vo;

import com.cdzeroly.common.core.domain.GeoPoint;
import com.cdzeroly.weather.domain.RegionalInfo;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 区域信息视图对象 w_regional_info
 *
 * @author MGARY
 * @date 2025-03-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = RegionalInfo.class)
public class RegionalInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 类型
     */
    @ExcelProperty(value = "类型")
    private String type;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String name;

    /**
     * 地址
     */
    @ExcelProperty(value = "地址")
    private String addr;

    /**
     * 中心坐标
     */
    @ExcelProperty(value = "中心坐标")
    private GeoPoint centerCoordinates;

    /**
     * 面积
     */
    @ExcelProperty(value = "面积")
    private Long area;

    /**
     * 颜色
     */
    @ExcelProperty(value = "颜色")
    private String colour;

    /**
     * 透明度
     */
    @ExcelProperty(value = "透明度")
    private Long transparency;

    /**
     * 轮廓线
     */
    @ExcelProperty(value = "轮廓线")
    private Long contourLine;

    /**
     * 高温
     */
    @ExcelProperty(value = "高温")
    private Long highTem;

    /**
     * 低温
     */
    @ExcelProperty(value = "低温")
    private Long lowTem;

    /**
     * 1H变温
     */
    @ExcelProperty(value = "1H变温")
    private Long variableTem1h;

    /**
     * 1H降水
     */
    @ExcelProperty(value = "1H降水")
    private Long rainfall1h;

    /**
     * 大风
     */
    @ExcelProperty(value = "大风")
    private Long gale;

    /**
     * 内容(JSON)
     */
    @ExcelProperty(value = "内容(JSON)")
    private String content;


}
