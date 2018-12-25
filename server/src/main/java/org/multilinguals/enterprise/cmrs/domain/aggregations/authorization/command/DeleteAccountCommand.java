package org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.AccountId;

public class DeleteAccountCommand {
    @TargetAggregateIdentifier
    private AccountId accountId;

    public DeleteAccountCommand(AccountId accountId) {
        this.accountId = accountId;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
