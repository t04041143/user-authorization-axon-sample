package org.multilinguals.example.domain.aggregations.usersession.event;

import org.multilinguals.example.domain.aggregations.AbstractEvent;

import java.util.Date;

public class UserSessionCreatedEvent extends AbstractEvent {
    private String userSessionId;

    private String userId;

    private Date expiredAt;

    public UserSessionCreatedEvent(String userSessionId, String userId, Date expiredAt, String senderId) {
        super(senderId);
        this.userSessionId = userSessionId;
        this.userId = userId;
        this.expiredAt = expiredAt;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public String getUserId() {
        return userId;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }
}
