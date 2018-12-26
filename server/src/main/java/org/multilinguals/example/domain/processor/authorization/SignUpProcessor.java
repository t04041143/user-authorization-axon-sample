package org.multilinguals.example.domain.processor.authorization;

import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.multilinguals.example.domain.aggregations.authorization.AccountId;
import org.multilinguals.example.domain.aggregations.authorization.command.BindAccountToUserCommand;
import org.multilinguals.example.domain.aggregations.authorization.command.CompleteAccountSignUpCommand;
import org.multilinguals.example.domain.aggregations.authorization.command.DeleteAccountCommand;
import org.multilinguals.example.domain.aggregations.authorization.event.AccountBoundUserEvent;
import org.multilinguals.example.domain.aggregations.authorization.event.AccountSignedUpEvent;
import org.multilinguals.example.domain.aggregations.authorization.event.AccountSignUpFailedEvent;
import org.multilinguals.example.domain.aggregations.password.command.BindUserPasswordToUserCommand;
import org.multilinguals.example.domain.aggregations.password.command.DeleteUserPasswordCommand;
import org.multilinguals.example.domain.aggregations.password.command.SignUpUserPasswordCommand;
import org.multilinguals.example.domain.aggregations.password.event.UserPasswordBoundUserEvent;
import org.multilinguals.example.domain.aggregations.password.event.UserPasswordCreatedEvent;
import org.multilinguals.example.domain.aggregations.user.command.CreateUserCommand;
import org.multilinguals.example.domain.aggregations.user.event.UserCreatedEvent;
import org.multilinguals.example.domain.constant.CommonResultCode;
import org.multilinguals.example.domain.processor.AbstractProcessor;
import org.slf4j.Logger;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.eventhandling.saga.SagaLifecycle.associateWith;
import static org.slf4j.LoggerFactory.getLogger;

@Saga
public class SignUpProcessor extends AbstractProcessor {
    private static final Logger LOGGER = getLogger(SignUpProcessor.class);

    private AccountId accountId;

    private String userPasswordId;

    private boolean userIdentityBoundUser = false;

    private boolean userPasswordBoundUser = false;

    @StartSaga
    @SagaEventHandler(associationProperty = "accountId")
    public void handle(AccountSignedUpEvent event) {
        this.accountId = event.getAccountId();
        if (StringUtils.isNotEmpty(event.getPassword())) {
            this.commandGateway.send(
                    new SignUpUserPasswordCommand(event.getPassword(), event.getAccountId(), event.getSenderId()),
                    new CommandCallback<SignUpUserPasswordCommand, String>() {

                        @Override
                        public void onSuccess(CommandMessage commandMessage, String result) {

                        }

                        @Override
                        public void onFailure(CommandMessage commandMessage, Throwable cause) {
                            cause.printStackTrace();
                            apply(new AccountSignUpFailedEvent(event.getAccountId(), CommonResultCode.UNKNOWN_EXCEPTION, event.getSenderId()));
                        }
                    }
            );
        }
    }

    @SagaEventHandler(associationProperty = "accountId")
    public void handle(UserPasswordCreatedEvent event) {
        this.userPasswordId = event.getUserPasswordId();
        associateWith("userPasswordId", event.getUserPasswordId());
        this.commandGateway.send(new CreateUserCommand(event.getAccountId(), event.getSenderId()));
    }

    @SagaEventHandler(associationProperty = "accountId")
    public void handle(UserCreatedEvent event) {
        this.commandGateway.send(new BindAccountToUserCommand(accountId, event.getUserId(), event.getSenderId()));
        this.commandGateway.send(new BindUserPasswordToUserCommand(userPasswordId, event.getUserId(), event.getSenderId()));
    }

    @SagaEventHandler(associationProperty = "accountId")
    public void handle(AccountBoundUserEvent event) {
        this.userIdentityBoundUser = true;
        if (this.userPasswordBoundUser) {
            commandGateway.send(new CompleteAccountSignUpCommand(event.getUserId(), accountId, userPasswordId, event.getSenderId()));
            SagaLifecycle.end();
        }
    }

    @SagaEventHandler(associationProperty = "userPasswordId")
    public void handle(UserPasswordBoundUserEvent event) {
        this.userPasswordBoundUser = true;
        if (this.userIdentityBoundUser) {
            commandGateway.send(new CompleteAccountSignUpCommand(event.getUserId(), accountId, userPasswordId, event.getSenderId()));
            SagaLifecycle.end();
        }
    }

    @SagaEventHandler(associationProperty = "accountId")
    @EndSaga
    public void handle(AccountSignUpFailedEvent event) {
        this.commandGateway.send(new DeleteAccountCommand(this.accountId));

        if (StringUtils.isNotEmpty(this.userPasswordId)) {
            this.commandGateway.send(new DeleteUserPasswordCommand(this.userPasswordId));
        }

        LOGGER.debug("User Identity {} SignUp Failed", event.getAccountId());
    }
}
