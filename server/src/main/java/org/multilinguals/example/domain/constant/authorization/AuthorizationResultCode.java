package org.multilinguals.example.domain.constant.authorization;

public interface AuthorizationResultCode {
    // 用户身份凭证已经存在
    static final int DUPLICATED_USER_IDENTITY = 40901;

    // 用户认证失败
    static final int AUTHORIZE_FAILED = 40101;
}
