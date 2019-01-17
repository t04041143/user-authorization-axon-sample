package org.multilinguals.example.command.aggregate;

import org.axonframework.common.IdentifierFactory;

import java.io.Serializable;

public abstract class AbstractAggregateIdentifier implements Serializable {
    private String identifier;

    protected AbstractAggregateIdentifier() {
        this.identifier = this.buildIdentifier();
    }

    protected AbstractAggregateIdentifier(String identifier) {
        this.identifier = identifier;
    }

    protected String buildIdentifier() {
        return IdentifierFactory.getInstance().generateIdentifier();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAggregateIdentifier))
            return false;

        return identifier.equals(((AbstractAggregateIdentifier) o).identifier);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public String toString() {
        return this.identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    protected void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
