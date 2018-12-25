package org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.domain.aggregations.AbstractEvent;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.AccountId;

public class AccountBoundUserEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private AccountId accountId;

    private String userId;

    public AccountBoundUserEvent(AccountId accountId, String userId, String senderId) {
        super(senderId);
        this.accountId = accountId;
        this.userId = userId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getUserId() {
        return userId;
    }
}
