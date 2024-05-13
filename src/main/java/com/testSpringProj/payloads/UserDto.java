package com.testSpringProj.payloads;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testSpringProj.bean.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min = 4, message = "Username must be greater than 4 characters")
	private String name;
	@NotEmpty(message = "Email is required")
	@Email(message = "Email address is not valid")
	private String email;
	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 3, max = 10, message = "Password must be in between 3 and 10 characters")
//	@Pattern(regexp = )
	private String password;
	@NotEmpty
	private String about;
	private Set<RoleDto> roles = new HashSet<>();
	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

}

/*
 * Annotations for bean validation - Hibernate Validator
 * @NotNull
 * @Size
 * @Min
 * @Max
 * @Email
 * @NotEmpty
*/