package com.testSpringProj.main;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testSpringProj.bean.User;
import com.testSpringProj.exceptions.InvalidUserDetailsException;
import com.testSpringProj.exceptions.ResourceNotFoundException;
import com.testSpringProj.payloads.JwtAuthRequest;
import com.testSpringProj.payloads.JwtAuthResponse;
import com.testSpringProj.payloads.UserDto;
import com.testSpringProj.repository.UserRepo;
import com.testSpringProj.security.JwtTokenHelper;
import com.testSpringProj.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		
		System.out.println(request.getUsername() + " " + request.getPassword());
		this.authenticate(request.getUsername(), request.getPassword());

		User user = this.userRepo.findByEmail(request.getUsername())
				.orElseThrow(()->new ResourceNotFoundException("user", "with email : " + request.getUsername() + " status code : ", -1));
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(this.modelMapper.map(user, UserDto.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
		
	}

	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken = new 
				UsernamePasswordAuthenticationToken(username, password);
		try {
		this.authenticationManager.authenticate(authenticationToken);
		}catch (BadCredentialsException e) {
			throw new InvalidUserDetailsException("Password", password, -1);
		}
	}
	
	// register new user api
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		UserDto registerNewUser = this.userService.registerNewUser(userDto);
		
		return new ResponseEntity<UserDto>(registerNewUser, HttpStatus.CREATED);
	}
	
}
