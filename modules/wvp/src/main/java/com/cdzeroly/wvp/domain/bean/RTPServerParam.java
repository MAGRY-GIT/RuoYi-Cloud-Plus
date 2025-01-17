package com.cdzeroly.wvp.domain.bean;

import com.cdzeroly.wvp.domain.MediaServer;
import lombok.Data;

/**
 * RTP服务器参数
 * @author MGARY
 */
@Data
public class RTPServerParam {

    private MediaServer mediaServer;
    /**
     * 流ID
     */
    private String streamId;

    /**
     * 预设SSRC
     */
    private String presetSsrc;

    /**
     * ssrc 检查
     */
    private boolean ssrcCheck;

    /**
     * 回放
     */
    private boolean playback;

    /**
     * 端口
     */
    private Integer port;


    private boolean onlyAuto;

    /**
     * 禁用音频
     */
    private boolean disableAudio;

    /**
     * 重新使用端口
     */
    private boolean reUsePort;

    /**
     * tcp模式，0时为不启用tcp监听，1时为启用tcp监听，2时为tcp主动连接模式
     */
    private Integer tcpMode;


}
