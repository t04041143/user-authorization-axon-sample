package org.multilinguals.example.query.user;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.example.domain.aggregations.authorization.event.AccountBoundUserEvent;
import org.multilinguals.example.domain.aggregations.password.event.UserPasswordBoundUserEvent;
import org.multilinguals.example.domain.aggregations.user.event.UserCreatedEvent;
import org.multilinguals.example.domain.aggregations.usersession.event.UserSessionCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
public class UserEventHandler {

    private final UserDetailsViewRepository userDetailsViewRepository;

    @Autowired
    public UserEventHandler(UserDetailsViewRepository userDetailsViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
    }

    @EventHandler
    public void on(UserCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        UserDetailsView userDetailsView = new UserDetailsView(event.getUserId(), new Date(createdTime.toEpochMilli()));
        this.userDetailsViewRepository.save(userDetailsView).subscribe();
    }

    @EventHandler
    public void on(AccountBoundUserEvent event, @Timestamp java.time.Instant createdTime) {
        this.userDetailsViewRepository.findById(event.getUserId()).flatMap(user -> {
            Account account = new Account(
                    event.getAccountId().getId(),
                    event.getAccountId().getType(),
                    new Date(createdTime.toEpochMilli())
            );

            user.setAccount(account);

            return Mono.just(user);
        }).subscribe(user ->
                this.userDetailsViewRepository.save(user).subscribe()
        );
    }

    @EventHandler
    public void on(UserPasswordBoundUserEvent event) {
        this.userDetailsViewRepository.findById(event.getUserId()).flatMap(user -> {
            user.setUserPasswordId(event.getUserPasswordId());
            return Mono.just(user);
        }).subscribe(user ->
                this.userDetailsViewRepository.save(user).subscribe()
        );
    }

    @EventHandler
    public void on(UserSessionCreatedEvent event) {
        this.userDetailsViewRepository.findById(event.getUserId()).flatMap(user -> {
            user.setUserSessionId(event.getUserSessionId());
            user.setUserSessionExpiredAt(event.getExpiredAt());
            return Mono.just(user);
        }).subscribe(user ->
                this.userDetailsViewRepository.save(user).subscribe()
        );
    }
}
