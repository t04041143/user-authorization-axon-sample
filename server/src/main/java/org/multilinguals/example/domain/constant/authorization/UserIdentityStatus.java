package org.multilinguals.example.domain.constant.authorization;

public enum UserIdentityStatus {
    ACTIVE(1), INACTIVE(2);

    private int value;

    private UserIdentityStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
