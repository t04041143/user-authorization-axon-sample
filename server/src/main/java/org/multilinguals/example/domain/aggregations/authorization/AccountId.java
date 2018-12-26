package org.multilinguals.example.domain.aggregations.authorization;

import org.axonframework.common.Assert;
import org.multilinguals.example.domain.aggregations.AbstractAggregateIdentifier;
import org.multilinguals.example.domain.constant.authorization.UserIdentityType;
import org.springframework.util.DigestUtils;

public class AccountId extends AbstractAggregateIdentifier {
    private String id;
    private UserIdentityType type;

    public AccountId() {
    }

    public AccountId(String id, UserIdentityType type) {
        Assert.notNull(id, () -> "user identity id not be null");
        Assert.notNull(type, () -> "user identity type not be null");

        this.id = id;
        this.type = type;

        initIdentifier();
    }

    public String getId() {
        return id;
    }

    public UserIdentityType getType() {
        return type;
    }

    @Override
    protected String buildIdentifier() {
        return DigestUtils.md5DigestAsHex(this.type.toString().concat(this.id).getBytes());
    }
}
