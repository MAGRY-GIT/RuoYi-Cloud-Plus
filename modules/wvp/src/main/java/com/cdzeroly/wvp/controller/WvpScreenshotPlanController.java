package com.cdzeroly.wvp.controller;

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
import com.cdzeroly.wvp.domain.vo.WvpScreenshotPlanVo;
import com.cdzeroly.wvp.domain.bo.WvpScreenshotPlanBo;
import com.cdzeroly.wvp.service.IWvpScreenshotPlanService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 截图计划
 * 前端访问路由地址为:/wvp/screenshotPlan
 *
 * @author MGARY
 * @date 2025-02-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/screenshotPlan")
public class WvpScreenshotPlanController extends BaseController {

    private final IWvpScreenshotPlanService wvpScreenshotPlanService;

    /**
     * 查询截图计划列表
     */
    @SaCheckPermission("wvp:screenshotPlan:list")
    @GetMapping("/list")
    public TableDataInfo<WvpScreenshotPlanVo> list(WvpScreenshotPlanBo bo, PageQuery pageQuery) {
        return wvpScreenshotPlanService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出截图计划列表
     */
    @SaCheckPermission("wvp:screenshotPlan:export")
    @Log(title = "截图计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WvpScreenshotPlanBo bo, HttpServletResponse response) {
        List<WvpScreenshotPlanVo> list = wvpScreenshotPlanService.queryList(bo);
        ExcelUtil.exportExcel(list, "截图计划", WvpScreenshotPlanVo.class, response);
    }

    /**
     * 获取截图计划详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wvp:screenshotPlan:query")
    @GetMapping("/{id}")
    public R<WvpScreenshotPlanVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(wvpScreenshotPlanService.queryById(id));
    }

    /**
     * 新增截图计划
     */
    @SaCheckPermission("wvp:screenshotPlan:add")
    @Log(title = "截图计划", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WvpScreenshotPlanBo bo) {
        return toAjax(wvpScreenshotPlanService.insertByBo(bo));
    }

    /**
     * 修改截图计划
     */
    @SaCheckPermission("wvp:screenshotPlan:edit")
    @Log(title = "截图计划", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WvpScreenshotPlanBo bo) {
        return toAjax(wvpScreenshotPlanService.updateByBo(bo));
    }

    /**
     * 删除截图计划
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wvp:screenshotPlan:remove")
    @Log(title = "截图计划", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(wvpScreenshotPlanService.deleteWithValidByIds(List.of(ids), true));
    }
}
