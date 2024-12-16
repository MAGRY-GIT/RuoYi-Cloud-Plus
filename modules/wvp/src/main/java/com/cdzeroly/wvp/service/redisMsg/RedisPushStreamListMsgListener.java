package com.cdzeroly.wvp.service.redisMsg;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.streamPush.bean.RedisPushStreamMessage;
import com.cdzeroly.wvp.streamPush.domian.vo.StreamPushVo;
import com.cdzeroly.wvp.streamPush.service.IStreamPushService;
import com.cdzeroly.wvp.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Auther: JiangFeng
 * @Date: 2022/8/16 11:32
 * @Description: 接收redis发送的推流设备列表更新通知
 * 监听：  SUBSCRIBE VM_MSG_PUSH_STREAM_LIST_CHANGE
 * 发布 PUBLISH VM_MSG_PUSH_STREAM_LIST_CHANGE '[{"app":1000,"stream":10000000,"gbId":"12345678901234567890","name":"A6","status":false},{"app":1000,"stream":10000021,"gbId":"24212345671381000021","name":"终端9273","status":false},{"app":1000,"stream":10000022,"gbId":"24212345671381000022","name":"终端9434","status":true},{"app":1000,"stream":10000025,"gbId":"24212345671381000025","name":"华为M10","status":false},{"app":1000,"stream":10000051,"gbId":"11111111111381111122","name":"终端9720","status":false}]'
 */
@Slf4j
@Component
public class RedisPushStreamListMsgListener implements MessageListener {

    @Resource
    private IMediaServerService mediaServerService;

    @Resource
    private IStreamPushService streamPushService;

    private final ConcurrentLinkedQueue<Message> taskQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info("[REDIS: 流设备列表更新]： {}", new String(message.getBody()));
        taskQueue.offer(message);
    }

    @Scheduled(fixedDelay = 100)
    public void executeTaskQueue() {
        if (taskQueue.isEmpty()) {
            return;
        }
        List<Message> messageDataList = new ArrayList<>();
        int size = taskQueue.size();
        for (int i = 0; i < size; i++) {
            Message msg = taskQueue.poll();
            if (msg != null) {
                messageDataList.add(msg);
            }
        }
        if (messageDataList.isEmpty()) {
            return;
        }
        for (Message msg : messageDataList) {
            try {
                List<RedisPushStreamMessage> streamPushItems = JSON.parseArray(new String(msg.getBody()), RedisPushStreamMessage.class);
                //查询全部的app+stream 用于判断是添加还是修改
                Map<String, StreamPushVo> allAppAndStream = streamPushService.getAllAppAndStreamMap();
                Map<String, StreamPushVo> allGBId = streamPushService.getAllGBId();

                // 用于存储更具APP+Stream过滤后的数据，可以直接存入stream_push表与gb_stream表
                List<StreamPushVo> streamPushVoItemForSave = new ArrayList<>();
                List<StreamPushVo> streamPushVoItemForUpdate = new ArrayList<>();
                for (RedisPushStreamMessage pushStreamMessage : streamPushItems) {
                    String app = pushStreamMessage.getApp();
                    String stream = pushStreamMessage.getStream();
                    boolean contains = allAppAndStream.containsKey(app + stream);
                    //不存在就添加
                    if (!contains) {
                        if (allGBId.containsKey(pushStreamMessage.getGbId())) {
                            StreamPushVo streamPushVoInDb = allGBId.get(pushStreamMessage.getGbId());
                            log.warn("[REDIS消息-推流设备列表更新-INSERT] 国标编号重复: {}, 已分配给{}/{}",
                                    streamPushVoInDb.getGbDeviceId(), streamPushVoInDb.getApp(), streamPushVoInDb.getStream());
                            continue;
                        }
                        StreamPushVo streamPushVo = pushStreamMessage.buildstreamPush();
                        streamPushVo.setMediaServerId(mediaServerService.getDefaultMediaServer().getId());
                        streamPushVoItemForSave.add(streamPushVo);
                        allGBId.put(streamPushVo.getGbDeviceId(), streamPushVo);
                    } else {
                        StreamPushVo streamPushVoForGbDeviceId = allGBId.get(pushStreamMessage.getGbId());
                        if (streamPushVoForGbDeviceId != null
                                && (!streamPushVoForGbDeviceId.getApp().equals(pushStreamMessage.getApp())
                                || !streamPushVoForGbDeviceId.getStream().equals(pushStreamMessage.getStream()))) {
                            StreamPushVo streamPushVoInDb = allGBId.get(pushStreamMessage.getGbId());
                            log.warn("[REDIS消息-推流设备列表更新-UPDATE] 国标编号重复: {}, 已分配给{}/{}",
                                    pushStreamMessage.getGbId(), streamPushVoInDb.getApp(), streamPushVoInDb.getStream());
                            continue;
                        }
                        StreamPushVo streamPushVo = allAppAndStream.get(app + stream);
                        streamPushVo.setGbDeviceId(pushStreamMessage.getGbId());
                        streamPushVo.setGbName(pushStreamMessage.getName());
                        streamPushVo.setGbStatus(pushStreamMessage.isStatus() ? "ON" : "OFF");
                        //存在就只修改 name和gbId
                        streamPushVoItemForUpdate.add(streamPushVo);
                    }
                }
                if (!streamPushVoItemForSave.isEmpty()) {
                    log.info("添加{}条", streamPushVoItemForSave.size());
                    log.info(JSONObject.toJSONString(streamPushVoItemForSave));
                    streamPushService.batchAdd(streamPushVoItemForSave);

                }
                if (!streamPushVoItemForUpdate.isEmpty()) {
                    log.info("修改{}条", streamPushVoItemForUpdate.size());
                    log.info(JSONObject.toJSONString(streamPushVoItemForUpdate));
                    streamPushService.batchUpdate(streamPushVoItemForUpdate);
                }
            } catch (Exception e) {
                log.warn("[REDIS消息-推流设备列表更新] 发现未处理的异常, \r\n{}", new String(msg.getBody()));
                log.error("[REDIS消息-推流设备列表更新] 异常内容： ", e);
            }
        }

    }
}
