package org.multilinguals.enterprise.cmrs.domain.aggregations.authorization;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.command.BindAccountToUserCommand;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.command.DeleteAccountCommand;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.command.SignUpWithPasswordCommand;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.event.AccountBoundUserEvent;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.event.AccountDeletedEvent;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.event.AccountSignedUpEvent;

import java.util.Date;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;

@Aggregate
public class Account {
    @AggregateIdentifier
    private AccountId id;

    private String userId;

    /**
     * 对于可溯源的事件，需要提供一个无参构造函数，
     * axon框架会在使用过往事件回溯之前，使用这个构造函数进行对象实例化
     */
    protected Account() {
    }

    @CommandHandler
    public Account(SignUpWithPasswordCommand command) {
        apply(new AccountSignedUpEvent(command.getAccountId(), command.getPassword(), new Date(), command.getSenderId()));
    }

    @CommandHandler
    public void handle(DeleteAccountCommand command) {
        markDeleted();
        apply(new AccountDeletedEvent(id));
    }

    @CommandHandler
    public void handle(BindAccountToUserCommand command) {
        apply(new AccountBoundUserEvent(command.getAccountId(), command.getUserId(), command.getSenderId()));
    }

    /**
     * @param event
     */
    @EventSourcingHandler
    public void on(AccountSignedUpEvent event) {
        this.id = event.getAccountId();
    }

    /**
     * 在用户与用户身份凭证绑定完毕后，建立快照
     *
     * @param event 用户创建事件
     */
    @EventSourcingHandler
    public void on(AccountBoundUserEvent event) {
        this.userId = event.getUserId();
    }

    public AccountId getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }
}
