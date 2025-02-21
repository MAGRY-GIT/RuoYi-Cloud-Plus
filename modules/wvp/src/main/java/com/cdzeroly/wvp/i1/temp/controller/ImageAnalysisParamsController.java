package com.cdzeroly.wvp.i1.temp.controller;

import java.util.List;

import com.cdzeroly.wvp.i1.bean.AlarmLinkageParamsQuery;
import com.cdzeroly.wvp.i1.bean.ImageAnalysisParamsQuery;
import com.cdzeroly.wvp.i1.temp.domain.vo.AlarmLinkageConfigVo;
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
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAnalysisParamsVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageAnalysisParamsBo;
import com.cdzeroly.wvp.i1.temp.service.IImageAnalysisParamsService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 图像分析参数设置报
 * 前端访问路由地址为:/temp/analysisParams
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/analysisParams")
public class ImageAnalysisParamsController extends BaseController {

    private final IImageAnalysisParamsService imageAnalysisParamsService;

    /**
     * 查询图像分析参数设置报列表
     */
    @SaCheckPermission("temp:analysisParams:list")
    @GetMapping("/list")
    public TableDataInfo<ImageAnalysisParamsVo> list(ImageAnalysisParamsBo bo, PageQuery pageQuery) {
        return imageAnalysisParamsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出图像分析参数设置报列表
     */
    @SaCheckPermission("temp:analysisParams:export")
    @Log(title = "图像分析参数设置报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImageAnalysisParamsBo bo, HttpServletResponse response) {
        List<ImageAnalysisParamsVo> list = imageAnalysisParamsService.queryList(bo);
        ExcelUtil.exportExcel(list, "图像分析参数设置报", ImageAnalysisParamsVo.class, response);
    }

    /**
     * 获取图像分析参数设置报详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("temp:analysisParams:query")
    @GetMapping("/{id}")
    public R<ImageAnalysisParamsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imageAnalysisParamsService.queryById(id));
    }

    /**
     * 获取图像分析参数详细信息
     *
     * @param monitoringDeviceId 主键
     */
    @GetMapping("/find/{monitoringDeviceId}")
    public R<ImageAnalysisParamsVo> find(@NotNull(message = "设备ID不能为空") @PathVariable String monitoringDeviceId, List<ImageAnalysisParamsQuery> alarmLinkageParamsQuery) {
        return R.ok(imageAnalysisParamsService.queryByMonitoringDeviceId(monitoringDeviceId,alarmLinkageParamsQuery));
    }
    /**
     * 新增图像分析参数设置报
     */
    @SaCheckPermission("temp:analysisParams:add")
    @Log(title = "图像分析参数设置报", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImageAnalysisParamsBo bo) {
        return toAjax(imageAnalysisParamsService.insertByBo(bo));
    }

    /**
     * 修改图像分析参数设置报
     */
    @SaCheckPermission("temp:analysisParams:edit")
    @Log(title = "图像分析参数设置报", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImageAnalysisParamsBo bo) {
        return toAjax(imageAnalysisParamsService.updateByBo(bo));
    }

    /**
     * 删除图像分析参数设置报
     *
     * @param ids 主键串
     */
    @SaCheckPermission("temp:analysisParams:remove")
    @Log(title = "图像分析参数设置报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imageAnalysisParamsService.deleteWithValidByIds(List.of(ids), true));
    }
}
