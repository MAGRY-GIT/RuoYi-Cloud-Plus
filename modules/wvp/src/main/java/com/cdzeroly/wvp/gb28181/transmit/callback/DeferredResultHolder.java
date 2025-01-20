package com.cdzeroly.wvp.gb28181.transmit.callback;

import com.cdzeroly.wvp.domain.DeferredResultEx;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  异步请求处理
 * @author swwheihei
 */
@SuppressWarnings(value = {"rawtypes", "unchecked"})
@Component
public class DeferredResultHolder {

	public static final String CALLBACK_CMD_DEVICE_STATUS = "CALLBACK_DEVICE_STATUS_";

	public static final String CALLBACK_CMD_DEVICE_INFO = "CALLBACK_DEVICE_INFO_";

	public static final String CALLBACK_CMD_DEVICE_CONTROL = "CALLBACK_DEVICE_CONTROL_";

	public static final String CALLBACK_CMD_DEVICE_CONFIG = "CALLBACK_DEVICE_CONFIG_";

	public static final String CALLBACK_CMD_CONFIG_DOWNLOAD = "CALLBACK_CONFIG_DOWNLOAD_";

	public static final String CALLBACK_CMD_CATALOG = "CALLBACK_CATALOG_";

	public static final String CALLBACK_CMD_RECORD_INFO = "CALLBACK_RECORD_INFO_";

	public static final String CALLBACK_CMD_PLAY = "CALLBACK_PLAY_";

	public static final String CALLBACK_CMD_PLAYBACK = "CALLBACK_PLAYBACK_";

	public static final String CALLBACK_CMD_DOWNLOAD = "CALLBACK_DOWNLOAD_";

	public static final String CALLBACK_CMD_PROXY = "CALLBACK_PROXY_";

	public static final String CALLBACK_CMD_STOP = "CALLBACK_STOP_";

	public static final String UPLOAD_FILE_CHANNEL = "UPLOAD_FILE_CHANNEL_";

	public static final String CALLBACK_CMD_MOBILE_POSITION = "CALLBACK_CMD_MOBILE_POSITION_";

	public static final String CALLBACK_CMD_PRESET_QUERY = "CALLBACK_PRESET_QUERY_";

	public static final String CALLBACK_CMD_ALARM = "CALLBACK_ALARM_";

	public static final String CALLBACK_CMD_BROADCAST = "CALLBACK_BROADCAST_";

	public static final String CALLBACK_CMD_SNAP= "CALLBACK_SNAP_";

	private final Map<String, Map<String, DeferredResultEx>> MAP = new ConcurrentHashMap<>();


	public void put(String key, String id, DeferredResultEx result) {
        Map<String, DeferredResultEx> deferredResultMap = MAP.computeIfAbsent(key, k -> new ConcurrentHashMap<>());
        deferredResultMap.put(id, result);
	}

	public void put(String key, String id, DeferredResult result) {
        Map<String, DeferredResultEx> deferredResultMap = MAP.computeIfAbsent(key, k -> new ConcurrentHashMap<>());
        deferredResultMap.put(id, new DeferredResultEx(result));
	}

	public DeferredResultEx get(String key, String id) {
		Map<String, DeferredResultEx> deferredResultMap = MAP.get(key);
		if (deferredResultMap == null || ObjectUtils.isEmpty(id)) {
			return null;
		}
		return deferredResultMap.get(id);
	}

	public Collection<DeferredResultEx> getAllByKey(String key) {
		Map<String, DeferredResultEx> deferredResultMap = MAP.get(key);
		if (deferredResultMap == null) {
			return null;
		}
		return deferredResultMap.values();
	}

	public boolean exist(String key, String id){
		if (key == null) {
			return false;
		}
		Map<String, DeferredResultEx> deferredResultMap = MAP.get(key);
		if (id == null) {
			return deferredResultMap != null;
		}else {
			return deferredResultMap != null && deferredResultMap.get(id) != null;
		}
	}

	/**
	 * 释放单个请求
	 * @param msg 消息
	 */
	public void invokeResult(RequestMessage msg) {
		Map<String, DeferredResultEx> deferredResultMap = MAP.get(msg.getKey());
		if (deferredResultMap == null) {
			return;
		}
		DeferredResultEx result = deferredResultMap.get(msg.getId());
		if (result == null) {
			return;
		}
		result.getDeferredResult().setResult(msg.getData());
		deferredResultMap.remove(msg.getId());
		if (deferredResultMap.isEmpty()) {
			MAP.remove(msg.getKey());
		}
	}

	/**
	 * 释放所有的请求
	 * @param msg 消息
	 */
	public void invokeAllResult(RequestMessage msg) {
		Map<String, DeferredResultEx> deferredResultMap = MAP.get(msg.getKey());
		if (deferredResultMap == null) {
			return;
		}
		synchronized (this) {
			deferredResultMap = MAP.get(msg.getKey());
			if (deferredResultMap == null) {
				return;
			}
			Set<String> ids = deferredResultMap.keySet();
			for (String id : ids) {
				DeferredResultEx result = deferredResultMap.get(id);
				if (result == null) {
					return;
				}
				if (result.getFilter() != null) {
					Object handler = result.getFilter().handler(msg.getData());
					result.getDeferredResult().setResult(handler);
				}else {
					result.getDeferredResult().setResult(msg.getData());
				}

			}
			MAP.remove(msg.getKey());
		}
	}


}
