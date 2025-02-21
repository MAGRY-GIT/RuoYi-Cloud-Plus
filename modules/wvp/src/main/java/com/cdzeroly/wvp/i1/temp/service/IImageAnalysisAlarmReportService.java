package com.cdzeroly.wvp.i1.temp.service;

import com.cdzeroly.wvp.i1.temp.domain.ImageAnalysisAlarmReport;
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAnalysisAlarmReportVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageAnalysisAlarmReportBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 图像分析告警上报报Service接口
 *
 * @author MGARY
 * @date 2025-02-20
 */
public interface IImageAnalysisAlarmReportService {

    /**
     * 查询图像分析告警上报报
     *
     * @param id 主键
     * @return 图像分析告警上报报
     */
    ImageAnalysisAlarmReportVo queryById(Long id);

    /**
     * 分页查询图像分析告警上报报列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 图像分析告警上报报分页列表
     */
    TableDataInfo<ImageAnalysisAlarmReportVo> queryPageList(ImageAnalysisAlarmReportBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的图像分析告警上报报列表
     *
     * @param bo 查询条件
     * @return 图像分析告警上报报列表
     */
    List<ImageAnalysisAlarmReportVo> queryList(ImageAnalysisAlarmReportBo bo);

    /**
     * 新增图像分析告警上报报
     *
     * @param bo 图像分析告警上报报
     * @return 是否新增成功
     */
    Boolean insertByBo(ImageAnalysisAlarmReportBo bo);

    /**
     * 修改图像分析告警上报报
     *
     * @param bo 图像分析告警上报报
     * @return 是否修改成功
     */
    Boolean updateByBo(ImageAnalysisAlarmReportBo bo);

    /**
     * 校验并批量删除图像分析告警上报报信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
