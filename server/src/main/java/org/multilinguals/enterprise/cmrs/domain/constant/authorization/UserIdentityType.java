package org.multilinguals.enterprise.cmrs.domain.constant.authorization;

public enum UserIdentityType {
    ACCOUNT, DINGDING;

    public static UserIdentityType ofValue(int value) {
        for (UserIdentityType userIdentityType : UserIdentityType.values()) {
            if (userIdentityType.ordinal() == value) {
                return userIdentityType;
            }
        }

        throw new IllegalArgumentException("No element matches " + value);
    }
}
