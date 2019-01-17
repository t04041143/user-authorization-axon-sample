package org.multilinguals.example.command.aggregate.user.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.command.AbstractEvent;
import org.multilinguals.example.command.aggregate.account.AccountId;
import org.multilinguals.example.command.aggregate.password.UserPasswordId;
import org.multilinguals.example.command.aggregate.user.UserId;

public class UserCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserId userId;

    private AccountId accountId;

    private UserPasswordId userPasswordId;

    public UserCreatedEvent(UserId userId, AccountId accountId, UserPasswordId userPasswordId) {
        this.userId = userId;
        this.accountId = accountId;
        this.userPasswordId = userPasswordId;
    }

    public UserId getUserId() {
        return userId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}
