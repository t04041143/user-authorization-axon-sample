package org.multilinguals.example.query.user;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.multilinguals.example.command.aggregate.account.event.AccountBoundUserEvent;
import org.multilinguals.example.command.aggregate.password.event.UserPasswordBoundUserEvent;
import org.multilinguals.example.command.aggregate.user.event.UserCreatedEvent;
import org.multilinguals.example.command.aggregate.usersession.event.UserSessionCreatedEvent;
import org.multilinguals.example.command.aggregate.usersession.event.UserSessionDeletedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserDetailsViewEventHandler {

    private UserDetailsViewRepository userDetailsViewRepository;

    @Autowired
    public UserDetailsViewEventHandler(UserDetailsViewRepository userDetailsViewRepository) {
        this.userDetailsViewRepository = userDetailsViewRepository;
    }

    @EventHandler
    public void on(UserCreatedEvent event, @Timestamp java.time.Instant createdTime) {
        UserDetailsView userDetailsView = new UserDetailsView(event.getUserId().getIdentifier(), new Date(createdTime.toEpochMilli()));
        this.userDetailsViewRepository.save(userDetailsView);
    }

    @EventHandler
    public void on(AccountBoundUserEvent event, @Timestamp java.time.Instant createdTime) throws ChangeSetPersister.NotFoundException {
        UserDetailsView userDetailsView = this.userDetailsViewRepository.findById(event.getUserId().getIdentifier())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        UserAccount userAccount = new UserAccount(
                event.getAccountId().getIdentifier(),
                event.getAccountId().getIdInType(),
                event.getAccountId().getType(),
                new Date(createdTime.toEpochMilli())
        );
        userDetailsView.setUserAccount(userAccount);

        this.userDetailsViewRepository.save(userDetailsView);
    }

    @EventHandler
    public void on(UserPasswordBoundUserEvent event) throws ChangeSetPersister.NotFoundException {
        UserDetailsView userDetailsView = this.userDetailsViewRepository.findById(event.getUserId().getIdentifier())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        userDetailsView.setUserPasswordId(event.getUserPasswordId().getIdentifier());

        this.userDetailsViewRepository.save(userDetailsView);
    }

    @EventHandler
    public void on(UserSessionCreatedEvent event) throws ChangeSetPersister.NotFoundException {
        UserDetailsView userDetailsView = this.userDetailsViewRepository.findById(event.getUserId().getIdentifier())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        userDetailsView.setUserSessionId(event.getUserSessionId().getIdentifier());
        userDetailsView.setUserSessionExpiredAt(event.getExpiredAt());
        this.userDetailsViewRepository.save(userDetailsView);
    }

    @EventHandler
    public void on(UserSessionDeletedEvent event) throws ChangeSetPersister.NotFoundException {
        UserDetailsView userDetailsView = this.userDetailsViewRepository.findById(event.getUserId().getIdentifier())
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        if (userDetailsView.getUserSessionId().equals(event.getUserSessionId().getIdentifier())) {
            userDetailsView.setUserSessionId(null);
            userDetailsView.setUserSessionExpiredAt(null);
        }

        this.userDetailsViewRepository.save(userDetailsView);
    }
}
