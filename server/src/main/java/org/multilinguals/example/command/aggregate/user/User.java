package org.multilinguals.example.command.aggregate.user;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.example.command.aggregate.account.AccountId;
import org.multilinguals.example.command.aggregate.password.UserPasswordId;
import org.multilinguals.example.command.aggregate.user.command.CreateUserCommand;
import org.multilinguals.example.command.aggregate.user.event.UserCreatedEvent;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class User {
    @AggregateIdentifier
    private UserId id;

    private String realName;

    private List<AccountId> accountIdList = new ArrayList<>();

    private UserPasswordId userPasswordId;

    protected User() {
    }

    @CommandHandler
    public User(CreateUserCommand command) {
        apply(new UserCreatedEvent(new UserId(), command.getAccountId(), command.getUserPasswordId()));
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.id = event.getUserId();
        if (event.getAccountId() != null) {
            this.accountIdList.add(event.getAccountId());
        }

        if (event.getUserPasswordId() != null) {
            this.userPasswordId = event.getUserPasswordId();
        }
        this.accountIdList.add(event.getAccountId());
    }

    public UserId getId() {
        return id;
    }

    public String getRealName() {
        return realName;
    }

    public List<AccountId> getAccountIdList() {
        return accountIdList;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}
