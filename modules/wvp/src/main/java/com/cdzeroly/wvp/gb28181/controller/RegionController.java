package com.cdzeroly.wvp.gb28181.controller;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.conf.exception.ControllerException;
import com.cdzeroly.wvp.gb28181.domian.Region;
import com.cdzeroly.wvp.gb28181.domian.bean.RegionTree;
import com.cdzeroly.wvp.gb28181.service.IRegionService;
import com.cdzeroly.wvp.vmanager.bean.ErrorCode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "区域管理")
@RestController
@RequestMapping("/api/region")
public class RegionController {

    private final static Logger logger = LoggerFactory.getLogger(RegionController.class);

    @Autowired
    private IRegionService regionService;

    @Operation(summary = "添加区域")
    @Parameter(name = "region", description = "Region", required = true)
    @ResponseBody
    @PostMapping("/add")
    public void add(@RequestBody Region region){
        regionService.add(region);
    }

    @Operation(summary = "查询区域")
    @Parameter(name = "query", description = "要搜索的内容", required = true)
    @ResponseBody
    @GetMapping("/page/list")
    public TableDataInfo<Region> query(
        @RequestParam(required = false) String query, PageQuery page

        ){
        return regionService.query(query, page);
    }

    @Operation(summary = "查询区域")
    @Parameter(name = "query", description = "要搜索的内容", required = true)
    @Parameter(name = "parent", description = "所属行政区划编号", required = true)
    @ResponseBody
    @GetMapping("/tree/list")
    public List<RegionTree> queryForTree(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Integer parent,
            @RequestParam(required = false) Boolean hasChannel
    ){
        if (ObjectUtils.isEmpty(query)) {
            query = null;
        }
        return regionService.queryForTree(query, parent, hasChannel);
    }

    @Operation(summary = "更新区域")
    @Parameter(name = "region", description = "Region", required = true)
    @ResponseBody
    @PostMapping("/update")
    public void update(@RequestBody Region region){
        regionService.update(region);
    }

    @Operation(summary = "删除区域")
    @Parameter(name = "id", description = "区域ID", required = true)
    @ResponseBody
    @DeleteMapping("/delete")
    public void delete(Integer id){
        Assert.notNull(id, "区域ID需要存在");
        boolean result = regionService.deleteByDeviceId(id);
        if (!result) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "移除失败");
        }
    }

    @Operation(summary = "根据区域Id查询区域")
    @Parameter(name = "regionDeviceId", description = "行政区划节点编号", required = true)
    @ResponseBody
    @GetMapping("/one")
    public Region queryRegionByDeviceId(
            @RequestParam(required = true) String regionDeviceId
    ){
        if (ObjectUtils.isEmpty(regionDeviceId.trim())) {
            throw new ControllerException(ErrorCode.ERROR400);
        }
        return regionService.queryRegionByDeviceId(regionDeviceId);
    }

    @Operation(summary = "获取所属的行政区划下的行政区划")
    @Parameter(name = "parent", description = "所属的行政区划", required = false)
    @ResponseBody
    @GetMapping("/base/child/list")
    public List<Region> getAllChild(@RequestParam(required = false) String parent){
        if (ObjectUtils.isEmpty(parent)) {
            parent = null;
        }
        return regionService.getAllChild(parent);
    }

    @Operation(summary = "获取所属的行政区划下的行政区划")
    @Parameter(name = "deviceId", description = "当前的行政区划", required = false)
    @ResponseBody
    @GetMapping("/path")
    public List<Region> getPath(String deviceId){
        return regionService.getPath(deviceId);
    }

    @Operation(summary = "从通道中同步行政区划")
    @ResponseBody
    @GetMapping("/sync")
    public void sync(){
        regionService.syncFromChannel();
    }
}
