package com.cdzeroly.wvp.i1.temp.controller;

import java.util.List;

import com.cdzeroly.wvp.i1.bean.CameraScheduleDto;
import com.cdzeroly.wvp.i1.bean.PhotoTimeTableDto;
import com.cdzeroly.wvp.i1.temp.domain.vo.PhotoTimeTableVo;
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
import com.cdzeroly.wvp.i1.temp.domain.vo.CameraScheduleVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.CameraScheduleBo;
import com.cdzeroly.wvp.i1.temp.service.ICameraScheduleService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 摄像机定时工作时间设置
 * 前端访问路由地址为:/temp/schedule
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class CameraScheduleController extends BaseController {

    private final ICameraScheduleService cameraScheduleService;

    /**
     * 查询摄像机定时工作时间设置列表
     */
    @SaCheckPermission("temp:schedule:list")
    @GetMapping("/list")
    public TableDataInfo<CameraScheduleVo> list(CameraScheduleBo bo, PageQuery pageQuery) {
        return cameraScheduleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出摄像机定时工作时间设置列表
     */
    @SaCheckPermission("temp:schedule:export")
    @Log(title = "摄像机定时工作时间设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CameraScheduleBo bo, HttpServletResponse response) {
        List<CameraScheduleVo> list = cameraScheduleService.queryList(bo);
        ExcelUtil.exportExcel(list, "摄像机定时工作时间设置", CameraScheduleVo.class, response);
    }

    /**
     * 获取摄像机定时工作时间设置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("temp:schedule:query")
    @GetMapping("/{id}")
    public R<CameraScheduleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(cameraScheduleService.queryById(id));
    }

    /**
     * 新增摄像机定时工作时间设置
     */
    @SaCheckPermission("temp:schedule:add")
    @Log(title = "摄像机定时工作时间设置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CameraScheduleBo bo) {
        return toAjax(cameraScheduleService.insertByBo(bo));
    }

    /**
     * 修改摄像机定时工作时间设置
     */
    @SaCheckPermission("temp:schedule:edit")
    @Log(title = "摄像机定时工作时间设置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CameraScheduleBo bo) {
        return toAjax(cameraScheduleService.updateByBo(bo));
    }


    /**
     * 获取图像分析类型详细信息
     *
     * @param monitoringDeviceId 主键
     */
    @GetMapping("/find/{monitoringDeviceId}")
    public R<CameraScheduleVo> find(@NotNull(message = "设备ID不能为空") @PathVariable String monitoringDeviceId, CameraScheduleDto cameraScheduleDto ) {
        return R.ok(cameraScheduleService.queryByMonitoringDeviceId(monitoringDeviceId,cameraScheduleDto));
    }

    /**
     * 删除摄像机定时工作时间设置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("temp:schedule:remove")
    @Log(title = "摄像机定时工作时间设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(cameraScheduleService.deleteWithValidByIds(List.of(ids), true));
    }
}
