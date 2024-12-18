package com.cdzeroly.wvp.media.zlm.dto.hook;

import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class HookResult {

    private int code;
    private String msg;


    public HookResult() {
    }

    public HookResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static HookResult success(){
        return new HookResult(0, "success");
    }

    public static HookResultForOnPublish fail(){
        return new HookResultForOnPublish(-1, "fail");
    }

}
