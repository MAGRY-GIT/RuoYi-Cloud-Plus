package com.cdzeroly.assist.domain.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Date;

/**
 * 视频文件
 * @author MAGRY
 */
@Setter
@Getter
public class VideoFile {

    /**
     * 文件对象
     */
    private File file;

    /**
     * 文件开始时间
     */
    private Date startTime;

    /**
     * 文件结束时间
     */
    private Date endTime;


    /**
     * 时长, 单位：秒
     */
    private long duration;


    /**
     * 是否是目标格式
     */
    private boolean targetFormat;

}
