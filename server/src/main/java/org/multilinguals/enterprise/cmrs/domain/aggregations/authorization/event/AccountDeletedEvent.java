package org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.event;

import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.AccountId;

public class AccountDeletedEvent {
    private AccountId accountId;

    public AccountDeletedEvent(AccountId accountId) {
        this.accountId = accountId;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
