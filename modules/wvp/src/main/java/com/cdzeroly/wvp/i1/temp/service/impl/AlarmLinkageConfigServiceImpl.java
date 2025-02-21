package com.cdzeroly.wvp.i1.temp.service.impl;

import com.cdzeroly.common.core.utils.MapstructUtils;
import com.cdzeroly.common.core.utils.StringUtils;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cdzeroly.wvp.i1.bean.AlarmLinkageConfigDto;
import com.cdzeroly.wvp.i1.bean.AlarmLinkageParamsQuery;
import com.cdzeroly.wvp.i1.service.I12020Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.cdzeroly.wvp.i1.temp.domain.bo.AlarmLinkageConfigBo;
import com.cdzeroly.wvp.i1.temp.domain.vo.AlarmLinkageConfigVo;
import com.cdzeroly.wvp.i1.temp.domain.AlarmLinkageConfig;
import com.cdzeroly.wvp.i1.temp.mapper.AlarmLinkageConfigMapper;
import com.cdzeroly.wvp.i1.temp.service.IAlarmLinkageConfigService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 监拍装置告警联动参数配置报Service业务层处理
 *
 * @author MGARY
 * @date 2025-02-20
 */
@RequiredArgsConstructor
@Service
public class AlarmLinkageConfigServiceImpl implements IAlarmLinkageConfigService {

    private final AlarmLinkageConfigMapper baseMapper;


    private final I12020Service i12020Service;

    /**
     * 查询监拍装置告警联动参数配置报
     *
     * @param id 主键
     * @return 监拍装置告警联动参数配置报
     */
    @Override
    public AlarmLinkageConfigVo queryById(Long id){

        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询监拍装置告警联动参数配置报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 监拍装置告警联动参数配置报分页列表
     */
    @Override
    public TableDataInfo<AlarmLinkageConfigVo> queryPageList(AlarmLinkageConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AlarmLinkageConfig> lqw = buildQueryWrapper(bo);
        Page<AlarmLinkageConfigVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的监拍装置告警联动参数配置报列表
     *
     * @param bo 查询条件
     * @return 监拍装置告警联动参数配置报列表
     */
    @Override
    public List<AlarmLinkageConfigVo> queryList(AlarmLinkageConfigBo bo) {
        LambdaQueryWrapper<AlarmLinkageConfig> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AlarmLinkageConfig> buildQueryWrapper(AlarmLinkageConfigBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AlarmLinkageConfig> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getChannelNo() != null, AlarmLinkageConfig::getChannelNo, bo.getChannelNo());
        lqw.eq(bo.getPresettingNo() != null, AlarmLinkageConfig::getPresettingNo, bo.getPresettingNo());
        return lqw;
    }

    /**
     * 新增监拍装置告警联动参数配置报
     *
     * @param bo 监拍装置告警联动参数配置报
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(AlarmLinkageConfigBo bo) {
        AlarmLinkageConfig add = MapstructUtils.convert(bo, AlarmLinkageConfig.class);
        AlarmLinkageConfigDto alarmLinkageConfigDto = new AlarmLinkageConfigDto();
        BeanUtils.copyProperties(bo, alarmLinkageConfigDto);
        try {
            i12020Service.alarmLinkageConfig(bo.getMonitoringDeviceId(),alarmLinkageConfigDto);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }


        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改监拍装置告警联动参数配置报
     *
     * @param bo 监拍装置告警联动参数配置报
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(AlarmLinkageConfigBo bo) {
        AlarmLinkageConfig update = MapstructUtils.convert(bo, AlarmLinkageConfig.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AlarmLinkageConfig entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除监拍装置告警联动参数配置报信息
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
    public AlarmLinkageConfigVo queryByMonitoringDeviceId(String monitoringDeviceId, List<AlarmLinkageParamsQuery> alarmLinkageParamsQuery) {
        try {
            AlarmLinkageConfigDto linkageConfig = i12020Service.alarmLinkageParamsQuery(monitoringDeviceId, alarmLinkageParamsQuery);
            AlarmLinkageConfigVo alarmLinkageConfigVo = new AlarmLinkageConfigVo();
            BeanUtils.copyProperties(linkageConfig, alarmLinkageConfigVo);
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
