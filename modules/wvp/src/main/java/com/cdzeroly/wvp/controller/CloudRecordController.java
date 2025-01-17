package com.cdzeroly.wvp.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONArray;
import com.cdzeroly.common.core.domain.R;
import com.cdzeroly.common.core.exception.ServiceException;
import com.cdzeroly.common.core.utils.AssertUtils;
import com.cdzeroly.common.mybatis.core.page.PageQuery;
import com.cdzeroly.common.mybatis.core.page.TableDataInfo;
import com.cdzeroly.common.web.core.BaseController;
import com.cdzeroly.wvp.gb28181.service.ICloudRecordService;
import com.cdzeroly.wvp.domain.MediaServer;
import com.cdzeroly.wvp.media.service.IMediaServerService;
import com.cdzeroly.wvp.domain.CloudRecord;
import com.cdzeroly.wvp.domain.bean.DownloadFileInfo;
import com.cdzeroly.wvp.domain.bo.CloudRecordItemBo;
import com.cdzeroly.wvp.domain.vo.CloudRecordUrlVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author MGARY
 */
@Tag(name = "云端录像接口")
@Slf4j
@RestController
@RequiredArgsConstructor()
@RequestMapping("/cloudRecord")
public class CloudRecordController extends BaseController {


    private final ICloudRecordService cloudRecordService;

    private final IMediaServerService mediaServerService;


    @GetMapping("/date/list")
    @Operation(summary = "查询存在云端录像的日期")
    @Parameter(name = "app", description = "应用名", required = true)
    @Parameter(name = "stream", description = "流ID", required = true)
    @Parameter(name = "year", description = "年，置空则查询当年", required = false)
    @Parameter(name = "month", description = "月，置空则查询当月", required = false)
    @Parameter(name = "mediaServerId", description = "流媒体ID，置空则查询全部")
    public R<List<String>> openRtpServer(@RequestParam() String app, @RequestParam() String stream, @RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month, @RequestParam(required = false) String mediaServerId
    ) {
        log.info("[云端录像] 查询存在云端录像的日期 app->{}, stream->{}, mediaServerId->{}, year->{}, month->{}", app, stream, mediaServerId, year, month);
        Calendar calendar = Calendar.getInstance();
        if (ObjectUtils.isEmpty(year)) {
            year = calendar.get(Calendar.YEAR);
        }
        if (ObjectUtils.isEmpty(month)) {
            month = calendar.get(Calendar.MONTH) + 1;
        }
        List<MediaServer> mediaServers;
        if (!ObjectUtils.isEmpty(mediaServerId)) {
            mediaServers = new ArrayList<>();
            MediaServer mediaServer = mediaServerService.getOne(mediaServerId);

            AssertUtils.isNotNull(mediaServer, "未找到流媒体: " + mediaServerId);
            mediaServers.add(mediaServer);
        } else {
            mediaServers = mediaServerService.getAllOnlineList();
        }
        if (mediaServers.isEmpty()) {
            return R.ok();
        }

        return R.ok(cloudRecordService.getDateList(app, stream, year, month, mediaServers));
    }


    @GetMapping("/list")
    @Operation(summary = "分页查询云端录像")
    public TableDataInfo<CloudRecord> openRtpServer(CloudRecordItemBo bo, PageQuery pageQuery) {
        List<MediaServer> mediaServers;
        if (!ObjectUtils.isEmpty(bo.getMediaServerId())) {
            mediaServers = new ArrayList<>();
            MediaServer mediaServer = mediaServerService.getOne(bo.getMediaServerId());
            if (mediaServer == null) {
                throw new ServiceException("未找到流媒体: " + bo.getMediaServerId());
            }
            mediaServers.add(mediaServer);
        } else {
            mediaServers = mediaServerService.getAllOnlineList();
        }
        if (mediaServers.isEmpty()) {
            throw new ServiceException("当前无流媒体");
        } else {
            List<String> list = mediaServers.stream().map(MediaServer::getId).distinct().toList();
            bo.setMediaServerIds(list);
        }

        return cloudRecordService.getList(bo, pageQuery);
    }


