package org.multilinguals.example.dto.user.authorization;

public class AggregateCreatedDTO<IDType> {
    private IDType aggregateId;
    private String aggregateName;

    public AggregateCreatedDTO(IDType aggregateId, String aggregateName) {
        this.aggregateId = aggregateId;
        this.aggregateName = aggregateName;
    }

    public IDType getAggregateId() {
        return aggregateId;
    }

    public String getAggregateName() {
        return aggregateName;
    }
}