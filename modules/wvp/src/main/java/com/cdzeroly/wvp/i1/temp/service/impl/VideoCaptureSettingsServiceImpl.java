package com.cdzeroly.wvp.i1.temp.service.impl;

import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.wvp.i1.bean.VideoCaptureSettingsDto;
import com.cdzeroly.wvp.i1.service.I12020Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.cdzeroly.wvp.i1.temp.domain.bo.VideoCaptureSettingsBo;
import com.cdzeroly.wvp.i1.temp.domain.vo.VideoCaptureSettingsVo;
import com.cdzeroly.wvp.i1.temp.domain.VideoCaptureSettings;
import com.cdzeroly.wvp.i1.temp.mapper.VideoCaptureSettingsMapper;
import com.cdzeroly.wvp.i1.temp.service.IVideoCaptureSettingsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 短视频采集参数设置报Service业务层处理
 *
 * @author MGARY
 * @date 2025-02-20
 */
@RequiredArgsConstructor
@Service
public class VideoCaptureSettingsServiceImpl implements IVideoCaptureSettingsService {

    private final VideoCaptureSettingsMapper baseMapper;
    private final I12020Service i12020Service;

    /**
     * 查询短视频采集参数设置报
     *
     * @param id 主键
     * @return 短视频采集参数设置报
     */
    @Override
    public VideoCaptureSettingsVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询短视频采集参数设置报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 短视频采集参数设置报分页列表
     */
    @Override
    public TableDataInfo<VideoCaptureSettingsVo> queryPageList(VideoCaptureSettingsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<VideoCaptureSettings> lqw = buildQueryWrapper(bo);
        Page<VideoCaptureSettingsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的短视频采集参数设置报列表
     *
     * @param bo 查询条件
     * @return 短视频采集参数设置报列表
     */
    @Override
    public List<VideoCaptureSettingsVo> queryList(VideoCaptureSettingsBo bo) {
        LambdaQueryWrapper<VideoCaptureSettings> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<VideoCaptureSettings> buildQueryWrapper(VideoCaptureSettingsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<VideoCaptureSettings> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getChannelNo()), VideoCaptureSettings::getChannelNo, bo.getChannelNo());
        lqw.eq(StringUtils.isNotBlank(bo.getVideoFormat()), VideoCaptureSettings::getVideoFormat, bo.getVideoFormat());
        lqw.eq(StringUtils.isNotBlank(bo.getVideoTime()), VideoCaptureSettings::getVideoTime, bo.getVideoTime());
        return lqw;
    }

    /**
     * 新增短视频采集参数设置报
     *
     * @param bo 短视频采集参数设置报
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(VideoCaptureSettingsBo bo) {


        try {
            VideoCaptureSettingsDto captureSettingsDto = new VideoCaptureSettingsDto();
            BeanUtils.copyProperties(bo, captureSettingsDto);
            VideoCaptureSettingsDto videoCaptureSettingsDto = i12020Service.videoCaptureSettings(bo.getMonitoringDeviceId(), (byte) 0x00,captureSettingsDto);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        VideoCaptureSettings add = MapstructUtils.convert(bo, VideoCaptureSettings.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改短视频采集参数设置报
     *
     * @param bo 短视频采集参数设置报
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(VideoCaptureSettingsBo bo) {
        VideoCaptureSettings update = MapstructUtils.convert(bo, VideoCaptureSettings.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(VideoCaptureSettings entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除短视频采集参数设置报信息
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
    public VideoCaptureSettingsVo queryByMonitoringDeviceId(String monitoringDeviceId, VideoCaptureSettingsDto captureSettingsDto) {
        try {

            VideoCaptureSettingsDto videoCaptureSettingsDto = i12020Service.videoCaptureSettings(monitoringDeviceId, (byte) 0x00,captureSettingsDto);
            VideoCaptureSettingsVo videoCaptureSettingsVo = new VideoCaptureSettingsVo();
            BeanUtils.copyProperties(videoCaptureSettingsDto, videoCaptureSettingsVo);
            return  videoCaptureSettingsVo;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
