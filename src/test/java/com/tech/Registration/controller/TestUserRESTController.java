package com.tech.Registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.Registration.model.User;
import com.tech.Registration.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.time.Month;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestUserRESTController {

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserRepository userRecordRepository;

    private static final String CREATE_USER_ENDPOINT_URL = "/api/v1/rest/users";
    private static final String GET_USER_ENDPOINT_URL = "/api/v1/rest/users/find";

    @Test
    public void createRecord_success() throws Exception {
        User record=new User();
        record.setUsername("nelson");
        record.setBirthdate(LocalDate.of(1996, Month.JANUARY,1));
        record.setCountry("France");

        Mockito.when(userRecordRepository.save(record)).thenReturn(record);

        final String content = "{\"username\":\"nelson\",\"birthDate\":\"1996-01-01\",\"country\":\"France\"}";

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post(CREATE_USER_ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mvc.perform(mockRequest)
        .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", is("nelson")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate", is("1996-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country", is("France")));
    }

    @Test
    public void findUser() throws Exception {

        User record=new User();
        record.setUsername("nelson");
        record.setBirthdate(LocalDate.of(1996, Month.JANUARY,1));
        record.setCountry("France");

        Mockito.when(userRecordRepository.findByUsername(record.getUsername())).thenReturn(java.util.Optional.of(record));

        mvc.perform(MockMvcRequestBuilders
                        .get(GET_USER_ENDPOINT_URL)
                        .param("username",record.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", is("nelson")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate", is("1996-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country", is("France")));
    }
}
