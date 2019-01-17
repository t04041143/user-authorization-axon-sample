package org.multilinguals.example.command.aggregate.password.command;

import org.multilinguals.example.command.AbstractCommand;
import org.multilinguals.example.command.aggregate.account.AccountId;

public class SignUpUserPasswordCommand extends AbstractCommand {
    private String password;

    private AccountId accountId;

    public SignUpUserPasswordCommand(String password, AccountId accountId) {
        this.password = password;
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
