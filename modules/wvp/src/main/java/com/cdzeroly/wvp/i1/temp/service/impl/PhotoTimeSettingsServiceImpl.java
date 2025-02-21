package com.cdzeroly.wvp.i1.temp.service.impl;

import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cdzeroly.wvp.i1.temp.domain.bo.PhotoTimeSettingsBo;
import com.cdzeroly.wvp.i1.temp.domain.vo.PhotoTimeSettingsVo;
import com.cdzeroly.wvp.i1.temp.domain.PhotoTimeSettings;
import com.cdzeroly.wvp.i1.temp.mapper.PhotoTimeSettingsMapper;
import com.cdzeroly.wvp.i1.temp.service.IPhotoTimeSettingsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 拍照时间设置报Service业务层处理
 *
 * @author MGARY
 * @date 2025-02-20
 */
@RequiredArgsConstructor
@Service
public class PhotoTimeSettingsServiceImpl implements IPhotoTimeSettingsService {

    private final PhotoTimeSettingsMapper baseMapper;

    /**
     * 查询拍照时间设置报
     *
     * @param id 主键
     * @return 拍照时间设置报
     */
    @Override
    public PhotoTimeSettingsVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询拍照时间设置报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 拍照时间设置报分页列表
     */
    @Override
    public TableDataInfo<PhotoTimeSettingsVo> queryPageList(PhotoTimeSettingsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PhotoTimeSettings> lqw = buildQueryWrapper(bo);
        Page<PhotoTimeSettingsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的拍照时间设置报列表
     *
     * @param bo 查询条件
     * @return 拍照时间设置报列表
     */
    @Override
    public List<PhotoTimeSettingsVo> queryList(PhotoTimeSettingsBo bo) {
        LambdaQueryWrapper<PhotoTimeSettings> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PhotoTimeSettings> buildQueryWrapper(PhotoTimeSettingsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PhotoTimeSettings> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getChannelNo() != null, PhotoTimeSettings::getChannelNo, bo.getChannelNo());
        lqw.eq(bo.getHour1() != null, PhotoTimeSettings::getHour1, bo.getHour1());
        lqw.eq(bo.getMinute1() != null, PhotoTimeSettings::getMinute1, bo.getMinute1());
        lqw.eq(bo.getHour2() != null, PhotoTimeSettings::getHour2, bo.getHour2());
        lqw.eq(bo.getMinute2() != null, PhotoTimeSettings::getMinute2, bo.getMinute2());
        lqw.eq(bo.getHour3() != null, PhotoTimeSettings::getHour3, bo.getHour3());
        lqw.eq(bo.getMinute3() != null, PhotoTimeSettings::getMinute3, bo.getMinute3());
        return lqw;
    }

    /**
     * 新增拍照时间设置报
     *
     * @param bo 拍照时间设置报
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(PhotoTimeSettingsBo bo) {
        PhotoTimeSettings add = MapstructUtils.convert(bo, PhotoTimeSettings.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改拍照时间设置报
     *
     * @param bo 拍照时间设置报
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(PhotoTimeSettingsBo bo) {
        PhotoTimeSettings update = MapstructUtils.convert(bo, PhotoTimeSettings.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PhotoTimeSettings entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除拍照时间设置报信息
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
