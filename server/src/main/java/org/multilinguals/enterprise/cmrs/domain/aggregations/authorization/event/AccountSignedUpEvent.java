package org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.domain.aggregations.AbstractEvent;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.AccountId;

import java.util.Date;

public class AccountSignedUpEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private AccountId accountId;

    private String password;

    private Date createdAt;

    public AccountSignedUpEvent(AccountId accountId, String password, Date createdAt, String senderId) {
        super(senderId);
        this.accountId = accountId;
        this.password = password;
        this.createdAt = createdAt;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}