package org.multilinguals.example.command.aggregate.account.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.command.AbstractCommand;
import org.multilinguals.example.command.aggregate.account.AccountId;
import org.multilinguals.example.command.aggregate.password.UserPasswordId;

public class BindUserPasswordToAccountCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private AccountId accountId;

    private UserPasswordId userPasswordId;

    public BindUserPasswordToAccountCommand(AccountId accountId, UserPasswordId userPasswordId) {
        this.accountId = accountId;
        this.userPasswordId = userPasswordId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}
