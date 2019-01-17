package org.multilinguals.example.command.aggregate.account;

import org.apache.commons.lang3.StringUtils;
import org.multilinguals.example.command.aggregate.AbstractAggregateIdentifier;
import org.multilinguals.example.constant.aggregate.account.AccountType;
import org.springframework.util.DigestUtils;

public class AccountId extends AbstractAggregateIdentifier {
    private String idInType;
    private AccountType type;

    public AccountId(String idInType, AccountType type) {
        this.idInType = idInType;
        this.type = type;
        super.setIdentifier(buildIdentifier());
    }

    @Override
    protected String buildIdentifier() {
        if (StringUtils.isEmpty(idInType) || this.type == null) {
            return null;
        } else {
            return DigestUtils.md5DigestAsHex(this.type.toString().concat(this.idInType).getBytes());
        }
    }

    public String getIdInType() {
        return idInType;
    }

    public AccountType getType() {
        return type;
    }
}
