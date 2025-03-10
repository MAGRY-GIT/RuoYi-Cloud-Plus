package com.cdzeroly.weather.service;

import com.cdzeroly.weather.domain.StationData;
import com.cdzeroly.weather.domain.vo.StationDataVo;
import com.cdzeroly.weather.domain.bo.StationDataBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * 地面站点数据Service接口
 *
 * @author MGARY
 * @date 2025-03-07
 */
public interface IStationDataService {

    /**
     * 查询地面站点数据
     *
     * @param id 主键
     * @return 地面站点数据
     */
    StationDataVo queryById(Long id) throws Exception;

    /**
     * 分页查询地面站点数据列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 地面站点数据分页列表
     */
    TableDataInfo<StationDataVo> queryPageList(StationDataBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的地面站点数据列表
     *
     * @param bo 查询条件
     * @return 地面站点数据列表
     */
    List<StationDataVo> queryList(StationDataBo bo);

    /**
     * 新增地面站点数据
     *
     * @param bo 地面站点数据
     * @return 是否新增成功
     */
    Boolean insertByBo(StationDataBo bo);

    /**
     * 修改地面站点数据
     *
     * @param bo 地面站点数据
     * @return 是否修改成功
     */
    Boolean updateByBo(StationDataBo bo);

    /**
     * 校验并批量删除地面站点数据信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    void importData(MultipartFile file);
}
