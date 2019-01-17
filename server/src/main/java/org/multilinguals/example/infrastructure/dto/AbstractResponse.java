package org.multilinguals.example.infrastructure.dto;


import org.multilinguals.example.constant.CommonResultCode;

public class AbstractResponse {
    private int code;

    AbstractResponse() {
        this.code = CommonResultCode.SUCCESS;
    }

    AbstractResponse(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
