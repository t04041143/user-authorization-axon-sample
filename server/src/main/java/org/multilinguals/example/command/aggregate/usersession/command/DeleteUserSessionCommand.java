package org.multilinguals.example.command.aggregate.usersession.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.command.AbstractCommand;
import org.multilinguals.example.command.aggregate.usersession.UserSessionId;

public class DeleteUserSessionCommand extends AbstractCommand {
    @TargetAggregateIdentifier
    private UserSessionId userSessionId;

    public DeleteUserSessionCommand(UserSessionId userSessionId) {
        this.userSessionId = userSessionId;
    }

    public UserSessionId getUserSessionId() {
        return userSessionId;
    }
}
