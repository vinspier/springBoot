package com.example.demo.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


@Configuration
@EnableWebSocketMessageBroker //开启STOMP协议
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //广播式配置一个/topic消息代理
        registry.enableSimpleBroker("/topic");
        registry.enableSimpleBroker("/queue","/topic"); // 点对点 增加一个消息代理 /queue
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //注册一个STOMP节点 并指定URL 使用SockJS协议
        stompEndpointRegistry.addEndpoint("/endpointWebSocket").withSockJS();
        stompEndpointRegistry.addEndpoint("/endpointChat").withSockJS();
    }


}
