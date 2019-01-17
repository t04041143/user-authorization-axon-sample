package org.multilinguals.example.infrastructure.exception.http;

import javax.xml.ws.http.HTTPException;

public class CMRSHTTPException extends HTTPException {
    private int messageCode;

    public CMRSHTTPException(int statusCode, int messageCode) {
        super(statusCode);
        this.messageCode = messageCode;
    }

    public int getMessageCode() {
        return messageCode;
    }
}
