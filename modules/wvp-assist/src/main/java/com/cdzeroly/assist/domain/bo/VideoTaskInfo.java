package com.cdzeroly.assist.domain.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author MAGRY
 */
@Setter
@Getter
@Schema(description = "视频合并任务的信息")
public class VideoTaskInfo {

    private String app;
    private String stream;
    private String startTime;
    private String endTime;
    private String callId;


    @Schema(description = "视频文件路径列表")
    private List<String> filePathList;

    @Schema(description = "返回地址时的远程地址")
    private String remoteHost;

}
