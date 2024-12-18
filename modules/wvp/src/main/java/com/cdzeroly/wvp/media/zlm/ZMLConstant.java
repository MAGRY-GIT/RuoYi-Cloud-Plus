package com.cdzeroly.wvp.media.zlm;

/**
 * @author : MGARY
 * @description :ZML常量
 * @createDate : 2024/12/16 18:23
 */
public interface ZMLConstant {

    // API
    /**
     * 通过 fork FFmpeg 进程的方式拉流代理，支持任意协议
     */
    String ADD_FFMPEG_SOURCE = "addFFmpegSource";
    /**
     * ：动态添加 rtsp/rtmp/hls/http-ts/http-flv 拉流代理(只支持 H264/H265/aac/G711/opus 负载)
     */
    String ADD_STREAM_PROXY = "addStreamProxy";
    /**
     * 关闭拉流代理
     */
    @Deprecated
    String CLOSE_STREAM = "close_stream";
    /**
     * 关闭流(目前所有类型的流都支持关闭)
     */
    String CLOSE_STREAMS = "close_streams";
    /**
     * 关闭 ffmpeg 拉流代理 (流注册成功后，也可以使用close_streams接口替代)
     */
    String DEL_FFMPEG_SOURCE = "delFFmpegSource";
    /**
     * 关闭拉流代理 (流注册成功后，也可以使用close_streams接口替代)
     */
    String DEL_STREAM_PROXY = "delStreamProxy";
    /**
     * 获取所有 TcpSession 列表(获取所有 tcp 客户端相关信息)
     */
    String GET_ALL_SESSION = "getAllSession";
    /**
     * 获取 API 列表
     */
    String GET_API_LIST = "getApiList";
    /**
     * 获取流列表，可选筛选参数
     */
    String GET_MEDIA_LIST = "getMediaList";
    /**
     * 获取服务器配置
     */
    String GET_SERVER_CONFIG = "getServerConfig";
    /**
     * 获取各 epoll(或 select)线程负载以及延时
     */
    String GET_THREADS_LOAD = "getThreadsLoad";
    /**
     * 获取各后台 epoll(或 select)线程负载以及延时
     */
    String GET_WORK_THREADS_LOAD = "getWorkThreadsLoad";
    /**
     * 断开 tcp 连接，比如说可以断开 rtsp、rtmp 播放器等
     */
    String KICK_SESSION = "kick_session";
    /**
     * 断开 tcp 连接，比如说可以断开 rtsp、rtmp 播放器等
     */
    String KICK_SESSIONS = "kick_sessions";
    /**
     * 重启服务器,只有 Daemon 方式才能重启，否则是直接关闭！
     */
    String RESTART_SERVER = "restartServer";
    /**
     * 设置服务器配置
     */
    String SET_SERVER_CONFIG = "setServerConfig";
    /**
     * 判断直播流是否在线 (已过期，请使用getMediaList接口替代)
     */
    @Deprecated
    String IS_MEDIA_ONLINE = "isMediaOnline";
    /**
     * 获取流相关信息(已过期，请使用getMediaList接口替代)
     */
    @Deprecated
    String GET_MEDIA_INFO = "getMediaInfo";
    /**
     * 获取 rtp 代理时的某路 ssrc rtp 信息
     */
    String GET_RTP_INFO = "getRtpInfo";
    /**
     * 搜索文件系统，获取流对应的录像文件列表或日期文件夹列表
     */
    String GET_MP4_RECORD_FILE = "getMp4RecordFile";
    /**
     * 开始录制 hls 或 MP4
     */
    String START_RECORD = "startRecord";
    /**
     * 停止录制流
     */
    String STOP_RECORD = "stopRecord";
    /**
     *
     */
    String GET_RECORD_STATUS = "getRecordStatus";
    /**
     * 获取截图或生成实时截图并返回
     */
    String GET_SNAP = "getSnap";
    /**
     * 创建 GB28181 RTP 接收端口，如果该端口接收数据超时，则会自动被回收(不用调用 closeRtpServer 接口)
     */
    String OPEN_RTP_SERVER = "openRtpServer";
    /**
     * 关闭 GB28181 RTP 接收端口
     */
    String CLOSE_RTP_SERVER = "closeRtpServer";
    /**
     * 获取 openRtpServer 接口创建的所有 RTP 服务器
     */
    String LIST_RTP_SERVER = "listRtpServer";
    /**
     * 作为 GB28181 客户端，启动 ps-rtp 推流，支持 rtp/udp 方式；该接口支持 rtsp/rtmp 等协议转 ps-rtp 推流。第一次推流失败会直接返回错误，成功一次后，后续失败也将无限重试。
     */
    String START_SEND_RTP = "startSendRtp";
    /**
     * 停止 GB28181 ps-rtp 推流
     */
    String STOP_SEND_RTP = "stopSendRtp";
    /**
     * 获取主要对象个数统计，主要用于分析内存性能
     */
    String GET_STATISTIC = "getStatistic";
    /**
     * 添加 rtsp/rtmp 主动推流(把本服务器的直播流推送到其他服务器去)
     */
    String ADD_STREAM_PUSHER_PROXY = "addStreamPusherProxy";
    /**
     * 关闭推流(可以使用close_streams接口关闭源直播流也可以停止推流)
     */
    @Deprecated
    String DEL_STREAM_PUSHER_PROXY = "delStreamPusherProxy";
    /**
     * 获取版本信息，如分支，commit id, 编译时间
     */
    String VERSION = "version";
    /**
     * 获取某个流观看者列表
     */
    String GET_MEDIA_PLAYER_LIST = "getMediaPlayerList";
    /**
     * 作为 GB28181 Passive TCP 服务器；该接口支持 rtsp/rtmp 等协议转 ps-rtp 被动推流。调用该接口，zlm 会启动 tcp 服务器等待连接请求，连接建立后，zlm 会关闭 tcp 服务器，然后源源不断的往客户端推流。第一次推流失败会直接返回错误，成功一次后，后续失败也将无限重试(不停地建立 tcp 监听，超时后再关闭)。
     */
    String START_SEND_RTP_PASSIVE = "startSendRtpPassive";

    /**
     * 删除录像文件夹
     */
    String DELETE_RECORD_DIRECTORY = "deleteRecordDirectory";

    /**
     * 更新RTP服务器过滤SSRC
     */
    String UPDATE_RTP_SERVER_SSRC = "updateRtpServerSSRC";
    /**
     * 连接RTP服务器
     */
    String CONNECT_RTP_SERVER = "connectRtpServer";
    /**
     * 暂停RTP超时检查
     */
    String PAUSE_RTP_CHECK = "pauseRtpCheck";
    /**
     * 恢复RTP超时检查
     */
    String RESUME_RTP_CHECK = "resumeRtpCheck";
}
