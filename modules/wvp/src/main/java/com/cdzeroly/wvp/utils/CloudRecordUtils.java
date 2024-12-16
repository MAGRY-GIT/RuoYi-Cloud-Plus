package com.cdzeroly.wvp.utils;

import com.cdzeroly.wvp.media.domian.MediaServer;
import com.cdzeroly.wvp.service.bean.DownloadFileInfo;

public class CloudRecordUtils {

    public static DownloadFileInfo getDownloadFilePath(MediaServer mediaServerItem, String filePath) {
        DownloadFileInfo downloadFileInfo = new DownloadFileInfo();

        String pathTemplate = "%s://%s:%s/index/api/downloadFile?file_path=" + filePath;

        downloadFileInfo.setHttpPath(String.format(pathTemplate, "http", mediaServerItem.getStreamIp(),
                mediaServerItem.getHttpPort()));

        if (mediaServerItem.getHttpSslPort() > 0) {
            downloadFileInfo.setHttpsPath(String.format(pathTemplate, "https", mediaServerItem.getStreamIp(),
                    mediaServerItem.getHttpSslPort()));
        }
        return downloadFileInfo;
    }
}
