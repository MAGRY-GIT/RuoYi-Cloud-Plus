package com.cdzeroly.weather.service.impl;

import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cdzeroly.weather.domain.bo.RegionalInfoBo;
import com.cdzeroly.weather.domain.vo.RegionalInfoVo;
import com.cdzeroly.weather.domain.RegionalInfo;
import com.cdzeroly.weather.mapper.RegionalInfoMapper;
import com.cdzeroly.weather.service.IRegionalInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 区域信息Service业务层处理
 *
 * @author MGARY
 * @date 2025-03-20
 */
@RequiredArgsConstructor
@Service
public class RegionalInfoServiceImpl implements IRegionalInfoService {

    private final RegionalInfoMapper baseMapper;

    /**
     * 查询区域信息
     *
     * @param id 主键
     * @return 区域信息
     */
    @Override
    public RegionalInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询区域信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 区域信息分页列表
     */
    @Override
    public TableDataInfo<RegionalInfoVo> queryPageList(RegionalInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<RegionalInfo> lqw = buildQueryWrapper(bo);
        Page<RegionalInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的区域信息列表
     *
     * @param bo 查询条件
     * @return 区域信息列表
     */
    @Override
    public List<RegionalInfoVo> queryList(RegionalInfoBo bo) {
        LambdaQueryWrapper<RegionalInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<RegionalInfo> buildQueryWrapper(RegionalInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<RegionalInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getType()), RegionalInfo::getType, bo.getType());
        lqw.like(StringUtils.isNotBlank(bo.getName()), RegionalInfo::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getAddr()), RegionalInfo::getAddr, bo.getAddr());
        lqw.eq(bo.getCenterCoordinates() != null, RegionalInfo::getCenterCoordinates, bo.getCenterCoordinates());
        lqw.eq(bo.getArea() != null, RegionalInfo::getArea, bo.getArea());
        lqw.eq(StringUtils.isNotBlank(bo.getColour()), RegionalInfo::getColour, bo.getColour());
        lqw.eq(bo.getTransparency() != null, RegionalInfo::getTransparency, bo.getTransparency());
        lqw.eq(bo.getContourLine() != null, RegionalInfo::getContourLine, bo.getContourLine());
        lqw.eq(bo.getHighTem() != null, RegionalInfo::getHighTem, bo.getHighTem());
        lqw.eq(bo.getLowTem() != null, RegionalInfo::getLowTem, bo.getLowTem());
        lqw.eq(bo.getVariableTem1h() != null, RegionalInfo::getVariableTem1h, bo.getVariableTem1h());
        lqw.eq(bo.getRainfall1h() != null, RegionalInfo::getRainfall1h, bo.getRainfall1h());
        lqw.eq(bo.getGale() != null, RegionalInfo::getGale, bo.getGale());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), RegionalInfo::getContent, bo.getContent());
        return lqw;
    }

    /**
     * 新增区域信息
     *
     * @param bo 区域信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(RegionalInfoBo bo) {
        RegionalInfo add = MapstructUtils.convert(bo, RegionalInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改区域信息
     *
     * @param bo 区域信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(RegionalInfoBo bo) {
        RegionalInfo update = MapstructUtils.convert(bo, RegionalInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(RegionalInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除区域信息信息
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
