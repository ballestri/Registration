package com.tech.Registration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotAllowedPhoneNumber extends RuntimeException {
    public UserNotAllowedPhoneNumber(String message) {
        super(message);
    }
}

