package org.multilinguals.example.domain.aggregations.authorization.event;

import org.multilinguals.example.domain.aggregations.AbstractEvent;
import org.multilinguals.example.domain.aggregations.authorization.AccountId;

public class AccountSignInFailedEvent extends AbstractEvent {
    private AccountId accountId;

    private int code;

    public AccountSignInFailedEvent(AccountId accountId, int code, String senderId) {
        super(senderId);
        this.accountId = accountId;
        this.code = code;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public int getCode() {
        return code;
    }
}