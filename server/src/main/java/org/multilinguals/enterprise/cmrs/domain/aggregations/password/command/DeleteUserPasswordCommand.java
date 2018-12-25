package org.multilinguals.enterprise.cmrs.domain.aggregations.password.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.domain.aggregations.AbstractCommand;

public class DeleteUserPasswordCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private String userPasswordId;

    public DeleteUserPasswordCommand(String userPasswordId) {
        this.userPasswordId = userPasswordId;
    }

    public DeleteUserPasswordCommand(String senderId, String userPasswordId) {
        super(senderId);
        this.userPasswordId = userPasswordId;
    }

    public String getUserPasswordId() {
        return userPasswordId;
    }
}
