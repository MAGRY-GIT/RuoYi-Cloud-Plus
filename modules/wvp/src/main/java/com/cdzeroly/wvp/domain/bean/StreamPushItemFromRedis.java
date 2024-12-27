package com.cdzeroly.wvp.domain.bean;


import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class StreamPushItemFromRedis {
    private String app;
    private String stream;
    private long timeStamp;
}


