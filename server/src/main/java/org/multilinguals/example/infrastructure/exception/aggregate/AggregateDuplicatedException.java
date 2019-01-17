package org.multilinguals.example.infrastructure.exception.aggregate;

import org.multilinguals.example.infrastructure.exception.AbstractException;

public class AggregateDuplicatedException extends AbstractException {
    public AggregateDuplicatedException(int messageCode) {
        super(messageCode);
    }
}
