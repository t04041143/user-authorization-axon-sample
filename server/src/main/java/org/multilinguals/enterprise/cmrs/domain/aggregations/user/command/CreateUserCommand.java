package org.multilinguals.enterprise.cmrs.domain.aggregations.user.command;

import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.AccountId;
import org.multilinguals.enterprise.cmrs.domain.aggregations.AbstractCommand;

import javax.validation.constraints.NotNull;

public class CreateUserCommand extends AbstractCommand {
    @NotNull
    private AccountId accountId;

    public CreateUserCommand(AccountId accountId, String senderId) {
        super(senderId);
        this.accountId = accountId;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
