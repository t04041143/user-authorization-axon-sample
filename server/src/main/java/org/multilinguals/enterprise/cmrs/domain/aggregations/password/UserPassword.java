package org.multilinguals.enterprise.cmrs.domain.aggregations.password;

import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.domain.aggregations.password.command.BindUserPasswordToUserCommand;
import org.multilinguals.enterprise.cmrs.domain.aggregations.password.command.SignUpUserPasswordCommand;
import org.multilinguals.enterprise.cmrs.domain.aggregations.password.event.UserPasswordBoundUserEvent;
import org.multilinguals.enterprise.cmrs.domain.aggregations.password.event.UserPasswordCreatedEvent;
import org.springframework.util.DigestUtils;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class UserPassword {
    @AggregateIdentifier
    private String id;

    private String hashValue;

    private String userId;

    protected UserPassword() {
    }

    @CommandHandler
    public UserPassword(SignUpUserPasswordCommand command) {
        apply(new UserPasswordCreatedEvent(
                IdentifierFactory.getInstance().generateIdentifier(),
                hashInputPassword(command.getPassword()),
                command.getAccountId(),
                command.getSenderId())
        );
    }

    @CommandHandler
    public void handle(BindUserPasswordToUserCommand command) {
        apply(new UserPasswordBoundUserEvent(command.getUserPasswordId(), command.getUserId(), command.getSenderId()));
    }

    @EventSourcingHandler
    public void on(UserPasswordCreatedEvent event) {
        this.id = event.getUserPasswordId();
        this.hashValue = event.getHashValue();
    }

    /**
     * 响应用户创建事件，与用户ID建立关系
     *
     * @param event 用户创建事件
     */
    @EventSourcingHandler
    public void on(UserPasswordBoundUserEvent event) {
        this.userId = event.getUserId();
    }

    public Boolean passwordIsValid(String password) {
        return StringUtils.isNotEmpty(hashValue)
                && StringUtils.isNotEmpty(password)
                && hashValue.equals(hashInputPassword(password));
    }

    private String hashInputPassword(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }

    public String getId() {
        return id;
    }

    public String getHashValue() {
        return hashValue;
    }

    public String getUserId() {
        return userId;
    }
}
