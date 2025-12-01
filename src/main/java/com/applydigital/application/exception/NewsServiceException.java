package com.applydigital.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NewsServiceException extends RuntimeException {

    private final String code;

    public NewsServiceException(String message) {
        super(message);
        this.code = "SERVICE_ERROR";
    }

    public NewsServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public NewsServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
