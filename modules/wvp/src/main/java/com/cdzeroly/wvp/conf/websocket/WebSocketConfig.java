package com.cdzeroly.wvp.conf.websocket;

import com.cdzeroly.wvp.conf.webLog.LogChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author MGARY
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        ServerEndpointExporter endpointExporter = new ServerEndpointExporter();

        endpointExporter.setAnnotatedEndpointClasses(LogChannel.class);

        return endpointExporter;
    }
}
