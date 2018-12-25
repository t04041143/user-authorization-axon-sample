package org.multilinguals.enterprise.cmrs.infrastructure.websocket;

import org.apache.commons.lang3.StringUtils;
import org.axonframework.common.IdentifierFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.security.Principal;

public class ConnectionInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        assert accessor != null;

        // 如果是连接命令，那么要初始化连接中的用户信息
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            // 判断是否已经有授权的
            String token = (String) message.getHeaders().get("X-TOKEN");
            String userId = null;
            if (StringUtils.isEmpty(token)) {
                // 如果连接请求中没有用户令牌，那么，可以先生成一个匿名用户
                userId = IdentifierFactory.getInstance().generateIdentifier();
            } else {
                // TODO 如果token存在，那么需要验证Token有效性，并获取其中的用户ID
            }

            final String finalUserId = userId;
            // 设置访问器用户
            accessor.setUser(new Principal() {

                @Override
                public String getName() {
                    return finalUserId;
                }
            });
        }

        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        // StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
    }
}
