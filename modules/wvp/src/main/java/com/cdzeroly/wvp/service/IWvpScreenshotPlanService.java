package com.cdzeroly.wvp.service;

import com.cdzeroly.wvp.domain.WvpScreenshotPlan;
import com.cdzeroly.wvp.domain.vo.WvpScreenshotPlanVo;
import com.cdzeroly.wvp.domain.bo.WvpScreenshotPlanBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 截图计划Service接口
 *
 * @author MGARY
 * @date 2025-02-28
 */
public interface IWvpScreenshotPlanService {

    /**
     * 查询截图计划
     *
     * @param id 主键
     * @return 截图计划
     */
    WvpScreenshotPlanVo queryById(Long id);

    /**
     * 分页查询截图计划列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 截图计划分页列表
     */
    TableDataInfo<WvpScreenshotPlanVo> queryPageList(WvpScreenshotPlanBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的截图计划列表
     *
     * @param bo 查询条件
     * @return 截图计划列表
     */
    List<WvpScreenshotPlanVo> queryList(WvpScreenshotPlanBo bo);

    /**
     * 新增截图计划
     *
     * @param bo 截图计划
     * @return 是否新增成功
     */
    Boolean insertByBo(WvpScreenshotPlanBo bo);

    /**
     * 修改截图计划
     *
     * @param bo 截图计划
     * @return 是否修改成功
     */
    Boolean updateByBo(WvpScreenshotPlanBo bo);

    /**
     * 校验并批量删除截图计划信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
