package com.cdzeroly.wvp.i1.temp.service;

import com.cdzeroly.wvp.i1.temp.domain.ImageAcquisition;
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAcquisitionVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageAcquisitionBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 图像采集参数设置Service接口
 *
 * @author MGARY
 * @date 2025-02-20
 */
public interface IImageAcquisitionService {

    /**
     * 查询图像采集参数设置
     *
     * @param id 主键
     * @return 图像采集参数设置
     */
    ImageAcquisitionVo queryById(Long id);

    /**
     * 分页查询图像采集参数设置列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 图像采集参数设置分页列表
     */
    TableDataInfo<ImageAcquisitionVo> queryPageList(ImageAcquisitionBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的图像采集参数设置列表
     *
     * @param bo 查询条件
     * @return 图像采集参数设置列表
     */
    List<ImageAcquisitionVo> queryList(ImageAcquisitionBo bo);

    /**
     * 新增图像采集参数设置
     *
     * @param bo 图像采集参数设置
     * @return 是否新增成功
     */
    Boolean insertByBo(ImageAcquisitionBo bo);

    /**
     * 修改图像采集参数设置
     *
     * @param bo 图像采集参数设置
     * @return 是否修改成功
     */
    Boolean updateByBo(ImageAcquisitionBo bo);

    /**
     * 校验并批量删除图像采集参数设置信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
