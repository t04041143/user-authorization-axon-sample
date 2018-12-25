package org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.domain.aggregations.AbstractCommand;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.AccountId;

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
