package com.cdzeroly.wvp.gb28181.domian.bean;


import lombok.Getter;
import lombok.Setter;

/**
 * 通过redis分发报警消息
 * @author Administrator
 */
@Setter
@Getter
public class AlarmChannelMessage {
    /**
     * 国标编号
     */
    private String gbId;
    /**
     * 报警编号
     */
    private int alarmSn;
    /**
     * 告警类型
     */
    private int alarmType;

    /**
     * 报警描述
     */
    private String alarmDescription;

}
