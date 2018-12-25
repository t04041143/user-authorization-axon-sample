package org.multilinguals.enterprise.cmrs.infrastructure.dto.constant;

public enum MessageType {
    EVENT(1), NOTICE(2);

    private int value;

    private MessageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
