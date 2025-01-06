package com.cdzeroly.wvp.domain.bo;

import com.cdzeroly.wvp.common.StreamInfo;
import com.cdzeroly.wvp.domain.Device;
import com.cdzeroly.wvp.domain.WVPResult;
import org.springframework.web.context.request.async.DeferredResult;

public class PlayResult {

    private DeferredResult<WVPResult<StreamInfo>> result;
    private String uuid;

    private Device device;

    public DeferredResult<WVPResult<StreamInfo>> getResult() {
        return result;
    }

    public void setResult(DeferredResult<WVPResult<StreamInfo>> result) {
        this.result = result;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
