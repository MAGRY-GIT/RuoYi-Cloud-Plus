package com.cdzeroly.wvp.domain.bo;

import cn.hutool.core.date.DateUtil;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.cdzeroly.wvp.media.event.media.MediaRecordMp4Event;
import com.cdzeroly.wvp.domain.CloudRecord;
import com.cdzeroly.wvp.utils.MediaServerUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 云端录像数据
 * @author MGARY
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = CloudRecord.class, reverseConvertGenerate = false)
public class CloudRecordItemBo extends BaseEntity  {
    /**
     * 主键
     */
    private Long id;

    /**
     * 远程地址
     */
    String remoteHost;

    /**
     * 主键集合
     */
    private List<Integer> ids;

    /**
     * 应用名
     */
    private String app;
    /**
     * 应用名
     */
    private String query;

    /**
     * 流
     */
    private String stream;

    /**
     * 健全ID
     */
    private String callId;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * ZLM Id
     */
    private String mediaServerId;

    /**
     * ZLM Ids
     */
    private List<String> mediaServerIds;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件夹
     */
    private String folder;

    /**
     * 收藏，收藏的文件不移除
     */
    private Boolean collect;

    /**
     * 保留，收藏的文件不移除
     */
    private Boolean reserve;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 文件时长
     */
    private long timeLen;

    public static CloudRecordItemBo getInstance(MediaRecordMp4Event param) {
        CloudRecordItemBo cloudRecordItem = new CloudRecordItemBo();
        cloudRecordItem.setApp(param.getApp());
        cloudRecordItem.setStream(param.getStream());
        cloudRecordItem.setStartTime(DateUtil.date(param.getRecordInfo().getStartTime()*1000));
        cloudRecordItem.setFileName(param.getRecordInfo().getFileName());
        cloudRecordItem.setFolder(param.getRecordInfo().getFolder());
        cloudRecordItem.setFileSize(param.getRecordInfo().getFileSize());
        cloudRecordItem.setFilePath(param.getRecordInfo().getFilePath());
        cloudRecordItem.setMediaServerId(param.getMediaServer().getId());
        cloudRecordItem.setTimeLen((long) param.getRecordInfo().getTimeLen() * 1000);
        cloudRecordItem.setEndTime(DateUtil.date((param.getRecordInfo().getStartTime() + (long)param.getRecordInfo().getTimeLen()) * 1000));
        Map<String, String> paramsMap = MediaServerUtils.urlParamToMap(param.getRecordInfo().getParams());
        if (paramsMap.get("callId") != null) {
            cloudRecordItem.setCallId(paramsMap.get("callId"));
        }
        return cloudRecordItem;
    }

}
