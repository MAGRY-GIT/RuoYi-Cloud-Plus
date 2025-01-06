package com.cdzeroly.wvp.gb28181.service;

import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.wvp.common.CommonCallback;
import com.cdzeroly.wvp.domain.CommonGbChannel;
import com.cdzeroly.wvp.domain.Platform;
import com.cdzeroly.wvp.domain.bean.SipTransactionInfo;
import com.cdzeroly.wvp.gb28181.event.SipSubscribe;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.event.hook.HookSubscribe;
import com.cdzeroly.wvp.domain.bean.InviteTimeOutCallback;


import javax.sip.InvalidArgumentException;
import javax.sip.SipException;
import java.text.ParseException;
import java.util.List;

/**
 * 国标平台的业务类
 * @author lin
 */
public interface IPlatformService {

    Platform queryPlatformByServerGBId(String platformGbId);

    /**
     * 分页获取上级平台
     * @return
     */
    TableDataInfo<Platform> queryPlatformList(PageQuery pageQuery, String query);

    /**
     * 添加级联平台
     * @param parentPlatform 级联平台
     */
    boolean add(Platform parentPlatform);

    /**
     * 添加级联平台
     * @param parentPlatform 级联平台
     */
    boolean update(Platform parentPlatform);

    /**
     * 平台上线
     * @param parentPlatform 平台信息
     */
    void online(Platform parentPlatform, SipTransactionInfo sipTransactionInfo);

    /**
     * 平台离线
     * @param parentPlatform 平台信息
     */
    void offline(Platform parentPlatform, boolean stopRegisterTask);

    /**
     * 向上级平台发起注册
     * @param parentPlatform
     */
    void login(Platform parentPlatform);

    /**
     * 向上级平台发送位置订阅
     * @param platformId 平台
     */
    void sendNotifyMobilePosition(String platformId);

    /**
     * 向上级发送语音喊话的消息
     * @param platform 平台
     * @param channelId 通道
     * @param  sourceId 通道
     * @param hookEvent hook事件
     * @param errorEvent 信令错误事件
     * @param timeoutCallback 超时事件
     */
    void broadcastInvite(Platform platform, CommonGbChannel channelId, String sourceId, MediaServer mediaServerItem, HookSubscribe.Event hookEvent,
                         SipSubscribe.Event errorEvent, InviteTimeOutCallback timeoutCallback) throws InvalidArgumentException, ParseException, SipException;


    /**
     * 语音喊话回复BYE
     */
    void stopBroadcast(Platform platform, CommonGbChannel channel, String stream, boolean sendBye, MediaServer mediaServerItem);

    void addSimulatedSubscribeInfo(Platform parentPlatform);

    Platform queryOne(Long platformId);

    List<Platform> queryEnablePlatformList();

    void delete(Integer platformId, CommonCallback<Object> callback);
}
