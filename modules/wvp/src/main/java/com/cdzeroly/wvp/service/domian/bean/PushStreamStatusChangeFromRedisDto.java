package com.cdzeroly.wvp.service.domian.bean;

import lombok.Data;

import java.util.List;

/**
 * 收到redis通知修改推流通道状态
 * @author lin
 */
@Data
public class PushStreamStatusChangeFromRedisDto {

    private boolean setAllOffline;

    private List<StreamPushItemFromRedis> onlineStreams;

    private List<StreamPushItemFromRedis> offlineStreams;
}
