package com.cdzeroly.assist.domain.vo;


import lombok.Getter;
import lombok.Setter;

/**
 * 合并或剪切任务信息
 * @author MGARY
 */
@Setter
@Getter
public class MergeOrCutTaskInfoVo {
    private String id;
    private String createTime;
    private String percentage;

    /**
     * 记录文件地址
     */
    private String recordFile;

    /**
     * 下载文件地址
     */
    private String downloadFile;

    /**
     * 播放文件地址
     */
    private String playFile;

    private String app;
    private String stream;
    private String startTime;
    private String endTime;
    private String callId;

}
