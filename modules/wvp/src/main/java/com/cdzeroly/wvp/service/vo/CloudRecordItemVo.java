package com.cdzeroly.wvp.service.vo;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cdzeroly.common.tenant.core.TenantEntity;
import com.cdzeroly.wvp.media.event.media.MediaRecordMp4Event;
import com.cdzeroly.wvp.service.bean.CloudRecordItem;
import com.cdzeroly.wvp.utils.MediaServerUtils;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 云端录像数据
 * @author MGARY
 */
@Getter
@Setter
@Data
@AutoMapper(target = CloudRecordItem.class)
public class CloudRecordItemVo implements Serializable  {
    /**
     * 主键
     */
    private int id;

    /**
     * 应用名
     */
    private String app;

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
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * ZLM Id
     */
    private String mediaServerId;

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

    public static CloudRecordItemVo getInstance(MediaRecordMp4Event param) {
        CloudRecordItemVo cloudRecordItem = new CloudRecordItemVo();
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
