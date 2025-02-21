package com.cdzeroly.wvp.i1.temp.service;

import com.cdzeroly.wvp.i1.bean.CameraScheduleDto;
import com.cdzeroly.wvp.i1.temp.domain.CameraSchedule;
import com.cdzeroly.wvp.i1.temp.domain.vo.CameraScheduleVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.CameraScheduleBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * 摄像机定时工作时间设置Service接口
 *
 * @author MGARY
 * @date 2025-02-20
 */
public interface ICameraScheduleService {

    /**
     * 查询摄像机定时工作时间设置
     *
     * @param id 主键
     * @return 摄像机定时工作时间设置
     */
    CameraScheduleVo queryById(Long id);

    /**
     * 分页查询摄像机定时工作时间设置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 摄像机定时工作时间设置分页列表
     */
    TableDataInfo<CameraScheduleVo> queryPageList(CameraScheduleBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的摄像机定时工作时间设置列表
     *
     * @param bo 查询条件
     * @return 摄像机定时工作时间设置列表
     */
    List<CameraScheduleVo> queryList(CameraScheduleBo bo);

    /**
     * 新增摄像机定时工作时间设置
     *
     * @param bo 摄像机定时工作时间设置
     * @return 是否新增成功
     */
    Boolean insertByBo(CameraScheduleBo bo);

    /**
     * 修改摄像机定时工作时间设置
     *
     * @param bo 摄像机定时工作时间设置
     * @return 是否修改成功
     */
    Boolean updateByBo(CameraScheduleBo bo);

    /**
     * 校验并批量删除摄像机定时工作时间设置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    CameraScheduleVo queryByMonitoringDeviceId(@NotNull(message = "设备ID不能为空") String monitoringDeviceId, CameraScheduleDto cameraScheduleDto);
}
