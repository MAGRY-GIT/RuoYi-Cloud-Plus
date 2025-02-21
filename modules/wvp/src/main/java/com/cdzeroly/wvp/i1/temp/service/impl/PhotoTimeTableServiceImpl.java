package com.cdzeroly.wvp.i1.temp.service.impl;

import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cdzeroly.wvp.i1.temp.domain.bo.PhotoTimeTableBo;
import com.cdzeroly.wvp.i1.temp.domain.vo.PhotoTimeTableVo;
import com.cdzeroly.wvp.i1.temp.domain.PhotoTimeTable;
import com.cdzeroly.wvp.i1.temp.mapper.PhotoTimeTableMapper;
import com.cdzeroly.wvp.i1.temp.service.IPhotoTimeTableService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 拍照时间设置Service业务层处理
 *
 * @author MGARY
 * @date 2025-02-20
 */
@RequiredArgsConstructor
@Service
public class PhotoTimeTableServiceImpl implements IPhotoTimeTableService {

    private final PhotoTimeTableMapper baseMapper;

    /**
     * 查询拍照时间设置
     *
     * @param id 主键
     * @return 拍照时间设置
     */
    @Override
    public PhotoTimeTableVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询拍照时间设置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 拍照时间设置分页列表
     */
    @Override
    public TableDataInfo<PhotoTimeTableVo> queryPageList(PhotoTimeTableBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PhotoTimeTable> lqw = buildQueryWrapper(bo);
        Page<PhotoTimeTableVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的拍照时间设置列表
     *
     * @param bo 查询条件
     * @return 拍照时间设置列表
     */
    @Override
    public List<PhotoTimeTableVo> queryList(PhotoTimeTableBo bo) {
        LambdaQueryWrapper<PhotoTimeTable> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PhotoTimeTable> buildQueryWrapper(PhotoTimeTableBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PhotoTimeTable> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getChannelNo() != null, PhotoTimeTable::getChannelNo, bo.getChannelNo());
        lqw.eq(StringUtils.isNotBlank(bo.getTimeTables()), PhotoTimeTable::getTimeTables, bo.getTimeTables());

        return lqw;
    }

    /**
     * 新增拍照时间设置
     *
     * @param bo 拍照时间设置
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(PhotoTimeTableBo bo) {
        PhotoTimeTable add = MapstructUtils.convert(bo, PhotoTimeTable.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改拍照时间设置
     *
     * @param bo 拍照时间设置
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(PhotoTimeTableBo bo) {
        PhotoTimeTable update = MapstructUtils.convert(bo, PhotoTimeTable.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PhotoTimeTable entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除拍照时间设置信息
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
