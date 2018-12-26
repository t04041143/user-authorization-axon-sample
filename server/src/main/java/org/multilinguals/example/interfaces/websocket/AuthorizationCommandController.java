package org.multilinguals.example.interfaces.websocket;

import org.multilinguals.example.application.AuthorizationService;
import org.multilinguals.example.domain.aggregations.authorization.command.SignInWithPasswordCommand;
import org.multilinguals.example.domain.aggregations.authorization.command.SignUpWithPasswordCommand;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@MessageMapping("/user-authorization")
public class AuthorizationCommandController {
    @Resource
    private AuthorizationService authorizationService;

    /**
     * 使用密码注册
     *
     * @param command 密码注册命令
     */
    @MessageMapping("/sign-up-with-password")
    public void signUpWithPassword(SignUpWithPasswordCommand command) {
        authorizationService.userSignUp(command);
    }

    /**
     * 使用密码登录
     *
     * @param command 密码登录命令
     */
    @MessageMapping("/sign-in-with-password")
    public void signIn(SignInWithPasswordCommand command) {
        authorizationService.userSignIn(command);
    }
}
