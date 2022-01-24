package com.tech.Registration.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.tech.Registration.repository.UserRepository;
import com.tech.Registration.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;





@ExtendWith(MockitoExtension.class)
public class ServiceTests {

    @InjectMocks
    UserService service=new UserServiceImpl();

    @Mock
    UserRepository dao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser()
    {
        //User record = new User("nelson", LocalDate.of(1996, Month.JANUARY,1), "France");
        User record=new User();
        record.setUsername("nelson");
        record.setBirthdate(LocalDate.of(1996, Month.JANUARY,1));
        record.setCountry("France");

        service.saveUser(record);
        verify(dao, times(1)).save(record);
    }



    /*
    @Test
    void testFindUser()
    {
        User record = new User("nelson", LocalDate.of(1996, Month.JANUARY,1), "France");
        when(dao.findByUsername(record.getUsername())).thenReturn(java.util.Optional.of(record));
        verify(dao, times(1)).findByUsername(record.getUsername());
    }
    */



}
