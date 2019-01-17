package org.multilinguals.example.constant.aggregate.account;

public enum AccountType {
    USERNAME, DINGDING;

    public static AccountType ofValue(int value) {
        for (AccountType accountType : AccountType.values()) {
            if (accountType.ordinal() == value) {
                return accountType;
            }
        }

        throw new IllegalArgumentException("No element matches " + value);
    }
}
