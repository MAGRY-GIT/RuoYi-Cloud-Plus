package com.cdzeroly.wvp.i1.temp.service.impl;

import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.wvp.i1.bean.CameraScheduleDto;
import com.cdzeroly.wvp.i1.bean.PhotoTimeTableDto;
import com.cdzeroly.wvp.i1.packet.GCameraSchedulePacket;
import com.cdzeroly.wvp.i1.service.I1Service;
import com.cdzeroly.wvp.i1.temp.domain.vo.PhotoTimeTableVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.cdzeroly.wvp.i1.temp.domain.bo.CameraScheduleBo;
import com.cdzeroly.wvp.i1.temp.domain.vo.CameraScheduleVo;
import com.cdzeroly.wvp.i1.temp.domain.CameraSchedule;
import com.cdzeroly.wvp.i1.temp.mapper.CameraScheduleMapper;
import com.cdzeroly.wvp.i1.temp.service.ICameraScheduleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 摄像机定时工作时间设置Service业务层处理
 *
 * @author MGARY
 * @date 2025-02-20
 */
@RequiredArgsConstructor
@Service
public class CameraScheduleServiceImpl implements ICameraScheduleService {

    private final CameraScheduleMapper baseMapper;
    private final I1Service i1Service;

    /**
     * 查询摄像机定时工作时间设置
     *
     * @param id 主键
     * @return 摄像机定时工作时间设置
     */
    @Override
    public CameraScheduleVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询摄像机定时工作时间设置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 摄像机定时工作时间设置分页列表
     */
    @Override
    public TableDataInfo<CameraScheduleVo> queryPageList(CameraScheduleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CameraSchedule> lqw = buildQueryWrapper(bo);
        Page<CameraScheduleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的摄像机定时工作时间设置列表
     *
     * @param bo 查询条件
     * @return 摄像机定时工作时间设置列表
     */
    @Override
    public List<CameraScheduleVo> queryList(CameraScheduleBo bo) {
        LambdaQueryWrapper<CameraSchedule> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CameraSchedule> buildQueryWrapper(CameraScheduleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CameraSchedule> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getStartTimes()), CameraSchedule::getStartTimes, bo.getStartTimes());
        lqw.eq(StringUtils.isNotBlank(bo.getEndTimes()), CameraSchedule::getEndTimes, bo.getEndTimes());
        return lqw;
    }

    /**
     * 新增摄像机定时工作时间设置
     *
     * @param bo 摄像机定时工作时间设置
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(CameraScheduleBo bo) {
//        try {
//            CameraScheduleDto cameraScheduleDto = new CameraScheduleDto();
//            BeanUtils.copyProperties(bo.getMonitoringDeviceId(), cameraScheduleDto);
//            GCameraSchedulePacket videoCaptureSettingsDto = i1Service.cameraTimerWorkScheduleSettings(bo.getMonitoringDeviceId(), (byte) 0x01,cameraScheduleDto);
//        } catch (ExecutionException | TimeoutException |InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        CameraSchedule add = MapstructUtils.convert(bo, CameraSchedule.class);
        add.setStartTimes(bo.getStartTimes());
        add.setEndTimes(bo.getEndTimes());
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改摄像机定时工作时间设置
     *
     * @param bo 摄像机定时工作时间设置
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(CameraScheduleBo bo) {
        CameraSchedule update = MapstructUtils.convert(bo, CameraSchedule.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CameraSchedule entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除摄像机定时工作时间设置信息
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
    public CameraScheduleVo queryByMonitoringDeviceId(String monitoringDeviceId, CameraScheduleDto cameraScheduleDto) {
        try {

            GCameraSchedulePacket videoCaptureSettingsDto = i1Service.cameraTimerWorkScheduleSettings(monitoringDeviceId, (byte) 0x00,cameraScheduleDto);
            CameraScheduleVo cameraScheduleVo = new CameraScheduleVo();
            BeanUtils.copyProperties(videoCaptureSettingsDto, cameraScheduleVo);
            return  cameraScheduleVo;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
