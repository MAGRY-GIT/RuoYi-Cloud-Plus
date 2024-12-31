package com.cdzeroly.wvp.service.impl;

import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.system.api.RemoteUserService;
import com.cdzeroly.wvp.common.InviteInfo;
import com.cdzeroly.wvp.common.InviteSessionStatus;
import com.cdzeroly.wvp.common.InviteSessionType;
import com.cdzeroly.wvp.common.VideoManagerConstants;
import com.cdzeroly.wvp.conf.UserSetting;
import com.cdzeroly.wvp.gb28181.domian.DeviceChannel;
import com.cdzeroly.wvp.domain.bean.SsrcTransaction;
import com.cdzeroly.wvp.gb28181.service.IDeviceChannelService;
import com.cdzeroly.wvp.gb28181.service.IInviteStreamService;
import com.cdzeroly.wvp.gb28181.session.SipInviteSessionManager;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.zlm.dto.ResultForOnPublish;
import com.cdzeroly.wvp.media.zlm.dto.StreamAuthorityInfo;
import com.cdzeroly.wvp.service.IMediaService;
import com.cdzeroly.wvp.service.IRecordPlanService;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import com.cdzeroly.wvp.domain.vo.StreamProxyVo;
import com.cdzeroly.wvp.service.IStreamProxyService;
import com.cdzeroly.wvp.utils.DateUtil;
import com.cdzeroly.wvp.utils.MediaServerUtils;
import com.cdzeroly.wvp.domain.OtherPsSendInfo;
import com.cdzeroly.wvp.domain.OtherRtpSendInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author MAGRY
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements IMediaService {

    private final IRedisCatchStorage redisCatchStorage;

    private final IStreamProxyService streamProxyService;

    private final UserSetting userSetting;

    private final RedisTemplate<Object, Object> redisTemplate;

    private final IInviteStreamService inviteStreamService;

    private final IDeviceChannelService deviceChannelService;

    private final SipInviteSessionManager sessionManager;

    private final IRecordPlanService recordPlanService;

    @DubboReference
    private RemoteUserService remoteUserService;


    @Override
    public boolean authenticatePlay(String app, String stream, String callId) {
        if (app == null || stream == null) {
            return false;
        }
        if ("rtp".equals(app)) {
            return true;
        }
        StreamAuthorityInfo streamAuthorityInfo = redisCatchStorage.getStreamAuthorityInfo(app, stream);
        if (streamAuthorityInfo == null || streamAuthorityInfo.getCallId() == null) {
            return true;
        }
        return streamAuthorityInfo.getCallId().equals(callId);
    }

    @Override
    public ResultForOnPublish authenticatePublish(MediaServer mediaServer, String app, String stream, String params) {
        // 推流鉴权的处理
        if (!"rtp".equals(app)) {
            if ("talk".equals(app) && stream.endsWith("_talk")) {
                ResultForOnPublish result = new ResultForOnPublish();
                result.setEnable_mp4(false);
                result.setEnable_audio(true);
                return result;
            }
            StreamProxyVo streamProxyVo = streamProxyService.getStreamProxyByAppAndStream(app, stream);
            if (streamProxyVo != null) {
                ResultForOnPublish result = new ResultForOnPublish();
                result.setEnable_audio(streamProxyVo.isEnableAudio());
                result.setEnable_mp4(streamProxyVo.isEnableMp4());
                return result;
            }
            if (userSetting.getPushAuthority()) {
                // 对于推流进行鉴权
                Map<String, String> paramMap = MediaServerUtils.urlParamToMap(params);
                // 推流鉴权
                if (params == null) {
                    log.info("推流鉴权失败： 缺少必要参数：sign=md5(user表的pushKey)");
                    throw new ServiceException( "Unauthorized");
                }

                String sign = paramMap.get("sign");
                if (sign == null) {
                    log.info("推流鉴权失败： 缺少必要参数：sign=md5(user表的pushKey)");
                    throw new ServiceException( "Unauthorized");
                }
                // 推流自定义播放鉴权码
                String callId = paramMap.get("callId");
                // 鉴权配置
                boolean hasAuthority = this.checkPushAuthority(callId, sign);
                if (!hasAuthority) {
                    log.info("推流鉴权失败： sign 无权限: callId={}. sign={}", callId, sign);
                    throw new ServiceException( "Unauthorized");
                }
                StreamAuthorityInfo streamAuthorityInfo = StreamAuthorityInfo.getInstanceByHook(app, stream, mediaServer.getId());
                streamAuthorityInfo.setCallId(callId);
                streamAuthorityInfo.setSign(sign);
                // 鉴权通过
                redisCatchStorage.updateStreamAuthorityInfo(app, stream, streamAuthorityInfo);
            }
        }


        ResultForOnPublish result = new ResultForOnPublish();
        result.setEnable_audio(true);

        // 是否录像
        if ("rtp".equals(app)) {
            result.setEnable_mp4(userSetting.getRecordSip());
        } else {
            result.setEnable_mp4(userSetting.getRecordPushLive());
        }
        // 国标流
        if ("rtp".equals(app)) {

            InviteInfo inviteInfo = inviteStreamService.getInviteInfoByStream(null, stream);

            // 单端口模式下修改流 ID
            if (!mediaServer.isRtpEnable() && inviteInfo == null) {
                String ssrc = String.format("%010d", Long.parseLong(stream, 16));
                inviteInfo = inviteStreamService.getInviteInfoBySsrc(ssrc);
                if (inviteInfo != null) {
                    result.setStream_replace(inviteInfo.getStream());
                    log.info("[ZLM HOOK]推流鉴权 stream: {} 替换为 {}", stream, inviteInfo.getStream());
                    stream = inviteInfo.getStream();
                }
            }

            // 设置音频信息及录制信息
            SsrcTransaction ssrcTransaction = sessionManager.getSsrcTransactionByStream(stream);
            if (ssrcTransaction != null ) {

                // 为录制国标模拟一个鉴权信息, 方便后续写入录像文件时使用
                StreamAuthorityInfo streamAuthorityInfo = StreamAuthorityInfo.getInstanceByHook(app, stream, mediaServer.getId());
                streamAuthorityInfo.setApp(app);
                streamAuthorityInfo.setStream(ssrcTransaction.getStream());
                streamAuthorityInfo.setCallId(ssrcTransaction.getSipTransactionInfo().getCallId());

                redisCatchStorage.updateStreamAuthorityInfo(app, ssrcTransaction.getStream(), streamAuthorityInfo);

                String deviceId = ssrcTransaction.getDeviceId();
                Long channelId = ssrcTransaction.getChannelId();
                DeviceChannel deviceChannel = deviceChannelService.getOneForSourceById(channelId);
                if (deviceChannel != null) {
                    result.setEnable_audio(deviceChannel.isHasAudio());
                }
                // 如果是录像下载就设置视频间隔十秒
                if (ssrcTransaction.getType() == InviteSessionType.DOWNLOAD) {
                    // 获取录像的总时长，然后设置为这个视频的时长
                    InviteInfo inviteInfoForDownload = inviteStreamService.getInviteInfo(InviteSessionType.DOWNLOAD,  channelId, stream);
                    if (inviteInfoForDownload != null && inviteInfoForDownload.getStreamInfo() != null) {
                        String startTime = inviteInfoForDownload.getStreamInfo().getStartTime();
                        String endTime = inviteInfoForDownload.getStreamInfo().getEndTime();
                        long difference = DateUtil.getDifference(startTime, endTime) / 1000;
                        result.setMp4_max_second((int) difference);
                        result.setEnable_mp4(true);
                        // 设置为2保证得到的mp4的时长是正常的
                        result.setModify_stamp(2);
                    }
                }
                // 如果是talk对讲，则默认获取声音
                if (ssrcTransaction.getType() == InviteSessionType.TALK) {
                    result.setEnable_audio(true);
                }
            }
        } else if ("broadcast".equals(app)) {
            result.setEnable_audio(true);
        } else if ("talk".equals(app)) {
            result.setEnable_audio(true);
        }
        if ("rtp".equalsIgnoreCase(app)) {
            String receiveKey = VideoManagerConstants.WVP_OTHER_RECEIVE_RTP_INFO + userSetting.getServerId() + "_" + stream;
            OtherRtpSendInfo otherRtpSendInfo = (OtherRtpSendInfo) redisTemplate.opsForValue().get(receiveKey);

            String receiveKeyForPS = VideoManagerConstants.WVP_OTHER_RECEIVE_PS_INFO + userSetting.getServerId() + "_" + stream;
            OtherPsSendInfo otherPsSendInfo = (OtherPsSendInfo) redisTemplate.opsForValue().get(receiveKeyForPS);
            if (otherRtpSendInfo != null || otherPsSendInfo != null) {
                result.setEnable_mp4(true);
            }
        }
        return result;
    }

    @Override
    public boolean closeStreamOnNoneReader(String mediaServerId, String app, String stream, String schema) {
        boolean result = false;
        if (recordPlanService.recording(app, stream) != null) {
            return false;
        }
        // 国标类型的流
        if ("rtp".equals(app)) {
            result = userSetting.getStreamOnDemand();
            // 国标流， 点播/录像回放/录像下载
            InviteInfo inviteInfo = inviteStreamService.getInviteInfoByStream(null, stream);
            // 点播
            if (inviteInfo != null && inviteInfo.getStatus() == InviteSessionStatus.OK) {
                // 录像下载
                if (inviteInfo.getType() == InviteSessionType.DOWNLOAD) {
                    return false;
                }
                DeviceChannel deviceChannel = deviceChannelService.getOneForSourceById(inviteInfo.getChannelId());
                if (deviceChannel == null) {
                    return false;
                }
                return result;
            }else {
                return false;
            }
        } else if ("talk".equals(app) || "broadcast".equals(app)) {
            return false;
        } else {
            // 非国标流 推流/拉流代理
            // 拉流代理
            StreamProxyVo streamProxyVo = streamProxyService.getStreamProxyByAppAndStream(app, stream);
            if (streamProxyVo != null) {
                if (streamProxyVo.isEnableRemoveNoneReader()) {
                    // 无人观看自动移除
                    streamProxyService.delteByAppAndStream(app, stream);
                    log.info("[{}/{}]<-[{}] 拉流代理无人观看已经移除", app, stream, streamProxyVo.getSrcUrl());
                    return true;
                } else if (streamProxyVo.isEnableDisableNoneReader()) {
                    // 无人观看停用
                    // 修改数据
                    streamProxyService.stopByAppAndStream(app, stream);
                    return true;
                } else {
                    // 无人观看不做处理
                    return false;
                }
            }else {
                return false;
            }
        }
    }

    /**
     *
     * @param callId  自定义签名
     * @param sign  签名
     * @return boolean
     */
    public boolean checkPushAuthority(String callId, String sign) {
        // List<RemoteUserVo> users = remoteUserService.selectListAll();
        // if (users.isEmpty())  {
        //     return false;
        // }
        // for (RemoteUserVo user : users) {
        //     if (user.getSecret() == null) {
        //         continue;
        //     }
        //     String checkStr = callId == null? user.getSecret():(callId + "_" + user.getSecret())  ;
        //     String checkSign = DigestUtils.md5DigestAsHex(checkStr.getBytes());
        //     if (checkSign.equals(sign)) {
        //         return true;
        //     }
        // }
        return true;
    }
}
