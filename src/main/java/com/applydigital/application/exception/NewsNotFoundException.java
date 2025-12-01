package com.applydigital.application.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsNotFoundException extends NewsServiceException{

    public NewsNotFoundException(String message) {
        super("NEWS_NOT_FOUND", message);
    }
}
