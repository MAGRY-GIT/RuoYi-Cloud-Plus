package com.cdzeroly.weather.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.http.MediaType;
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
import com.cdzeroly.weather.domain.vo.GroundInfoVo;
import com.cdzeroly.weather.domain.bo.GroundInfoBo;
import com.cdzeroly.weather.service.IGroundInfoService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 气象数据
 * 前端访问路由地址为:/weather/groundInfo
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/groundInfo")
public class GroundInfoController extends BaseController {

    private final IGroundInfoService groundInfoService;

    /**
     * 查询气象数据列表
     */
    @SaCheckPermission("weather:groundInfo:list")
    @GetMapping("/list")
    public TableDataInfo<GroundInfoVo> list(GroundInfoBo bo, PageQuery pageQuery) {
        return groundInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出气象数据列表
     */
    @SaCheckPermission("weather:groundInfo:export")
    @Log(title = "气象数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GroundInfoBo bo, HttpServletResponse response) {
        List<GroundInfoVo> list = groundInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "气象数据", GroundInfoVo.class, response);
    }


    /**
     * 导入气象数据列表
     */
    @SaCheckPermission("weather:groundInfo:export")
    @Log(title = "气象数据", businessType = BusinessType.IMPORT)
    @PostMapping("/import")
    public void importData(@RequestPart("file") MultipartFile file) {
       groundInfoService.importData(file);
    }

    /**
     * 获取气象数据详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("weather:groundInfo:query")
    @GetMapping("/{id}")
    public R<GroundInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(groundInfoService.queryById(id));
    }

    /**
     * 新增气象数据
     */
    @SaCheckPermission("weather:groundInfo:add")
    @Log(title = "气象数据", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GroundInfoBo bo) {
        return toAjax(groundInfoService.insertByBo(bo));
    }

    /**
     * 修改气象数据
     */
    @SaCheckPermission("weather:groundInfo:edit")
    @Log(title = "气象数据", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GroundInfoBo bo) {
        return toAjax(groundInfoService.updateByBo(bo));
    }

    /**
     * 删除气象数据
     *
     * @param ids 主键串
     */
    @SaCheckPermission("weather:groundInfo:remove")
    @Log(title = "气象数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(groundInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
