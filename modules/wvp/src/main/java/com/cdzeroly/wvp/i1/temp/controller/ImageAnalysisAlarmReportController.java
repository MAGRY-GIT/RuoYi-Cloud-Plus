package com.cdzeroly.wvp.i1.temp.controller;

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
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAnalysisAlarmReportVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageAnalysisAlarmReportBo;
import com.cdzeroly.wvp.i1.temp.service.IImageAnalysisAlarmReportService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 图像分析告警上报报
 * 前端访问路由地址为:/temp/analysisAlarmReport
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/analysisAlarmReport")
public class ImageAnalysisAlarmReportController extends BaseController {

    private final IImageAnalysisAlarmReportService imageAnalysisAlarmReportService;

    /**
     * 查询图像分析告警上报报列表
     */
    @SaCheckPermission("temp:analysisAlarmReport:list")
    @GetMapping("/list")
    public TableDataInfo<ImageAnalysisAlarmReportVo> list(ImageAnalysisAlarmReportBo bo, PageQuery pageQuery) {
        return imageAnalysisAlarmReportService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出图像分析告警上报报列表
     */
    @SaCheckPermission("temp:analysisAlarmReport:export")
    @Log(title = "图像分析告警上报报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImageAnalysisAlarmReportBo bo, HttpServletResponse response) {
        List<ImageAnalysisAlarmReportVo> list = imageAnalysisAlarmReportService.queryList(bo);
        ExcelUtil.exportExcel(list, "图像分析告警上报报", ImageAnalysisAlarmReportVo.class, response);
    }

    /**
     * 获取图像分析告警上报报详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("temp:analysisAlarmReport:query")
    @GetMapping("/{id}")
    public R<ImageAnalysisAlarmReportVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imageAnalysisAlarmReportService.queryById(id));
    }

    /**
     * 新增图像分析告警上报报
     */
    @SaCheckPermission("temp:analysisAlarmReport:add")
    @Log(title = "图像分析告警上报报", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImageAnalysisAlarmReportBo bo) {
        return toAjax(imageAnalysisAlarmReportService.insertByBo(bo));
    }

    /**
     * 修改图像分析告警上报报
     */
    @SaCheckPermission("temp:analysisAlarmReport:edit")
    @Log(title = "图像分析告警上报报", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImageAnalysisAlarmReportBo bo) {
        return toAjax(imageAnalysisAlarmReportService.updateByBo(bo));
    }

    /**
     * 删除图像分析告警上报报
     *
     * @param ids 主键串
     */
    @SaCheckPermission("temp:analysisAlarmReport:remove")
    @Log(title = "图像分析告警上报报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imageAnalysisAlarmReportService.deleteWithValidByIds(List.of(ids), true));
    }
}