    @GetMapping("/task/add")
    @Operation(summary = "添加合并任务")
    @Parameter(name = "app", description = "应用名", required = false)
    @Parameter(name = "stream", description = "流ID", required = false)
    @Parameter(name = "mediaServerId", description = "流媒体ID", required = false)
    @Parameter(name = "startTime", description = "鉴权ID", required = false)
    @Parameter(name = "endTime", description = "鉴权ID", required = false)
    @Parameter(name = "callId", description = "鉴权ID", required = false)
    @Parameter(name = "remoteHost", description = "返回地址时的远程地址", required = false)
    public R<String> addTask(HttpServletRequest request, @RequestParam(required = false) String app, @RequestParam(required = false) String stream, @RequestParam(required = false) String mediaServerId, @RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String callId, @RequestParam(required = false) String remoteHost) {
        MediaServer mediaServer;
        if (mediaServerId == null) {
            mediaServer = mediaServerService.getDefaultMediaServer();
        } else {
            mediaServer = mediaServerService.getOne(mediaServerId);
        }
        if (mediaServer == null) {
            throw new ServiceException("未找到可用的流媒体");
        } else {
            if (remoteHost == null) {
                remoteHost = request.getScheme() + "://" + mediaServer.getIp() + ":" + mediaServer.getRecordAssistPort();
            }
        }
        return R.ok(cloudRecordService.addTask(app, stream, mediaServer, startTime, endTime, callId, remoteHost, mediaServerId != null));
    }


    @GetMapping("/task/list")
    @Operation(summary = "查询合并任务")
    @Parameter(name = "taskId", description = "任务Id")
    @Parameter(name = "mediaServerId", description = "流媒体ID")
    @Parameter(name = "isEnd", description = "是否结束")
    public JSONArray queryTaskList(HttpServletRequest request, @RequestParam(required = false) String app, @RequestParam(required = false) String stream, @RequestParam(required = false) String callId, @RequestParam(required = false) String taskId, @RequestParam(required = false) String mediaServerId, @RequestParam(required = false) Boolean isEnd) {
        if (ObjectUtils.isEmpty(mediaServerId)) {
            mediaServerId = null;
        }

        return cloudRecordService.queryTask(app, stream, callId, taskId, mediaServerId, isEnd, request.getScheme());
    }


    @GetMapping("/collect/add")
    @Operation(summary = "添加收藏")
    @Parameter(name = "app", description = "应用名", required = false)
    @Parameter(name = "stream", description = "流ID", required = false)
    @Parameter(name = "mediaServerId", description = "流媒体ID", required = false)
    @Parameter(name = "startTime", description = "鉴权ID", required = false)
    @Parameter(name = "endTime", description = "鉴权ID", required = false)
    @Parameter(name = "callId", description = "鉴权ID", required = false)
    @Parameter(name = "recordId", description = "录像记录的ID，用于精准收藏一个视频文件", required = false)
    public R<Integer> addCollect(@RequestParam(required = false) String app, @RequestParam(required = false) String stream, @RequestParam(required = false) String mediaServerId, @RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String callId, @RequestParam(required = false) Long recordId) {
        log.info("[云端录像] 添加收藏，app={}，stream={},mediaServerId={},startTime={},endTime={},callId={},recordId={}", app, stream, mediaServerId, startTime, endTime, callId, recordId);
        int size;
        if (recordId != null) {
            size = cloudRecordService.changeCollectById(recordId, true);
        } else {
            size = cloudRecordService.changeCollect(true, app, stream, mediaServerId, startTime, endTime, callId);
        }
        return R.ok(size);
    }


    @GetMapping("/collect/delete")
    @Operation(summary = "移除收藏")
    @Parameter(name = "app", description = "应用名", required = false)
    @Parameter(name = "stream", description = "流ID", required = false)
    @Parameter(name = "mediaServerId", description = "流媒体ID", required = false)
    @Parameter(name = "startTime", description = "鉴权ID", required = false)
    @Parameter(name = "endTime", description = "鉴权ID", required = false)
    @Parameter(name = "callId", description = "鉴权ID", required = false)
    @Parameter(name = "recordId", description = "录像记录的ID，用于精准精准移除一个视频文件的收藏", required = false)
    public R<Integer> deleteCollect(@RequestParam(required = false) String app, @RequestParam(required = false) String stream, @RequestParam(required = false) String mediaServerId, @RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String callId, @RequestParam(required = false) Long recordId) {
        log.info("[云端录像] 移除收藏，app={}，stream={},mediaServerId={},startTime={},endTime={},callId={},recordId={}", app, stream, mediaServerId, startTime, endTime, callId, recordId);
        int size;
        if (recordId != null) {
            size = cloudRecordService.changeCollectById(recordId, false);
        } else {
            size = cloudRecordService.changeCollect(false, app, stream, mediaServerId, startTime, endTime, callId);
        }
        return R.ok(size);
    }


