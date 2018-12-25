package org.multilinguals.enterprise.cmrs.dto.user.authorization;

import org.multilinguals.enterprise.cmrs.domain.constant.authorization.UserIdentityType;

public class SignUpDTO {
    private String id;
    private UserIdentityType type;
    private String password;

    public String getId() {
        return id;
    }

    public UserIdentityType getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }
}