package com.tech.Registration.controller;

import com.tech.Registration.model.User;
import com.tech.Registration.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/rest",name = "app_users_")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/users",name = "create")
    @ResponseStatus(HttpStatus.CREATED)
    public User add(@RequestBody User user){
        return this.userService.saveUser(user);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @Operation(summary = "Get User by username")
    @RequestMapping(value = "/users/find", method = RequestMethod.GET)
    @ResponseBody
    public Optional<User> find(@RequestParam("username") String username){
        return this.userService.findByUsername(username);
    }
}
