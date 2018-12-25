package org.multilinguals.enterprise.cmrs.domain.aggregations.usersession;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.common.IdentifierFactory;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.domain.aggregations.usersession.command.CreateUserSessionCommand;
import org.multilinguals.enterprise.cmrs.domain.aggregations.usersession.event.UserSessionCreatedEvent;

import java.util.Calendar;
import java.util.Date;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class UserSession {
    @AggregateIdentifier
    private String id;

    private String userId;

    private Date expiredAt;

    @CommandHandler
    UserSession(CreateUserSessionCommand command) {
        this.id = IdentifierFactory.getInstance().generateIdentifier();

        this.userId = command.getUserId();

        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        // 1天的有效期
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        this.expiredAt = calendar.getTime();

        apply(new UserSessionCreatedEvent(this.id, this.userId, this.expiredAt, command.getSenderId()));
    }

    public String getId() {
        return id;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }
}
