package org.multilinguals.example.infrastructure.persistence;

import com.mongodb.MongoClient;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.StorageStrategy;
import org.axonframework.mongo.eventsourcing.eventstore.documentpercommit.DocumentPerCommitStorageStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.inject.Inject;

@Configuration
public class StorageConfig {
    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Inject
    public void configure(SimpleCommandBus simpleCommandBus) {
        simpleCommandBus.registerDispatchInterceptor(new BeanValidationInterceptor<>());
    }

    @Bean
    public MongoClient mongo(@Value("${spring.data.mongodb.host}") String mongoHost,
                             @Value("${spring.data.mongodb.port}") int mongoPort) {
        return new MongoClient(mongoHost, mongoPort);
    }

    @Bean
    public MongoTemplate mongoSpringTemplate(MongoClient client) {
        return new MongoTemplate(client, mongoDatabase);
    }

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
}