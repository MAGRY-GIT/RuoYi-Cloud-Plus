package com.cdzeroly.assist.controller;

import com.cdzeroly.assist.domain.bo.VideoTaskInfo;
import com.cdzeroly.assist.domain.vo.MergeOrCutTaskInfoVo;
import com.cdzeroly.assist.domain.vo.SpaceInfoVo;
import com.cdzeroly.assist.service.VideoFileService;
import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.core.exception.ServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 */
@Tag(name = "录像管理", description = "录像管理")
@CrossOrigin
@RestController
@RequestMapping("/api/record")
public class RecordController {


    @Autowired
    private VideoFileService videoFileService;


    /**
     * 添加视频裁剪合并任务
     */
    @Operation(summary ="添加视频裁剪合并任务")
    @Parameter(name = "videoTaskInfo", description = "视频合并任务的信息", required = true)
    @PostMapping(value = "/file/download/task/add")
    @ResponseBody
    public R<String> addTaskForDownload(@RequestBody VideoTaskInfo videoTaskInfo ){
        if (videoTaskInfo.getFilePathList() == null || videoTaskInfo.getFilePathList().isEmpty()) {
            throw new ServiceException( "视频文件列表不可为空");
        }
        String id = videoFileService.mergeOrCut(videoTaskInfo);
        if (id== null) {
            throw new ServiceException("可能未找到视频文件");
        }
        return R.ok(id);
    }

    /**
     * 查询视频裁剪合并任务列表
     */
    @Operation(summary ="查询视频裁剪合并任务列表")
    @Parameter(name = "taskId", description = "任务ID", required = true)
    @Parameter(name = "isEnd", description = "是否结束", required = true)
    @GetMapping(value = "/file/download/task/list")
    @ResponseBody
    public R<List<MergeOrCutTaskInfoVo>> getTaskListForDownload(
            @RequestParam(required = false) String app,
            @RequestParam(required = false) String stream,
            @RequestParam(required = false) String callId,
            @RequestParam(required = false) String taskId,
            @RequestParam(required = false) Boolean isEnd){
        return R.ok(videoFileService.getTaskListForDownload(app, stream, callId, isEnd, taskId));
    }


    /**
     * 磁盘空间查询
     */
    @Operation(summary ="磁盘空间查询")
    @ResponseBody
    @GetMapping(value = "/space", produces = "application/json;charset=UTF-8")
    public R<SpaceInfoVo> getSpace() {
        return R.ok(videoFileService.getSpaceInfo());
    }

    /**
     * 录像文件的时长
     */
    @Operation(summary ="录像文件的时长")
    @Parameter(name = "app", description = "应用名", required = true)
    @Parameter(name = "stream", description = "流ID", required = true)
    @Parameter(name = "recordIng", description = "是否录制中", required = true)
    @ResponseBody
    @GetMapping(value = "/file/duration", produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/file/duration", produces = "application/json;charset=UTF-8")
    public R<Long> fileDuration( @RequestParam String app, @RequestParam String stream) {
        return R.ok(videoFileService.fileDuration(app, stream));
    }
}
