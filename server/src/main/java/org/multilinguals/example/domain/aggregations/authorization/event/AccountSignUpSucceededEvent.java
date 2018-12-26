package org.multilinguals.example.domain.aggregations.authorization.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.domain.aggregations.AbstractEvent;
import org.multilinguals.example.domain.aggregations.authorization.AccountId;

public class AccountSignUpSucceededEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private String userId;

    private AccountId accountId;

    private String userPasswordId;

    public AccountSignUpSucceededEvent(String userId, AccountId accountId, String userPasswordId, String senderId) {
        super(senderId);
        this.userId = userId;
        this.accountId = accountId;
        this.userPasswordId = userPasswordId;
    }

    public String getUserId() {
        return userId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getUserPasswordId() {
        return userPasswordId;
    }
}
