package com.cdzeroly.job.snailjob.wvp;

import com.aizuda.snailjob.client.job.core.annotation.JobExecutor;
import com.aizuda.snailjob.client.job.core.dto.JobArgs;
import com.aizuda.snailjob.client.model.ExecuteResult;
import com.aizuda.snailjob.common.core.util.JsonUtil;
import com.aizuda.snailjob.common.log.SnailJobLog;
import com.cdzeroly.wvp.api.RemoteScreenshotService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author opensnail
 * @date 2024-05-17
 */
@Component
@JobExecutor(name = "screenshotJobExecutor")
public class ScreenshotJobExecutor {

    @DubboReference
    RemoteScreenshotService remoteScreenshotService;

    public ExecuteResult jobExecute(JobArgs jobArgs) {
        Object jobParams = jobArgs.getJobParams();
        String path = null;
        String prefix = null;

        if (jobParams != null) {
            Map<String, String> json = JsonUtil.parseHashMap(jobParams.toString());
            path = json.getOrDefault("path", null);
            prefix = json.getOrDefault("prefix", null);
        }
        remoteScreenshotService.screenshotStorageProxy(path, prefix);
        return ExecuteResult.success("截图定时任务执行成功");
    }
}
