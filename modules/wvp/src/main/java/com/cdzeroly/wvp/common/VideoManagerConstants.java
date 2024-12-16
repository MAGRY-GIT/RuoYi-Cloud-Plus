package com.cdzeroly.wvp.common;

/**
 * @description: 定义常量
 * @author: swwheihei
 * @date: 2019年5月30日 下午3:04:04
 */
public interface VideoManagerConstants {

    String WVP_SERVER_PREFIX = "VMP_SIGNALLING_SERVER_INFO_";

    String WVP_SERVER_STREAM_PREFIX = "VMP_SIGNALLING_STREAM_";

    String MEDIA_SERVER_PREFIX = "VMP_MEDIA_SERVER_INFO:";

    String ONLINE_MEDIA_SERVERS_PREFIX = "VMP_ONLINE_MEDIA_SERVERS:";

    String DEVICE_PREFIX = "VMP_DEVICE_INFO";

    String INVITE_PREFIX = "VMP_GB_INVITE_INFO";

    String PLATFORM_CATCH_PREFIX = "VMP_PLATFORM_CATCH_";

    String PLATFORM_REGISTER_INFO_PREFIX = "VMP_PLATFORM_REGISTER_INFO_";

    String SEND_RTP_PORT = "VM_SEND_RTP_PORT:";
    String SEND_RTP_INFO_CALLID = "VMP_SEND_RTP_INFO:CALL_ID:";
    String SEND_RTP_INFO_STREAM = "VMP_SEND_RTP_INFO:STREAM:";
    String SEND_RTP_INFO_CHANNEL = "VMP_SEND_RTP_INFO:CHANNEL:";

    String SIP_INVITE_SESSION = "VMP_SIP_INVITE_SESSION_INFO:";
    String SIP_INVITE_SESSION_CALL_ID = SIP_INVITE_SESSION + "CALL_ID:";
    String SIP_INVITE_SESSION_STREAM = SIP_INVITE_SESSION + "STREAM:";

    String MEDIA_STREAM_AUTHORITY = "VMP_MEDIA_STREAM_AUTHORITY:";

    String SIP_CSEQ_PREFIX = "VMP_SIP_CSEQ_";

    String SIP_SUBSCRIBE_PREFIX = "VMP_SIP_SUBSCRIBE_";

    String SYSTEM_INFO_CPU_PREFIX = "VMP_SYSTEM_INFO_CPU_";

    String SYSTEM_INFO_MEM_PREFIX = "VMP_SYSTEM_INFO_MEM_";

    String SYSTEM_INFO_NET_PREFIX = "VMP_SYSTEM_INFO_NET_";

    String SYSTEM_INFO_DISK_PREFIX = "VMP_SYSTEM_INFO_DISK_";
    String BROADCAST_WAITE_INVITE = "task_broadcast_waite_invite_";

    String REGISTER_EXPIRE_TASK_KEY_PREFIX = "VMP_device_register_expire_";
    String PUSH_STREAM_LIST = "VMP_PUSH_STREAM_LIST_";
    String WAITE_SEND_PUSH_STREAM = "VMP_WAITE_SEND_PUSH_STREAM:";
    String START_SEND_PUSH_STREAM = "VMP_START_SEND_PUSH_STREAM:";
    String SSE_TASK_KEY = "SSE_TASK_";


    //************************** redis 消息*********************************

    /**
     * 流变化的通知
     */
    String WVP_MSG_STREAM_CHANGE_PREFIX = "WVP_MSG_STREAM_CHANGE_";

    /**
     * 接收推流设备的GPS变化通知
     */
    String VM_MSG_GPS = "VM_MSG_GPS";

    /**
     * 接收推流设备的GPS变化通知
     */
    String VM_MSG_PUSH_STREAM_STATUS_CHANGE = "VM_MSG_PUSH_STREAM_STATUS_CHANGE";
    /**
     * 接收推流设备列表更新变化通知
     */
    String VM_MSG_PUSH_STREAM_LIST_CHANGE = "VM_MSG_PUSH_STREAM_LIST_CHANGE";

    /**
     * redis 消息通知设备推流到平台
     */
    String VM_MSG_STREAM_PUSH_REQUESTED = "VM_MSG_STREAM_PUSH_REQUESTED";

    /**
     * redis 消息通知上级平台开始观看流
     */
    String VM_MSG_STREAM_START_PLAY_NOTIFY = "VM_MSG_STREAM_START_PLAY_NOTIFY";

    /**
     * redis 消息通知上级平台停止观看流
     */
    String VM_MSG_STREAM_STOP_PLAY_NOTIFY = "VM_MSG_STREAM_STOP_PLAY_NOTIFY";

    /**
     * redis 消息接收关闭一个推流
     */
    String VM_MSG_STREAM_PUSH_CLOSE_REQUESTED = "VM_MSG_STREAM_PUSH_CLOSE_REQUESTED";


    /**
     * redis 消息通知平台通知设备推流结果
     */
    String VM_MSG_STREAM_PUSH_RESPONSE = "VM_MSG_STREAM_PUSH_RESPONSE";

    /**
     * redis 通知平台关闭推流
     */
    String VM_MSG_STREAM_PUSH_CLOSE = "VM_MSG_STREAM_PUSH_CLOSE";

    /**
     * redis 消息请求所有的在线通道
     */
    String VM_MSG_GET_ALL_ONLINE_REQUESTED = "VM_MSG_GET_ALL_ONLINE_REQUESTED";

    /**
     * 移动位置订阅通知
     */
    String VM_MSG_SUBSCRIBE_MOBILE_POSITION = "mobileposition";

    /**
     * 报警订阅的通知（收到报警向redis发出通知）
     */
    String VM_MSG_SUBSCRIBE_ALARM = "alarm";


    /**
     * 报警通知的发送 （收到redis发出的通知，转发给其他平台）
     */
    String VM_MSG_SUBSCRIBE_ALARM_RECEIVE = "alarm_receive";

    /**
     * 设备状态订阅的通知
     */
    String VM_MSG_SUBSCRIBE_DEVICE_STATUS = "device";


    //**************************    第三方  ****************************************

    String WVP_STREAM_GB_ID_PREFIX = "memberNo_";
    String WVP_STREAM_GPS_MSG_PREFIX = "WVP_STREAM_GPS_MSG_";
    String WVP_OTHER_SEND_RTP_INFO = "VMP_OTHER_SEND_RTP_INFO_";
    String WVP_OTHER_SEND_PS_INFO = "VMP_OTHER_SEND_PS_INFO_";
    String WVP_OTHER_RECEIVE_RTP_INFO = "VMP_OTHER_RECEIVE_RTP_INFO_";
    String WVP_OTHER_RECEIVE_PS_INFO = "VMP_OTHER_RECEIVE_PS_INFO_";

    /**
     * Redis Const
     * 设备录像信息结果前缀
     */
    String REDIS_RECORD_INFO_RES_PRE = "GB_RECORD_INFO_RES_";
    /**
     * Redis Const
     * 设备录像信息结果前缀
     */
    String REDIS_RECORD_INFO_RES_COUNT_PRE = "GB_RECORD_INFO_RES_COUNT:";

}
