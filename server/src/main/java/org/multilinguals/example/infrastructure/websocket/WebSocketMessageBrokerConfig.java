package org.multilinguals.example.infrastructure.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // 订阅消息时的前缀
        registry.setApplicationDestinationPrefixes("/app"); // 发送消息时的前缀
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 指定当前域名下，websocket服务的路径
        registry.addEndpoint("/ws-cmrs")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ConnectionInterceptor());
    }
}