package com.cdzeroly.wvp.gb28181.controller;

import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.wvp.gb28181.domian.bean.Group;
import com.cdzeroly.wvp.gb28181.domian.bean.GroupTree;
import com.cdzeroly.wvp.gb28181.service.IGroupService;
import com.cdzeroly.wvp.vmanager.bean.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MGARY
 */
@Slf4j
@Tag(name = "分组管理")
@RestController
@RequestMapping("/group")
public class GroupController {

    @Resource
    private IGroupService groupService;

    @Operation(summary = "添加分组")
    @Parameter(name = "group", description = "group", required = true)
    @PostMapping("")
    public R<Void> add(@RequestBody Group group){
        groupService.add(group);
        return R.ok();
    }

    @Operation(summary = "查询分组")
    @Parameter(name = "query", description = "要搜索的内容", required = true)
    @Parameter(name = "parent", description = "所属分组编号", required = true)
    @GetMapping("/tree/list")
    public R<List<GroupTree>> queryForTree(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Integer parent,
            @RequestParam(required = false) Boolean hasChannel
    ){
        if (ObjectUtils.isEmpty(query)) {
            query = null;
        }
        return R.ok(groupService.queryForTree(query, parent, hasChannel));
    }

    @Operation(summary = "更新分组")
    @Parameter(name = "group", description = "Group", required = true)
    @PutMapping("")
    public R<Void> update(@RequestBody Group group){
        groupService.update(group);
        return R.ok();

    }

    @Operation(summary = "删除分组")
    @Parameter(name = "id", description = "分组id", required = true)
    @DeleteMapping("")
    public R<Void> delete(Integer id){
        Assert.notNull(id, "分组id（deviceId）不需要存在");
        boolean result = groupService.delete(id);
        if (!result) {
            throw new ServiceException( "移除失败");
        }
        return R.ok();

    }

    @Operation(summary = "获取所属的行政区划下的行政区划")
    @Parameter(name = "deviceId", description = "当前的行政区划")
    @GetMapping("/path")
    public R<List<Group>> getPath(String deviceId, String businessGroup){
        return R.ok(groupService.getPath(deviceId, businessGroup));
    }
}
