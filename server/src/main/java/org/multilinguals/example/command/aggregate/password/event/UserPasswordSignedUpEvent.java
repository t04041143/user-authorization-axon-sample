package org.multilinguals.example.command.aggregate.password.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.command.AbstractEvent;
import org.multilinguals.example.command.aggregate.account.AccountId;
import org.multilinguals.example.command.aggregate.password.UserPasswordId;

public class UserPasswordSignedUpEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserPasswordId userPasswordId;

    private String hashValue;

    private AccountId accountId;

    public UserPasswordSignedUpEvent(UserPasswordId userPasswordId, String hashValue, AccountId accountId) {
        this.userPasswordId = userPasswordId;
        this.accountId = accountId;
        this.hashValue = hashValue;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getHashValue() {
        return hashValue;
    }
}
