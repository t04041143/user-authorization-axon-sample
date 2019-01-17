package org.multilinguals.example.command.aggregate.account.event;

import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.multilinguals.example.command.AbstractEvent;
import org.multilinguals.example.command.aggregate.account.AccountId;

public class AccountSignedUpEvent extends AbstractEvent {
    @AggregateIdentifier
    private AccountId accountId;

    public AccountSignedUpEvent(AccountId accountId) {
        this.accountId = accountId;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
