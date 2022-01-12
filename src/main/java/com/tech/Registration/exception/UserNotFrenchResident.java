package com.tech.Registration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotFrenchResident extends RuntimeException {
    public UserNotFrenchResident(String message) {
        super(message);
    }
}
