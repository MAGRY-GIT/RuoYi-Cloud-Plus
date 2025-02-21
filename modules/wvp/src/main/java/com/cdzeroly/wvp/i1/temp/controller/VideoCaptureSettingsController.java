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
import com.cdzeroly.wvp.i1.temp.domain.vo.VideoCaptureSettingsVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.VideoCaptureSettingsBo;
import com.cdzeroly.wvp.i1.temp.service.IVideoCaptureSettingsService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 短视频采集参数设置报
 * 前端访问路由地址为:/temp/captureSettings
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/captureSettings")
public class VideoCaptureSettingsController extends BaseController {

    private final IVideoCaptureSettingsService videoCaptureSettingsService;

    /**
     * 查询短视频采集参数设置报列表
     */
    @SaCheckPermission("temp:captureSettings:list")
    @GetMapping("/list")
    public TableDataInfo<VideoCaptureSettingsVo> list(VideoCaptureSettingsBo bo, PageQuery pageQuery) {
        return videoCaptureSettingsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出短视频采集参数设置报列表
     */
    @SaCheckPermission("temp:captureSettings:export")
    @Log(title = "短视频采集参数设置报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(VideoCaptureSettingsBo bo, HttpServletResponse response) {
        List<VideoCaptureSettingsVo> list = videoCaptureSettingsService.queryList(bo);
        ExcelUtil.exportExcel(list, "短视频采集参数设置报", VideoCaptureSettingsVo.class, response);
    }

    /**
     * 获取短视频采集参数设置报详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("temp:captureSettings:query")
    @GetMapping("/{id}")
    public R<VideoCaptureSettingsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(videoCaptureSettingsService.queryById(id));
    }

    /**
     * 新增短视频采集参数设置报
     */
    @SaCheckPermission("temp:captureSettings:add")
    @Log(title = "短视频采集参数设置报", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody VideoCaptureSettingsBo bo) {
        return toAjax(videoCaptureSettingsService.insertByBo(bo));
    }

    /**
     * 修改短视频采集参数设置报
     */
    @SaCheckPermission("temp:captureSettings:edit")
    @Log(title = "短视频采集参数设置报", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody VideoCaptureSettingsBo bo) {
        return toAjax(videoCaptureSettingsService.updateByBo(bo));
    }

    /**
     * 删除短视频采集参数设置报
     *
     * @param ids 主键串
     */
    @SaCheckPermission("temp:captureSettings:remove")
    @Log(title = "短视频采集参数设置报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(videoCaptureSettingsService.deleteWithValidByIds(List.of(ids), true));
    }
}
