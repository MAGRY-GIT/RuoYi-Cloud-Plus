package com.cdzeroly.wvp.gb28181.controller;

import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.core.utils.AssertUtils;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.domain.Region;
import com.cdzeroly.wvp.gb28181.bean.RegionTree;
import com.cdzeroly.wvp.gb28181.service.IRegionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MGARY
 */
@Tag(name = "区域管理")
@RestController
@RequestMapping("/region")
public class RegionController {


    @Resource
    private IRegionService regionService;

    @Operation(summary = "添加区域")
    @Parameter(name = "region", description = "Region", required = true)
    @PostMapping()
    public R<Void> add(@RequestBody Region region) {
        regionService.add(region);
        return R.ok();
    }

    @Operation(summary = "查询区域")
    @Parameter(name = "query", description = "要搜索的内容", required = true)
    @GetMapping("/page/list")
    public TableDataInfo<Region> query(@RequestParam(required = false) String query, PageQuery page

    ) {
        return regionService.query(query, page);
    }

    @Operation(summary = "查询区域")
    @Parameter(name = "query", description = "要搜索的内容", required = true)
    @Parameter(name = "parent", description = "所属行政区划编号", required = true)
    @GetMapping("/tree/list")
    public R<List<RegionTree>> queryForTree(@RequestParam(required = false) String query, @RequestParam(required = false) Long parent, @RequestParam(required = false) Boolean hasChannel) {
        if (ObjectUtils.isEmpty(query)) {
            query = null;
        }
        return R.ok(regionService.queryForTree(query, parent, hasChannel));
    }

    @Operation(summary = "更新区域")
    @Parameter(name = "region", description = "Region", required = true)
    @PutMapping("")
    public R<Void> update(@RequestBody Region region) {
        regionService.update(region);
        return R.ok();
    }

    @Operation(summary = "删除区域")
    @Parameter(name = "id", description = "区域ID", required = true)
    @DeleteMapping()
    public R<Void> delete(Long id) {
        Assert.notNull(id, "区域ID需要存在");
        boolean result = regionService.deleteByDeviceId(id);
        if (!result) {
            throw new ServiceException("移除失败");
        }
        return R.ok();
    }

    @Operation(summary = "根据区域Id查询区域")
    @Parameter(name = "regionDeviceId", description = "行政区划节点编号", required = true)
    @GetMapping("")
    public R<Region> queryRegionByDeviceId(@RequestParam() String regionDeviceId) {
        AssertUtils.isNotNull(regionDeviceId.trim(),"参数或方法错误");
        return R.ok(regionService.queryRegionByDeviceId(regionDeviceId));
    }

    @Operation(summary = "获取所属的行政区划下的行政区划")
    @Parameter(name = "parent", description = "所属的行政区划", required = false)
    @GetMapping("/base/child/list")
    public R<List<Region>> getAllChild(@RequestParam(required = false) String parent) {
        if (ObjectUtils.isEmpty(parent)) {
            parent = null;
        }
        return R.ok(regionService.getAllChild(parent));
    }

    @Operation(summary = "获取所属的行政区划下的行政区划")
    @Parameter(name = "deviceId", description = "当前的行政区划", required = false)
    @GetMapping("/path")
    public R<List<Region>> getPath(String deviceId) {
        return R.ok(regionService.getPath(deviceId));
    }

    @Operation(summary = "从通道中同步行政区划")
    @GetMapping("/sync")
    public R<Void> sync() {

        regionService.syncFromChannel();
        return R.ok();
    }
}
