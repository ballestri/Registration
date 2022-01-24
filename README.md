# SPRING BOOT REGISTRATION APPLICATION
## Prerequisites
- Java 11
- Spring Boot 2.6.2

## Install dependencies
mvn install

## Features
- User registration
- An endpoint to Registered User and search specifical User
- Persistence for storing users in H2 database
- Testing using MockMvc
- Document the API with OpenAPI

## Endpoints
- "/api/v1/rest/users" register user endpoint
- "/api/v1/rest/users/find" finding specifical user given username in  parameter endpoint

## Tests
- src/test holds the unit and integration tests

## Run the application
- Run command: java -jar Registration-0.0.1-SNAPSHOT.jar
- The web application is accessible via localhost:8080

## API document 
- "/swagger-ui/index.html"

## Liens
- https://start.spring.io/
- https://www.baeldung.com/rest-template
- https://www.baeldung.com/configuration-properties-in-spring-boot
- https://www.baeldung.com/spring-boot-testing
