package com.cdzeroly.assist.conf;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 配置文件 user-settings 映射的配置信息
 * @author MGARY
 */
@ConfigurationProperties(prefix = "user-settings", ignoreInvalidFields = true)
@Configuration
@Order(0)
public class UserSetting {

    private String id;

    private String recordTempPath = "./recordTemp";

    private int recordTempDay = 7;

    private String ffmpeg;

    private String ffprobe;

    private int threads = 2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecordTempPath() {
        return recordTempPath;
    }

    public void setRecordTempPath(String recordTempPath) {
        this.recordTempPath = recordTempPath;
    }

    public int getRecordTempDay() {
        return recordTempDay;
    }

    public void setRecordTempDay(int recordTempDay) {
        this.recordTempDay = recordTempDay;
    }

    public String getFfmpeg() {
        return ffmpeg;
    }

    public void setFfmpeg(String ffmpeg) {
        this.ffmpeg = ffmpeg;
    }

    public String getFfprobe() {
        return ffprobe;
    }

    public void setFfprobe(String ffprobe) {
        this.ffprobe = ffprobe;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }
}

