package com.cdzeroly.wvp.media.event.hook;

/**
 * hook类型
 * @author lin
 */

public enum HookType {
    /**
     * 推流鉴权事件
     */
    ON_PUBLISH,
    /**
     * 录制 mp4 完成后通知事件；此事件对回复不敏感。
     */
    ON_RECORD_MP4,
    /**
     * 媒体抵达时
     */
    ON_MEDIA_ARRIVAL,
    /**
     * 媒体离开时
     */
    ON_MEDIA_DEPARTURE,
    /**
     * 调用 openRtpServer 接口，rtp server 长时间未收到数据,执行此 web hook,对回复不敏感
     */
    ON_RTP_SERVER_TIMEOUT,
}
