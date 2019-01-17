package org.multilinguals.example.infrastructure.config;

import com.mongodb.MongoClient;
import org.axonframework.config.ConfigurationScopeAwareProvider;
import org.axonframework.deadline.SimpleDeadlineManager;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.StorageStrategy;
import org.axonframework.mongo.eventsourcing.eventstore.documentpercommit.DocumentPerCommitStorageStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Bean
    public StorageStrategy storageStrategy() {
        return new DocumentPerCommitStorageStrategy();
    }

    @Bean
    public org.axonframework.mongo.MongoTemplate axonMongoTemplate(MongoClient client) {
        return new org.axonframework.mongo.DefaultMongoTemplate(client, mongoDatabase);
    }

    @Bean
    public MongoEventStorageEngine eventStorageEngine(org.axonframework.mongo.MongoTemplate axonTemplate) {
        return new MongoEventStorageEngine(axonTemplate);
    }

    @Bean
    public MongoSagaStore sagaRepository(org.axonframework.mongo.MongoTemplate axonTemplate) {
        return new MongoSagaStore(axonTemplate);
    }

    @Bean
    SimpleDeadlineManager deadlineManager(org.axonframework.spring.config.AxonConfiguration configuration) {
        return new SimpleDeadlineManager(new ConfigurationScopeAwareProvider(configuration));
    }
}
