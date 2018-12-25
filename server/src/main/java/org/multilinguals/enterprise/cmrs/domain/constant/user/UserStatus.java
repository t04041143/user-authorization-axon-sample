package org.multilinguals.enterprise.cmrs.domain.constant.user;

public enum UserStatus {
    ACTIVE(1), NOT_COMPLETE(2);
    private int value;

    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
