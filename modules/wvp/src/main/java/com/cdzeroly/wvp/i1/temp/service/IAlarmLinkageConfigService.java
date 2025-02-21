package com.cdzeroly.wvp.i1.temp.service;

import com.cdzeroly.wvp.i1.bean.AlarmLinkageParamsQuery;
import com.cdzeroly.wvp.i1.temp.domain.AlarmLinkageConfig;
import com.cdzeroly.wvp.i1.temp.domain.vo.AlarmLinkageConfigVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.AlarmLinkageConfigBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * 监拍装置告警联动参数配置报Service接口
 *
 * @author MGARY
 * @date 2025-02-20
 */
public interface IAlarmLinkageConfigService {

    /**
     * 查询监拍装置告警联动参数配置报
     *
     * @param id 主键
     * @return 监拍装置告警联动参数配置报
     */
    AlarmLinkageConfigVo queryById(Long id);

    /**
     * 分页查询监拍装置告警联动参数配置报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 监拍装置告警联动参数配置报分页列表
     */
    TableDataInfo<AlarmLinkageConfigVo> queryPageList(AlarmLinkageConfigBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的监拍装置告警联动参数配置报列表
     *
     * @param bo 查询条件
     * @return 监拍装置告警联动参数配置报列表
     */
    List<AlarmLinkageConfigVo> queryList(AlarmLinkageConfigBo bo);

    /**
     * 新增监拍装置告警联动参数配置报
     *
     * @param bo 监拍装置告警联动参数配置报
     * @return 是否新增成功
     */
    Boolean insertByBo(AlarmLinkageConfigBo bo);

    /**
     * 修改监拍装置告警联动参数配置报
     *
     * @param bo 监拍装置告警联动参数配置报
     * @return 是否修改成功
     */
    Boolean updateByBo(AlarmLinkageConfigBo bo);

    /**
     * 校验并批量删除监拍装置告警联动参数配置报信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    AlarmLinkageConfigVo queryByMonitoringDeviceId( String monitoringDeviceId, List<AlarmLinkageParamsQuery> alarmLinkageParamsQuery);
}
