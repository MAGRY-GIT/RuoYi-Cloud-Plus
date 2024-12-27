package com.cdzeroly.wvp.domain.bean;

import lombok.Data;

/**
 * @author MGARY
 */
@Data
public class LogFileInfo {

    private String fileName;
    private Long fileSize;
    private Long startTime;
    private Long endTime;

}
