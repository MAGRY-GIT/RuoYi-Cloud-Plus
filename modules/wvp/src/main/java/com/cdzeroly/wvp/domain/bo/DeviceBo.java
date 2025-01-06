package com.cdzeroly.wvp.domain.bo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.cdzeroly.common.core.validate.AddGroup;
import com.cdzeroly.common.core.validate.EditGroup;
import com.cdzeroly.common.mybatis.core.domain.BaseEntity;
import com.cdzeroly.wvp.domain.Device;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 国标设备/平台业务对象 wvp_device
 *
 * @author MGARY
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Device.class, reverseConvertGenerate = false)
public class DeviceBo extends BaseEntity {



    @Schema(description = "数据库自增ID")
    @TableId(value = "id")
    private Long id;

    /**
     * 设备编号
     */
    @NotBlank(message = "设备编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceId;

    /**
     * 设备名
     */
    @Schema(description = "名称")
    private String name;


    /**
     * 设备使用的媒体id, 默认为null
     */
    @Schema(description = "设备使用的媒体id, 默认为null")
    private String mediaServerId;

    /**
     * 字符集, 支持 UTF-8 与 GB2312
     */
    @Schema(description = "符集, 支持 UTF-8 与 GB2312")
    private String charset ;

    /**
     * 目录订阅周期，0为不订阅
     */
    @Schema(description = "目录订阅周期，o为不订阅")
    private int subscribeCycleForCatalog;

    /**
     * 移动设备位置订阅周期，0为不订阅
     */
    @Schema(description = "移动设备位置订阅周期，0为不订阅")
    private int subscribeCycleForMobilePosition;

    /**
     * 移动设备位置信息上报时间间隔,单位:秒,默认值5
     */
    @Schema(description = "移动设备位置信息上报时间间隔,单位:秒,默认值5")
    private int mobilePositionSubmissionInterval = 5;

    /**
     * 报警订阅周期，0为不订阅
     */
    @Schema(description = "报警心跳时间订阅周期，0为不订阅")
    private int subscribeCycleForAlarm;




    @Schema(description = "密码")
    private String password;

    @Schema(description = "收流IP")
    private String sdpIp;

    /**
     * 是否开启ssrc校验，默认关闭，开启可以防止串流
     */
    @Schema(description = "是否开启ssrc校验，默认关闭，开启可以防止串流")
    private boolean ssrcCheck = false;

    @Schema(description = "是否作为消息通道")
    private boolean asMessageChannel;



    @Schema(description = "控制语音对讲流程，释放收到ACK后发流")
    private boolean broadcastPushAfterAck;


}
