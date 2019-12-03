package com.onoriel.userservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onoriel.userservice.dto.UserDto;
import com.onoriel.userservice.entity.Users;
import com.onoriel.userservice.repository.UsersRepository;
import com.onoriel.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @Override
    public Long createUser(UserDto userDto) {
        Users user = new Users();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        return this.usersRepository.save(user).getId();
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto) {
        this.usersRepository.findById(userDto.getId())
                .ifPresent(user -> {
                    user.setFirstname(userDto.getFirstname());
                    user.setLastname(userDto.getLastname());
                    user.setEmail(userDto.getEmail());
                    this.raiseEvent(userDto);
                });
    }

    private void raiseEvent(UserDto dto){
        try{
            String value = OBJECT_MAPPER.writeValueAsString(dto);
            this.kafkaTemplate.sendDefault(dto.getId(), value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

	@Override
	public List<UserDto> getUsers() {
		return this.usersRepository.findAll().stream()
				.map(user -> { 
						UserDto userDto = new UserDto();
						userDto.setId(user.getId());
						userDto.setEmail(user.getEmail());
						userDto.setFirstname(user.getFirstname());
						userDto.setLastname(user.getLastname());
						return userDto;
					}
				).collect(Collectors.toList());
	}
}
