package com.onoriel.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onoriel.userservice.dto.UserDto;
import com.onoriel.userservice.service.UserService;

@RestController
@RequestMapping("/user-service")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Long createUser(@RequestBody UserDto userDto){
        return this.userService.createUser(userDto);
    }
    
    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return this.userService.getUsers();
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody UserDto userDto){
        this.userService.updateUser(userDto);
    }

}
