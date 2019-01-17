package org.multilinguals.example.infrastructure.config;

import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.multilinguals.example.command.aggregate.account.Account;
import org.multilinguals.example.command.aggregate.password.UserPassword;
import org.multilinguals.example.command.aggregate.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonRepositoryConfig {
    @Bean()
    public Repository<Account> accountAggregateRepository(EventStore eventStore) {
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
