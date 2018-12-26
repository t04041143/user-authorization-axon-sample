package org.multilinguals.example.query.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserDetailsView {
    @Id
    private String id;

    private String realName;

    private List<Account> accountList = Collections.emptyList();

    private String userSessionId;

    private Date userSessionExpiredAt;

    private String userPasswordId;

    private Date createdAt;

    private Date updatedAt;

    @Version
    private Long version;

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

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setAccount(Account account) {
        this.accountList.add(account);
    }
}
