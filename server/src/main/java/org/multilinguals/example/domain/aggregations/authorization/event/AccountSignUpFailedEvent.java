package org.multilinguals.example.domain.aggregations.authorization.event;

import org.multilinguals.example.domain.aggregations.AbstractEvent;
import org.multilinguals.example.domain.aggregations.authorization.AccountId;

public class AccountSignUpFailedEvent extends AbstractEvent {
    private AccountId accountId;

    private int code;

    public AccountSignUpFailedEvent(AccountId accountId, int code, String sender) {
        super(sender);
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
