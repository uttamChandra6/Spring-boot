package com.testSpringProj.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.testSpringProj.bean.Role;
import com.testSpringProj.bean.User;
import com.testSpringProj.config.AppConstants;
import com.testSpringProj.exceptions.ResourceNotFoundException;
import com.testSpringProj.payloads.UserDto;
import com.testSpringProj.repository.RoleRepo;
import com.testSpringProj.repository.UserRepo;
import com.testSpringProj.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto creteUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		List<UserDto> usersDto = new ArrayList<UserDto>();
		for(int i=0;i<users.size();i++) {
			usersDto.add(this.userToDto(users.get(i)));
		}
		
		return usersDto;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
		this.userRepo.delete(user);

	}
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//Roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);
		
		User saveUser = this.userRepo.save(user);
		
		return this.modelMapper.map(saveUser, UserDto.class);
	}

}
