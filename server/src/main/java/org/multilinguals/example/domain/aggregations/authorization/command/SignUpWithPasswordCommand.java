package org.multilinguals.example.domain.aggregations.authorization.command;

import org.apache.commons.lang3.StringUtils;
import org.multilinguals.example.domain.aggregations.AbstractCommand;
import org.multilinguals.example.domain.aggregations.authorization.AccountId;

import javax.validation.constraints.NotNull;

public class SignUpWithPasswordCommand extends AbstractCommand {
    @NotNull
    private AccountId accountId;

    @NotNull
    private String password;

    public SignUpWithPasswordCommand() {
        super();
    }

    public SignUpWithPasswordCommand(AccountId accountId, String password, String senderId) {
        super(senderId);
        this.accountId = accountId;
        this.password = password;
    }

    public AccountId getAccountId() {
        if (StringUtils.isEmpty(accountId.getIdentifier())) {
            accountId.initIdentifier();
        }
        return accountId;
    }

    public String getPassword() {
        return password;
    }
}