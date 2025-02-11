package com.cdzeroly.assist.service;


import com.cdzeroly.assist.domain.bo.SignInfo;
import com.cdzeroly.assist.domain.bo.VideoTaskInfo;
import com.cdzeroly.assist.domain.vo.MergeOrCutTaskInfoVo;
import com.cdzeroly.assist.domain.vo.SpaceInfoVo;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author 视频文件服务
 */
public interface VideoFileService {
    List<File> getAppList(Boolean sort);

    SpaceInfoVo getSpaceInfo();

    List<File> getStreamList(File appFile, Boolean sort);

    /**
     * 获取制定推流的指定时间段内的推流
     *
     * @param app
     * @param stream
     * @param startTime
     * @param endTime
     * @return
     */
    List<File> getFilesInTime(String app, String stream, Date startTime, Date endTime);

    String mergeOrCut(VideoTaskInfo videoTaskInfo);

    List<File> getDateList(File streamFile, Integer year, Integer month, Boolean sort);

    List<MergeOrCutTaskInfoVo> getTaskListForDownload(String app, String stream, String callId, Boolean isEnd, String taskId);

    boolean collection(String app, String stream, String type);

    boolean removeCollection(String app, String stream, String type);

    /**
     * 获取录像文件
     * @param app
     * @param stream
     * @param type
     * @return
     */
    List<SignInfo> getCollectionList(String app, String stream, String type);

    long fileDuration(String app, String stream);

    int deleteFile(List<String> filePathList);
}
