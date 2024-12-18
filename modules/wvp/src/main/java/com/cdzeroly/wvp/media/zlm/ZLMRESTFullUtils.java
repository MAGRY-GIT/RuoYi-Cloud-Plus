package com.cdzeroly.wvp.media.zlm;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.cdzeroly.wvp.media.domian.MediaServer;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.cdzeroly.wvp.media.zlm.ZMLConstant.*;

/**
 *  ZLM 服务请求
 * @author MAGRY
 */
@Slf4j
@Component
public class ZLMRESTFullUtils {

    private OkHttpClient client;

    public interface RequestCallback{
        void run(JSONObject response);
    }

    private OkHttpClient getClient(){
        return getClient(null);
    }

    private OkHttpClient getClient(Integer readTimeOut){
        if (client == null) {
            if (readTimeOut == null) {
                readTimeOut = 10;
            }
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            //todo 暂时写死超时时间 均为5s
            // 设置连接超时时间
            httpClientBuilder.connectTimeout(8,TimeUnit.SECONDS);
            // 设置读取超时时间
            httpClientBuilder.readTimeout(readTimeOut,TimeUnit.SECONDS);
            // 设置连接池
            httpClientBuilder.connectionPool(new ConnectionPool(16, 5, TimeUnit.MINUTES));
            if (log.isDebugEnabled()) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> {
                    log.debug("http请求参数：{}", message);
                });
                logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
                // OkHttp進行添加攔截器loggingInterceptor
                httpClientBuilder.addInterceptor(logging);
            }
            client = httpClientBuilder.build();
        }
        return client;

    }

    public JSONObject sendPost(MediaServer mediaServer, String api, Map<String, Object> param, RequestCallback callback) {
        return sendPost(mediaServer, api, param, callback, null);
    }


    public JSONObject sendPost(MediaServer mediaServer, String api, Map<String, Object> param, RequestCallback callback, Integer readTimeOut) {
        OkHttpClient client = getClient(readTimeOut);

        if (mediaServer == null) {
            return null;
        }
        String url = String.format("http://%s:%s/index/api/%s",  mediaServer.getIp(), mediaServer.getHttpPort(), api);
        JSONObject jsonObject = new JSONObject();
        //-2自定义流媒体 调用错误码
        jsonObject.put("code",-2);
        jsonObject.put("msg","流媒体调用失败");

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("secret",mediaServer.getSecret());
        if (param != null && !param.isEmpty()) {
            for (String key : param.keySet()){
                if (param.get(key) != null) {
                    builder.add(key, param.get(key).toString());
                }
            }
        }

        FormBody body = builder.build();

        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
            if (callback == null) {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        ResponseBody responseBody = response.body();
                        if (responseBody != null) {
                            String responseStr = responseBody.string();
                            jsonObject = JSON.parseObject(responseStr);
                        }
                    }else {
                        response.close();
                        Objects.requireNonNull(response.body()).close();
                    }
                }catch (IOException e) {
                    log.error(String.format("[ %s ]请求失败: %s", url, e.getMessage()));

                    if(e instanceof SocketTimeoutException){
                        //读取超时超时异常
                        log.error(String.format("读取ZLM数据超时失败: %s, %s", url, e.getMessage()));
                    }
                    if(e instanceof ConnectException){
                        //判断连接异常，我这里是报Failed to connect to 10.7.5.144
                        log.error(String.format("连接ZLM连接失败: %s, %s", url, e.getMessage()));
                    }

                }catch (Exception e){
                    log.error(String.format("访问ZLM失败: %s, %s", url, e.getMessage()));
                }
            }else {
                client.newCall(request).enqueue(new Callback(){

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response){
                        if (response.isSuccessful()) {
                            try {
                                String responseStr = Objects.requireNonNull(response.body()).string();
                                callback.run(JSON.parseObject(responseStr));
                            } catch (IOException e) {
                                log.error(String.format("[ %s ]请求失败: %s", url, e.getMessage()));
                            }

                        }else {
                            response.close();
                            Objects.requireNonNull(response.body()).close();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        log.error(String.format("连接ZLM失败: %s, %s", call.request().toString(), e.getMessage()));

                        if(e instanceof SocketTimeoutException){
                            //读取超时超时异常
                            log.error(String.format("读取ZLM数据失败: %s, %s", call.request().toString(), e.getMessage()));
                        }
                        if(e instanceof ConnectException){
                            //判断连接异常，我这里是报Failed to connect to 10.7.5.144
                            log.error(String.format("连接ZLM失败: %s, %s", call.request().toString(), e.getMessage()));
                        }
                    }
                });
            }



        return jsonObject;
    }

    public void sendGetForImg(MediaServer mediaServer, String api, Map<String, Object> params, String targetPath, String fileName) {
        String url = String.format("http://%s:%s/index/api/%s", mediaServer.getIp(), mediaServer.getHttpPort(), api);
        HttpUrl parseUrl = HttpUrl.parse(url);
        if (parseUrl == null) {
            return;
        }
        HttpUrl.Builder httpBuilder = parseUrl.newBuilder();

        httpBuilder.addQueryParameter("secret", mediaServer.getSecret());
        if (params != null) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(), param.getValue().toString());
            }
        }

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .build();
        if (log.isDebugEnabled()){
            log.debug(request.toString());
        }
        try {
            OkHttpClient client = getClient();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                if (targetPath != null) {
                    File snapFolder = new File(targetPath);
                    if (!snapFolder.exists()) {
                        if (!snapFolder.mkdirs()) {
                            log.warn("{}路径创建失败", snapFolder.getAbsolutePath());
                        }

                    }
                    File snapFile = new File(targetPath + File.separator + fileName);
                    FileOutputStream outStream = new FileOutputStream(snapFile);

                    outStream.write(Objects.requireNonNull(response.body()).bytes());
                    outStream.flush();
                    outStream.close();
                } else {
                    log.error("[ {} ]请求失败: {} {}", url, response.code(), response.message());
                }
            } else {
                log.error("[ {} ]请求失败: {} {}", url, response.code(), response.message());
            }
            Objects.requireNonNull(response.body()).close();
        } catch (ConnectException e) {
            log.error("连接ZLM失败: {}, {}", e.getCause().getMessage(), e.getMessage());
            log.info("请检查media配置并确认ZLM已启动...");
        } catch (IOException e) {
            log.error("[ {} ]请求失败: {}", url, e.getMessage());
        }
    }

    public JSONObject isMediaOnline(MediaServer mediaServer, String app, String stream, String schema){
        Map<String, Object> param = getParam(app, stream, schema);
        return sendPost(mediaServer, IS_MEDIA_ONLINE, param, null);
    }

    public JSONObject getMediaList(MediaServer mediaServer, String app, String stream, String schema, RequestCallback callback){
        Map<String, Object> param = getParam(app, stream, schema);
        return sendPost(mediaServer, ZMLConstant.GET_MEDIA_LIST,param, callback);
    }

    /**
     * 组装请求参数
     * @param app  应用名称
     * @param stream  流ID
     * @param schema  图式
     * @return  请求参数
     */
    private Map<String, Object> getParam(String app, String stream, String schema) {
        Map<String, Object> param = new HashMap<>();
        if (app != null) {
            param.put("app",app);
        }
        if (stream != null) {
            param.put("stream",stream);
        }
        if (schema != null) {
            param.put("schema",schema);
        }
        param.put("vhost","__defaultVhost__");
        return param;
    }

    public JSONObject getMediaList(MediaServer mediaServer, String app, String stream){
        return getMediaList(mediaServer, app, stream,null,  null);
    }

    public JSONObject getMediaList(MediaServer mediaServer, RequestCallback callback){
        return sendPost(mediaServer, ZMLConstant.GET_MEDIA_LIST,null, callback);
    }

    public JSONObject getMediaInfo(MediaServer mediaServer, String app, String schema, String stream){
        Map<String, Object> param = new HashMap<>();
        param.put("app",app);
        param.put("schema",schema);
        param.put("stream",stream);
        param.put("vhost","__defaultVhost__");
        return sendPost(mediaServer, ZMLConstant.GET_MEDIA_INFO,param, null);
    }

    public JSONObject getRtpInfo(MediaServer mediaServer, String streamId){
        Map<String, Object> param = new HashMap<>();
        param.put("stream_id",streamId);
        return sendPost(mediaServer, GET_RTP_INFO,param, null);
    }

    public JSONObject addFFmpegSource(MediaServer mediaServer, String srcUrl, String dstUrl, Integer timeoutSec,
                                      boolean enableAudio, boolean enableMp4, String ffmpegCmdKey){
        log.info(srcUrl);
        log.info(dstUrl);
        Map<String, Object> param = new HashMap<>();
        param.put("src_url", srcUrl);
        param.put("dst_url", dstUrl);
        param.put("timeout_ms", timeoutSec*1000);
        param.put("enable_mp4", enableMp4);
        param.put("ffmpeg_cmd_key", ffmpegCmdKey);
        return sendPost(mediaServer, ADD_FFMPEG_SOURCE,param, null);
    }

    public JSONObject delFFmpegSource(MediaServer mediaServer, String key){
        Map<String, Object> param = new HashMap<>();
        param.put("key", key);
        return sendPost(mediaServer, DEL_FFMPEG_SOURCE,param, null);
    }

    public JSONObject delStreamProxy(MediaServer mediaServer, String key){
        Map<String, Object> param = new HashMap<>();
        param.put("key", key);
        return sendPost(mediaServer, DEL_STREAM_PROXY,param, null);
    }

    public JSONObject getMediaServerConfig(MediaServer mediaServer){
        return sendPost(mediaServer, GET_SERVER_CONFIG,null, null);
    }

    public JSONObject setServerConfig(MediaServer mediaServer, Map<String, Object> param){
        return sendPost(mediaServer,SET_SERVER_CONFIG,param, null);
    }

    public JSONObject openRtpServer(MediaServer mediaServer, Map<String, Object> param){
        return sendPost(mediaServer, OPEN_RTP_SERVER,param, null);
    }

    public JSONObject closeRtpServer(MediaServer mediaServer, Map<String, Object> param) {
        return sendPost(mediaServer, CLOSE_RTP_SERVER,param, null);
    }

    public void closeRtpServer(MediaServer mediaServer, Map<String, Object> param, RequestCallback callback) {
        sendPost(mediaServer, CLOSE_RTP_SERVER,param, callback);
    }

    public JSONObject listRtpServer(MediaServer mediaServer) {
        return sendPost(mediaServer, LIST_RTP_SERVER,null, null);
    }

    public JSONObject startSendRtp(MediaServer mediaServer, Map<String, Object> param) {
        return sendPost(mediaServer, START_SEND_RTP,param, null);
    }

    public JSONObject startSendRtpPassive(MediaServer mediaServer, Map<String, Object> param) {
        return sendPost(mediaServer, START_SEND_RTP_PASSIVE,param, null);
    }

    public JSONObject startSendRtpPassive(MediaServer mediaServer, Map<String, Object> param, RequestCallback callback) {
        return sendPost(mediaServer, START_SEND_RTP_PASSIVE,param, callback);
    }

    public JSONObject stopSendRtp(MediaServer mediaServer, Map<String, Object> param) {
        return sendPost(mediaServer, STOP_SEND_RTP,param, null);
    }

    public JSONObject restartServer(MediaServer mediaServer) {
        return sendPost(mediaServer, RESTART_SERVER,null, null);
    }

    public JSONObject addStreamProxy(MediaServer mediaServer, String app, String stream, String url, boolean enable_audio, boolean enable_mp4, String rtp_type, Integer timeOut) {
        Map<String, Object> param = new HashMap<>();
        param.put("vhost", "__defaultVhost__");
        param.put("app", app);
        param.put("stream", stream);
        param.put("url", url);
        param.put("enable_mp4", enable_mp4?1:0);
        param.put("enable_audio", enable_audio?1:0);
        param.put("rtp_type", rtp_type);
        param.put("timeout_sec", timeOut);
        // 拉流重试次数,默认为3
        param.put("retry_count", 3);
        return sendPost(mediaServer, ADD_STREAM_PROXY,param, null, 20);
    }

    public JSONObject closeStreams(MediaServer mediaServer, String app, String stream) {
        Map<String, Object> param = new HashMap<>();
        param.put("vhost", "__defaultVhost__");
        param.put("app", app);
        param.put("stream", stream);
        param.put("force", 1);
        return sendPost(mediaServer, CLOSE_STREAMS,param, null);
    }

    public JSONObject getAllSession(MediaServer mediaServer) {
        return sendPost(mediaServer, GET_ALL_SESSION,null, null);
    }

    public void kickSessions(MediaServer mediaServer, String localPortSStr) {
        Map<String, Object> param = new HashMap<>();
        param.put("local_port", localPortSStr);
        sendPost(mediaServer, KICK_SESSIONS,param, null);
    }

    public void getSnap(MediaServer mediaServer, String streamUrl, int timeout_sec, int expire_sec, String targetPath, String fileName) {
        Map<String, Object> param = new HashMap<>(3);
        param.put("url", streamUrl);
        param.put("timeout_sec", timeout_sec);
        param.put("expire_sec", expire_sec);
        sendGetForImg(mediaServer, GET_SNAP, param, targetPath, fileName);
    }

    public JSONObject pauseRtpCheck(MediaServer mediaServer, String streamId) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("stream_id", streamId);
        return sendPost(mediaServer, PAUSE_RTP_CHECK,param, null);
    }

    public JSONObject resumeRtpCheck(MediaServer mediaServer, String streamId) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("stream_id", streamId);
        return sendPost(mediaServer, RESUME_RTP_CHECK,param, null);
    }

    public JSONObject connectRtpServer(MediaServer mediaServer, String dst_url, int dst_port, String stream_id) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("dst_url", dst_url);
        param.put("dst_port", dst_port);
        param.put("stream_id", stream_id);
        return sendPost(mediaServer, CONNECT_RTP_SERVER,param, null);
    }

    public JSONObject updateRtpServerSSRC(MediaServer mediaServer, String streamId, String ssrc) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("ssrc", ssrc);
        param.put("stream_id", streamId);
        return sendPost(mediaServer, UPDATE_RTP_SERVER_SSRC,param, null);
    }

    public JSONObject deleteRecordDirectory(MediaServer mediaServer, String app, String stream, String date, String fileName) {
        Map<String, Object> param = new HashMap<>(1);
        param.put("vhost", "__defaultVhost__");
        param.put("app", app);
        param.put("stream", stream);
        param.put("period", date);
        param.put("name", fileName);
        return sendPost(mediaServer, DELETE_RECORD_DIRECTORY,param, null);
    }
}
