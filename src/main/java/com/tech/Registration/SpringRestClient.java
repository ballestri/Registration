package com.tech.Registration;

import com.tech.Registration.model.User;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.HashMap;

public class SpringRestClient {

    private static final String CREATE_USER_ENDPOINT_URL = "http://localhost:8080/api/v1/rest/users";
    private static final String GET_USER_ENDPOINT_URL = "http://localhost:8080/api/v1/rest/users/find";

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String... args){

        //SpringApplication.run(RegistrationApplication.class, args);

        SpringRestClient springRestClient = new SpringRestClient();


        // Step1: first create a new user
        springRestClient.createUser();

        // Step2: get user by username from step 1
        springRestClient.getUserByUsername();
    }


    public void createUser()  {

        HashMap<String,String> parts = new HashMap<>();
        parts.put("username", "nelson");
        parts.put("birthDate", "1996-01-01");
        parts.put("country", "France");

        try {
            restTemplate.postForObject( CREATE_USER_ENDPOINT_URL, parts, User.class);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }

    }

    public void getUserByUsername() {

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(GET_USER_ENDPOINT_URL)
                        .queryParam("username", "nelson");

        restTemplate.getForEntity(builder.build().encode().toUri(), User.class);
    }
}
