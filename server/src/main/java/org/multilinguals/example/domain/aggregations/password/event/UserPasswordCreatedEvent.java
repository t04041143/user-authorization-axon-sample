package org.multilinguals.example.domain.aggregations.password.event;

import org.multilinguals.example.domain.aggregations.AbstractEvent;
import org.multilinguals.example.domain.aggregations.authorization.AccountId;

public class UserPasswordCreatedEvent extends AbstractEvent {
    private String userPasswordId;

    private String hashValue;

    private AccountId accountId;

    public UserPasswordCreatedEvent(String userPasswordId, String hashValue, AccountId accountId, String senderId) {
        super(senderId);
        this.userPasswordId = userPasswordId;
        this.hashValue = hashValue;
        this.accountId = accountId;
    }

    public String getUserPasswordId() {
        return userPasswordId;
    }

    public String getHashValue() {
        return hashValue;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
