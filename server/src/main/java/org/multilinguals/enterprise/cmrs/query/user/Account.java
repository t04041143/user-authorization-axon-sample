package org.multilinguals.enterprise.cmrs.query.user;

import org.multilinguals.enterprise.cmrs.domain.constant.authorization.UserIdentityType;

import java.util.Date;

public class Account {
    private String id;

    private UserIdentityType type;

    private Date createdAt;

    private Date updatedAt;

    public Account() {
    }

    public Account(String id, UserIdentityType type, Date createdAt) {
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
    }

    public Account(String id, UserIdentityType type, String userId, Date createdAt, Date updatedAt) {
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserIdentityType getType() {
        return type;
    }

    public void setType(UserIdentityType type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
