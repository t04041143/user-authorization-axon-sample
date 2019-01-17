package org.multilinguals.example.command.aggregate.account.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.command.AbstractCommand;
import org.multilinguals.example.command.aggregate.account.AccountId;
import org.multilinguals.example.command.aggregate.user.UserId;

public class BindAccountToUserCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private AccountId accountId;

    private UserId userId;

    public BindAccountToUserCommand(AccountId accountId, UserId userId) {
        this.accountId = accountId;
        this.userId = userId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public UserId getUserId() {
        return userId;
    }
}
