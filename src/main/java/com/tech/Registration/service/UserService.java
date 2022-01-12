package com.tech.Registration.service;

import com.tech.Registration.model.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByUsername(String username);
}
