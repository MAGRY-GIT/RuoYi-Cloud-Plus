package com.cdzeroly.wvp.gb28181.domian.bean;

import com.cdzeroly.wvp.media.event.hook.HookData;
import com.cdzeroly.wvp.service.bean.SSRCInfo;
import lombok.Data;

/**
 * @author MGARY
 */
@Data
public class OpenRTPServerResult {

    private SSRCInfo ssrcInfo;
    private HookData hookData;
}
