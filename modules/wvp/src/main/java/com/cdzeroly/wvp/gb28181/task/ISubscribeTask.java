package com.cdzeroly.wvp.gb28181.task;

import com.cdzeroly.wvp.common.CommonCallback;

/**
 * @author lin
 */
public interface ISubscribeTask extends Runnable{
    void stop(CommonCallback<Boolean> callback);
}
