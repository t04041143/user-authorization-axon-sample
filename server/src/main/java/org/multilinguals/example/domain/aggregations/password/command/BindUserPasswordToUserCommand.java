package org.multilinguals.example.domain.aggregations.password.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.domain.aggregations.AbstractCommand;

public class BindUserPasswordToUserCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private String userPasswordId;

    private String userId;

    public BindUserPasswordToUserCommand(String userPasswordId, String userId, String senderId) {
        super(senderId);
        this.userPasswordId = userPasswordId;
        this.userId = userId;
    }

    public String getUserPasswordId() {
        return userPasswordId;
    }

    public String getUserId() {
        return userId;
    }
}
