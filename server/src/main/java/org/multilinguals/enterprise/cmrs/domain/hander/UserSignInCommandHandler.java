package org.multilinguals.enterprise.cmrs.domain.hander;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.Account;
import org.multilinguals.enterprise.cmrs.domain.aggregations.authorization.command.SignInWithPasswordCommand;
import org.multilinguals.enterprise.cmrs.domain.aggregations.password.UserPassword;
import org.multilinguals.enterprise.cmrs.domain.aggregations.user.User;
import org.multilinguals.enterprise.cmrs.domain.aggregations.usersession.command.CreateUserSessionCommand;
import org.multilinguals.enterprise.cmrs.infrastructure.exception.UserPasswordInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSignInCommandHandler extends AbstractCommandHandler {
    private final Repository<Account> userIdentityAggregateRepository;
    private final Repository<User> userAggregateRepository;
    private final Repository<UserPassword> userPasswordRepositoryAggregateRepository;

    @Autowired
    public UserSignInCommandHandler(Repository<Account> userIdentityAggregateRepository, Repository<User> userAggregateRepository, Repository<UserPassword> userPasswordRepositoryAggregateRepository) {
        this.userIdentityAggregateRepository = userIdentityAggregateRepository;
        this.userAggregateRepository = userAggregateRepository;
        this.userPasswordRepositoryAggregateRepository = userPasswordRepositoryAggregateRepository;
    }


    @CommandHandler
    public void handle(SignInWithPasswordCommand command) throws UserPasswordInvalidException {
        Aggregate<Account> userIdentityAggregate = userIdentityAggregateRepository.load(command.getAccountId().getIdentifier());

        String userId = userIdentityAggregate.invoke(Account::getUserId);

        Aggregate<User> userAggregate = userAggregateRepository.load(userId);

        String userPasswordId = userAggregate.invoke(User::getUserPasswordId);

        Aggregate<UserPassword> userPasswordAggregate = userPasswordRepositoryAggregateRepository.load(userPasswordId);

        String passwordHashValue = userPasswordAggregate.invoke(UserPassword::getHashValue);

        // 判断用户密码是否正确
        if (userPasswordAggregate.invoke(userPassword -> userPassword.passwordIsValid(command.getPassword()))) {
            // 创建用户会话
            commandGateway.send(new CreateUserSessionCommand(userId, command.getSenderId()));
        } else {
            throw new UserPasswordInvalidException();
        }
    }
}
