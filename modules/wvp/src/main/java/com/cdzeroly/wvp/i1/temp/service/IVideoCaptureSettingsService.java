package com.cdzeroly.wvp.i1.temp.service;

import com.cdzeroly.wvp.i1.temp.domain.VideoCaptureSettings;
import com.cdzeroly.wvp.i1.temp.domain.vo.VideoCaptureSettingsVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.VideoCaptureSettingsBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 短视频采集参数设置报Service接口
 *
 * @author MGARY
 * @date 2025-02-20
 */
public interface IVideoCaptureSettingsService {

    /**
     * 查询短视频采集参数设置报
     *
     * @param id 主键
     * @return 短视频采集参数设置报
     */
    VideoCaptureSettingsVo queryById(Long id);

    /**
     * 分页查询短视频采集参数设置报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 短视频采集参数设置报分页列表
     */
    TableDataInfo<VideoCaptureSettingsVo> queryPageList(VideoCaptureSettingsBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的短视频采集参数设置报列表
     *
     * @param bo 查询条件
     * @return 短视频采集参数设置报列表
     */
    List<VideoCaptureSettingsVo> queryList(VideoCaptureSettingsBo bo);

    /**
     * 新增短视频采集参数设置报
     *
     * @param bo 短视频采集参数设置报
     * @return 是否新增成功
     */
    Boolean insertByBo(VideoCaptureSettingsBo bo);

    /**
     * 修改短视频采集参数设置报
     *
     * @param bo 短视频采集参数设置报
     * @return 是否修改成功
     */
    Boolean updateByBo(VideoCaptureSettingsBo bo);

    /**
     * 校验并批量删除短视频采集参数设置报信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
