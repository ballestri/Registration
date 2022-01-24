package com.tech.Registration.controller;

import com.tech.Registration.model.User;
import com.tech.Registration.repository.UserRepository;
import com.tech.Registration.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.*;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class IntegrationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {

        User user=new User();
        user.setUsername("nelson");
        user.setBirthdate(LocalDate.of(1996, Month.JANUARY,1));
        user.setCountry("France");

        User savedUser = userService.saveUser(user);
        assertThat(savedUser).isNotNull();
    }

    @Test
    public void testFindUser(){

        User record=new User();
        record.setUsername("nelson");
        record.setBirthdate(LocalDate.of(1996, Month.JANUARY,1));
        record.setCountry("France");

        Optional<User> user= userRepository.findByUsername(record.getUsername());
        assertThat(user).isNotNull();
    }

}
