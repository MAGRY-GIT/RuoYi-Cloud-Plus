package com.cdzeroly.wvp.utils;

import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MGARY
 */
public class MediaServerUtils {

    public static Map<String, String> urlParamToMap(String params) {
        HashMap<String, String> map = new HashMap<>();
        if (ObjectUtils.isEmpty(params)) {
            return map;
        }
        String[] paramsArray = params.split("&");
        for (String param : paramsArray) {
            String[] paramArray = param.split("=");
            if (paramArray.length == 2) {
                map.put(paramArray[0], paramArray[1]);
            }
        }
        return map;
    }
}
