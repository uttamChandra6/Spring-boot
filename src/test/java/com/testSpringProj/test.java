package com.testSpringProj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class test {
	
	@Autowired
	private static PasswordEncoder passwordEncoder;
	
	public static void main(String Args[]) {
		System.out.println(passwordEncoder.encode("abc"));
	}

}
