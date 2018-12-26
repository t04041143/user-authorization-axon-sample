package org.multilinguals.example.domain.aggregations.authorization.command;

import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.domain.aggregations.AbstractCommand;
import org.multilinguals.example.domain.aggregations.authorization.AccountId;

import javax.validation.constraints.NotNull;

public class SignInWithPasswordCommand extends AbstractCommand {
    @NotNull
    @TargetAggregateIdentifier
    private AccountId accountId;

    @NotNull
    private String password;

    public SignInWithPasswordCommand() {
        super();
    }

    public SignInWithPasswordCommand(AccountId accountId, String password, String senderId) {
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
