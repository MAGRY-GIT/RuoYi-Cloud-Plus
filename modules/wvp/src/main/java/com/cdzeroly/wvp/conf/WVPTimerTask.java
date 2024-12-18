package com.cdzeroly.wvp.conf;

import com.alibaba.fastjson2.JSONObject;
import com.cdzeroly.wvp.storager.IRedisCatchStorage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author MGARY
 */
@Component
public class WVPTimerTask {

    @Autowired
    private  IRedisCatchStorage redisCatchStorage;

    @Value("${server.port}")
    private Integer serverPort;

    @Autowired
    private  SipConfig sipConfig;



    @Scheduled(fixedDelay = 2 * 1000)   //每3秒执行一次
    public void execute(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ip", sipConfig.getShowIp());
        jsonObject.put("port", serverPort);
        redisCatchStorage.updateWVPInfo(jsonObject, 3);
    }
}
