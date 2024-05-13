package com.testSpringProj.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.testSpringProj.bean.User;
import com.testSpringProj.exceptions.ResourceNotFoundException;
import com.testSpringProj.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// loading user from database by username
		User userDetails = this.userRepo.findByEmail(username)
				.orElseThrow(()->new ResourceNotFoundException("User", "email : " + username, -1));
		
		return userDetails;
	}

}
