package org.multilinguals.enterprise.cmrs.domain.aggregations.user;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.AccountId;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.command.CompleteAccountSignUpCommand;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.event.AccountSignUpSucceededEvent;
import org.multilinguals.enterprise.cmrs.domain.aggregations.user.command.CreateUserCommand;
import org.multilinguals.enterprise.cmrs.domain.aggregations.user.event.UserCreatedEvent;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class User {
    @AggregateIdentifier
    private String id;

    private String realName;

    private List<AccountId> accountIdList = new ArrayList<>();

    private String userPasswordId;

    protected User() {
    }

    @CommandHandler
    public User(CreateUserCommand command) {
        apply(new UserCreatedEvent(IdentifierFactory.getInstance().generateIdentifier(), command.getAccountId(), command.getSenderId()));
    }

    @CommandHandler
    public void handle(CompleteAccountSignUpCommand command) {
        apply(new AccountSignUpSucceededEvent(
                command.getUserId(),
                command.getAccountId(),
                command.getUserPasswordId(),
                command.getSenderId())
        );
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.id = event.getUserId();
        this.accountIdList.add(event.getAccountId());
    }

    @EventSourcingHandler
    public void on(AccountSignUpSucceededEvent event) {
        this.accountIdList.add(event.getAccountId());
        this.userPasswordId = event.getUserPasswordId();
    }

    public String getId() {
        return id;
    }

    public String getRealName() {
        return realName;
    }

    public List<AccountId> getAccountIdList() {
        return accountIdList;
    }

    public String getUserPasswordId() {
        return userPasswordId;
    }
}
