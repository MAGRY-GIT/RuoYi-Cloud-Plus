package com.cdzeroly.wvp.i1.temp.service.impl;

import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.wvp.i1.bean.ImageAcquisitionDto;
import com.cdzeroly.wvp.i1.packet.v2020.GImageAnalysisTypeQueryPacket;
import com.cdzeroly.wvp.i1.service.I12020Service;
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAnalysisTypeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageAcquisitionBo;
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAcquisitionVo;
import com.cdzeroly.wvp.i1.temp.domain.ImageAcquisition;
import com.cdzeroly.wvp.i1.temp.mapper.ImageAcquisitionMapper;
import com.cdzeroly.wvp.i1.temp.service.IImageAcquisitionService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 图像采集参数设置Service业务层处理
 *
 * @author MGARY
 * @date 2025-02-20
 */
@RequiredArgsConstructor
@Service
public class ImageAcquisitionServiceImpl implements IImageAcquisitionService {

    private final ImageAcquisitionMapper baseMapper;
    private final I12020Service i12020Service;

    /**
     * 查询图像采集参数设置
     *
     * @param id 主键
     * @return 图像采集参数设置
     */
    @Override
    public ImageAcquisitionVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询图像采集参数设置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 图像采集参数设置分页列表
     */
    @Override
    public TableDataInfo<ImageAcquisitionVo> queryPageList(ImageAcquisitionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImageAcquisition> lqw = buildQueryWrapper(bo);
        Page<ImageAcquisitionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的图像采集参数设置列表
     *
     * @param bo 查询条件
     * @return 图像采集参数设置列表
     */
    @Override
    public List<ImageAcquisitionVo> queryList(ImageAcquisitionBo bo) {
        LambdaQueryWrapper<ImageAcquisition> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImageAcquisition> buildQueryWrapper(ImageAcquisitionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImageAcquisition> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getColorSelect()), ImageAcquisition::getColorSelect, bo.getColorSelect());
        lqw.eq(StringUtils.isNotBlank(bo.getResolution()), ImageAcquisition::getResolution, bo.getResolution());
        lqw.eq(bo.getLuminance() != null, ImageAcquisition::getLuminance, bo.getLuminance());
        lqw.eq(bo.getContrast() != null, ImageAcquisition::getContrast, bo.getContrast());
        lqw.eq(bo.getSaturation() != null, ImageAcquisition::getSaturation, bo.getSaturation());
        return lqw;
    }

    /**
     * 新增图像采集参数设置
     *
     * @param bo 图像采集参数设置
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImageAcquisitionBo bo) {

        try {
            ImageAcquisitionDto acquisitionDto = new ImageAcquisitionDto();
            BeanUtils.copyProperties(bo, acquisitionDto);
            ImageAcquisitionDto imageAcquisitionDto = i12020Service.imageAcquisitionSettings(bo.getMonitoringDeviceId(), (byte) 0x01,acquisitionDto);

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        ImageAcquisition add = MapstructUtils.convert(bo, ImageAcquisition.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改图像采集参数设置
     *
     * @param bo 图像采集参数设置
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImageAcquisitionBo bo) {
        ImageAcquisition update = MapstructUtils.convert(bo, ImageAcquisition.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImageAcquisition entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除图像采集参数设置信息
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
    public ImageAcquisitionVo queryByMonitoringDeviceId(String monitoringDeviceId, ImageAcquisitionDto imageAcquisition) {
        try {

            ImageAcquisitionDto imageAcquisitionDto = i12020Service.imageAcquisitionSettings(monitoringDeviceId, (byte) 0x00,imageAcquisition);
            ImageAcquisitionVo imageAcquisitionVo = new ImageAcquisitionVo();
            BeanUtils.copyProperties(imageAcquisitionDto, imageAcquisitionVo);
            return  imageAcquisitionVo;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
