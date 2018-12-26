package org.multilinguals.example.dto.user.authorization;

import org.multilinguals.example.domain.constant.authorization.UserIdentityType;

public class SignInDTO {
    private String identityId;
    private UserIdentityType identityType;
    private String password;
    private String code;

    public String getIdentityId() {
        return identityId;
    }

    public UserIdentityType getIdentityType() {
        return identityType;
    }

    public String getPassword() {
        return password;
    }

    public String getCode() {
        return code;
    }
}