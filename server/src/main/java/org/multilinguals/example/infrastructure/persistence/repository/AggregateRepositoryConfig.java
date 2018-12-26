package org.multilinguals.example.infrastructure.persistence.repository;

import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.multilinguals.example.domain.aggregations.authorization.Account;
import org.multilinguals.example.domain.aggregations.password.UserPassword;
import org.multilinguals.example.domain.aggregations.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AggregateRepositoryConfig {
    @Bean()
    public Repository<Account> userIdentityAggregateRepository(EventStore eventStore) {
        return new EventSourcingRepository<>(Account.class, eventStore);
    }

    @Bean()
    public Repository<User> userAggregateRepository(EventStore eventStore) {
        return new EventSourcingRepository<>(User.class, eventStore);
    }

    @Bean()
    public Repository<UserPassword> userPasswordRepositoryAggregateRepository(EventStore eventStore) {
        return new EventSourcingRepository<>(UserPassword.class, eventStore);
    }
}
