package org.multilinguals.enterprise.cmrs.domain.aggregations.password.event;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.multilinguals.enterprise.cmrs.domain.aggregations.AbstractEvent;

public class UserPasswordBoundUserEvent extends AbstractEvent {
    @TargetAggregateIdentifier
    private String userPasswordId;

    private String userId;

    public UserPasswordBoundUserEvent(String userPasswordId, String userId, String senderId) {
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
