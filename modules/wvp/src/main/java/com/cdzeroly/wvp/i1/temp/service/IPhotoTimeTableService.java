package com.cdzeroly.wvp.i1.temp.service;

import com.cdzeroly.wvp.i1.bean.PhotoTimeTableDto;
import com.cdzeroly.wvp.i1.temp.domain.vo.PhotoTimeTableVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.PhotoTimeTableBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * 拍照时间设置Service接口
 *
 * @author MGARY
 * @date 2025-02-20
 */
public interface IPhotoTimeTableService {

    /**
     * 查询拍照时间设置
     *
     * @param id 主键
     * @return 拍照时间设置
     */
    PhotoTimeTableVo queryById(Long id);

    /**
     * 分页查询拍照时间设置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 拍照时间设置分页列表
     */
    TableDataInfo<PhotoTimeTableVo> queryPageList(PhotoTimeTableBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的拍照时间设置列表
     *
     * @param bo 查询条件
     * @return 拍照时间设置列表
     */
    List<PhotoTimeTableVo> queryList(PhotoTimeTableBo bo);

    /**
     * 新增拍照时间设置
     *
     * @param bo 拍照时间设置
     * @return 是否新增成功
     */
    Boolean insertByBo(PhotoTimeTableBo bo);

    /**
     * 修改拍照时间设置
     *
     * @param bo 拍照时间设置
     * @return 是否修改成功
     */
    Boolean updateByBo(PhotoTimeTableBo bo);

    /**
     * 校验并批量删除拍照时间设置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    PhotoTimeTableVo queryByMonitoringDeviceId(@NotNull(message = "设备ID不能为空") String monitoringDeviceId, PhotoTimeTableDto photoTimeTable);
}
