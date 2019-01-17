package org.multilinguals.example.command.aggregate.account.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.command.AbstractEvent;
import org.multilinguals.example.command.aggregate.account.AccountId;
import org.multilinguals.example.command.aggregate.user.UserId;

public class AccountBoundUserEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private AccountId accountId;

    private UserId userId;

    public AccountBoundUserEvent(AccountId accountId, UserId userId) {
        this.accountId = accountId;
        this.userId = userId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public UserId getUserId() {
        return userId;
    }
}
