package com.cdzeroly.weather.service.impl;

import com.cdzeroly.common.core.constant.CacheNames;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.cdzeroly.weather.domain.bo.GroundStationBo;
import com.cdzeroly.weather.domain.vo.GroundStationVo;
import com.cdzeroly.weather.domain.GroundStation;
import com.cdzeroly.weather.mapper.GroundStationMapper;
import com.cdzeroly.weather.service.IGroundStationService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 中国地面气象站点Service业务层处理
 *
 * @author MGARY
 * @date 2025-03-07
 */
@RequiredArgsConstructor
@Service
public class GroundStationServiceImpl implements IGroundStationService {

    private final GroundStationMapper baseMapper;

    /**
     * 查询中国地面气象站点
     *
     * @param id 主键
     * @return 中国地面气象站点
     */
    @Cacheable(cacheNames = CacheNames.WEATHER_STATIONS, key = "#id")
    @Override
    public GroundStationVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询中国地面气象站点列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 中国地面气象站点分页列表
     */
    @Override
    public TableDataInfo<GroundStationVo> queryPageList(GroundStationBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GroundStation> lqw = buildQueryWrapper(bo);
        Page<GroundStationVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的中国地面气象站点列表
     *
     * @param bo 查询条件
     * @return 中国地面气象站点列表
     */
    @Override
    public List<GroundStationVo> queryList(GroundStationBo bo) {
        LambdaQueryWrapper<GroundStation> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<GroundStation> buildQueryWrapper(GroundStationBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<GroundStation> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getProvince()), GroundStation::getProvince, bo.getProvince());
        lqw.like(StringUtils.isNotBlank(bo.getStationName()), GroundStation::getStationName, bo.getStationName());
        lqw.eq(StringUtils.isNotBlank(bo.getStationType()), GroundStation::getStationType, bo.getStationType());
        lqw.eq(bo.getLongitude() != null, GroundStation::getLongitude, bo.getLongitude());
        lqw.eq(bo.getLatitude() != null, GroundStation::getLatitude, bo.getLatitude());
        lqw.eq(bo.getGeoPoint() != null, GroundStation::getGeoPoint, bo.getGeoPoint());
        lqw.eq(StringUtils.isNotBlank(bo.getGeoType()), GroundStation::getGeoType, bo.getGeoType());
        lqw.eq(bo.getObsSeaLevel() != null, GroundStation::getObsSeaLevel, bo.getObsSeaLevel());
        lqw.eq(bo.getApSensorSeaLevel() != null, GroundStation::getApSensorSeaLevel, bo.getApSensorSeaLevel());
        return lqw;
    }

    /**
     * 新增中国地面气象站点
     *
     * @param bo 中国地面气象站点
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(GroundStationBo bo) {
        GroundStation add = MapstructUtils.convert(bo, GroundStation.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改中国地面气象站点
     *
     * @param bo 中国地面气象站点
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(GroundStationBo bo) {
        GroundStation update = MapstructUtils.convert(bo, GroundStation.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GroundStation entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除中国地面气象站点信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
