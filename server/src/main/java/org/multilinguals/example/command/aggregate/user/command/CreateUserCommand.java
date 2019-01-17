package org.multilinguals.example.command.aggregate.user.command;

import org.multilinguals.example.command.AbstractCommand;
import org.multilinguals.example.command.aggregate.account.AccountId;
import org.multilinguals.example.command.aggregate.password.UserPasswordId;

import javax.validation.constraints.NotNull;

public class CreateUserCommand extends AbstractCommand {
    @NotNull
    private AccountId accountId;

    private UserPasswordId userPasswordId;

    public CreateUserCommand(@NotNull AccountId accountId, UserPasswordId userPasswordId) {
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
