package org.multilinguals.example.infrastructure.exception.aggregate;

import org.multilinguals.example.constant.result.code.AuthResultCode;
import org.multilinguals.example.infrastructure.exception.AbstractException;

public class AccountSignedUpException extends AbstractException {
    public AccountSignedUpException() {
        super(AuthResultCode.SIGNED_UP_ACCOUNT);
    }
}
