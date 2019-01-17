package org.multilinguals.example.infrastructure.persistence;

import org.springframework.data.annotation.Version;

public abstract class AbstractMongoEntity {
    @Version
    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
