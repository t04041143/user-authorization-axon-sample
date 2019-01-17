package org.multilinguals.example.query.user;

import org.multilinguals.example.constant.aggregate.account.AccountType;

import java.util.Date;

public class UserAccount {
    private String id;

    private String idInType;

    private AccountType type;

    private Date createdAt;

    private Date updatedAt;

    public UserAccount(String id, String idInType, AccountType type, Date createdAt) {
        this.id = id;
        this.idInType = idInType;
        this.type = type;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdInType() {
        return idInType;
    }

    public void setIdInType(String idInType) {
        this.idInType = idInType;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
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
