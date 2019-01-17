package org.multilinguals.example.command.aggregate.account.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.command.AbstractEvent;
import org.multilinguals.example.command.aggregate.account.AccountId;
import org.multilinguals.example.command.aggregate.password.UserPasswordId;

public class AccountBoundUserPasswordEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private AccountId accountId;

    private UserPasswordId userPasswordId;

    public AccountBoundUserPasswordEvent(AccountId accountId, UserPasswordId userPasswordId) {
        this.accountId = accountId;
        this.userPasswordId = userPasswordId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}
