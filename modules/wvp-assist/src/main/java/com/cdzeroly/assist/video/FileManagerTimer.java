package com.cdzeroly.assist.video;

import com.cdzeroly.assist.conf.UserSetting;
import com.cdzeroly.assist.domain.vo.MergeOrCutTaskInfoVo;
import com.cdzeroly.assist.service.VideoFileService;
import com.cdzeroly.common.redis.utils.RedisUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author MAGRY
 */
@Component
public class FileManagerTimer {

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat simpleDateFormatForTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final static Logger logger = LoggerFactory.getLogger(FileManagerTimer.class);

    @Autowired
    private UserSetting userSettings;

    @Autowired
    private VideoFileService videoFileService;


//    @Scheduled(fixedDelay = 2000)   //测试 20秒执行一次
    @Scheduled(cron = "0 0 0 * * ?")   //每天的0点执行
    public void execute(){
        if (userSettings.getRecordTempPath() == null) {
            return;
        }

        // 清理任务临时文件
        int recordTempDay = userSettings.getRecordTempDay();
        Date lastTempDate = new Date();
        Calendar lastTempCalendar = Calendar.getInstance();
        lastTempCalendar.setTime(lastTempDate);
        lastTempCalendar.add(Calendar.DAY_OF_MONTH, -recordTempDay);
        lastTempDate = lastTempCalendar.getTime();
        logger.info("[录像巡查]移除合并任务临时文件 {} 之前的文件", formatter.format(lastTempDate));
        File recordTempFile = new File(userSettings.getRecordTempPath());
        if (recordTempFile.exists() && recordTempFile.isDirectory() && recordTempFile.canWrite()) {
            File[] tempFiles = recordTempFile.listFiles();
            if (tempFiles != null) {
                for (File tempFile : tempFiles) {
                    if (tempFile.isFile() && tempFile.lastModified() < lastTempDate.getTime()) {
                        boolean result = FileUtils.deleteQuietly(tempFile);
                        if (result) {
                            logger.info("[录像巡查]成功移除合并任务临时文件 {} ", tempFile.getAbsolutePath());
                        }else {
                            logger.info("[录像巡查]合并任务临时文件移除失败 {} ", tempFile.getAbsolutePath());
                        }
                    }
                }
            }
        }
        // 清理redis记录
        String key = String.format("%S_%S_*", VideoFileFactory.MERGEORCUT, userSettings.getId());
        List<String> taskKeys = RedisUtils.scan(key);
        for (String taskKey : taskKeys) {
            MergeOrCutTaskInfoVo mergeOrCutTaskInfo = RedisUtils.getCacheObject(taskKey);
            try {
                if (StringUtils.hasLength(mergeOrCutTaskInfo.getCreateTime())
                        || simpleDateFormatForTime.parse(mergeOrCutTaskInfo.getCreateTime()).before(lastTempDate)) {
                    RedisUtils.deleteObject(taskKey);
                }
            } catch (ParseException e) {
                logger.error("[清理过期的redis合并任务信息] 失败", e);
            }
        }
    }
}
