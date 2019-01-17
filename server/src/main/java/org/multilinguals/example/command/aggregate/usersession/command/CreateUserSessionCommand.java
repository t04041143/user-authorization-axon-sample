package org.multilinguals.example.command.aggregate.usersession.command;

import org.multilinguals.example.command.AbstractCommand;
import org.multilinguals.example.command.aggregate.user.UserId;

public class CreateUserSessionCommand extends AbstractCommand {
    private UserId userId;

    public CreateUserSessionCommand(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }
}
