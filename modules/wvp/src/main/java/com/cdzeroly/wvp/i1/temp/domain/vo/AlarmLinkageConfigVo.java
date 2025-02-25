package com.cdzeroly.wvp.i1.temp.domain.vo;

import java.util.Date;

import com.cdzeroly.common.json.utils.JsonUtils;
import com.cdzeroly.wvp.i1.bean.AlarmLinkage;
import com.cdzeroly.wvp.i1.temp.domain.ImageAcquisition;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdzeroly.wvp.i1.temp.domain.AlarmLinkageConfig;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cdzeroly.common.excel.annotation.ExcelDictFormat;
import com.cdzeroly.common.excel.convert.ExcelDictConvert;
import com.fasterxml.jackson.core.type.TypeReference;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMapping;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


/**
 * 监拍装置告警联动参数配置报视图对象 alarm_linkage_config
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AlarmLinkageConfig.class)
public class AlarmLinkageConfigVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 通道号
     */
    @ExcelProperty(value = "通道号")
    private Long channelNo;

    /**
     * 预置位号
     */
    @ExcelProperty(value = "预置位号")
    private Long presettingNo;

    /**
     * 联动配置
     */
    private String linkageConfigs;

    /**
     * 联动配置
     */
    @ExcelProperty(value = "联动配置")
    private List<AlarmLinkage> linkageConfigList;



    // 新增方法：将 List<AlarmLinkage> 转换为 String
    public   List<AlarmLinkage> getAlarmLinkageList() {
        return JsonUtils.parseObject(linkageConfigs, new TypeReference<>() {
        });
    }
}


