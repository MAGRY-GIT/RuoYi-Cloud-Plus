package com.cdzeroly.weather.service;

import com.cdzeroly.weather.domain.GroundStation;
import com.cdzeroly.weather.domain.vo.GroundStationVo;
import com.cdzeroly.weather.domain.bo.GroundStationBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 中国地面气象站点Service接口
 *
 * @author MGARY
 * @date 2025-01-10
 */
public interface IGroundStationService {

    /**
     * 查询中国地面气象站点
     *
     * @param id 主键
     * @return 中国地面气象站点
     */
    GroundStationVo queryById(Long id);

    /**
     * 分页查询中国地面气象站点列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 中国地面气象站点分页列表
     */
    TableDataInfo<GroundStationVo> queryPageList(GroundStationBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的中国地面气象站点列表
     *
     * @param bo 查询条件
     * @return 中国地面气象站点列表
     */
    List<GroundStationVo> queryList(GroundStationBo bo);

    /**
     * 新增中国地面气象站点
     *
     * @param bo 中国地面气象站点
     * @return 是否新增成功
     */
    Boolean insertByBo(GroundStationBo bo);

    /**
     * 修改中国地面气象站点
     *
     * @param bo 中国地面气象站点
     * @return 是否修改成功
     */
    Boolean updateByBo(GroundStationBo bo);

    /**
     * 校验并批量删除中国地面气象站点信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    void saveBatch(List<GroundStation> list);
}
