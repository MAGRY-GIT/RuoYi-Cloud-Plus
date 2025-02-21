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
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageOsdVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageOsdBo;
import com.cdzeroly.wvp.i1.temp.service.IImageOsdService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 图像OSD查询/设置报
 * 前端访问路由地址为:/temp/osd
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/osd")
public class ImageOsdController extends BaseController {

    private final IImageOsdService imageOsdService;

    /**
     * 查询图像OSD查询/设置报列表
     */
    @SaCheckPermission("temp:osd:list")
    @GetMapping("/list")
    public TableDataInfo<ImageOsdVo> list(ImageOsdBo bo, PageQuery pageQuery) {
        return imageOsdService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出图像OSD查询/设置报列表
     */
    @SaCheckPermission("temp:osd:export")
    @Log(title = "图像OSD查询/设置报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImageOsdBo bo, HttpServletResponse response) {
        List<ImageOsdVo> list = imageOsdService.queryList(bo);
        ExcelUtil.exportExcel(list, "图像OSD查询/设置报", ImageOsdVo.class, response);
    }

    /**
     * 获取图像OSD查询/设置报详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("temp:osd:query")
    @GetMapping("/{id}")
    public R<ImageOsdVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imageOsdService.queryById(id));
    }

    /**
     * 新增图像OSD查询/设置报
     */
    @SaCheckPermission("temp:osd:add")
    @Log(title = "图像OSD查询/设置报", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImageOsdBo bo) {
        return toAjax(imageOsdService.insertByBo(bo));
    }

    /**
     * 修改图像OSD查询/设置报
     */
    @SaCheckPermission("temp:osd:edit")
    @Log(title = "图像OSD查询/设置报", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImageOsdBo bo) {
        return toAjax(imageOsdService.updateByBo(bo));
    }

    /**
     * 删除图像OSD查询/设置报
     *
     * @param ids 主键串
     */
    @SaCheckPermission("temp:osd:remove")
    @Log(title = "图像OSD查询/设置报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imageOsdService.deleteWithValidByIds(List.of(ids), true));
    }
}
