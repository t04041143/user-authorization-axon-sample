package org.multilinguals.example.domain.aggregations.password.command;

import org.multilinguals.example.domain.aggregations.authorization.AccountId;
import org.multilinguals.example.domain.aggregations.AbstractCommand;

public class SignUpUserPasswordCommand extends AbstractCommand {
    private String password;

    private AccountId accountId;

    public SignUpUserPasswordCommand(String password, AccountId accountId, String senderId) {
        super(senderId);
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
