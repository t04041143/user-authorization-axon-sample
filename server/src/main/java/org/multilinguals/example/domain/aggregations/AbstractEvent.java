package org.multilinguals.example.domain.aggregations;

public class AbstractEvent {
    private String senderId;

    protected AbstractEvent(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderId() {
        return senderId;
    }
}
