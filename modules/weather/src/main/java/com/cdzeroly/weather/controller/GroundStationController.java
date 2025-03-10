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
import com.cdzeroly.weather.domain.vo.GroundStationVo;
import com.cdzeroly.weather.domain.bo.GroundStationBo;
import com.cdzeroly.weather.service.IGroundStationService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 中国地面气象站点
 * 前端访问路由地址为:/weather/groundStation
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/groundStation")
public class GroundStationController extends BaseController {

    private final IGroundStationService groundStationService;

    /**
     * 查询中国地面气象站点列表
     */
    @SaCheckPermission("weather:groundStation:list")
    @GetMapping("/list")
    public TableDataInfo<GroundStationVo> list(GroundStationBo bo, PageQuery pageQuery) {
        return groundStationService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出中国地面气象站点列表
     */
    @SaCheckPermission("weather:groundStation:export")
    @Log(title = "中国地面气象站点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GroundStationBo bo, HttpServletResponse response) {
        List<GroundStationVo> list = groundStationService.queryList(bo);
        ExcelUtil.exportExcel(list, "中国地面气象站点", GroundStationVo.class, response);
    }

    /**
     * 获取中国地面气象站点详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("weather:groundStation:query")
    @GetMapping("/{id}")
    public R<GroundStationVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(groundStationService.queryById(id));
    }

    /**
     * 新增中国地面气象站点
     */
    @SaCheckPermission("weather:groundStation:add")
    @Log(title = "中国地面气象站点", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GroundStationBo bo) {
        return toAjax(groundStationService.insertByBo(bo));
    }

    /**
     * 修改中国地面气象站点
     */
    @SaCheckPermission("weather:groundStation:edit")
    @Log(title = "中国地面气象站点", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GroundStationBo bo) {
        return toAjax(groundStationService.updateByBo(bo));
    }

    /**
     * 删除中国地面气象站点
     *
     * @param ids 主键串
     */
    @SaCheckPermission("weather:groundStation:remove")
    @Log(title = "中国地面气象站点", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(groundStationService.deleteWithValidByIds(List.of(ids), true));
    }
}
