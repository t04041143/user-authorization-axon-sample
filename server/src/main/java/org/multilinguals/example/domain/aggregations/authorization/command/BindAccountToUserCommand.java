package org.multilinguals.example.domain.aggregations.authorization.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.domain.aggregations.AbstractCommand;
import org.multilinguals.example.domain.aggregations.authorization.AccountId;

public class BindAccountToUserCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private AccountId accountId;

    private String userId;

    public BindAccountToUserCommand(AccountId accountId, String userId, String senderId) {
        super(senderId);
        this.accountId = accountId;
        this.userId = userId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getUserId() {
        return userId;
    }
}