    @GetMapping("/play/path")
    @Operation(summary = "获取播放地址")
    @Parameter(name = "recordId", description = "录像记录的ID", required = true)
    public R<DownloadFileInfo> getPlayUrlPath(@RequestParam(required = true) Long recordId) {
        return R.ok(cloudRecordService.getPlayUrlPath(recordId));
    }

    /************************* 以下这些接口只适合wvp和zlm部署在同一台服务器的情况，且wvp只有一个zlm节点的情况 ***************************************/

    /**
     * 下载指定录像文件的压缩包
     */
    @GetMapping("/zip")
    public R<Void> downloadZipFile(CloudRecordItemBo bo) {
        List<MediaServer> mediaServers;
        if (!ObjectUtils.isEmpty(bo.getMediaServerId())) {
            mediaServers = new ArrayList<>();
            MediaServer mediaServer = mediaServerService.getOne(bo.getMediaServerId());
            AssertUtils.isFalse(mediaServer == null, "未找到流媒体: " + bo.getMediaServerId());
            mediaServers.add(mediaServer);
        } else {
            mediaServers = mediaServerService.getAll();
        }
        AssertUtils.isFalse(mediaServers.isEmpty(), "当前无流媒体");
        List<String> list = mediaServers.stream().map(MediaServer::getId).distinct().toList();
        bo.setMediaServerIds(list);

        if (bo.getStream() != null && bo.getCallId() != null) {
            response.addHeader("Content-Disposition", "attachment;filename=" + bo.getStream() + "_" + bo.getCallId() + ".zip");
        }
        List<CloudRecord> cloudRecordItemList = cloudRecordService.getAllList(bo);
        if (ObjectUtils.isEmpty(cloudRecordItemList)) {
            return R.ok();
        }
        try {
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            for (CloudRecord cloudRecordItem : cloudRecordItemList) {
                zos.putNextEntry(new ZipEntry(DateUtil.formatDateTime(cloudRecordItem.getStartTime()) + ".mp4"));
                File file = new File(cloudRecordItem.getFilePath());
                if (!file.exists() || file.isDirectory()) {
                    continue;
                }
                FileInputStream fis = new FileInputStream(cloudRecordItem.getFilePath());
                byte[] buf = new byte[2 * 1024];
                int len;
                while ((len = fis.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
        } catch (IOException e) {
            log.error("[下载指定录像文件的压缩包] 失败： {}]", e);
        }
        return R.ok();
    }


    @GetMapping("/list-url")
    @Operation(summary = "分页查询云端录像")
    public TableDataInfo<CloudRecordUrlVo> getListWithUrl(CloudRecordItemBo bo, PageQuery pageQuery) {

        List<MediaServer> mediaServers;
        if (!ObjectUtils.isEmpty(bo.getMediaServerId())) {
            mediaServers = new ArrayList<>();
            MediaServer mediaServer = mediaServerService.getOne(bo.getMediaServerId());
            if (mediaServer == null) {
                throw new ServiceException("未找到流媒体: " + bo.getMediaServerId());
            }
            mediaServers.add(mediaServer);
        } else {
            mediaServers = mediaServerService.getAll();
        }
        if (mediaServers.isEmpty()) {
            throw new ServiceException("当前无流媒体");
        } else {
            List<String> list = mediaServers.stream().map(MediaServer::getId).distinct().toList();
            bo.setMediaServerIds(list);
        }
        MediaServer mediaServer = mediaServerService.getDefaultMediaServer();
        if (mediaServer == null) {
            throw new ServiceException("未找到流媒体节点");
        }
        String remoteHost = bo.getRemoteHost();
        if (remoteHost == null) {
            remoteHost = request.getScheme() + "://" + request.getLocalAddr() + ":" + ("https".equals(request.getScheme()) ? mediaServer.getHttpSslPort() : mediaServer.getHttpPort());
        }
        TableDataInfo<CloudRecord> cloudRecordItemPageInfo = cloudRecordService.getList(bo, pageQuery);
        String finalRemoteHost = remoteHost;
        return cloudRecordItemPageInfo.map(e -> {
            CloudRecordUrlVo cloudRecordUrlVo = new CloudRecordUrlVo();
            cloudRecordUrlVo.setId(e.getId());
            cloudRecordUrlVo.setDownloadUrl(finalRemoteHost + "/index/api/downloadFile?file_path=" + e.getFilePath() + "&save_name=" + e.getStream() + "_" + e.getCallId() + "_" + DateUtil.formatDateTime(e.getStartTime()));
            cloudRecordUrlVo.setPlayUrl(finalRemoteHost + "/index/api/downloadFile?file_path=" + e.getFilePath());
            return cloudRecordUrlVo;

        });
    }
}
