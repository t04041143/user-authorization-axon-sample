package org.multilinguals.example.command.aggregate.password.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.command.AbstractCommand;
import org.multilinguals.example.command.aggregate.password.UserPasswordId;

public class DeleteUserPasswordCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserPasswordId userPasswordId;

    public DeleteUserPasswordCommand(UserPasswordId userPasswordId) {
        this.userPasswordId = userPasswordId;
    }

    public UserPasswordId getUserPasswordId() {
        return userPasswordId;
    }
}
