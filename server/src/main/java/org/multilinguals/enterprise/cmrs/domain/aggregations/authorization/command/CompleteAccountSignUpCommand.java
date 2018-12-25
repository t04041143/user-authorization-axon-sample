package org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.domain.aggregations.AbstractCommand;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.AccountId;

public class CompleteAccountSignUpCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private String userId;

    private AccountId accountId;

    private String userPasswordId;

    public CompleteAccountSignUpCommand(String userId, AccountId accountId, String userPasswordId, String senderId) {
        super(senderId);
        this.userId = userId;
        this.accountId = accountId;
        this.userPasswordId = userPasswordId;
    }

    public String getUserId() {
        return userId;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getUserPasswordId() {
        return userPasswordId;
    }
}
