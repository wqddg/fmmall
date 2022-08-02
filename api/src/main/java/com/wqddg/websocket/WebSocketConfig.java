package com.wqddg.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: wqddg
 * @ClassName WebSocketConfig
 * @DateTime: 2021/11/23 23:12
 * @remarks : #
 */
@Configuration
public class WebSocketConfig {
    @Bean//配置到spring容器中
    public ServerEndpointExporter getServerEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
