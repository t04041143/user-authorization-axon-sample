package org.multilinguals.example.command.aggregate.usersession.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.example.command.AbstractEvent;
import org.multilinguals.example.command.aggregate.user.UserId;
import org.multilinguals.example.command.aggregate.usersession.UserSessionId;

import java.util.Date;

public class UserSessionCreatedEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private UserSessionId userSessionId;

    private UserId userId;

    private Date expiredAt;

    public UserSessionCreatedEvent(UserSessionId userSessionId, UserId userId, Date expiredAt) {
        this.userSessionId = userSessionId;
        this.userId = userId;
        this.expiredAt = expiredAt;
    }

    public UserSessionId getUserSessionId() {
        return userSessionId;
    }

    public UserId getUserId() {
        return userId;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }
}
