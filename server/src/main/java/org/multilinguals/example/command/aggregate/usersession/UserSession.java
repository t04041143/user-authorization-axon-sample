package org.multilinguals.example.command.aggregate.usersession;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.example.command.aggregate.user.UserId;
import org.multilinguals.example.command.aggregate.usersession.command.CreateUserSessionCommand;
import org.multilinguals.example.command.aggregate.usersession.command.DeleteUserSessionCommand;
import org.multilinguals.example.command.aggregate.usersession.event.UserSessionCreatedEvent;
import org.multilinguals.example.command.aggregate.usersession.event.UserSessionDeletedEvent;

import java.util.Calendar;
import java.util.Date;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;

@Aggregate
public class UserSession {
    @AggregateIdentifier
    private UserSessionId id;

    private UserId userId;

    private Date expiredAt;

    protected UserSession() {
    }

    @CommandHandler
    public UserSession(CreateUserSessionCommand command) {
        UserSessionId userSessionId = new UserSessionId();

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        // 1天的有效期
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date expiredAt = calendar.getTime();

        apply(new UserSessionCreatedEvent(userSessionId, command.getUserId(), expiredAt));
    }

    @CommandHandler
    public void handler(DeleteUserSessionCommand command) {
        markDeleted();
        apply(new UserSessionDeletedEvent(command.getUserSessionId(), this.userId));
    }

    @EventSourcingHandler
    public void on(UserSessionCreatedEvent event) {
        this.id = event.getUserSessionId();
        this.userId = event.getUserId();
        this.expiredAt = event.getExpiredAt();
    }

    public UserSessionId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }
}
