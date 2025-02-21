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
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageAnalysisAlarmReportBo;
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAnalysisAlarmReportVo;
import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisAlarmReport;
import com.cdzeroly.wvp.i1.temp.mapper.ImageAnalysisAlarmReportMapper;
import com.cdzeroly.wvp.i1.temp.service.IImageAnalysisAlarmReportService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 图像分析告警上报报Service业务层处理
 *
 * @author MGARY
 * @date 2025-02-20
 */
@RequiredArgsConstructor
@Service
public class ImageAnalysisAlarmReportServiceImpl implements IImageAnalysisAlarmReportService {

    private final ImageAnalysisAlarmReportMapper baseMapper;

    /**
     * 查询图像分析告警上报报
     *
     * @param id 主键
     * @return 图像分析告警上报报
     */
    @Override
    public ImageAnalysisAlarmReportVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询图像分析告警上报报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 图像分析告警上报报分页列表
     */
    @Override
    public TableDataInfo<ImageAnalysisAlarmReportVo> queryPageList(ImageAnalysisAlarmReportBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImageAnalysisAlarmReport> lqw = buildQueryWrapper(bo);
        Page<ImageAnalysisAlarmReportVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的图像分析告警上报报列表
     *
     * @param bo 查询条件
     * @return 图像分析告警上报报列表
     */
    @Override
    public List<ImageAnalysisAlarmReportVo> queryList(ImageAnalysisAlarmReportBo bo) {
        LambdaQueryWrapper<ImageAnalysisAlarmReport> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImageAnalysisAlarmReport> buildQueryWrapper(ImageAnalysisAlarmReportBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImageAnalysisAlarmReport> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getChannelNo() != null, ImageAnalysisAlarmReport::getChannelNo, bo.getChannelNo());
        lqw.eq(bo.getPresettingNo() != null, ImageAnalysisAlarmReport::getPresettingNo, bo.getPresettingNo());
        lqw.eq(bo.getAlarmTime() != null, ImageAnalysisAlarmReport::getAlarmTime, bo.getAlarmTime());
        lqw.eq(bo.getAlarmTargetCount() != null, ImageAnalysisAlarmReport::getAlarmTargetCount, bo.getAlarmTargetCount());
        lqw.eq(StringUtils.isNotBlank(bo.getAlarmTargetsInfo()), ImageAnalysisAlarmReport::getAlarmTargetsInfo, bo.getAlarmTargetsInfo());
        return lqw;
    }

    /**
     * 新增图像分析告警上报报
     *
     * @param bo 图像分析告警上报报
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImageAnalysisAlarmReportBo bo) {
        ImageAnalysisAlarmReport add = MapstructUtils.convert(bo, ImageAnalysisAlarmReport.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改图像分析告警上报报
     *
     * @param bo 图像分析告警上报报
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImageAnalysisAlarmReportBo bo) {
        ImageAnalysisAlarmReport update = MapstructUtils.convert(bo, ImageAnalysisAlarmReport.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImageAnalysisAlarmReport entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除图像分析告警上报报信息
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
