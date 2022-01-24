package com.tech.Registration.controller;

import com.tech.Registration.model.User;
import com.tech.Registration.model.dto.RegisterUser;
import com.tech.Registration.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import com.tech.Registration.aspect.Timed;

@RestController
@RequestMapping(path = "/api/v1/rest", name = "app_users_")
@Tag(name = "users", description = "The Registration API")
public class UserController {

    @Autowired
    private UserService userService;

    // CREATE NEW USER
    @Timed
    @ApiOperation(value = "Add a new user", tags = {"users"})
    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class))}),})
    @PostMapping(path = "/users")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUser registerUser) {
        User createdUser = userService.saveUser(registerUser.toUser());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // FIND USER BY USERNAME
    @Timed
    @ApiOperation(value = "Find user by username", tags = {"users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @Operation(summary = "Get User by username")
    @RequestMapping(value = "/users/find", method = RequestMethod.GET)
    @ResponseBody
    public Optional<User> find(@RequestParam String username) {
        return this.userService.findByUsername(username);
    }
}

