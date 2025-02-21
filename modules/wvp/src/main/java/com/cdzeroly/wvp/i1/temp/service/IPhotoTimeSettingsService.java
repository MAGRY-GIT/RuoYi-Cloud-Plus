package com.cdzeroly.wvp.i1.temp.service;

import com.cdzeroly.wvp.i1.bean.PhotoTimeTableV2020;
import com.cdzeroly.wvp.i1.temp.domain.vo.PhotoTimeSettingsVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.PhotoTimeSettingsBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * 拍照时间设置报Service接口
 *
 * @author MGARY
 * @date 2025-02-20
 */
public interface IPhotoTimeSettingsService {

    /**
     * 查询拍照时间设置报
     *
     * @param id 主键
     * @return 拍照时间设置报
     */
    PhotoTimeSettingsVo queryById(Long id);

    /**
     * 分页查询拍照时间设置报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 拍照时间设置报分页列表
     */
    TableDataInfo<PhotoTimeSettingsVo> queryPageList(PhotoTimeSettingsBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的拍照时间设置报列表
     *
     * @param bo 查询条件
     * @return 拍照时间设置报列表
     */
    List<PhotoTimeSettingsVo> queryList(PhotoTimeSettingsBo bo);

    /**
     * 新增拍照时间设置报
     *
     * @param bo 拍照时间设置报
     * @return 是否新增成功
     */
    Boolean insertByBo(PhotoTimeSettingsBo bo);

    /**
     * 修改拍照时间设置报
     *
     * @param bo 拍照时间设置报
     * @return 是否修改成功
     */
    Boolean updateByBo(PhotoTimeSettingsBo bo);

    /**
     * 校验并批量删除拍照时间设置报信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    PhotoTimeSettingsVo queryByMonitoringDeviceId(@NotNull(message = "设备ID不能为空") String monitoringDeviceId, PhotoTimeTableV2020 photoTimeTableV2022);
}
