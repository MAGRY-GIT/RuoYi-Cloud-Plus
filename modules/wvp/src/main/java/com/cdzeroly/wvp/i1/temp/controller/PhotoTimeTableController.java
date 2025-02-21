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
import com.cdzeroly.wvp.i1.temp.domain.vo.PhotoTimeTableVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.PhotoTimeTableBo;
import com.cdzeroly.wvp.i1.temp.service.IPhotoTimeTableService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 拍照时间设置
 * 前端访问路由地址为:/temp/timeTable
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/timeTable")
public class PhotoTimeTableController extends BaseController {

    private final IPhotoTimeTableService photoTimeTableService;

    /**
     * 查询拍照时间设置列表
     */
    @SaCheckPermission("temp:timeTable:list")
    @GetMapping("/list")
    public TableDataInfo<PhotoTimeTableVo> list(PhotoTimeTableBo bo, PageQuery pageQuery) {
        return photoTimeTableService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出拍照时间设置列表
     */
    @SaCheckPermission("temp:timeTable:export")
    @Log(title = "拍照时间设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PhotoTimeTableBo bo, HttpServletResponse response) {
        List<PhotoTimeTableVo> list = photoTimeTableService.queryList(bo);
        ExcelUtil.exportExcel(list, "拍照时间设置", PhotoTimeTableVo.class, response);
    }

    /**
     * 获取拍照时间设置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("temp:timeTable:query")
    @GetMapping("/{id}")
    public R<PhotoTimeTableVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(photoTimeTableService.queryById(id));
    }

    /**
     * 新增拍照时间设置
     */
    @SaCheckPermission("temp:timeTable:add")
    @Log(title = "拍照时间设置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PhotoTimeTableBo bo) {
        return toAjax(photoTimeTableService.insertByBo(bo));
    }

    /**
     * 修改拍照时间设置
     */
    @SaCheckPermission("temp:timeTable:edit")
    @Log(title = "拍照时间设置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PhotoTimeTableBo bo) {
        return toAjax(photoTimeTableService.updateByBo(bo));
    }

    /**
     * 删除拍照时间设置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("temp:timeTable:remove")
    @Log(title = "拍照时间设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(photoTimeTableService.deleteWithValidByIds(List.of(ids), true));
    }
}
