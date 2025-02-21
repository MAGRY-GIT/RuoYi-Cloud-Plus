package com.cdzeroly.wvp.i1.temp.controller;

import java.util.List;

import com.cdzeroly.wvp.i1.bean.AlarmLinkageParamsQuery;
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
import com.cdzeroly.wvp.i1.temp.domain.vo.AlarmLinkageConfigVo;
import com.cdzeroly.wvp.i1.temp.domain.bo.AlarmLinkageConfigBo;
import com.cdzeroly.wvp.i1.temp.service.IAlarmLinkageConfigService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;

/**
 * 监拍装置告警联动参数配置报
 * 前端访问路由地址为:/temp/linkageConfig
 *
 * @author MGARY
 * @date 2025-02-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/linkageConfig")
public class AlarmLinkageConfigController extends BaseController {

    private final IAlarmLinkageConfigService alarmLinkageConfigService;

    /**
     * 查询监拍装置告警联动参数配置报列表
     */
    @SaCheckPermission("temp:linkageConfig:list")
    @GetMapping("/list")
    public TableDataInfo<AlarmLinkageConfigVo> list(AlarmLinkageConfigBo bo, PageQuery pageQuery) {
        return alarmLinkageConfigService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出监拍装置告警联动参数配置报列表
     */
    @SaCheckPermission("temp:linkageConfig:export")
    @Log(title = "监拍装置告警联动参数配置报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AlarmLinkageConfigBo bo, HttpServletResponse response) {
        List<AlarmLinkageConfigVo> list = alarmLinkageConfigService.queryList(bo);
        ExcelUtil.exportExcel(list, "监拍装置告警联动参数配置报", AlarmLinkageConfigVo.class, response);
    }

    /**
     * 获取监拍装置告警联动参数配置报详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("temp:linkageConfig:query")
    @GetMapping("/{id}")
    public R<AlarmLinkageConfigVo> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(alarmLinkageConfigService.queryById(id));
    }


    /**
     * 获取监拍装置告警联动详细信息
     *
     * @param monitoringDeviceId 主键
     */
    @GetMapping("/find/{monitoringDeviceId}")
    public R<AlarmLinkageConfigVo> find(@NotNull(message = "设备ID不能为空") @PathVariable String monitoringDeviceId, List<AlarmLinkageParamsQuery> alarmLinkageParamsQuery) {
        return R.ok(alarmLinkageConfigService.queryByMonitoringDeviceId(monitoringDeviceId,alarmLinkageParamsQuery));
    }

    /**
     * 新增监拍装置告警联动参数配置报
     */
    @SaCheckPermission("temp:linkageConfig:add")
    @Log(title = "监拍装置告警联动参数配置报", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AlarmLinkageConfigBo bo) {
        return toAjax(alarmLinkageConfigService.insertByBo(bo));
    }

    /**
     * 修改监拍装置告警联动参数配置报
     */
    @SaCheckPermission("temp:linkageConfig:edit")
    @Log(title = "监拍装置告警联动参数配置报", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AlarmLinkageConfigBo bo) {
        return toAjax(alarmLinkageConfigService.updateByBo(bo));
    }

    /**
     * 删除监拍装置告警联动参数配置报
     *
     * @param ids 主键串
     */
    @SaCheckPermission("temp:linkageConfig:remove")
    @Log(title = "监拍装置告警联动参数配置报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(alarmLinkageConfigService.deleteWithValidByIds(List.of(ids), true));
    }
}
