package com.cdzeroly.wvp.gb28181.transmit.bean;


import com.cdzeroly.wvp.gb28181.domian.DeviceChannel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author MGARY
 */
@Setter
@Getter
public class NotifyCatalogChannel {

    private Type type;

    private DeviceChannel channel;


    public enum Type {
        ADD, DELETE, UPDATE, STATUS_CHANGED
    }


    public static NotifyCatalogChannel getInstance(Type type, DeviceChannel channel) {
        NotifyCatalogChannel notifyCatalogChannel = new NotifyCatalogChannel();
        notifyCatalogChannel.setType(type);
        notifyCatalogChannel.setChannel(channel);
        return notifyCatalogChannel;
    }

}
