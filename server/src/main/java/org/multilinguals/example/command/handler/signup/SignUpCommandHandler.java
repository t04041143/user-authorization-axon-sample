package org.multilinguals.example.command.handler.signup;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.commandhandling.model.Repository;
import org.multilinguals.example.command.aggregate.account.Account;
import org.multilinguals.example.command.aggregate.account.AccountId;
import org.multilinguals.example.command.aggregate.account.command.BindUserPasswordToAccountCommand;
import org.multilinguals.example.command.aggregate.account.command.SignUpAccountCommand;
import org.multilinguals.example.command.aggregate.password.UserPasswordId;
import org.multilinguals.example.command.aggregate.password.command.DeleteUserPasswordCommand;
import org.multilinguals.example.command.aggregate.password.command.SignUpUserPasswordCommand;
import org.multilinguals.example.command.handler.AbstractCommandHandler;
import org.multilinguals.example.constant.aggregate.account.AccountType;
import org.multilinguals.example.infrastructure.exception.aggregate.AccountSignedUpException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SignUpCommandHandler extends AbstractCommandHandler {
    @Resource
    private Repository<Account> accountAggregateRepository;

    /**
     * @param command 注册用户名账号命令
     * @throws AccountSignedUpException 账号注册失败异常
     */
    @CommandHandler
    public void handler(SignUpUsernameAccountCommand command) throws Exception {
        AccountId accountId = new AccountId(command.getUsername(), AccountType.USERNAME);
        try {
            Aggregate<Account> accountAggregate = accountAggregateRepository.load(accountId.getIdentifier());

            if (accountAggregate.invoke(Account::getUserPasswordId) == null) {
                // 如果账号存在但是没有关联密码，那么需要注册一个新的密码
                UserPasswordId userPasswordId = this.commandGateway.sendAndWait(new SignUpUserPasswordCommand(command.getPassword(), accountId));

                bindUserPasswordToAccount(accountId, userPasswordId);
            } else {
                // 如果账号已经关联密码，说明账号已经被注册，抛出异常中断。
                throw new AccountSignedUpException();
            }
        } catch (AggregateNotFoundException ex) {
            // 要注册的账号不存在，需要将账号和密码都注册
            this.commandGateway.sendAndWait(new SignUpAccountCommand(accountId));
            UserPasswordId userPasswordId = this.commandGateway.sendAndWait(new SignUpUserPasswordCommand(command.getPassword(), accountId));

            bindUserPasswordToAccount(accountId, userPasswordId);
        }
    }

    private void bindUserPasswordToAccount(AccountId accountId, UserPasswordId userPasswordId) throws Exception {
        try {
            // 绑定账号和用户
            this.commandGateway.sendAndWait(new BindUserPasswordToAccountCommand(accountId, userPasswordId));
        } catch (Exception bindAccountEx) {
            // 如果绑定异常要删除密码，避免脏数据
            this.commandGateway.send(new DeleteUserPasswordCommand(userPasswordId));

            throw new Exception("User Account Bind User Password Exception!");
        }
    }
}
