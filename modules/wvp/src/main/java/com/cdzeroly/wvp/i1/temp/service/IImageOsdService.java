package com.cdzeroly.wvp.i1.temp.service;

import com.cdzeroly.wvp.i1.bean.ImageOSD;
import com.cdzeroly.wvp.i1.temp.domain.ImageOsd;
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageOsdVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageOsdBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * 图像OSD查询/设置报Service接口
 *
 * @author MGARY
 * @date 2025-02-20
 */
public interface IImageOsdService {

    /**
     * 查询图像OSD查询/设置报
     *
     * @param id 主键
     * @return 图像OSD查询/设置报
     */
    ImageOsdVo queryById(Long id);

    /**
     * 分页查询图像OSD查询/设置报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 图像OSD查询/设置报分页列表
     */
    TableDataInfo<ImageOsdVo> queryPageList(ImageOsdBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的图像OSD查询/设置报列表
     *
     * @param bo 查询条件
     * @return 图像OSD查询/设置报列表
     */
    List<ImageOsdVo> queryList(ImageOsdBo bo);

    /**
     * 新增图像OSD查询/设置报
     *
     * @param bo 图像OSD查询/设置报
     * @return 是否新增成功
     */
    Boolean insertByBo(ImageOsdBo bo);

    /**
     * 修改图像OSD查询/设置报
     *
     * @param bo 图像OSD查询/设置报
     * @return 是否修改成功
     */
    Boolean updateByBo(ImageOsdBo bo);

    /**
     * 校验并批量删除图像OSD查询/设置报信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    ImageOsdVo queryByMonitoringDeviceId(@NotNull(message = "设备ID不能为空") String monitoringDeviceId, ImageOSD osd);
}
