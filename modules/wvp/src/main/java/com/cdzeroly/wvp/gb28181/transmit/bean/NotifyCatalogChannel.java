package com.cdzeroly.wvp.gb28181.transmit.bean;


import com.cdzeroly.wvp.domain.DeviceChannel;
import lombok.Getter;
import lombok.Setter;

/**
 * 通知目录频道
 *
 * @author MGARY
 */
@Setter
@Getter
public class NotifyCatalogChannel {

    private Type type;

    private DeviceChannel channel;


    public enum Type {
        /**
         * 添加
         */
        ADD,
        /**
         * 删除
         */
        DELETE,
        /**
         * 更新
         */
        UPDATE,
        /**
         * 状态改变
         */
        STATUS_CHANGED
    }


    public static NotifyCatalogChannel getInstance(Type type, DeviceChannel channel) {
        NotifyCatalogChannel notifyCatalogChannel = new NotifyCatalogChannel();
        notifyCatalogChannel.setType(type);
        notifyCatalogChannel.setChannel(channel);
        return notifyCatalogChannel;
    }

}
