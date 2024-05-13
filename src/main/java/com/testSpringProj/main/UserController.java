package com.testSpringProj.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testSpringProj.payloads.ApiResponse;
import com.testSpringProj.payloads.UserDto;
import com.testSpringProj.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") int id) {
		UserDto userDto = this.userService.getUserById(id);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.FOUND);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(), HttpStatus.FOUND);
	}
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUser = this.userService.creteUser(userDto);
		return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable(name = "id") int id){
		UserDto updatedUser = this.userService.updateUser(userDto, id);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "id") int id) {
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
	}
	
	
}
