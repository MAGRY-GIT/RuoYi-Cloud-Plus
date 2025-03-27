package com.cdzeroly.weather.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.cdzeroly.common.idempotent.annotation.RepeatSubmit;
import com.cdzeroly.common.log.annotation.Log;
import com.cdzeroly.common.web.core.BaseController;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
import com.cdzeroly.common.log.enums.BusinessType;
import com.cdzeroly.common.excel.utils.ExcelUtil;
import com.cdzeroly.weather.domain.vo.RegionalInfoVo;
import com.cdzeroly.weather.domain.bo.RegionalInfoBo;
import com.cdzeroly.weather.service.IRegionalInfoService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 区域信息
 * 前端访问路由地址为:/weather/regionalInfo
 *
 * @author MGARY
 * @date 2025-03-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/regionalInfo")
public class RegionalInfoController extends BaseController {

    private final IRegionalInfoService regionalInfoService;

    /**
     * 查询区域信息列表
     */
    @SaCheckPermission("weather:regionalInfo:list")
    @GetMapping("/list")
    public TableDataInfo<RegionalInfoVo> list(RegionalInfoBo bo, PageQuery pageQuery) {
        return regionalInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出区域信息列表
     */
    @SaCheckPermission("weather:regionalInfo:export")
    @Log(title = "区域信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(RegionalInfoBo bo, HttpServletResponse response) {
        List<RegionalInfoVo> list = regionalInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "区域信息", RegionalInfoVo.class, response);
    }

    /**
     * 获取区域信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("weather:regionalInfo:query")
    @GetMapping("/{id}")
    public R<RegionalInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(regionalInfoService.queryById(id));
    }

    /**
     * 新增区域信息
     */
    @SaCheckPermission("weather:regionalInfo:add")
    @Log(title = "区域信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody RegionalInfoBo bo) {
        return toAjax(regionalInfoService.insertByBo(bo));
    }

    /**
     * 修改区域信息
     */
    @SaCheckPermission("weather:regionalInfo:edit")
    @Log(title = "区域信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody RegionalInfoBo bo) {
        return toAjax(regionalInfoService.updateByBo(bo));
    }

    /**
     * 删除区域信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("weather:regionalInfo:remove")
    @Log(title = "区域信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(regionalInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
