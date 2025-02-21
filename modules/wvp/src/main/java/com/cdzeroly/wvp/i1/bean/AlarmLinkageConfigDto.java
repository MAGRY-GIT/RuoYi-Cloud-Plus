package com.cdzeroly.wvp.i1.bean;

import com.cdzeroly.wvp.i1.temp.domain.AlarmLinkageConfig;
import com.cdzeroly.wvp.i1.temp.domain.vo.AlarmLinkageConfigVo;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.util.List;

/**
 * 监拍装置告警联动参数查询报
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/18 18:18
 */
@Data
public class AlarmLinkageConfigDto {


    // 通道号
    private byte channelNo;
    // 预置位号，无云台固定为255 (FFH)
    private byte presettingNo;
    // 联动配置列表
    private List<AlarmLinkage> linkageConfigs;
}
