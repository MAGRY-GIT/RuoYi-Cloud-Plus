package com.cdzeroly.wvp.i1.temp.service.impl;

import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.wvp.i1.bean.AlarmLinkageConfigDto;
import com.cdzeroly.wvp.i1.bean.ImageAnalysisParamsDto;
import com.cdzeroly.wvp.i1.bean.ImageAnalysisParamsQuery;
import com.cdzeroly.wvp.i1.service.I12020Service;
import com.cdzeroly.wvp.i1.temp.domain.vo.AlarmLinkageConfigVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageAnalysisParamsBo;
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAnalysisParamsVo;
import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisParams;
import com.cdzeroly.wvp.i1.temp.mapper.ImageAnalysisParamsMapper;
import com.cdzeroly.wvp.i1.temp.service.IImageAnalysisParamsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 图像分析参数设置报Service业务层处理
 *
 * @author MGARY
 * @date 2025-02-20
 */
@RequiredArgsConstructor
@Service
public class ImageAnalysisParamsServiceImpl implements IImageAnalysisParamsService {

    private final ImageAnalysisParamsMapper baseMapper;
    private final I12020Service i12020Service;

    /**
     * 查询图像分析参数设置报
     *
     * @param id 主键
     * @return 图像分析参数设置报
     */
    @Override
    public ImageAnalysisParamsVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询图像分析参数设置报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 图像分析参数设置报分页列表
     */
    @Override
    public TableDataInfo<ImageAnalysisParamsVo> queryPageList(ImageAnalysisParamsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImageAnalysisParams> lqw = buildQueryWrapper(bo);
        Page<ImageAnalysisParamsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的图像分析参数设置报列表
     *
     * @param bo 查询条件
     * @return 图像分析参数设置报列表
     */
    @Override
    public List<ImageAnalysisParamsVo> queryList(ImageAnalysisParamsBo bo) {
        LambdaQueryWrapper<ImageAnalysisParams> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImageAnalysisParams> buildQueryWrapper(ImageAnalysisParamsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImageAnalysisParams> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getChannelNo() != null, ImageAnalysisParams::getChannelNo, bo.getChannelNo());
        lqw.eq(bo.getPresettingNo() != null, ImageAnalysisParams::getPresettingNo, bo.getPresettingNo());
        lqw.eq(bo.getAnalysisEnableFlag() != null, ImageAnalysisParams::getAnalysisEnableFlag, bo.getAnalysisEnableFlag());
        lqw.eq(StringUtils.isNotBlank(bo.getAlarmTypeInfo()), ImageAnalysisParams::getAlarmTypeInfo, bo.getAlarmTypeInfo());
        lqw.eq(StringUtils.isNotBlank(bo.getAlarmRegion()), ImageAnalysisParams::getAlarmRegion, bo.getAlarmRegion());
        return lqw;
    }

    /**
     * 新增图像分析参数设置报
     *
     * @param bo 图像分析参数设置报
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImageAnalysisParamsBo bo) {
        ImageAnalysisParams add = MapstructUtils.convert(bo, ImageAnalysisParams.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改图像分析参数设置报
     *
     * @param bo 图像分析参数设置报
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImageAnalysisParamsBo bo) {
        ImageAnalysisParams update = MapstructUtils.convert(bo, ImageAnalysisParams.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImageAnalysisParams entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除图像分析参数设置报信息
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

    @Override
    public ImageAnalysisParamsVo queryByMonitoringDeviceId(String monitoringDeviceId, List<ImageAnalysisParamsQuery> alarmLinkageParamsQuery) {
        try {
            ImageAnalysisParamsDto imageAnalysisParamsDto = i12020Service.imageAnalysisParamsQuery(monitoringDeviceId, alarmLinkageParamsQuery);
            ImageAnalysisParamsVo alarmLinkageConfigVo = new ImageAnalysisParamsVo();
            BeanUtils.copyProperties(imageAnalysisParamsDto, alarmLinkageConfigVo);
            return  alarmLinkageConfigVo;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
