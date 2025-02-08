package com.cdzeroly.wvp.api;


/**
 * 远程截图服务
 *
 * @author MGARY
 */
public interface RemoteScreenshotService {



    /**
     * 截取代理的数据流图片
     *
     * @param path 存储路径
     * @param prefix 照片前缀
     */
    void screenshotStorageProxy(String path, String prefix);
}
