package com.cdzeroly.wvp.i1.temp.controller;

import java.util.List;

import com.cdzeroly.wvp.i1.bean.ImageAnalysisParamsQuery;
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAnalysisParamsVo;
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
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAnalysisTypeVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageAnalysisTypeBo;
import com.cdzeroly.wvp.i1.temp.service.IImageAnalysisTypeService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 图像分析类型查询报
 * 前端访问路由地址为:/temp/analysisType
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/analysisType")
public class ImageAnalysisTypeController extends BaseController {

    private final IImageAnalysisTypeService imageAnalysisTypeService;

    /**
     * 查询图像分析类型查询报列表
     */
    @SaCheckPermission("temp:analysisType:list")
    @GetMapping("/list")
    public TableDataInfo<ImageAnalysisTypeVo> list(ImageAnalysisTypeBo bo, PageQuery pageQuery) {
        return imageAnalysisTypeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出图像分析类型查询报列表
     */
    @SaCheckPermission("temp:analysisType:export")
    @Log(title = "图像分析类型查询报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImageAnalysisTypeBo bo, HttpServletResponse response) {
        List<ImageAnalysisTypeVo> list = imageAnalysisTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "图像分析类型查询报", ImageAnalysisTypeVo.class, response);
    }

    /**
     * 获取图像分析类型查询报详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("temp:analysisType:query")
    @GetMapping("/{id}")
    public R<ImageAnalysisTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imageAnalysisTypeService.queryById(id));
    }

    /**
     * 新增图像分析类型查询报
     */
    @SaCheckPermission("temp:analysisType:add")
    @Log(title = "图像分析类型查询报", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImageAnalysisTypeBo bo) {
        return toAjax(imageAnalysisTypeService.insertByBo(bo));
    }


    /**
     * 获取图像分析类型详细信息
     *
     * @param monitoringDeviceId 主键
     */
    @GetMapping("/find/{monitoringDeviceId}")
    public R<ImageAnalysisTypeVo> find(@NotNull(message = "设备ID不能为空") @PathVariable String monitoringDeviceId, List<Integer> dataSources) {
        return R.ok(imageAnalysisTypeService.queryByMonitoringDeviceId(monitoringDeviceId,dataSources));
    }

    /**
     * 修改图像分析类型查询报
     */
    @SaCheckPermission("temp:analysisType:edit")
    @Log(title = "图像分析类型查询报", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImageAnalysisTypeBo bo) {
        return toAjax(imageAnalysisTypeService.updateByBo(bo));
    }

    /**
     * 删除图像分析类型查询报
     *
     * @param ids 主键串
     */
    @SaCheckPermission("temp:analysisType:remove")
    @Log(title = "图像分析类型查询报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imageAnalysisTypeService.deleteWithValidByIds(List.of(ids), true));
    }
}
