package org.multilinguals.example.command.aggregate.password;

import org.multilinguals.example.command.aggregate.AbstractAggregateIdentifier;

public class UserPasswordId extends AbstractAggregateIdentifier {
    public UserPasswordId() {
        super();
    }

    public UserPasswordId(String identifier) {
        super(identifier);
    }
}
