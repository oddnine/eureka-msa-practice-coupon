package com.practice.gateway.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InvalidAuthorizationHeaderFormatException extends RuntimeException {
    public InvalidAuthorizationHeaderFormatException() {
        super("Invalid authorization header format");
    }
}