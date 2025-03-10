package com.cdzeroly.weather.service;

import com.cdzeroly.weather.domain.GroundInfo;
import com.cdzeroly.weather.domain.vo.GroundInfoVo;
import com.cdzeroly.weather.domain.bo.GroundInfoBo;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * 气象数据Service接口
 *
 * @author MGARY
 * @date 2025-03-07
 */
public interface IGroundInfoService {

    /**
     * 查询气象数据
     *
     * @param id 主键
     * @return 气象数据
     */
    GroundInfoVo queryById(Long id);

    /**
     * 分页查询气象数据列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 气象数据分页列表
     */
    TableDataInfo<GroundInfoVo> queryPageList(GroundInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的气象数据列表
     *
     * @param bo 查询条件
     * @return 气象数据列表
     */
    List<GroundInfoVo> queryList(GroundInfoBo bo);

    /**
     * 新增气象数据
     *
     * @param bo 气象数据
     * @return 是否新增成功
     */
    Boolean insertByBo(GroundInfoBo bo);

    /**
     * 修改气象数据
     *
     * @param bo 气象数据
     * @return 是否修改成功
     */
    Boolean updateByBo(GroundInfoBo bo);

    /**
     * 校验并批量删除气象数据信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 站点数据导入
     * @param file  文件数据
     */
    void importData(MultipartFile file);
}
