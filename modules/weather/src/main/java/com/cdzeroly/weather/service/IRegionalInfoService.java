package com.cdzeroly.weather.service;

import com.cdzeroly.weather.domain.RegionalInfo;
import com.cdzeroly.weather.domain.vo.RegionalInfoVo;
import com.cdzeroly.weather.domain.bo.RegionalInfoBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 区域信息Service接口
 *
 * @author MGARY
 * @date 2025-03-20
 */
public interface IRegionalInfoService {

    /**
     * 查询区域信息
     *
     * @param id 主键
     * @return 区域信息
     */
    RegionalInfoVo queryById(Long id);

    /**
     * 分页查询区域信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 区域信息分页列表
     */
    TableDataInfo<RegionalInfoVo> queryPageList(RegionalInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的区域信息列表
     *
     * @param bo 查询条件
     * @return 区域信息列表
     */
    List<RegionalInfoVo> queryList(RegionalInfoBo bo);

    /**
     * 新增区域信息
     *
     * @param bo 区域信息
     * @return 是否新增成功
     */
    Boolean insertByBo(RegionalInfoBo bo);

    /**
     * 修改区域信息
     *
     * @param bo 区域信息
     * @return 是否修改成功
     */
    Boolean updateByBo(RegionalInfoBo bo);

    /**
     * 校验并批量删除区域信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
