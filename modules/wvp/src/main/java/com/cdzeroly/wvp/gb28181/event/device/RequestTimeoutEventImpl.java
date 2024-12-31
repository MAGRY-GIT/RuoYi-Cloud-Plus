package com.cdzeroly.wvp.gb28181.event.device;

import com.cdzeroly.wvp.gb28181.domian.Device;
import com.cdzeroly.wvp.gb28181.service.IDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.sip.ClientTransaction;
import javax.sip.address.SipURI;
import javax.sip.message.Request;

/**
 * @author lin
 */
@Component
@RequiredArgsConstructor
public class RequestTimeoutEventImpl implements ApplicationListener<RequestTimeoutEvent> {

    private final IDeviceService deviceService;

    @Override
    public void onApplicationEvent(RequestTimeoutEvent event) {
        ClientTransaction clientTransaction = event.getTimeoutEvent().getClientTransaction();
        if (clientTransaction != null) {
            Request request = clientTransaction.getRequest();
            if (request != null) {
                String host = ((SipURI) request.getRequestURI()).getHost();
                int port = ((SipURI) request.getRequestURI()).getPort();
                Device device = deviceService.getDeviceByHostAndPort(host, port);
                if (device == null) {
                    return;
                }
                deviceService.offline(device.getDeviceId(), "等待消息超时");
            }

        }
    }
}
