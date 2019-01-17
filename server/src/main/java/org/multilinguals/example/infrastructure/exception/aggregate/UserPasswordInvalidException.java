package org.multilinguals.example.infrastructure.exception.aggregate;

import org.multilinguals.example.constant.result.code.AuthResultCode;
import org.multilinguals.example.infrastructure.exception.AbstractException;

public class UserPasswordInvalidException extends AbstractException {
    public UserPasswordInvalidException() {
        super(AuthResultCode.AUTHORIZE_FAILED);
    }
}
