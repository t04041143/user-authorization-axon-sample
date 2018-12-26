package org.multilinguals.example.domain.aggregations.usersession.command;

import org.multilinguals.example.domain.aggregations.AbstractCommand;

public class CreateUserSessionCommand extends AbstractCommand {
    private String userId;

    public CreateUserSessionCommand(String userId, String senderId) {
        super(senderId);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
