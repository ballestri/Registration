# SPRING BOOT REGISTRATION APPLICATION
## Prerequisites
- Java 11

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
- In command line: java -jar Registration-0.0.1-SNAPSHOT.jar

## API document 
- "/swagger-ui/index.html"
