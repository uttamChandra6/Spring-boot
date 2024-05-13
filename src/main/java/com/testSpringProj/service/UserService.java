package com.testSpringProj.service;

import java.util.List;

import com.testSpringProj.payloads.UserDto;

public interface UserService {
	
	UserDto registerNewUser(UserDto user);
	UserDto creteUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);

}
