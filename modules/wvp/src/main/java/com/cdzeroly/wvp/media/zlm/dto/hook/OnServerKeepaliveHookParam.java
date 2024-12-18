package com.cdzeroly.wvp.media.zlm.dto.hook;

import com.cdzeroly.wvp.media.zlm.dto.ServerKeepaliveData;
import lombok.Getter;
import lombok.Setter;

/**
 * zlm hook事件中的on_play事件的参数
 * @author lin
 */
@Setter
@Getter
public class OnServerKeepaliveHookParam extends HookParam{

    private ServerKeepaliveData data;

    @Override
    public String toString() {
        return "OnServerKeepaliveHookParam{" +
                "data=" + data +
                '}';
    }
}
