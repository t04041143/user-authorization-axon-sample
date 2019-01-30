package org.multilinguals.example.infrastructure.exception;


import org.axonframework.messaging.interceptors.JSR303ViolationException;
import org.multilinguals.example.constant.CommonResultCode;
import org.multilinguals.example.infrastructure.dto.ExceptionResponse;
import org.multilinguals.example.infrastructure.exception.http.CMRSHTTPException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CMRSExceptionHandler {
    @ExceptionHandler(CMRSHTTPException.class)
    @ResponseBody
    public ExceptionResponse handleHTTPException(HttpServletResponse response, CMRSHTTPException ex) {
        response.setStatus(ex.getStatusCode());
        return new ExceptionResponse(ex.getMessageCode());
    }

    @ExceptionHandler(JSR303ViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponse handleJSR303ViolationException(HttpServletResponse response, JSR303ViolationException ex) {
        return new ExceptionResponse(CommonResultCode.INVALID_COMMAND_PARAMS);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ExceptionResponse handleAccessDeniedException(HttpServletResponse response, AccessDeniedException ex) {
        return new ExceptionResponse(CommonResultCode.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse handleException(HttpServletRequest request, Exception ex) {
        ex.printStackTrace();
        return new ExceptionResponse(CommonResultCode.UNKNOWN_EXCEPTION);
    }
}
