package org.multilinguals.example.query.user;

import org.multilinguals.example.infrastructure.persistence.AbstractMongoEntity;
import org.springframework.data.annotation.Id;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserDetailsView extends AbstractMongoEntity {
    @Id
    private String id;

    private String realName;

    private List<UserAccount> accountViewList = Collections.emptyList();

    private String userSessionId;

    private Date userSessionExpiredAt;

    private String userPasswordId;

    private Date createdAt;

    private Date updatedAt;


    public UserDetailsView(String id, Date createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<UserAccount> getAccountViewList() {
        return accountViewList;
    }

    public void setAccountViewList(List<UserAccount> accountViewList) {
        this.accountViewList = accountViewList;
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    public Date getUserSessionExpiredAt() {
        return userSessionExpiredAt;
    }

    public void setUserSessionExpiredAt(Date userSessionExpiredAt) {
        this.userSessionExpiredAt = userSessionExpiredAt;
    }

    public String getUserPasswordId() {
        return userPasswordId;
    }

    public void setUserPasswordId(String userPasswordId) {
        this.userPasswordId = userPasswordId;
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

    public void setUserAccount(UserAccount userAccount) {
        this.accountViewList.add(userAccount);
    }
}
