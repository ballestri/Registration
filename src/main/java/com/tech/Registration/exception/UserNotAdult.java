package com.tech.Registration.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotAdult extends RuntimeException {
    public UserNotAdult(String message) {
        super(message);
    }
}
