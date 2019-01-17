package org.multilinguals.example.command.aggregate.password;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.multilinguals.example.command.aggregate.account.AccountId;
import org.multilinguals.example.command.aggregate.password.command.BindUserPasswordToUserCommand;
import org.multilinguals.example.command.aggregate.password.command.SignUpUserPasswordCommand;
import org.multilinguals.example.command.aggregate.password.event.UserPasswordBoundUserEvent;
import org.multilinguals.example.command.aggregate.password.event.UserPasswordSignedUpEvent;
import org.multilinguals.example.command.aggregate.user.UserId;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class UserPassword {
    @AggregateIdentifier
    private UserPasswordId id;

    private String hashValue;

    private UserId userId;

    // 需要使用密码的账号
    private List<AccountId> accountIdList = new ArrayList<>();

    protected UserPassword() {
    }

    @CommandHandler
    public UserPassword(SignUpUserPasswordCommand command) {
        apply(new UserPasswordSignedUpEvent(new UserPasswordId(), hashInputPassword(command.getPassword()),
                command.getAccountId()));
    }

    @CommandHandler
    public UserPassword(BindUserPasswordToUserCommand command) {
        apply(new UserPasswordBoundUserEvent(new UserPasswordId(), command.getUserId()));
    }

    @EventSourcingHandler
    public void on(UserPasswordSignedUpEvent event) {
        this.id = event.getUserPasswordId();
        this.hashValue = event.getHashValue();
        this.accountIdList.add(event.getAccountId());
    }

    @EventSourcingHandler
    public void on(UserPasswordBoundUserEvent event) {
        this.userId = event.getUserId();
    }

    private String hashInputPassword(String input) {
        return DigestUtils.md5DigestAsHex(input.getBytes());
    }

    public UserPasswordId getId() {
        return id;
    }

    public String getHashValue() {
        return hashValue;
    }

    public UserId getUserId() {
        return userId;
    }

    public List<AccountId> getAccountIdList() {
        return accountIdList;
    }
}
