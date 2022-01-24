package com.tech.Registration.service;

import com.tech.Registration.model.User;
import java.util.Optional;

/**
 * This UserService will handle all the user interactions
 * @author nelson
 */

public interface UserService {
    /**
     * This method can be used to register a user
     * @param user object hold user registration information
     */
    User saveUser(User user);
    /**
     * Method to find a user information with a given username
     * @param username unique identifier of a user
     * @return User Object
     */
    Optional<User> findByUsername(String username);
}
