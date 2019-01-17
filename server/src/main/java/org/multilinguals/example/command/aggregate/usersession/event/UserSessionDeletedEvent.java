package org.multilinguals.example.command.aggregate.usersession.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.command.AbstractEvent;
import org.multilinguals.example.command.aggregate.user.UserId;
import org.multilinguals.example.command.aggregate.usersession.UserSessionId;

public class UserSessionDeletedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserSessionId userSessionId;

    private UserId userId;

    public UserSessionDeletedEvent(UserSessionId userSessionId, UserId userId) {
        this.userSessionId = userSessionId;
        this.userId = userId;
    }

    public UserSessionId getUserSessionId() {
        return userSessionId;
    }

    public UserId getUserId() {
        return userId;
    }
}
