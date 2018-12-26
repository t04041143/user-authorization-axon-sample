package org.multilinguals.example.domain.aggregations.usersession.event;

public class UserSessionDeletedEvent {
    private String userSessionId;

    private String userId;

    public String getUserSessionId() {
        return userSessionId;
    }

    public String getUserId() {
        return userId;
    }
}
