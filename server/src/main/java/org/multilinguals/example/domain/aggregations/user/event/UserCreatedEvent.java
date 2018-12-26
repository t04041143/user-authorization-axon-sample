package org.multilinguals.example.domain.aggregations.user.event;


import org.multilinguals.example.domain.aggregations.AbstractEvent;
import org.multilinguals.example.domain.aggregations.authorization.AccountId;

public class UserCreatedEvent extends AbstractEvent {
    private String userId;
    private AccountId accountId;

    public UserCreatedEvent(String userId, AccountId accountId, String senderId) {
        super(senderId);
        this.userId = userId;
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
