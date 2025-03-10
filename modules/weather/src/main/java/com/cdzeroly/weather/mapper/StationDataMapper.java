package com.cdzeroly.weather.mapper;

import com.cdzeroly.weather.domain.StationData;
import com.cdzeroly.weather.domain.vo.StationDataVo;
import com.cdzeroly.common.mybatis.core.mapper.BaseMapperPlus;
import com.cdzeroly.weather.domain.vo.StationDataVo1;

import java.util.Date;
import java.util.List;

/**
 * 地面站点数据Mapper接口
 *
 * @author MGARY
 * @date 2025-03-07
 */
public interface StationDataMapper extends BaseMapperPlus<StationData, StationDataVo> {


    /**
     * 根据时间查询指定数据
     * @param dataTime
     * @return
     */
    List<StationDataVo1> findByDataTime(Date dataTime, String value);

}
