package com.cdzeroly.wvp.i1.temp.service.impl;

import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.wvp.i1.packet.v2020.GImageAnalysisTypeQueryPacket;
import com.cdzeroly.wvp.i1.service.I12020Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageAnalysisTypeBo;
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAnalysisTypeVo;
import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisType;
import com.cdzeroly.wvp.i1.temp.mapper.ImageAnalysisTypeMapper;
import com.cdzeroly.wvp.i1.temp.service.IImageAnalysisTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 图像分析类型查询报Service业务层处理
 *
 * @author MGARY
 * @date 2025-02-20
 */
@RequiredArgsConstructor
@Service
public class ImageAnalysisTypeServiceImpl implements IImageAnalysisTypeService {

    private final ImageAnalysisTypeMapper baseMapper;

    private final I12020Service i12020Service;

    /**
     * 查询图像分析类型查询报
     *
     * @param id 主键
     * @return 图像分析类型查询报
     */
    @Override
    public ImageAnalysisTypeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询图像分析类型查询报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 图像分析类型查询报分页列表
     */
    @Override
    public TableDataInfo<ImageAnalysisTypeVo> queryPageList(ImageAnalysisTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ImageAnalysisType> lqw = buildQueryWrapper(bo);
        Page<ImageAnalysisTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的图像分析类型查询报列表
     *
     * @param bo 查询条件
     * @return 图像分析类型查询报列表
     */
    @Override
    public List<ImageAnalysisTypeVo> queryList(ImageAnalysisTypeBo bo) {
        LambdaQueryWrapper<ImageAnalysisType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }



    private LambdaQueryWrapper<ImageAnalysisType> buildQueryWrapper(ImageAnalysisTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ImageAnalysisType> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getChannelNo() != null, ImageAnalysisType::getChannelNo, bo.getChannelNo());
        return lqw;
    }

    /**
     * 新增图像分析类型查询报
     *
     * @param bo 图像分析类型查询报
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ImageAnalysisTypeBo bo) {
        ImageAnalysisType add = MapstructUtils.convert(bo, ImageAnalysisType.class);
        byte[] byteArray = new byte[ bo.getDataSources().size()];

        for (int i = 0; i <  bo.getDataSources().size(); i++) {
            byteArray[i] = bo.getDataSources().get(i);
        }
//        try {
//            i12020Service.imageAnalysisTypeQuery(bo.getMonitoringDeviceId(), (byte) bo.getDataSources().size(), byteArray);
//        } catch (ExecutionException | InterruptedException |TimeoutException e) {
//          throw new  ServiceException("超时");
//        }
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改图像分析类型查询报
     *
     * @param bo 图像分析类型查询报
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ImageAnalysisTypeBo bo) {
        ImageAnalysisType update = MapstructUtils.convert(bo, ImageAnalysisType.class);
        validEntityBeforeSave(update);



        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ImageAnalysisType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除图像分析类型查询报信息
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
    public ImageAnalysisTypeVo queryByMonitoringDeviceId(String monitoringDeviceId, List<Integer> dataSources) {
        try {
            byte[] byteArray = new byte[dataSources.size()];
            for (int i = 0; i < dataSources.size(); i++) {
                byteArray[i] = dataSources.get(i).byteValue();
            }

            GImageAnalysisTypeQueryPacket imageAnalysisParamsDto = i12020Service.imageAnalysisTypeQuery(monitoringDeviceId, (byte) byteArray.length, byteArray);
            ImageAnalysisTypeVo imageAnalysisTypeVo = new ImageAnalysisTypeVo();
            BeanUtils.copyProperties(imageAnalysisParamsDto, imageAnalysisTypeVo);
            imageAnalysisTypeVo.setImageAnalysisType(imageAnalysisParamsDto.imageAnalysisTypeToJson());
            return  imageAnalysisTypeVo;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
