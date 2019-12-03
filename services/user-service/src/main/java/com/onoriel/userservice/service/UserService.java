package com.onoriel.userservice.service;


import java.util.List;

import com.onoriel.userservice.dto.UserDto;

public interface UserService {
    Long createUser(UserDto userDto);
    List<UserDto> getUsers();
    void updateUser(UserDto userDto);
}
