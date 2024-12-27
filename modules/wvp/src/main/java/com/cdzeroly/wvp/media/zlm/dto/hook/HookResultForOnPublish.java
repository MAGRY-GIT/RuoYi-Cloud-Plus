package com.cdzeroly.wvp.media.zlm.dto.hook;

import com.cdzeroly.wvp.media.zlm.dto.ResultForOnPublish;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author mgary
 */
@Setter
@Getter
public class HookResultForOnPublish extends HookResult{

    @JsonProperty("enable_audio")
    private boolean enableAudio;
     @JsonProperty("enable_mp4")
    private boolean enableMp4;
     @JsonProperty("mp4_max_second")
    private Integer mp4MaxSecond;
     @JsonProperty("mp4_save_path")
    private String mp4SavePath;
     @JsonProperty("stream_replace")
    private String streamReplace;
     @JsonProperty("modify_stamp")
    private Integer modifyStamp;


    public HookResultForOnPublish() {
    }

    public static HookResultForOnPublish SUCCESS(){
        return new HookResultForOnPublish(0, "success");
    }

    public static HookResultForOnPublish getInstance(ResultForOnPublish resultForOnPublish){
        HookResultForOnPublish successResult = new HookResultForOnPublish(0, "success");
        successResult.setEnableAudio(resultForOnPublish.isEnable_audio());
        successResult.setEnableMp4(resultForOnPublish.isEnable_mp4());
        successResult.setModifyStamp(resultForOnPublish.getModify_stamp());
        successResult.setStreamReplace(resultForOnPublish.getStream_replace());
        successResult.setMp4MaxSecond(resultForOnPublish.getMp4_max_second());
        successResult.setMp4SavePath(resultForOnPublish.getMp4_save_path());
        return successResult;
    }

    public HookResultForOnPublish(int code, String msg) {
        setCode(code);
        setMsg(msg);
    }

    @Override
    public String toString() {
        return "HookResultForOnPublish{" +
                "enable_audio=" + enableAudio +
                ", enable_mp4=" + enableMp4 +
                ", mp4_max_second=" + mp4MaxSecond +
                ", mp4_save_path='" + mp4SavePath + '\'' +
                ", stream_replace='" + streamReplace + '\'' +
                ", modify_stamp='" + modifyStamp + '\'' +
                '}';
    }
}
