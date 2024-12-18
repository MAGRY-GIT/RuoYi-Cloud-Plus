package com.cdzeroly.wvp.storager.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 录像记录
 * @author MGARY
 */
@Setter
@Getter
public class RecordInfo {

    /**
     * ID
     */
    private int id;

    /**
     * 应用名
     */
    private String app;

    /**
     * 流ID
     */
    private String stream;

    /**
     * 对应的zlm流媒体的ID
     */
    private String mediaServerId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 类型 对应zlm的 originType
     * unknown = 0,
     * rtmp_push=1,
     * rtsp_push=2,
     * rtp_push=3,
     * pull=4,
     * ffmpeg_pull=5,
     * mp4_vod=6,
     * device_chn=7,
     * rtc_push=8
     */
    private int type;

    /**
     * 国标录像时的设备ID
     */
    private String deviceId;

    /**
     * 国标录像时的通道ID
     */
    private String channelId;

    /**
     * 拉流代理录像时的名称
     */
    private String name;

}
