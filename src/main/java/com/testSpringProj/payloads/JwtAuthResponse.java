package com.testSpringProj.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {
	
	private String token;
	private UserDto user;

}
