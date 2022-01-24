package com.tech.Registration;

import com.tech.Registration.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.HashMap;

public class SystemTests {

    private static final String CREATE_USER_ENDPOINT_URL = "http://localhost:8080/api/v1/rest/users";
    private static final String GET_USER_ENDPOINT_URL = "http://localhost:8080/api/v1/rest/users/find";
    private static final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void createUser()  {

        HashMap<String,String> parts = new HashMap<>();
        parts.put("username", "nelson");
        parts.put("birthDate", "1996-01-01");
        parts.put("country", "France");

        try {
            restTemplate.postForObject( CREATE_USER_ENDPOINT_URL, parts, User.class);
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex){
            System.out.println("Something went wrong.");
        }
    }

    @Test
    public void findUser(){

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(GET_USER_ENDPOINT_URL)
                        .queryParam("username", "nelson");
        try {
            restTemplate.getForEntity(builder.build().encode().toUri(), User.class);
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            System.out.println("Something went wrong.");
        }

    }

}
