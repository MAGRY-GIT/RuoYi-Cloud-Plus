package com.cdzeroly.wvp.gb28181.event.alarm;

import com.cdzeroly.wvp.domain.DeviceAlarm;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

/**
 * 报警事件
 * @author lawrencehj
 * 2021-01-20
 */
public class AlarmEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = 1L;

    public AlarmEvent(Object source) {
        super(source);
    }

    private DeviceAlarm deviceAlarm;

    public DeviceAlarm getAlarmInfo() {
        return deviceAlarm;
    }

    public void setAlarmInfo(DeviceAlarm deviceAlarm) {
        this.deviceAlarm = deviceAlarm;
    }
}
