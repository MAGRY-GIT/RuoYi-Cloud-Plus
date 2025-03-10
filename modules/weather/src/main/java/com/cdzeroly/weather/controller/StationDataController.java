package com.cdzeroly.weather.controller;

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
import com.cdzeroly.weather.domain.vo.StationDataVo;
import com.cdzeroly.weather.domain.bo.StationDataBo;
import com.cdzeroly.weather.service.IStationDataService;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 地面站点数据
 * 前端访问路由地址为:/weather/data
 *
 * @author MGARY
 * @date 2025-03-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/data")
public class StationDataController extends BaseController {

    private final IStationDataService stationDataService;

    /**
     * 查询地面站点数据列表
     */
    @SaCheckPermission("weather:data:list")
    @GetMapping("/list")
    public TableDataInfo<StationDataVo> list(StationDataBo bo, PageQuery pageQuery) {
        return stationDataService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出地面站点数据列表
     */
    @SaCheckPermission("weather:data:export")
    @Log(title = "地面站点数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(StationDataBo bo, HttpServletResponse response) {
        List<StationDataVo> list = stationDataService.queryList(bo);
        ExcelUtil.exportExcel(list, "地面站点数据", StationDataVo.class, response);
    }

    /**
     * 导入气象数据列表
     */
    @SaCheckPermission("weather:groundInfo:export")
    @Log(title = "气象数据", businessType = BusinessType.IMPORT)
    @PostMapping("/import")
    public void importData(@RequestPart("file") MultipartFile file) {
        stationDataService.importData(file);
    }

    /**
     * 获取地面站点数据详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("weather:data:query")
    @GetMapping("/{id}")
    public R<StationDataVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) throws Exception {
        return R.ok(stationDataService.queryById(id));
    }

    /**
     * 新增地面站点数据
     */
    @SaCheckPermission("weather:data:add")
    @Log(title = "地面站点数据", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody StationDataBo bo) {
        return toAjax(stationDataService.insertByBo(bo));
    }

    /**
     * 修改地面站点数据
     */
    @SaCheckPermission("weather:data:edit")
    @Log(title = "地面站点数据", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody StationDataBo bo) {
        return toAjax(stationDataService.updateByBo(bo));
    }

    /**
     * 删除地面站点数据
     *
     * @param ids 主键串
     */
    @SaCheckPermission("weather:data:remove")
    @Log(title = "地面站点数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(stationDataService.deleteWithValidByIds(List.of(ids), true));
    }
}
