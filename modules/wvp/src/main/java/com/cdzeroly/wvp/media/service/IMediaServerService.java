package com.cdzeroly.wvp.media.service;

import com.cdzeroly.wvp.common.CommonCallback;
import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.domain.bean.MediaInfo;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.domain.bean.SendRtpInfo;
import com.cdzeroly.wvp.domain.vo.MediaServerLoadVo;
import com.cdzeroly.wvp.domain.bean.SSRCInfo;
import com.cdzeroly.wvp.domain.StreamProxy;
import com.cdzeroly.wvp.domain.WVPResult;

import java.util.List;
import java.util.Map;

/**
 * 媒体服务节点
 *
 * @author MGARY
 */
public interface IMediaServerService {

    /**
     * 获取所有在线列表
     *
     * @return List<MediaServer>
     */
    List<MediaServer> getAllOnlineList();

    /**
     * 获取所有列表
     *
     * @return List<MediaServer>
     */
    List<MediaServer> getAll();


    /**
     * 从 Database 获取全部
     *
     * @return List<MediaServer>
     */
    List<MediaServer> getAllFromDatabase();

    /**
     * 获取所有在线列表
     *
     * @return List<MediaServer>
     */
    List<MediaServer> getAllOnline();


    /**
     * 媒体服务器 ID获取数据
     *
     * @return List<MediaServer>
     */
    MediaServer getOne(String generalMediaServerId);

    /**
     * 从数据库同步 catch
     */
    void syncCatchFromDatabase();


    /**
     * 获取最小负载的媒体服务器
     *
     * @param hasAssist 是否查询 协助
     * @return
     */
    MediaServer getMediaServerForMinimumLoad(Boolean hasAssist);

    void updateVmServer(List<MediaServer> mediaServerItemList);

    /**
     * 打开 RTP 服务器
     *
     * @param mediaServerItem 媒体服务器
     * @param streamId        流ID
     * @param presetSsrc      预设 SSRC
     * @param ssrcCheck       ssrc 检查
     * @param isPlayback      是否播放
     * @param port            端口
     * @param onlyAuto        仅自动
     * @param disableAudio    禁用音频
     * @param reUsePort       重新使用端口
     * @param tcpMode         TCP 模式
     * @return SSRCInfo
     */
    SSRCInfo openRTPServer(MediaServer mediaServerItem, String streamId, String presetSsrc, boolean ssrcCheck, boolean isPlayback, Integer port, Boolean onlyAuto, Boolean disableAudio, Boolean reUsePort, Integer tcpMode);

    /**
     * 关闭 RTP 服务器
     *
     * @param mediaServerItem 媒体服务器
     * @param streamId        流ID
     */
    void closeRTPServer(MediaServer mediaServerItem, String streamId);


    /**
     * 关闭 RTP 服务器
     *
     * @param mediaServerItem 媒体服务器
     * @param streamId        流ID
     * @param callback        回调
     */
    void closeRTPServer(MediaServer mediaServerItem, String streamId, CommonCallback<Boolean> callback);

    /**
     * 更新 rtp 服务器 SSRC
     *
     * @param mediaServerItem 媒体服务器
     * @param streamId        流ID
     * @param ssrc            SSRC
     * @return Boolean
     */
    Boolean updateRtpServerSSRC(MediaServer mediaServerItem, String streamId, String ssrc);

    /**
     * 关闭 RTP 服务器
     *
     * @param mediaServerId 媒体服务器ID
     * @param streamId      流ID
     */
    void closeRTPServer(String mediaServerId, String streamId);

    /**
     * 关闭 RTP 服务器
     *
     * @param mediaServer 媒体服务器
     */
    void clearRTPServer(MediaServer mediaServer);

    /**
     * 更新 RTP 服务器
     *
     * @param mediaServer 媒体服务器
     */
    void update(MediaServer mediaServer);

    /**
     * 添加统计
     *
     * @param mediaServerId 媒体服务器ID
     */
    void addCount(String mediaServerId);

    /**
     * 删除统计
     *
     * @param mediaServerId 媒体服务器ID
     */
    void removeCount(String mediaServerId);

    /**
     * 发布 ssrc
     *
     * @param mediaServerId 媒体服务器ID
     * @param ssrc          ssrc
     */
    void releaseSsrc(String mediaServerId, String ssrc);

    /**
     * 清除联机媒体服务器
     */
    void clearMediaServerForOnline();

    /**
     * 添加媒体服务器
     *
     * @param mediaServer 媒体服务器
     */
    void add(MediaServer mediaServer);

    /**
     * 重置在线服务器
     *
     * @param mediaServer 媒体服务器
     */
    void resetOnlineServer(MediaServer mediaServer);

    /**
     * 检查媒体服务器
     *
     * @param ip     IP
     * @param port   端口
     * @param secret 密钥
     * @param type   类型
     * @return
     */
    MediaServer checkMediaServer(String ip, int port, String secret, String type);

    /**
     * 检查媒体记录服务器
     *
     * @param ip   IP
     * @param port 端口
     * @return boolean
     */
    boolean checkMediaRecordServer(String ip, int port);

