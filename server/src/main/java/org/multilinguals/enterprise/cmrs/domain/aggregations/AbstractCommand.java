package org.multilinguals.enterprise.cmrs.domain.aggregations;

public abstract class AbstractCommand {
    private String senderId;

    protected AbstractCommand() {
    }

    protected AbstractCommand(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderId() {
        return senderId;
    }
}