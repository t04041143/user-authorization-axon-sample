package org.multilinguals.example.command.handler.signin;

import org.multilinguals.example.command.AbstractCommand;
import org.multilinguals.example.constant.aggregate.account.AccountType;

import javax.validation.constraints.NotNull;

public class SignInWithPasswordCommand extends AbstractCommand {
    @NotNull
    private String idInAccountType;

    @NotNull
    private AccountType accountType;

    @NotNull
    private String password;

    public SignInWithPasswordCommand() {
        super();
    }

    public SignInWithPasswordCommand(@NotNull String idInAccountType, @NotNull AccountType accountType, @NotNull String password) {
        this.idInAccountType = idInAccountType;
        this.accountType = accountType;
        this.password = password;
    }

    public String getIdInAccountType() {
        return idInAccountType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getPassword() {
        return password;
    }
}
