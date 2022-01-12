package com.tech.Registration;

import com.tech.Registration.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SpringRestClient {

    private static final String CREATE_USER_ENDPOINT_URL = "http://localhost:8080/api/v1/rest/users";
    private static final String GET_USER_ENDPOINT_URL = "http://localhost:8080/api/v1/rest/users/find?username={username}";
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String... args){
        SpringRestClient springRestClient = new SpringRestClient();

        // Step1: first create a new user
        springRestClient.createUser();

        // Step2: get user by username from step 1
        springRestClient.getUserByUsername();
    }

    private void createUser()  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date yourDate = null;
        try {
            yourDate = sdf.parse("1990-07-05");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User newUser = new User("nelson", yourDate, "France");

        //RestTemplate restTemplate = new RestTemplate();
        User result = restTemplate.postForObject(CREATE_USER_ENDPOINT_URL, newUser, User.class);

        System.out.println(result);
    }


    private void getUserByUsername() {

        Map< String, String > params = new HashMap<>();
        params.put("username", "nelson");

        //RestTemplate restTemplate = new RestTemplate();
        User result = restTemplate.getForObject(GET_USER_ENDPOINT_URL, User.class, params);

        System.out.println(result);
    }


}
