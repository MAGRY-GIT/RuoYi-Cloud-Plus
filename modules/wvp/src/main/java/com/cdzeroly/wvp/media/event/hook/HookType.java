package com.cdzeroly.wvp.media.event.hook;

/**
 * hook类型
 * @author lin
 */

public enum HookType {
    /**
     * 推流鉴权事件
     */
    on_publish,
    /**
     * 录制 mp4 完成后通知事件；此事件对回复不敏感。
     */
    on_record_mp4,
    on_media_arrival,
    on_media_departure,
    /**
     * 调用 openRtpServer 接口，rtp server 长时间未收到数据,执行此 web hook,对回复不敏感
     */
    on_rtp_server_timeout,
}
