package com.cdzeroly.wvp.i1.temp.service.impl;

import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.wvp.i1.bean.ImageOSD;
import com.cdzeroly.wvp.i1.service.I12020Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageOsdBo;
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageOsdVo;
import com.cdzeroly.wvp.i1.temp.domain.ImageOsd;
import com.cdzeroly.wvp.i1.temp.mapper.ImageOsdMapper;
import com.cdzeroly.wvp.i1.temp.service.IImageOsdService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 图像OSD查询/设置报Service业务层处理
 *
 * @author MGARY
 * @date 2025-02-20
 */
@RequiredArgsConstructor
@Service
public class ImageOsdServiceImpl implements IImageOsdService {

    private final ImageOsdMapper baseMapper;
    private final I12020Service i12020Service;

    /**
     * 查询图像OSD查询/设置报
     *
     * @param id 主键
     * @return 图像OSD查询/设置报
     */
    @Override
    public ImageOsdVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询图像OSD查询/设置报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 图像OSD查询/设置报分页列表
     */
    @Override
    public TableDataInfo<ImageOsdVo> queryPageList(ImageOsdBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImageOsd> lqw = buildQueryWrapper(bo);
        Page<ImageOsdVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的图像OSD查询/设置报列表
     *
     * @param bo 查询条件
     * @return 图像OSD查询/设置报列表
     */
    @Override
    public List<ImageOsdVo> queryList(ImageOsdBo bo) {
        LambdaQueryWrapper<ImageOsd> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ImageOsd> buildQueryWrapper(ImageOsdBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImageOsd> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getChannelNo() != null, ImageOsd::getChannelNo, bo.getChannelNo());
        lqw.eq(bo.getShowTime() != null, ImageOsd::getShowTime, bo.getShowTime());
        lqw.eq(bo.getShowText() != null, ImageOsd::getShowText, bo.getShowText());
        lqw.eq(StringUtils.isNotBlank(bo.getTextContent()), ImageOsd::getTextContent, bo.getTextContent());
        return lqw;
    }

    /**
     * 新增图像OSD查询/设置报
     *
     * @param bo 图像OSD查询/设置报
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImageOsdBo bo) {


//        try {
//            ImageOSD osd = new ImageOSD();
//            BeanUtils.copyProperties(bo, osd);
//            ImageOSD imageOSD = i12020Service.imageOsdSettings(bo.getMonitoringDeviceId(), (byte) 0x01,osd);
//        } catch (ExecutionException | InterruptedException |TimeoutException e) {
//          throw new ServiceException("超时");
//        }


        ImageOsd add = MapstructUtils.convert(bo, ImageOsd.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改图像OSD查询/设置报
     *
     * @param bo 图像OSD查询/设置报
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImageOsdBo bo) {
        ImageOsd update = MapstructUtils.convert(bo, ImageOsd.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImageOsd entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除图像OSD查询/设置报信息
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
    public ImageOsdVo queryByMonitoringDeviceId(String monitoringDeviceId ,ImageOSD osd ) {
        try {

            ImageOSD imageOSD = i12020Service.imageOsdSettings(monitoringDeviceId, (byte) 0x00,osd);
            ImageOsdVo imageOsdVo = new ImageOsdVo();
            BeanUtils.copyProperties(imageOSD, imageOsdVo);
            return  imageOsdVo;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
