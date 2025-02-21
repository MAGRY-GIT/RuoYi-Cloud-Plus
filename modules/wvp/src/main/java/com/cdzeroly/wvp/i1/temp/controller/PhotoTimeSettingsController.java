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
import com.cdzeroly.wvp.i1.temp.domain.vo.PhotoTimeSettingsVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.PhotoTimeSettingsBo;
import com.cdzeroly.wvp.i1.temp.service.IPhotoTimeSettingsService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 拍照时间设置报
 * 前端访问路由地址为:/temp/timeSettings
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/timeSettings")
public class PhotoTimeSettingsController extends BaseController {

    private final IPhotoTimeSettingsService photoTimeSettingsService;

    /**
     * 查询拍照时间设置报列表
     */
    @SaCheckPermission("temp:timeSettings:list")
    @GetMapping("/list")
    public TableDataInfo<PhotoTimeSettingsVo> list(PhotoTimeSettingsBo bo, PageQuery pageQuery) {
        return photoTimeSettingsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出拍照时间设置报列表
     */
    @SaCheckPermission("temp:timeSettings:export")
    @Log(title = "拍照时间设置报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PhotoTimeSettingsBo bo, HttpServletResponse response) {
        List<PhotoTimeSettingsVo> list = photoTimeSettingsService.queryList(bo);
        ExcelUtil.exportExcel(list, "拍照时间设置报", PhotoTimeSettingsVo.class, response);
    }

    /**
     * 获取拍照时间设置报详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("temp:timeSettings:query")
    @GetMapping("/{id}")
    public R<PhotoTimeSettingsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(photoTimeSettingsService.queryById(id));
    }

    /**
     * 新增拍照时间设置报
     */
    @SaCheckPermission("temp:timeSettings:add")
    @Log(title = "拍照时间设置报", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PhotoTimeSettingsBo bo) {
        return toAjax(photoTimeSettingsService.insertByBo(bo));
    }

    /**
     * 修改拍照时间设置报
     */
    @SaCheckPermission("temp:timeSettings:edit")
    @Log(title = "拍照时间设置报", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PhotoTimeSettingsBo bo) {
        return toAjax(photoTimeSettingsService.updateByBo(bo));
    }

    /**
     * 删除拍照时间设置报
     *
     * @param ids 主键串
     */
    @SaCheckPermission("temp:timeSettings:remove")
    @Log(title = "拍照时间设置报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(photoTimeSettingsService.deleteWithValidByIds(List.of(ids), true));
    }
}
