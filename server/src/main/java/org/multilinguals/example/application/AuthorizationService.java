package org.multilinguals.example.application;

import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.commandhandling.model.ConcurrencyException;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.multilinguals.example.domain.aggregations.authorization.command.SignInWithPasswordCommand;
import org.multilinguals.example.domain.aggregations.authorization.command.SignUpWithPasswordCommand;
import org.multilinguals.example.domain.aggregations.authorization.event.AccountSignInFailedEvent;
import org.multilinguals.example.domain.aggregations.authorization.event.AccountSignUpFailedEvent;
import org.multilinguals.example.domain.aggregations.authorization.event.AccountSignUpSucceededEvent;
import org.multilinguals.example.domain.aggregations.usersession.event.UserSessionCreatedEvent;
import org.multilinguals.example.domain.constant.CommonResultCode;
import org.multilinguals.example.domain.constant.authorization.AuthorizationResultCode;
import org.multilinguals.example.infrastructure.dto.Message;
import org.multilinguals.example.infrastructure.exception.UserPasswordInvalidException;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationService extends AbstractApplicationService {
    /**
     * 处理用户使用密码注册逻辑
     *
     * @param command 使用密码注册命令
     */
    public void userSignUp(SignUpWithPasswordCommand command) {
        try {
            // 执行创建用户身份凭证命令
            this.commandGateway.sendAndWait(command);
        } catch (ConcurrencyException ex) {
            messagingTemplate.convertAndSendToUser(command.getSenderId(),
                    "/topic/authorization-event",
                    new Message<>(new AccountSignUpFailedEvent(
                            command.getAccountId(),
                            AuthorizationResultCode.DUPLICATED_USER_IDENTITY,
                            command.getSenderId()))
            );
        } catch (IllegalArgumentException ex) {
            messagingTemplate.convertAndSendToUser(command.getSenderId(),
                    "/topic/authorization-event",
                    new Message<>(new AccountSignUpFailedEvent(
                            command.getAccountId(),
                            CommonResultCode.INVALID_PARAMS,
                            command.getSenderId()))
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            messagingTemplate.convertAndSendToUser(command.getSenderId(),
                    "/topic/authorization-event",
                    new Message<>(new AccountSignUpFailedEvent(
                            command.getAccountId(),
                            CommonResultCode.UNKNOWN_EXCEPTION,
                            command.getSenderId()))
            );
        }
    }

    public void userSignIn(SignInWithPasswordCommand command) {
        try {
            this.commandGateway.send(command, new CommandCallback<SignInWithPasswordCommand, Void>() {
                @Override
                public void onSuccess(CommandMessage<? extends SignInWithPasswordCommand> commandMessage, Void result) {

                }

                @Override
                public void onFailure(CommandMessage<? extends SignInWithPasswordCommand> commandMessage, Throwable cause) {
                    if (cause instanceof UserPasswordInvalidException) {
                        messagingTemplate.convertAndSendToUser(command.getSenderId(),
                                "/topic/authorization-event",
                                new Message<>(new AccountSignInFailedEvent(
                                        command.getAccountId(),
                                        AuthorizationResultCode.AUTHORIZE_FAILED,
                                        command.getSenderId()
                                )));
                    }
                }
            });
        } catch (JSR303ViolationException ex) {
            messagingTemplate.convertAndSendToUser(command.getSenderId(),
                    "/topic/authorization-event",
                    new Message<>(new AccountSignInFailedEvent(
                            command.getAccountId(),
                            CommonResultCode.INVALID_PARAMS,
                            command.getSenderId()
                    ))
            );
        } catch (AggregateNotFoundException ex) {
            messagingTemplate.convertAndSendToUser(command.getSenderId(),
                    "/topic/authorization-event",
                    new Message<>(new AccountSignInFailedEvent(
                            command.getAccountId(),
                            AuthorizationResultCode.AUTHORIZE_FAILED,
                            command.getSenderId()
                    ))
            );
        }
    }

    /**
     * 向用户推送注册成功事件
     *
     * @param event
     */
    @EventHandler
    public void on(AccountSignUpSucceededEvent event) {
        messagingTemplate.convertAndSendToUser(event.getSenderId(),
                "/topic/authorization-event",
                new Message<>(event)
        );
    }

    /**
     * 向用户推送登录成功事件
     *
     * @param event
     */
    @EventHandler
    public void on(UserSessionCreatedEvent event) {
        messagingTemplate.convertAndSendToUser(event.getSenderId(),
                "/topic/authorization-event",
                new Message<>(event)
        );
    }

    /**
     * 向用户推送用户注册失败事件
     *
     * @param event
     */
    @EventHandler
    public void on(AccountSignUpFailedEvent event) {
        messagingTemplate.convertAndSendToUser(event.getSenderId(),
                "/topic/authorization-event",
                new Message<>(event)
        );
    }
}