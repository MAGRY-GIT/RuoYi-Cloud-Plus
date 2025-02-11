package com.cdzeroly.assist.utils;

import com.cdzeroly.assist.conf.UserSetting;
import com.cdzeroly.assist.domain.bean.VideoFile;
import com.cdzeroly.assist.video.VideoFileFactory;
import lombok.Getter;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.progress.Progress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author MAGRY
 */
@Component
public class FFmpegExecUtils implements InitializingBean{

    private final static Logger logger = LoggerFactory.getLogger(FFmpegExecUtils.class);

    @Autowired
    private UserSetting userSetting;

    @Getter
    private FFprobe ffprobe;
    @Getter
    private FFmpeg ffmpeg;

    @Override
    public void afterPropertiesSet() throws Exception {
        String ffmpegPath = userSetting.getFfmpeg();
        String ffprobePath = userSetting.getFfprobe();

        this.ffmpeg = new FFmpeg(ffmpegPath);
        this.ffprobe = new FFprobe(ffprobePath);
        logger.info("wvp-pro辅助程序启动成功。 \n{}\n{} ", this.ffmpeg.version(), this.ffprobe.version());
    }



    public interface VideoHandEndCallBack {
        void run(String status, double percentage, String result);
    }

    /**
     * 剪辑文件
     * @param filePath 文件地址
     * @param start  开始时间 e.g "00:00:00"
     * @param duration  结束时间  e.g "00:1:00"
     * @param dest 临时文件位置
     * @param destFileName  临时文件名称
     * @param callBack 回调
     */
    @Async
    public void cutFile(String filePath, String start, String duration,File dest, String destFileName,VideoHandEndCallBack callBack){
        //判断是否为空
        if (filePath == null || ffmpeg == null || ffprobe == null){
            callBack.run("error", 0.0, null);
            return ;
        }

        File tempFile = new File(dest.getAbsolutePath());
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        double durationAll = 0.0;
        //存放地址
        String recordFileResultPath = dest.getAbsolutePath() + File.separator + destFileName + ".mp4";

        //创建合并请求
        FFmpegBuilder builder = new FFmpegBuilder()
            .setInput(filePath)
            // Or filename
            .addExtraArgs("-ss", start)
            .addExtraArgs("-t", duration)
            .addExtraArgs("-threads", userSetting.getThreads() + "")
            .addOutput(recordFileResultPath)
            .setVideoCodec("copy")
            .setAudioCodec("aac")
            .setFormat("mp4")
            .done();

        double finalDurationAll = durationAll;
        FFmpegJob job = executor.createJob(builder, (Progress progress) -> {
            final double durationNs = finalDurationAll * TimeUnit.SECONDS.toNanos(1);
            double percentage = progress.out_time_ns / durationNs;

            if (progress.status.equals(Progress.Status.END)){
                callBack.run(progress.status.name(), percentage, recordFileResultPath);
            }else {
                callBack.run(progress.status.name(), percentage, null);
            }

        });
        job.run();
    }







    /**
     * 合并文件
     * @param files 文件集合
     * @param dest  目标路径
     * @param destFileName  目标文件名称
     * @param callBack 回调
     */
    @Async
    public void mergeOrCutFile(List<File> files, File dest, String destFileName, VideoHandEndCallBack callBack){
        //判断是否为空
        if (files == null || files.isEmpty() || ffmpeg == null || ffprobe == null || dest== null || !dest.exists()){
            callBack.run("error", 0.0, null);
            return;
        }

        File tempFile = new File(dest.getAbsolutePath());
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);


        String fileListName = tempFile.getAbsolutePath() + File.separator + "fileList";
        double durationAll = 0.0;
        //定义合并文件内容
        writeTempFileList(durationAll,fileListName,files,callBack);
        String recordFileResultPath = dest.getAbsolutePath() + File.separator + destFileName + ".mp4";
        long startTime = System.currentTimeMillis();
        //创建合并请求
        FFmpegBuilder builder = new FFmpegBuilder()
                .setFormat("concat")
                .overrideOutputFiles(true)
                // Or filename
                .setInput(fileListName)
                .addExtraArgs("-safe", "0")
                .addExtraArgs("-threads", userSetting.getThreads() + "")
                .addOutput(recordFileResultPath)
                .setVideoCodec("copy")
                .setAudioCodec("aac")
                .setFormat("mp4")
                .done();

        double finalDurationAll = durationAll;
        FFmpegJob job = executor.createJob(builder, (Progress progress) -> {
            final double durationNs = finalDurationAll * TimeUnit.SECONDS.toNanos(1);
            double percentage = progress.out_time_ns / durationNs;

            if (progress.status.equals(Progress.Status.END)){
                callBack.run(progress.status.name(), percentage, recordFileResultPath);
            }else {
                callBack.run(progress.status.name(), percentage, null);
            }

        });
        job.run();
    }

    public long duration(File file) throws IOException {
        FFmpegProbeResult in = ffprobe.probe(file.getAbsolutePath());
        double duration = in.getFormat().duration * 1000;
        return (long) duration;
    }

    private void writeTempFileList(  double durationAll, String fileListName,List<File> files,VideoHandEndCallBack callBack){

        try {
            BufferedWriter bw =new BufferedWriter(new FileWriter(fileListName));
            for (File file : files) {
                VideoFile videoFile = VideoFileFactory.createFile(this, file);
                if (videoFile == null) {
                    return;
                }
                bw.write("file " + file.getAbsolutePath());
                bw.newLine();
                durationAll += videoFile.getDuration();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            logger.error("文件合并失败",e);
            callBack.run("error", 0.0, null);
        }
    }

}
