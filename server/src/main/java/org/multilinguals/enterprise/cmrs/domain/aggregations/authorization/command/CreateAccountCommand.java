package org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.command;

import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.AccountId;
import org.multilinguals.enterprise.cmrs.domain.aggregations.AbstractCommand;

public class CreateAccountCommand extends AbstractCommand {
    private AccountId accountId;

    public CreateAccountCommand(AccountId accountId, String userId) {
        super(userId);
        this.accountId = accountId;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}