    /**
     * 删除媒体服务器
     *
     * @return boolean
     */
    void delete(MediaServer mediaServer);

    /**
     * 获取默认媒体服务器
     *
     * @return MediaServer
     */
    MediaServer getDefaultMediaServer();

    /**
     * 获取负载
     *
     * @param mediaServer 媒体服务器
     * @return
     */
    MediaServerLoadVo getLoad(MediaServer mediaServer);

    /**
     * 使用辅助端口获取所有
     *
     * @return List<MediaServer>
     */
    List<MediaServer> getAllWithAssistPort();

    /**
     * 从 Database 中获取一个
     *
     * @param id id
     * @return MediaServer
     */
    MediaServer getMediaServer(String id);

    boolean stopSendRtp(MediaServer mediaInfo, String app, String stream, String ssrc);

    boolean initStopSendRtp(MediaServer mediaInfo, String app, String stream, String ssrc);

    boolean deleteRecordDirectory(MediaServer mediaServerItem, String app, String stream, String date, String fileName);

    List<StreamInfo> getMediaList(MediaServer mediaInfo, String app, String stream, String callId);

    Boolean connectRtpServer(MediaServer mediaServerItem, String address, int port, String stream);

    /**
     * @param mediaServer 媒体服务
     * @param streamUrl   流地址
     * @param timeoutSec  超时秒
     * @param expireSec   过期秒
     * @param path        路径
     * @param fileName    文件名
     */
    void getSnap(MediaServer mediaServer, String streamUrl, int timeoutSec, int expireSec, String path, String fileName);

    MediaInfo getMediaInfo(MediaServer mediaServerItem, String app, String stream);

    Boolean pauseRtpCheck(MediaServer mediaServerItem, String streamKey);

    boolean resumeRtpCheck(MediaServer mediaServerItem, String streamKey);

    String getFfmpegCmd(MediaServer mediaServer, String cmdKey);

    void closeStreams(MediaServer mediaServerItem, String app, String stream);

    WVPResult<String> addFFmpegSource(MediaServer mediaServerItem, String srcUrl, String dstUrl, int timeoutMs, boolean enableAudio, boolean enableMp4, String ffmpegCmdKey);

    WVPResult<String> addStreamProxy(MediaServer mediaServerItem, String app, String stream, String url, boolean enableAudio, boolean enableMp4, String rtpType, Integer timeout);

    Boolean delFFmpegSource(MediaServer mediaServerItem, String streamKey);

    Boolean delStreamProxy(MediaServer mediaServerItem, String streamKey);

    Map<String, String> getFFmpegCMDs(MediaServer mediaServer);

    /**
     * 根据应用名和流ID获取播放地址, 通过zlm接口检查是否存在
     *
     * @param app
     * @param stream
     * @return
     */
    StreamInfo getStreamInfoByAppAndStreamWithCheck(String app, String stream, String mediaServerId, String addr, boolean authority);


    /**
     * 根据应用名和流ID获取播放地址, 通过zlm接口检查是否存在, 返回的ip使用远程访问ip，适用与zlm与wvp在一台主机的情况
     *
     * @param app
     * @param stream
     * @return
     */
    StreamInfo getStreamInfoByAppAndStreamWithCheck(String app, String stream, String mediaServerId, boolean authority);

    /**
     * 根据应用名和流ID获取播放地址, 只是地址拼接
     *
     * @param app
     * @param stream
     * @return
     */
    StreamInfo getStreamInfoByAppAndStream(MediaServer mediaServerItem, String app, String stream, MediaInfo mediaInfo, String callId);

    /**
     * 根据应用名和流ID获取播放地址, 只是地址拼接，返回的ip使用远程访问ip，适用与zlm与wvp在一台主机的情况
     *
     * @param app
     * @param stream
     * @return
     */
    StreamInfo getStreamInfoByAppAndStream(MediaServer mediaServer, String app, String stream, MediaInfo mediaInfo, String addr, String callId, boolean isPlay);


    /**
     * 查看流媒体是否存在
     *
     * @param mediaServer 流媒体服务
     * @param rtp         rtp
     * @param streamId    流ID
     * @return 是否存在
     */
    Boolean isStreamReady(MediaServer mediaServer, String rtp, String streamId);

    Integer startSendRtpPassive(MediaServer mediaServer, SendRtpInfo sendRtpItem, Integer timeout);

    void startSendRtp(MediaServer mediaServer, SendRtpInfo sendRtpItem);

    MediaServer getMediaServerByAppAndStream(String app, String stream);

    Long updateDownloadProcess(MediaServer mediaServerItem, String app, String stream);

    StreamInfo startProxy(MediaServer mediaServer, StreamProxy streamProxy);

    void stopProxy(MediaServer mediaServer, String streamKey);

    StreamInfo getMediaByAppAndStream(String app, String stream);

    int createRTPServer(MediaServer mediaServerItem, String streamId, long ssrc, Integer port, boolean onlyAuto, boolean disableAudio, boolean reUsePort, Integer tcpMode);

    List<String> listRtpServer(MediaServer mediaServer);
}
