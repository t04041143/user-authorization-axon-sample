package org.multilinguals.example.command.handler.signin;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.multilinguals.example.command.aggregate.account.Account;
import org.multilinguals.example.command.aggregate.account.AccountId;
import org.multilinguals.example.command.aggregate.account.command.BindAccountToUserCommand;
import org.multilinguals.example.command.aggregate.account.command.BindUserPasswordToAccountCommand;
import org.multilinguals.example.command.aggregate.password.UserPassword;
import org.multilinguals.example.command.aggregate.password.UserPasswordId;
import org.multilinguals.example.command.aggregate.user.UserId;
import org.multilinguals.example.command.aggregate.user.command.CreateUserCommand;
import org.multilinguals.example.command.aggregate.usersession.UserSessionId;
import org.multilinguals.example.command.aggregate.usersession.command.CreateUserSessionCommand;
import org.multilinguals.example.command.handler.AbstractCommandHandler;
import org.multilinguals.example.infrastructure.data.Tuple2;
import org.multilinguals.example.infrastructure.exception.aggregate.UserPasswordInvalidException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SignInCommandHandler extends AbstractCommandHandler {
    @Resource
    private Repository<Account> userIdentityAggregateRepository;

    @Resource
    private Repository<UserPassword> userPasswordRepositoryAggregateRepository;

    @CommandHandler
    public Tuple2<UserSessionId, UserId> handler(SignInWithPasswordCommand command) throws UserPasswordInvalidException {
        AccountId accountId = new AccountId(command.getIdInAccountType(), command.getAccountType());
        Aggregate<Account> userIdentityAggregate = userIdentityAggregateRepository.load(accountId.getIdentifier());
        UserPasswordId userPasswordId = userIdentityAggregate.invoke(Account::getUserPasswordId);

        if (userPasswordId == null) {
            // 如果用户密码不存在
            throw new UserPasswordInvalidException();
        } else {
            UserId accountUserId = userIdentityAggregate.invoke(Account::getUserId);

            // 如果没有用户和账号关联，那么需要创建用户与账号和密码关联
            if (accountUserId == null) {
                accountUserId = this.commandGateway.sendAndWait(new CreateUserCommand(accountId, userPasswordId));
                this.commandGateway.sendAndWait(new BindAccountToUserCommand(accountId, accountUserId));
            }

            // 检查账号有无关联用户
            Aggregate<UserPassword> userPasswordAggregate = userPasswordRepositoryAggregateRepository.load(userPasswordId.getIdentifier());
            UserId passwordUserId = userPasswordAggregate.invoke(UserPassword::getUserId);

            // 如果账号也没有关联用户，那么需要关联
            if (passwordUserId == null) {
                this.commandGateway.sendAndWait(new BindUserPasswordToAccountCommand(accountId, userPasswordId));
            }

            // 创建一个用户会话
            UserSessionId userSessionId = this.commandGateway.sendAndWait(new CreateUserSessionCommand(accountUserId));

            return new Tuple2<>(userSessionId, accountUserId);
        }
    }
}
