package org.multilinguals.example.command.handler.signup;

import org.multilinguals.example.command.AbstractCommand;

import javax.validation.constraints.NotEmpty;

public class SignUpUsernameAccountCommand extends AbstractCommand {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public SignUpUsernameAccountCommand() {

    }

    public SignUpUsernameAccountCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}