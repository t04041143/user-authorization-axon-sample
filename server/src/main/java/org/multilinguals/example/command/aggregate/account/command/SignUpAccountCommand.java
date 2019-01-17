package org.multilinguals.example.command.aggregate.account.command;

import org.multilinguals.example.command.AbstractCommand;
import org.multilinguals.example.command.aggregate.account.AccountId;

public class SignUpAccountCommand extends AbstractCommand {
    private AccountId accountId;

    public SignUpAccountCommand(AccountId accountId) {
        this.accountId = accountId;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}