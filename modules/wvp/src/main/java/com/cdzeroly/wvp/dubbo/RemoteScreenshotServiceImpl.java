package com.cdzeroly.wvp.dubbo;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.cdzeroly.wvp.api.RemoteScreenshotService;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.domain.StreamProxy;
import com.cdzeroly.wvp.mapper.StreamProxyMapper;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * @author : MGARY
 * @description :
 * @createDate : 2025/2/8 10:13
 */
@Slf4j
@AllArgsConstructor
@Service
@DubboService
public class RemoteScreenshotServiceImpl  implements RemoteScreenshotService {

    private final StreamProxyMapper streamProxyMapper;

    private final IMediaServerService mediaServerService;


    @Override
    public void screenshotStorageProxy(String path, String prefix) {
        List<StreamProxy> list = streamProxyMapper.selectList().stream().filter(StreamProxy::isEnable).toList();
        Optional<MediaServer> mediaServerOptional = mediaServerService.getAll().stream().filter(MediaServer::isStatus).findFirst();
        if (StrUtil.isBlank(path)){
            String userDir = System.getProperty("user.dir");
            path = FileUtil.getAbsolutePath(userDir);

        }
        String temPath = path;
        mediaServerOptional.ifPresent((mediaServer)->{
            list.forEach((streamProxy)->{
                String finalPath = temPath + File.separator + streamProxy.getApp() + File.separator + streamProxy.getStream() + File.separator;
                String fileName = DateUtil.format(new DateTime(), DatePattern.PURE_DATETIME_MS_PATTERN) +".jpg";

                mediaServerService.getSnap(mediaServer, streamProxy.getSrcUrl(), 15, 1, finalPath, fileName);
            });
        });
    }


}
