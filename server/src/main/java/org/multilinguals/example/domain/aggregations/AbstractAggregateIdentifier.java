package org.multilinguals.example.domain.aggregations;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public abstract class AbstractAggregateIdentifier implements Serializable {
    private String identifier;
    private int hashCode;

    public void initIdentifier() {
        this.identifier = this.buildIdentifier();
        this.hashCode = this.identifier.hashCode();
    }

    @NotNull
    protected abstract String buildIdentifier();

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
}
