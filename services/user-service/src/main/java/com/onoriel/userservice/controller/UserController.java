package com.onoriel.userservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onoriel.userservice.dto.UserDto;
import com.onoriel.userservice.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user-service")
@Api(value="User Management System",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Add an user")
    @PostMapping("/create")
    public Long createUser( 
    		@ApiParam(value = "User object to store", required = true) 
    		@Valid @RequestBody UserDto userDto){
        return this.userService.createUser(userDto);
    }
    
    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return this.userService.getUsers();
    }

    @ApiOperation(value = "Update an user")
    @PutMapping("/update")
    public void updateUser(
    		@ApiParam(value = "User object to update", required = true) 
    		@Valid @RequestBody UserDto userDto){
        this.userService.updateUser(userDto);
    }

}
