package com.cdzeroly.wvp.conf.redis;


import com.cdzeroly.wvp.common.VideoManagerConstants;
import com.cdzeroly.wvp.service.redisMsg.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;


/**
 * Redis中间件配置类，使用spring-data-redis集成，自动从application.yml中加载redis配置
 * @author swwheihei
 *
 */
@Configuration
@Order(value=1)
@AllArgsConstructor
public class RedisMsgListenConfig {

	private final RedisGpsMsgListener redisGPSMsgListener;

	private final RedisAlarmMsgListener redisAlarmMsgListener;

	private final RedisPushStreamStatusMsgListener redisPushStreamStatusMsgListener;

	private final RedisPushStreamListMsgListener pushStreamListMsgListener;


	private final RedisCloseStreamMsgListener redisCloseStreamMsgListener;


	private final RedisRpcConfig redisRpcConfig;

	private final RedisPushStreamResponseListener redisPushStreamCloseResponseListener;


	/**
	 * redis消息监听器容器 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
	 * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
	 *
	 * @param connectionFactory
	 * @return
	 */
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
		container.addMessageListener(redisGPSMsgListener, new PatternTopic(VideoManagerConstants.VM_MSG_GPS));
		container.addMessageListener(redisAlarmMsgListener, new PatternTopic(VideoManagerConstants.VM_MSG_SUBSCRIBE_ALARM_RECEIVE));
		container.addMessageListener(redisPushStreamStatusMsgListener, new PatternTopic(VideoManagerConstants.VM_MSG_PUSH_STREAM_STATUS_CHANGE));
		container.addMessageListener(pushStreamListMsgListener, new PatternTopic(VideoManagerConstants.VM_MSG_PUSH_STREAM_LIST_CHANGE));
		container.addMessageListener(redisCloseStreamMsgListener, new PatternTopic(VideoManagerConstants.VM_MSG_STREAM_PUSH_CLOSE));
		container.addMessageListener(redisRpcConfig, new PatternTopic(RedisRpcConfig.REDIS_REQUEST_CHANNEL_KEY));
		container.addMessageListener(redisPushStreamCloseResponseListener, new PatternTopic(VideoManagerConstants.VM_MSG_STREAM_PUSH_RESPONSE));
        return container;
    }
}
