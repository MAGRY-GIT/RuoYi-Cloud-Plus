package com.cdzeroly.wvp.i1.temp.controller;

import java.util.List;

import com.cdzeroly.wvp.i1.bean.ImageAcquisitionDto;
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
import com.cdzeroly.wvp.i1.temp.domain.vo.ImageAcquisitionVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.ImageAcquisitionBo;
import com.cdzeroly.wvp.i1.temp.service.IImageAcquisitionService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 图像采集参数设置
 * 前端访问路由地址为:/temp/acquisition
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/acquisition")
public class ImageAcquisitionController extends BaseController {

    private final IImageAcquisitionService imageAcquisitionService;

    /**
     * 查询图像采集参数设置列表
     */
    @SaCheckPermission("temp:acquisition:list")
    @GetMapping("/list")
    public TableDataInfo<ImageAcquisitionVo> list(ImageAcquisitionBo bo, PageQuery pageQuery) {
        return imageAcquisitionService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出图像采集参数设置列表
     */
    @SaCheckPermission("temp:acquisition:export")
    @Log(title = "图像采集参数设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ImageAcquisitionBo bo, HttpServletResponse response) {
        List<ImageAcquisitionVo> list = imageAcquisitionService.queryList(bo);
        ExcelUtil.exportExcel(list, "图像采集参数设置", ImageAcquisitionVo.class, response);
    }


    /**
     * 获取图像分析类型详细信息
     *
     * @param monitoringDeviceId 主键
     */
    @GetMapping("/find/{monitoringDeviceId}")
    public R<ImageAcquisitionVo> find(@NotNull(message = "设备ID不能为空") @PathVariable String monitoringDeviceId, ImageAcquisitionDto imageAcquisition ) {
        return R.ok(imageAcquisitionService.queryByMonitoringDeviceId(monitoringDeviceId,imageAcquisition));
    }

    /**
     * 获取图像采集参数设置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("temp:acquisition:query")
    @GetMapping("/{id}")
    public R<ImageAcquisitionVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(imageAcquisitionService.queryById(id));
    }

    /**
     * 新增图像采集参数设置
     */
    @SaCheckPermission("temp:acquisition:add")
    @Log(title = "图像采集参数设置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ImageAcquisitionBo bo) {
        return toAjax(imageAcquisitionService.insertByBo(bo));
    }

    /**
     * 修改图像采集参数设置
     */
    @SaCheckPermission("temp:acquisition:edit")
    @Log(title = "图像采集参数设置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ImageAcquisitionBo bo) {
        return toAjax(imageAcquisitionService.updateByBo(bo));
    }

    /**
     * 删除图像采集参数设置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("temp:acquisition:remove")
    @Log(title = "图像采集参数设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(imageAcquisitionService.deleteWithValidByIds(List.of(ids), true));
    }
}
