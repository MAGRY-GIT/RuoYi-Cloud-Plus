package com.cdzeroly.wvp.gb28181.session;

import com.cdzeroly.wvp.conf.task.DynamicTask;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MGAR
 */
@Component
@Slf4j
@AllArgsConstructor
public class SseSessionManager {

    private static final Map<String, SseEmitter> SSE_EMITTER_MAP = new ConcurrentHashMap<>();


    /**
     * SSE 连接
     * @param browserId 浏览器 ID
     * @return SseEmitter
     */
    public SseEmitter conect(String browserId){
        SseEmitter sseEmitter = new SseEmitter(0L);
        sseEmitter.onError((err)-> {
            log.error("[SSE推送] 连接错误, 浏览器 ID: {}, {}", browserId, err.getMessage());
            SSE_EMITTER_MAP.remove(browserId);
            sseEmitter.completeWithError(err);
        });


        sseEmitter.onCompletion(() -> {
            log.info("[SSE推送] 连接结束, 浏览器 ID: {}", browserId);
            SSE_EMITTER_MAP.remove(browserId);
        });

        SSE_EMITTER_MAP.put(browserId, sseEmitter);

        log.info("[SSE推送] 连接已建立, 浏览器 ID: {}, 当前在线数: {}", browserId, SSE_EMITTER_MAP.size());
        return sseEmitter;
    }

    @Scheduled(fixedRate = 1000)   //每1秒执行一次
    public void execute(){
        if (SSE_EMITTER_MAP.isEmpty()){
            return;
        }
        sendForAll("keepalive", "alive");
    }

    /**
     * 发送通知事件
     * @param event  事件名称
     * @param data 数据
     */
    public void sendForAll(String event, Object data) {
        for (String browserId : SSE_EMITTER_MAP.keySet()) {
            SseEmitter sseEmitter = SSE_EMITTER_MAP.get(browserId);
            if (sseEmitter == null) {
                continue;
            };
            try {
                sseEmitter.send(SseEmitter.event().name(event).data(data));
            } catch (Exception e) {
                log.error("[SSE推送] 发送失败: {}", e.getMessage());
                SSE_EMITTER_MAP.remove(browserId);
                sseEmitter.completeWithError(e);
            }
        }
    }
}
