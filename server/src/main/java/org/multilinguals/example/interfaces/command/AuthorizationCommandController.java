package org.multilinguals.example.interfaces.command;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.multilinguals.example.command.aggregate.user.UserId;
import org.multilinguals.example.command.aggregate.usersession.UserSessionId;
import org.multilinguals.example.command.handler.signin.SignInWithPasswordCommand;
import org.multilinguals.example.command.handler.signup.SignUpUsernameAccountCommand;
import org.multilinguals.example.constant.result.code.AuthResultCode;
import org.multilinguals.example.infrastructure.data.Tuple2;
import org.multilinguals.example.infrastructure.dto.CommandResponse;
import org.multilinguals.example.infrastructure.dto.user.UserSignInDTO;
import org.multilinguals.example.infrastructure.exception.aggregate.AccountSignedUpException;
import org.multilinguals.example.infrastructure.exception.aggregate.UserPasswordInvalidException;
import org.multilinguals.example.infrastructure.exception.http.CMRSHTTPException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class AuthorizationCommandController {
    @Resource
    private CommandGateway commandGateway;

    @PostMapping("/sign-up-username")
    public void signUpUserName(@RequestBody SignUpUsernameAccountCommand command, HttpServletResponse response) {
        try {
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof AccountSignedUpException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_CONFLICT, AuthResultCode.SIGNED_UP_ACCOUNT);
            } else {
                throw ex;
            }
        }
    }

    /**
     * 使用密码登录
     *
     * @param command 密码登录命令
     */
    @PostMapping("/sign-in-with-password")
    public CommandResponse<UserSignInDTO> signIn(@RequestBody SignInWithPasswordCommand command) {
        try {
            Tuple2<UserSessionId, UserId> result = commandGateway.sendAndWait(command);
            return new CommandResponse<>(new UserSignInDTO(result.getT1().getIdentifier(), result.getT2().getIdentifier()));
        } catch (AggregateNotFoundException ex) {
            throw new CMRSHTTPException(HttpServletResponse.SC_UNAUTHORIZED, AuthResultCode.AUTHORIZE_FAILED);
        } catch (CommandExecutionException ex) {
            if (ex.getCause() instanceof UserPasswordInvalidException) {
                throw new CMRSHTTPException(HttpServletResponse.SC_UNAUTHORIZED, AuthResultCode.AUTHORIZE_FAILED);
            } else {
                throw ex;
            }
        }
    }
}
