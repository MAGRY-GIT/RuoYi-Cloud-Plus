package com.cdzeroly.wvp.domain.bean;

import com.cdzeroly.wvp.domain.MediaServer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class DownloadFileInfo {

    private String httpPath;
    private String httpsPath;
    private String httpDomainPath;
    private String httpsDomainPath;


    public static DownloadFileInfo convert(MediaServer mediaServerItem, String filePath) {
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
